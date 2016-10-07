import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class NotEndangeredAnimalSightingTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void notEndangeredAnimalSighting_instantiatesCorrectly_true() {
    NotEndangeredAnimalSighting testNotEndangeredAnimalSighting = new NotEndangeredAnimalSighting("Red Eared Fox", 1);
    assertEquals(true, testNotEndangeredAnimalSighting instanceof NotEndangeredAnimalSighting);
  }

}
