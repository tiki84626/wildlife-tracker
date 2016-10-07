import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class NotEndangeredAnimalSighting extends AnimalSighting implements DatabaseManagement {

  public static final String DATABASE_TYPE = "notEndangered";

  public NotEndangeredAnimalSighting(String name, int rangerId) {
    this.name = name;
    this.rangerId = rangerId;
    type = DATABASE_TYPE;
  }

  public void update() {
    try(Connection con = DB.sql2o.open()){
    String sql = "UPDATE animal_sightings SET (name, description, location, " +
                 "rangerId, timeOfLastSighting, type) = (:name, :description, " +
                 ":location, :rangerId, :timeOfLastSighting, :type) WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("name", this.name)
      .addParameter("description", this.description)
      .addParameter("location", this.location)
      .addParameter("rangerId", this.rangerId)
      .addParameter("timeOfLastSighting", this.timeOfLastSighting)
      .addParameter("type", this.type)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public static List<NotEndangeredAnimalSighting> all() {
    String sql = "SELECT * FROM animal_sightings WHERE type='notEndangered';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(NotEndangeredAnimalSighting.class);
    }
  }

}
