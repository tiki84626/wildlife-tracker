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
  public void endangeredAnimalSighting_getsNamefromEndangeredAnimalSightingObject_String() {
    EndangeredAnimalSighting testEndangeredAnimalSighting = new EndangeredAnimalSighting("Red Eared Fox", 1, "ill", "young");
    assertEquals("Red Eared Fox", testEndangeredAnimalSighting.getName());
  }

  @Test
  public void endangeredAnimalSighting_getsHealthfromEndangeredAnimalSightingObject_String() {
    EndangeredAnimalSighting testEndangeredAnimalSighting = new EndangeredAnimalSighting("Red Eared Fox", 1, "ill", "young");
    assertEquals("ill", testEndangeredAnimalSighting.getHealth());
  }

  @Test
  public void endangeredAnimalSighting_getsAgefromEndangeredAnimalSightingObject_String() {
    EndangeredAnimalSighting testEndangeredAnimalSighting = new EndangeredAnimalSighting("Red Eared Fox", 1, "ill", "young");
    assertEquals("young", testEndangeredAnimalSighting.getAge());
  }

  @Test
  public void save_insertsObjectIntoDatabase_EndangeredAnimalSighting() {
    EndangeredAnimalSighting testEndangeredAnimalSighting = new EndangeredAnimalSighting("Red Eared Fox", 1, "ill", "young");
    testEndangeredAnimalSighting.save();
    assertTrue(EndangeredAnimalSighting.all().get(0).equals(testEndangeredAnimalSighting));
  }

}
