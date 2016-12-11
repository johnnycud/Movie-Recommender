package models;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

import utils.ToJsonString;

/**
 * Movie class 
 * @author John Cuddihy
 * @version 2
 * @date 11/12/2016
 *
 */


public class Movie
{
	 static Long counter = 0l;
	
	 public Long id;
	
	 public String title;
	 public String year;
	 public String url;
	 public List<Rating> ratings = new ArrayList<Rating>();
	 
	 public Movie(String title, String year, String url)
	 {
	 this.id = counter++;
	 this.title = title;
	 this.year = year;
	 this.url = url;
	 }
	 
	 public Movie(Long id, String title, String year, String url){
			this.id = id;
			if(id >= counter){
				counter = id + 1;
			}
			this.title = title;
			this.year = year;
			this.url = url;
		}
	
	 @Override
	 public String toString()
	 {
	 return new ToJsonString(getClass(), this).toString();
	 }
	
	     /**
		 * Getters and setters
		 * @param rating
		 */
		public void addRating(Rating rating){
			ratings.add(rating);
		}
		
		public List<Rating> getRatings(){
			return ratings;
		}
		
		public void setRatings(List<Rating> ratings){
			this.ratings = ratings;
		}
		
		public String getTitle(){
			return title;
		}
		
		public void setTitle(String title){
			this.title = title;
		}
		
		public String getUrl(){
			return url;
		}
		
		public void setUrl(String url){
			this.url = url;
		}
		
		public void setYear(String year){
			this.year = year;
		}
		
		public String getYear(){
			return year;
		}
		
		/**
		 * Method for calculating an average rating
		 * Iterates over ratings adding ratings together
		 * incrementing a count 
		 * ratings total plus count divided together to give an average
		 * @return
		 */
		public double averageRating(){
			double total = 0;
			int count = 0;
			for(Rating rating : ratings){
				total += rating.score;
				count++;
			}
			if(count != 0){
				return total/count;
			}else{
				return 0;
			}	
		}
		
		@Override
		public int hashCode(){
			return Objects.hashCode(this.id, this.title, this.url);
		}
		
		
		/**
		 * Compares average ratings of movies
		 */
		public int compareTo(Movie other) {
			
			return Double.compare(this.averageRating(), other.averageRating());
		}

	
		 @Override
		 public boolean equals(final Object obj)
		 {
		 if (obj instanceof Movie)
		 {
		 final Movie other = (Movie) obj;
		 return Objects.equal(title, other.title)
		 && Objects.equal(year, other.year)
		 && Objects.equal(url, other.url);
		 }
		 else
		 {
		 return false;
		 }
		 }
}