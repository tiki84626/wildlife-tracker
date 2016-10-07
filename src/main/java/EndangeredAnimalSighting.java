import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class EndangeredAnimalSighting extends AnimalSighting implements DatabaseManagement {
  private String health;
  private String age;

  public static final String HEALTH_HEALTHY = "healthy";
  public static final String HEALTH_ILL = "ill";
  public static final String HEALTH_OKAY = "okay";
  public static final String AGE_NEWBORN = "newborn";
  public static final String AGE_YOUNG = "young";
  public static final String AGE_ADULT = "adult";
  public static final String DATABASE_TYPE = "endangered";

  public EndangeredAnimalSighting(String name, int rangerId, String health, String age) {
    this.name = name;
    this.rangerId = rangerId;
    this.health = health;
    this.age = age;
    type = DATABASE_TYPE;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  public void setHealth(String health) {
    this.health = health;
  }

  public void setAge(String age) {
    this.age = age;
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animal_sightings (name, description, location, rangerId, timeOfLastSighting, type, health, age) VALUES (:name, :description, :location, :rangerId, :timeOfLastSighting, :type, :health, :age)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("description", this.description)
        .addParameter("location", this.location)
        .addParameter("rangerId", this.rangerId)
        .addParameter("timeOfLastSighting", this.timeOfLastSighting)
        .addParameter("type", this.type)
        .addParameter("health", this.health)
        .addParameter("age", this.age)
        .executeUpdate()
        .getKey();
    }
  }

  public void update() {
    try(Connection con = DB.sql2o.open()){
    String sql = "UPDATE animal_sightings SET (name, description, location, rangerId, timeOfLastSighting, type, health, age) = (:name, :description, :location, :rangerId, :timeOfLastSighting, :type, :health, :age) WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("name", this.name)
      .addParameter("description", this.description)
      .addParameter("location", this.location)
      .addParameter("rangerId", this.rangerId)
      .addParameter("timeOfLastSighting", this.timeOfLastSighting)
      .addParameter("type", this.type)
      .addParameter("health", this.health)
      .addParameter("age", this.age)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public static List<EndangeredAnimalSighting> all() {
    String sql = "SELECT * FROM animal_sightings WHERE type='endangered';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(EndangeredAnimalSighting.class);
    }
  }

  public static EndangeredAnimalSighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animal_sightings WHERE id = :id;";
      EndangeredAnimalSighting endangeredAnimalSighting = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(EndangeredAnimalSighting.class);
      return endangeredAnimalSighting;
    }
  }

}
