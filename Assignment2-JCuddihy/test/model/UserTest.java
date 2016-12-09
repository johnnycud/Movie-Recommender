package model;

import static model.ModelTest.users;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import models.User;



public class UserTest
{
  User james = new User ("James", "Kirk", "male",  "47", "Captain");

  @Test
  public void testCreate()
  {
    assertEquals ("James",           james.firstName);
    assertEquals ("Kirk",            james.surname);
    assertEquals ("male",            james.gender);   
    assertEquals ("47",              james.age); 
    assertEquals ("Captain",         james.occupation);
  }

  @Test
  public void testIds()
  {
    Set<Long> ids = new HashSet<>();
    
	for (User user : users)
    {
      ids.add(user.id);
    }
    assertEquals (users.length, ids.size());
  }

  @Test
  public void testToString()
  {
    assertEquals ("models.User/n{/n  year: 2016,/n  counter: 4,/n  id:"  + users[3].id + ",/n  title: Ben Hur,/n  url: www.paramount.com/n}", users[3].toString());
  }
  
  @Test
  public void testEquals()
  {
    User tiberius = new User ("James", "Kirk", "male",  "47", "Captain"); 
    User jean   = new User ("Jean-luc", "Picard", "male",  "50", "Captain"); 

    assertEquals(james, james);
    assertEquals(james, tiberius);
    assertNotEquals(james, jean);
  } 
  
}
