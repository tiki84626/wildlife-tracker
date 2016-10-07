import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class NotEndangeredAnimalSighting extends AnimalSighting implements DatabaseManagement {

  public static final String DATABASE_TYPE = "notEndangered";

  public NotEndangeredAnimalSighting(String name, int rangerId) {
    this.name = name;
    this.rangerId = rangerId;
    type = DATABASE_TYPE;
  }

  public static List<NotEndangeredAnimalSighting> all() {
    String sql = "SELECT * FROM animal_sightings WHERE type='notEndangered';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(NotEndangeredAnimalSighting.class);
    }
  }

  public static NotEndangeredAnimalSighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animal_sightings WHERE id = :id;";
      NotEndangeredAnimalSighting notEndangeredAnimalSighting = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(NotEndangeredAnimalSighting.class);
      return notEndangeredAnimalSighting;
    }
  }

}
