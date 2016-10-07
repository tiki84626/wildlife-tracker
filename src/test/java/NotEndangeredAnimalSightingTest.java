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

  @Test
  public void find_FindsNotNotEndangeredAnimalSightingInstanceRelatedToId_true() {
    NotEndangeredAnimalSighting testNotEndangeredAnimalSighting1 = new NotEndangeredAnimalSighting("Red Fox", 1);
    testNotEndangeredAnimalSighting1.save();
    NotEndangeredAnimalSighting testNotEndangeredAnimalSighting2 = new NotEndangeredAnimalSighting("Red Fox", 1);
    testNotEndangeredAnimalSighting2.save();
    assertEquals(testNotEndangeredAnimalSighting2, NotEndangeredAnimalSighting.find(testNotEndangeredAnimalSighting2.getId()));
  }

}
