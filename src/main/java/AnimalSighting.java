import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public abstract class AnimalSighting {

  public String name;
  public String description;
  public String location;
  public int rangerId;
  public int id;
  public Timestamp timeOfLastSighting;
  public String type;

  public static final String LOCATION_ZONE1 = "zone-1";
  public static final String LOCATION_ZONE2 = "zone-2";
  public static final String LOCATION_ZONE3 = "zone-3";
  public static final String LOCATION_ZONE4 = "zone-4";

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getLocation() {
    return location;
  }

  public int getRangerId() {
    return rangerId;
  }

  public Timestamp getTimeOfLastSighting() {
    return timeOfLastSighting;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherAnimalSighting){
    if (!(otherAnimalSighting instanceof AnimalSighting)) {
      return false;
    } else {
      AnimalSighting newAnimal = (AnimalSighting) otherAnimalSighting;
      return this.getName().equals(newAnimal.getName()) &&
             this.getRangerId() == newAnimal.getRangerId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animal_sightings (name, description, location, " +
                   "rangerId, timeOfLastSighting, type) VALUES (:name, " +
                   ":description, :location, :rangerId, :timeOfLastSighting, :type)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("description", this.description)
        .addParameter("location", this.location)
        .addParameter("rangerId", this.rangerId)
        .addParameter("timeOfLastSighting", this.timeOfLastSighting)
        .addParameter("type", this.type)
        .executeUpdate()
        .getKey();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM animal_sightings WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }



}
