import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Ranger implements DatabaseManagement {
  private String name;
  private String email;
  private String rank;
  private String imgURL;
  private int id;

  public Ranger(String name, String email, String rank, String imgURL) {
    this.name = name;
    this.email = email;
    this.rank = rank;
    this.imgURL = imgURL;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getRank() {
    return rank;
  }

  public String getImgURL() {
    return imgURL;
  }

  public int getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public void setImgURL(String imgURL) {
    this.imgURL = imgURL;
  }

  public void update() {
    try(Connection con = DB.sql2o.open()){
    String sql = "UPDATE rangers SET (name, email, rank, imgURL) = (:name, :email, :rank, :imgURL) WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("name", this.name)
      .addParameter("email", this.email)
      .addParameter("rank", this.rank)
      .addParameter("imgURL", this.imgURL)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public static Ranger find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM rangers WHERE id = :id;";
      Ranger ranger = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Ranger.class);
      return ranger;
    }
  }

  @Override
  public boolean equals(Object otherRanger){
    if (!(otherRanger instanceof Ranger)) {
      return false;
    } else {
      Ranger newRanger = (Ranger) otherRanger;
      return this.getName().equals(newRanger.getName()) &&
             this.getEmail().equals(newRanger.getEmail());
    }
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO rangers (name, email, rank, imgURL) " +
                   "VALUES (:name, :email, :rank, :imgURL)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("email", this.email)
        .addParameter("rank", this.rank)
        .addParameter("imgURL", this.imgURL)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Ranger> all() {
    String sql = "SELECT * FROM rangers";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Ranger.class);
    }
  }

  public List<Object> getAnimalSightings() {
    List<Object> allAnimalSightings = new ArrayList<Object>();

    try(Connection con = DB.sql2o.open()) {
      String sqlEndangered = "SELECT * FROM animal_sightings WHERE rangerId=:id AND type='endangered';";
      List<EndangeredAnimalSighting> endangeredAnimalSightings = con.createQuery(sqlEndangered)
        .addParameter("id", this.id)
        .throwOnMappingFailure(false)
        .executeAndFetch(EndangeredAnimalSighting.class);
      allAnimalSightings.addAll(endangeredAnimalSightings);

      String sqlNotEndangered = "SELECT * FROM animal_sightings WHERE rangerId=:id AND type='notEndangered';";
      List<NotEndangeredAnimalSighting> notEndangeredAnimalSighting = con.createQuery(sqlNotEndangered)
        .addParameter("id", this.id)
        .throwOnMappingFailure(false)
        .executeAndFetch(NotEndangeredAnimalSighting.class);
      allAnimalSightings.addAll(notEndangeredAnimalSighting);
      }

      return allAnimalSightings;
  }

  @Override
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM rangers WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }
}
