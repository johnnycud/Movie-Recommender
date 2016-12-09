package model;

import static model.ModelTest.movies;
import static org.junit.Assert.*;

import org.junit.Test;

public class MovieTest
{ 
  @Test
  public void testCreate()
  {
    assertEquals ( "UP", movies[1].title);
    assertEquals ("2009", movies[2].year);
  }

  @Test
  public void testIds()
  {
    assertNotEquals(movies[0].id, movies[1].id);
  }

  @Test
  public void testToString()
  {
    assertEquals ("models.Movie/n{/n  year: 2016,/n  counter: 4,/n  id:"  + movies[3].id + ",/n  title: Ben Hur,/n  url: www.paramount.com/n}", movies[3].toString());
  }
}
