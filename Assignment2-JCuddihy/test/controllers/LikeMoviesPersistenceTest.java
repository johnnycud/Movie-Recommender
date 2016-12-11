package controllers;
import controllers.LikeMoviesAPI;
import static org.junit.Assert.*;

import java.io.File;

import models.Rating;
import models.Movie;
import models.User;

import org.junit.Test;

import utils.Serializer;
import utils.XMLSerializer;
import static model.ModelTest.users;
import static model.ModelTest.ratings;
import static model.ModelTest.movies;

/**
 * This is a Junit test class that test persistence in the app.
 * @author John Cuddihy
 * @version 2
 * @date 11/12/2016
 *
 */

public class LikeMoviesPersistenceTest
{
  LikeMoviesAPI likeMovies;
  
  void populate (LikeMoviesAPI likeMovies)
  {
    for (User user : users)
    {
    	likeMovies.addUser(user.firstName, user.surname, user.gender, user.age, user.occupation);
    }
    User user1 = likeMovies.getUserBySurname(users[0].surname);
    Rating rating = likeMovies.rateAMovie(user1.id,ratings[0].userId, ratings[0].movieId, ratings[0].score);
    likeMovies.rateAMovie(user1.id, ratings[1].userId, ratings[1].userId, ratings[1].score);
    User user2 = likeMovies.getUserBySurname(users[1].surname);
    likeMovies.rateAMovie(user2.id, ratings[2].userId, ratings[2].userId, ratings[2].score);
    likeMovies.rateAMovie(user2.id, ratings[3].userId, ratings[3].userId, ratings[3].score);
    
    for (Movie movie : movies)
    {
    	likeMovies.addMovie(rating.id, movie.title, movie.year, movie.url);
    }
  }
  
  @Test
  public void testPopulate()
  { 
	likeMovies = new LikeMoviesAPI(null);
    assertEquals(0, likeMovies.getUsers().size());
    populate (likeMovies);
    
    assertEquals(users.length, likeMovies.getUsers().size());
    assertEquals(2, likeMovies.getUserBySurname(users[0].surname).rating.size());
    assertEquals(2,likeMovies.getUserBySurname(users[1].surname).rating.size());   
    //Long ratingID = likeMovies.getUserBySurname(users[0].surname).rating.keySet().iterator().next();
    assertEquals(movies.length, likeMovies.getUserRating(ratingsId).choice.size());   
  }

  void deleteFile(String fileName)
  {
    File datastore = new File ("testdatastore.xml");
    if (datastore.exists())
    {
      datastore.delete();
    }
  }
  
  @Test
  public void testXMLSerializer() throws Exception
  { 
    String datastoreFile = "testdatastore.xml";
    //deleteFile (datastoreFile);
    
    Serializer serializer = new XMLSerializer(new File (datastoreFile));
    
    likeMovies = new LikeMoviesAPI(serializer); 
    populate(likeMovies);
    likeMovies.store();
    
    LikeMoviesAPI likeMovies2 =  new LikeMoviesAPI(serializer);
    likeMovies.load();
    
    assertEquals (likeMovies.getUsers().size(), likeMovies2.getUsers().size());
    for (User user : likeMovies.getUsers())
    {
      assertTrue (likeMovies2.getUsers().contains(user));
    }
    //deleteFile ("testdatastore.xml");
  }
  
  
}