package models;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;
import utils.ToJsonString;

/**
 * Ratings class that is a representation of what a rating object is.
 * @author JohnCuddihy
 * @version 2
 * @date 11/12/2016
 *
 */

public class Rating {
	
	static Long   counter = 0l;
	  
	  public Long   userId;
	  public Long movieId;
	  public double score;
	  public Long id;
	  
	  public List<Movie> choice = new ArrayList<>();
	 
	  public Rating()
	  {
	  }
	  /**
		 * Two constructors,
		 * Second one when data is parsed in dataInput
		 * @param id
		 * @param userId
		 * @param movieId
		 * @param score
		 */
	  public Rating(long userId,long movieId, double score)
	  {
	    this.userId        = userId;
	    this.movieId       = movieId;
	    this.score         = score;
	    
	  }
	  
	  
		public Rating(long id, long userId, long movieId, double score){
			this.id = id;
			if(id >= counter){
				counter = id + 1;
			}
			this.userId = userId;
			this.movieId = movieId;
			this.score = score;
		}
	  
	  @Override
	
	  public String toString()
	  {
	    return new ToJsonString(getClass(), this).toString();
	  }
	  
	  @Override  
	  public int hashCode()  
	  {  
	     return Objects.hashCode(this.userId, this.movieId, this.score);  
	  } 
	  
	  public int compareTo(Rating that){
			return Double.compare(this.getRating(), that.getRating());
		}
	  
	  @Override
	  public boolean equals(final Object obj)
	  {
	    if (obj instanceof Rating)
	    {
	      final Rating other = (Rating) obj;
	      return Objects.equal(userId,      other.userId) 
	          && Objects.equal(score,     other.score)
	          && Objects.equal(movieId,other.movieId)
	          && Objects.equal(choice,     other.choice);     
	    }
	    else
	    {
	      return false;
	    }
	  }
	  
	  /**
		 * Setters and Getters
		 * @return
		 */
		
		public double getRating(){
			return score;
		}
		
		public void setRating(double score){
			this.score = score;
		}
		
		public long getUserId(){
			return userId;
		}
		
		public void setUserId(long userId){
			this.userId = userId;
		}
		
		public long getMovieId(){
			return movieId;
		}
		
		public void setMovieId(long movieId){
			this.movieId = movieId;
		}

}
