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

}
