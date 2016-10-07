import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class EndangeredAnimalSightingTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void endangeredAnimalSighting_instantiatesCorrectly_true() {
    EndangeredAnimalSighting testEndangeredAnimalSighting = new EndangeredAnimalSighting("Red Eared Fox", 1, "ill", "young");
    assertEquals(true, testEndangeredAnimalSighting instanceof EndangeredAnimalSighting);
  }

  @Test
  public void EndangeredAnimalSighting_instantiatesWithName_String() {
    EndangeredAnimalSighting testEndangeredAnimalSighting = new EndangeredAnimalSighting("Red Eared Fox", 1, "ill", "young");
    assertEquals("Red Eared Fox", testEndangeredAnimalSighting.getName());
  }

}
