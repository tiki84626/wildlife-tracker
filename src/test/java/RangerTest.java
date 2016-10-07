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

  @Test
  public void save_insertsObjectIntoDatabase_Ranger() {
    Ranger testRanger = new Ranger("John Smith", "johnsmith@test.com", "Sergeant", "../../../../img/sergeant.png");
    testRanger.save();
    assertEquals(true, Ranger.all().get(0).equals(testRanger));
  }

  @Test
  public void all_returnsAllInstancesOfRanger_true() {
    Ranger testRanger1 = new Ranger("John Smith", "johnsmith@test.com", "Sergeant", "../../../../img/sergeant.png");
    testRanger1.save();
    Ranger testRanger2 = new Ranger("Bob Lie", "boblie@test.com", "Capitan", "../../../../img/capitan.png");
    testRanger2.save();
    assertEquals(true, Ranger.all().get(0).equals(testRanger1));
    assertEquals(true, Ranger.all().get(1).equals(testRanger2));
  }

  @Test
  public void getMonsters_retrievesAllMonstersFromDatabase_monstersList() {
    Ranger testRanger = new Ranger("John Smith", "johnsmith@test.com", "Sergeant", "../../../../img/sergeant.png");
    testRanger.save();
    EndangeredAnimalSighting firstAnimalSighting = new EndangeredAnimalSighting("Red Eared Fox", testRanger.getId(), "healthy", "young");
    firstAnimalSighting.save();
    NotEndangeredAnimalSighting secondAnimalSighting = new NotEndangeredAnimalSighting("Fox", testRanger.getId());
    secondAnimalSighting.save();
    Object[] animalSightings = new Object[] { firstAnimalSighting, secondAnimalSighting };
    assertTrue(testRanger.getAnimalSightings().containsAll(Arrays.asList(animalSightings)));
  }

  @Test
  public void update_updatesRanger_true() {
    Ranger testRanger = new Ranger("John Smith", "johnsmith@test.com", "Sergeant", "../../../../img/sergeant.png");
    testRanger.save();
    testRanger.setName("Greg Hail");
    testRanger.setEmail("greghail@test.com");
    testRanger.setRank("Colonel");
    testRanger.setImgURL("../../../../img/colonel.png");
    testRanger.update();
    assertEquals("Greg Hail", testRanger.getName());
    assertEquals("greghail@test.com", testRanger.getEmail());
    assertEquals("Colonel", testRanger.getRank());
    assertEquals("../../../../img/colonel.png", testRanger.getImgURL());
  }

  @Test
  public void find_FindsRangerForGivenId_true() {
    Ranger testRanger1 = new Ranger("John Smith", "johnsmith@test.com", "Sergeant", "../../../../img/sergeant.png");
    testRanger1.save();
    Ranger testRanger2 = new Ranger("Bob Lie", "boblie@test.com", "Capitan", "../../../../img/capitan.png");
    testRanger2.save();
    assertEquals(testRanger2, Ranger.find(testRanger2.getId()));
  }



}
