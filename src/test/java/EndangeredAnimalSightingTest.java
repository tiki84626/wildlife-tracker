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

}
