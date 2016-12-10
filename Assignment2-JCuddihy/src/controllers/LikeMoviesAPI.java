package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import models.Movie;
import models.Rating;
import models.User;
import utils.Serializer;

public class LikeMoviesAPI
{
	 private Serializer serializer;
	 
	 public Map<Long, User> userIndex = new HashMap<>();
	 public Map<String,User> surnameIndex = new HashMap<>();
	 public Map<Long, Rating> ratingsIndex = new HashMap<>();
	 public Map<Long, Movie> movieIndex = new HashMap<>();
	 
	 public LikeMoviesAPI()
	 {}
	 
	 public LikeMoviesAPI(Serializer serializer)
	  {
	    this.serializer = serializer;
	  }
	 
	 @SuppressWarnings("unchecked")
	  public void load() throws Exception
	  {
	    serializer.read();
	    ratingsIndex = (Map<Long, Rating>) serializer.pop();
	    surnameIndex = (Map<String, User>)serializer.pop();
	    userIndex    = (Map<Long, User>)   serializer.pop();
	    movieIndex    = (Map<Long, Movie>)   serializer.pop();
	  }
	  
	  public void store() throws Exception
	  {
	    serializer.push(userIndex);
	    serializer.push(surnameIndex);
	    serializer.push(ratingsIndex);
	    serializer.push(movieIndex);
	    serializer.write(); 
	  }
	  
	 public Collection<User> getUsers ()
	  {
	    return userIndex.values();
	  }
	 
	 public User getUserBySurname(String surname) 
	  {
	    return surnameIndex.get(surname);
	  }
	 
	 public User addUser(String firstName, String surname, String age, String gender, String occupation)
	 {
	 User user = new User (firstName,surname, age, gender, occupation);
	 userIndex.put(user.id, user);
	 surnameIndex.put(surname, user);
	 return user;
	 }
	 
	 public void addMovie(Long id,String title, String year, String url)
	 {
		 Optional<Rating> rating = Optional.ofNullable(ratingsIndex.get(id));
		    if (rating.isPresent())
		    {
		      rating.get().choice.add(new Movie(title, year, url));
		    }
	 }
	 

	public  void removeUser(Long id) {
		
		User user = userIndex.remove(id);
	    surnameIndex.remove(user.surname);
		
	}
	
	 public Rating getUserRating (Long id)
	  {
		 User user = userIndex.get(id); 
	   
		return ratingsIndex.get(user.rating);
	  }
	 
	 @SuppressWarnings("null")
	public Rating rateAMovie(Long id, String type,String preference, double score)
	  {
		Movie movies = null; 
		Rating rating = null;
	    Optional<User> user = Optional.ofNullable(userIndex.get(id));
	    if (user.isPresent())
	    {
	      rating = new Rating (type,preference, score);
	      //user.get().rating.put(movies.id, rating);
	      ratingsIndex.put(movies.id, rating);
	    }
	    return rating;
	  }
	
	 public Movie getMovie (Long id)
	  {
	    return movieIndex.get(id);
	  }

	}
	 


	

