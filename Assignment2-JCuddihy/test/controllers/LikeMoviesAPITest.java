package controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.LikeMoviesAPI;
import models.Rating;
import models.Movie;
import models.User;

import static model.ModelTest.users;
import static model.ModelTest.ratings;
import static model.ModelTest.movies;

public class LikeMoviesAPITest
{
  private LikeMoviesAPI likeMovie;

  @Before
  public void setup()
  {
	  likeMovie = new LikeMoviesAPI();
    for (User user : users)
    {
    	likeMovie.addUser(user.firstName, user.surname, user.gender, user.age, user.occupation);
    }
  }

  @After
  public void tearDown()
  {
	  likeMovie = null;
  }

  @Test
  public void testUser()
  {
    assertEquals (users.length, likeMovie.getUsers().size());
    likeMovie.addUser("Conor", "O'Sullivan", "male", "25", "Lab Tech");
    assertEquals (users.length+1, likeMovie.getUsers().size());
    assertEquals (users[0], likeMovie.getUserBySurname(users[0].surname));
  }  

  @Test
  public void testUsers()
  {
    assertEquals (users.length, likeMovie.getUsers().size());
    for (User user: users)
    {
      User eachUser = likeMovie.getUserBySurname(user.surname);
      assertEquals (user, eachUser);
      assertNotSame(user, eachUser);
    }
  }

  @Test
  public void testDeleteUsers()
  {
    assertEquals (users.length, likeMovie.getUsers().size());
    User patty = likeMovie.getUserBySurname("gilroy");
    likeMovie.removeUser(patty.id);
    assertEquals (users.length-1, likeMovie.getUsers().size());    
  }  
  
  @Test
  public void testAddRating()
  {
    User patty = likeMovie.getUserBySurname("gilroy");
    Rating rating = likeMovie.addRating(patty.id, ratings[0].userId, ratings[0].movieId, ratings[0].score);
    Rating returnedRating = likeMovie.getUserRating(rating.id);
    assertEquals(ratings[0],  returnedRating);
    assertNotSame(ratings[0], returnedRating);
  }
  
  @Test
  public void testAddRatingWithSingleMovie()
  {
    User mike = likeMovie.getUserBySurname("gilman");
    Long ratingId = likeMovie.addRating(mike.id, ratings[3].userId, ratings[3].movieId, ratings[3].score).id;

    likeMovie.addMovie(ratingId, movies[3].title, movies[3].year, movies[3].url);

    Rating rating = likeMovie.getUserRating(ratingId);
    assertEquals (1, rating.choice.size());
    assertEquals( movies[3].title,  rating.choice.get(3).year,rating.choice.get(3).url);
    assertEquals( movies[3].year, rating.choice.get(3).title, rating.choice.get(3).url);   
  }
  
  @Test
  public void testAddActivityWithMultipleLocation()
  {
    User patty = likeMovie.getUserBySurname("gilroy");
    Long ratingId = likeMovie.addRating(patty.id, ratings[0].userId, ratings[0].movieId, ratings[0].score).id;

    for (Movie movie : movies)
    {
    	likeMovie.addMovie(ratingId, movie.title, movie.year, null);      
    }

    Rating rating = likeMovie.getUserRating(ratingId);
    assertEquals (movies.length, rating.choice.size());
    int i = 0;
    for (Movie movie : rating.choice)
    {
      assertEquals(movie, movies[i]);
      i++;
    }
  } 
}
