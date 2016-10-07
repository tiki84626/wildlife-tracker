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
