import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class RangerTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void ranger_instantiatesCorrectly_true() {
    Ranger testRanger = new Ranger("John Smith", "johnsmith@test.com", "Sergeant", "../../../../img/sergeant.png");
    assertEquals(true, testRanger instanceof Ranger);
  }

  @Test
  public void getName_rangerInstantiatesWithName_JohnSmith() {
    Ranger testRanger = new Ranger("John Smith", "johnsmith@test.com", "Sergeant", "../../../../img/sergeant.png");
    assertEquals("John Smith", testRanger.getName());
  }

  @Test
  public void getEmail_rangerInstantiatesWithEmail_johnsmithemail() {
    Ranger testRanger = new Ranger("John Smith", "johnsmith@test.com", "Sergeant", "../../../../img/sergeant.png");
    assertEquals("johnsmith@test.com", testRanger.getEmail());
  }

  @Test
  public void equals_returnsTrueIfNameAndEmailAreSame_true() {
    Ranger testRanger1 = new Ranger("John Smith", "johnsmith@test.com", "Sergeant", "../../../../img/sergeant.png");
    Ranger testRanger2 = new Ranger("John Smith", "johnsmith@test.com", "Sergeant", "../../../../img/sergeant.png");
    assertTrue(testRanger1.equals(testRanger2));
  }

  // @Test
  // public void save_insertsObjectIntoDatabase_Person() {
  //   Person testPerson = new Person("Henry", "henry@henry.com");
  //   testPerson.save();
  //   assertEquals(true, Person.all().get(0).equals(testPerson));
  // }
  //
  // @Test
  // public void all_returnsAllInstancesOfPerson_true() {
  //   Person firstPerson = new Person("Henry", "henry@henry.com");
  //   firstPerson.save();
  //   Person secondPerson = new Person("Harriet", "harriet@harriet.com");
  //   secondPerson.save();
  //   assertEquals(true, Person.all().get(0).equals(firstPerson));
  //   assertEquals(true, Person.all().get(1).equals(secondPerson));
  // }

}
