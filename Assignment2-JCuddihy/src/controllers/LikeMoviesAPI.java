package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import models.Movie;
import models.Rating;
import models.User;
import utils.Serializer;

/**
 * LikeMoviesAPI class 
 * @author John Cuddihy
 * @version 2
 * @date 11/12/2016
 *
 */

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
	 
	 public User getUser(Long id){
			for(User user : userIndex.values())
				if(user.id == id)
					return user;
		    return null;
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
	
	public Collection<Movie> getMovies(){
		return movieIndex.values();
	}
	
	 public Rating getUserRating (Long id)
	  {
		 User user = userIndex.get(id); 
	   
		return ratingsIndex.get(user.rating);
	  }
	 
	
	 public Movie getMovie (Long movieId)
	  {
		 for(Movie movie : movieIndex.values())
				if(movie.id == movieId)
					return movie;
				
			return null;	
		}
	  
	 
	 public Long getMaxMovieId(){
			Long maxId = 0l;
			for(Long id : movieIndex.keySet()){
				if(id>maxId){
					maxId=id;
				}
			}return maxId;
	 }
	 
     public Movie addMovie(String title, String year, String url) {
			Movie movie = new Movie(title, year, url);
			movieIndex.put(movie.id, movie);
			return movie;
		
	}

	public Rating addRating(Long id, Long userId, Long movieId, Double score) {
		
		Rating r = new Rating(userId, movieId, score);
		r.id = ratingsIndex.size() + 1l;
	    ratingsIndex.put(r.id, r);
	    return r;
	
	}

	
	public List<Movie>getTopTenMovie(){
		Collection<Movie> allMovie = getMovies();
		List<Movie> movieList = new ArrayList<Movie>(allMovie);
		//Collections.sort(movieList);
		//Collections.reverse(movieList);
		List<Movie> sub = movieList.subList(0, 10 > movieList.size() ? movieList.size() : 10);
		return sub;	
	}


	public void write() throws Exception {
		serializer.push(userIndex);
		serializer.push(movieIndex);
		serializer.push(ratingsIndex);
		serializer.write();	
	}

	public Rating rateAMovie(Long id, Long userId, Long movieId, double score) {
		// TODO Auto-generated method stub
		return null;
	}
}
	
	

	 


	

