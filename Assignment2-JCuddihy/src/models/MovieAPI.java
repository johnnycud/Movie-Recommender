package models;

import java.util.List;
import java.util.Map;

import utils.CSVLoader;
import utils.Serializer;
import utils.ToJsonString;

/**
 * MovieAPI class 
 * @author John Cuddihy
 * @version 2
 * @date 11/12/2016
 *
 */

public class MovieAPI {
	
	
	 public void load() throws Exception
	 {
		 Serializer.read();
		 Map<Long, Movie> movieIndex = (Map<Long, Movie>) serializer.pop();
		 Map<String, User> surnameIndex = (Map<String, User>) serializer.pop();
		 userIndex = (Map<Long, User>) serializer.pop();
		 User.counter = (Long) serializer.pop();
	 }

	 public void store() throws Exception
	 {
		 serializer.push(User.counter);
		 serializer.push(userIndex);
		 serializer.push(emailIndex);
		 serializer.push(preferencesIndex);
		 serializer.write();
	 }
	 
	 public void prime() throws Exception
	 {
	CSVLoader loader = new CSVLoader();
	List <User> users = loader.loadUsers("moviedata_small/users5.dat");
	for (User user : users)
	{
	 userIndex.put(user.id,user);
	}
	
	List <Movie> movies = loader.loadUsers("moviedata_small/users5.dat");
	for (User user : users)
	{
	 movieIndex.put(movie.name,movie);
	}


	List <Rating> ratings = loader.loadUsers("moviedata_small/users5.dat");
	for (User user : users)
	{
	 ratingIndex.put(rating.score,rating);
	}
	 }
	 
	 public String toString()
	  {
	    return new ToJsonString(getClass(), this).toString();
	  }

}
