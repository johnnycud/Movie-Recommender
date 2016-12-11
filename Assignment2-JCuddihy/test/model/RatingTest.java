package model;

import static org.junit.Assert.*;
import static model.ModelTest.ratings;
import org.junit.Test;

/**
 * Rating class 
 * @author John Cuddihy
 * @version 2
 * @date 11/12/2016
 *
 */

public class RatingTest {

	@Test
	  public void testCreate()
	  {
	    assertEquals ("Ben Hur", ratings[0].movieId);
	    assertEquals ("1", ratings[0].userId);
	  }

	  @Test
	  public void testIds()
	  {
	    assertNotEquals(ratings[0].id, ratings[1].id);
	  }

	  @Test
	  public void testToString()
	  {
	    assertEquals ("Rating{" + ratings[0].id + ", 23.3, 33.3}", ratings[0].toString());
	  }
	}
