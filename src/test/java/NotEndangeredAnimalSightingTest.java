import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class NotEndangeredAnimalSightingTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void NotEndangeredAnimalSighting_instantiatesCorrectly_true() {
    NotEndangeredAnimalSighting testNotEndangeredAnimalSighting = new NotEndangeredAnimalSighting("Red Fox", 1);
    assertEquals(true, testNotEndangeredAnimalSighting instanceof NotEndangeredAnimalSighting);
  }

  @Test
  public void NotEndangeredAnimalSighting_getsNamefromNotEndangeredAnimalSightingObject_String() {
    NotEndangeredAnimalSighting testNotEndangeredAnimalSighting = new NotEndangeredAnimalSighting("Red Fox", 1);
    assertEquals("Red Fox", testNotEndangeredAnimalSighting.getName());
  }

}
