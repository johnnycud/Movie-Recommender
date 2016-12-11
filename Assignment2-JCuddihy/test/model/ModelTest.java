package model;

import models.Movie;
import models.Rating;
import models.User;

/**
 * Model Test class 
 * @author John Cuddihy
 * @version 2
 * @date 11/12/2016
 *
 */

public class ModelTest {

	
	  public static User[] users =
	  {
	    new User ("patty", "gilroy", "female",  "38","Bus driver"),
	    new User ("ned", "power", "male", "22","Student"),
	    new User ("Shelia", "ahearn", "female",  "19","Student"),
	    new User ("mike", "gilman", "male",  "38","Janitor")
	  };

	  public static Rating[] ratings =
	  {
	    new Rating (),
	    new Rating ("like", "Up",    8.0),
	    new Rating ("ET",   "dislike",   2.5),
	    new Rating ("Planes",  "dislike",   4.5),
	    new Rating ("Cars", "like", 7.5)
	  };

	  public static Movie[]movies =
	  {
	    new Movie("ET","1985" ,"www.spielberg.com"),
	    new Movie("UP","2013" ,"www.sony.com"),  
	    new Movie("Cars","2009" ,"www.disney.com"),
	    new Movie("Ben Hur","2016" ,"www.paramount.com")       
	  };
	}


