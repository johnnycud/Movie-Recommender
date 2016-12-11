package utils;

import java.io.File;

import edu.princeton.cs.introcs.In;
import models.Rating;
import models.User;
import models.Movie;

/**
 * CSVLoader class 
 * @author John Cuddihy
 * @version 2
 * @date 11/12/2016
 *
 */

@SuppressWarnings("hiding")
public class CSVLoader<User,Movie,Rating> {
	
	 public void loadUsers(String User) throws Exception
	 {
		 File usersFile = new File("data_movieLens/users5.dat");
	        In inUsers = new In(usersFile);
	          //each field is separated(delimited) by a '|'
	        String delims = "[|]";
	        while (!inUsers.isEmpty()) {
	            // get user and rating from data source
	            String userDetails = inUsers.readLine();

	            // parse user details string
	            String[] userTokens = userDetails.split(delims);

	            // output user data to console.
	            if (userTokens.length == 7) {
	                System.out.println("UserID: "+userTokens[0]+",First Name:"+
	                        userTokens[1]+",Surname:" + userTokens[2]+",Age:"+
	                        Integer.parseInt(userTokens[3])+",Gender:"+userTokens[4]+",Occupation:"+
	                        userTokens[5]);

	            }else
	            {
	                throw new Exception("Invalid member length: "+userTokens.length);
	            }
	        }
	 
	 }

	 public void loadMovies (String Movie) throws Exception
	 {
		 File movieFile = new File("data_movieLens/items5.dat");
	        In inMovie = new In(movieFile);
	          //each field is separated(delimited) by a '|'
	        String delims = "[|]";
	        while (!inMovie.isEmpty()) {
	            // get user and rating from data source
	            String movieDetails = inMovie.readLine();

	            // parse user details string
	            String[] movieTokens = movieDetails.split(delims);

	            // output user data to console.
	            if (movieTokens.length == 7) {
	                System.out.println("MovieID: "+movieTokens[0]+",Title:"+
	                		movieTokens[1]+",Year:" + movieTokens[2]+",Url:"+
	                        Integer.parseInt(movieTokens[3]));

	            }else
	            {
	                throw new Exception("Invalid member length: "+movieTokens.length);
	            }
	        }


	 }
	 public void loadRatings (String Rating) throws Exception
	 {
		 File ratingFile = new File("data_movieLens/ratings5.dat");
	        In inRating = new In(ratingFile);
	          //each field is separated(delimited) by a '|'
	        String delims = "[|]";
	        while (!inRating.isEmpty()) {
	            // get user and rating from data source
	            String ratingDetails = inRating.readLine();

	            // parse user details string
	            String[] ratingTokens = ratingDetails.split(delims);

	            // output user data to console.
	            if (ratingTokens.length == 7) {
	                System.out.println("ratingID: "+ratingTokens[0]+",Type:"+
	                        ratingTokens[1]+",Preference:" + ratingTokens[2]+",Score:"+
	                        Integer.parseInt(ratingTokens[3]));

	            }else
	            {
	                throw new Exception("Invalid member length: "+ratingTokens.length);
	            }
	        }
	 
	 }

}
