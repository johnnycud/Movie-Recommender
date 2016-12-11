package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;



import utils.Serializer;
import utils.XMLSerializer;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import models.Movie;
import models.MovieAPI;
import models.Rating;
import models.User;

/**
 * Main class 
 * @author John Cuddihy
 * @version 2
 * @date 11/12/2016
 *
 */

public class Main
{
	
	private User user;
	private Movie movie;
	private Rating rating;
	
	 public LikeMoviesAPI likeMovies;
	 
	 public Main() throws Exception{	
			File datastore = new File("dataStore.xml");
			Serializer serializer = new XMLSerializer(datastore);
			
			likeMovies = new LikeMoviesAPI(serializer);
			if(datastore.isFile()){
				likeMovies.load();
			}
		}
	 
	 @Command(description="Add a new User")
	 public void addUser (@Param(name="first name") String firstName, @Param(name="last name") String lastName,
	 @Param(name="age") String age, @Param(name="gender") String gender, @Param(name="occupation") String occupation)
	 {
	        likeMovies.addUser(firstName, lastName, age, gender, occupation);
	 }
	
	 @Command(description="Delete a User")
	 public void removeUser (@Param(name="id") Long id)
	 {
	        likeMovies.removeUser(id);
	 }
	 @Command(description="Add a Movie")
	 public void addMovie (@Param(name="title") String title, @Param(name="year") String year, @Param(name="url") String url)
	 {
	        likeMovies.addMovie(title, year, url);
	 }
	 public static void main(String[] args) throws Exception
	 {
	 Main main = new Main();
	 Shell shell = ShellFactory.createConsoleShell("lm", "Welcome to likemovie - ?help for instructions", main);
	 shell.commandLoop();
	 main.likeMovies.store();
	 }
	 
	 @Command(description="Save data")
		public String save() throws Exception{
		 likeMovies.write();
			return "Data Saved";
		}
	 
	 @Command(description="Get List of Users")
		public void getUsers(){
			Collection<User>users = likeMovies.getUsers();
			System.out.println(users);
		}
	 
	 @Command(description="Get List of Movies")
		public void getMovies(){
			Collection<Movie>movies = likeMovies.getMovies();
			System.out.println(movies);
		}
	 
	 @Command(description="Add a Rating")
		public String addRating(@Param(name="userID") long userId, @Param(name="movieID") long movieId, @Param(name="rating") double score){
		 likeMovies.addRating(userId, movieId, null, score);
			movie = likeMovies.getMovie(movieId);
			user = likeMovies.getUser(userId);
			return "Movie " + movie.title + " Rated " + rating + " by " + user.firstName;
		}
	 
	 @Command(description="Get a Rating based on id")
		public String getRating(@Param(name="id") long id){
			rating = likeMovies.getUserRating(id);
			return rating.toString();
		}
	 
	 @Command(description="Get top ten movies")
		public void getTopTenMovies(){
			ArrayList<Movie> movies = new ArrayList<>(likeMovies.getTopTenMovie());
			if(movies.size()>0){
				for(Movie movie : movies){
					System.out.println(movie.toString());
				}
			}else{
				System.out.println("No top movies to show");
			}
		}
		
	 
	 
	 
}
