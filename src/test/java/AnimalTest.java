import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class AnimalTest{
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
