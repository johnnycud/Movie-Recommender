package models;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;
import utils.ToJsonString;

public class Rating {
	
	static Long   counter = 0l;
	  
	  public Long   id;
	  public String type;
	  public double score;
	  public String preference;
	  
	  public List<Movie> choice = new ArrayList<>();
	 
	  public Rating()
	  {
	  }
	  
	  public Rating(String type,String preference, double score)
	  {
	    this.id        = counter++;
	    this.type      = type;
	    this.score     = score;
	    this.preference = preference;
	  }
	  
	  @Override
	
	  public String toString()
	  {
	    return new ToJsonString(getClass(), this).toString();
	  }
	  
	  @Override  
	  public int hashCode()  
	  {  
	     return Objects.hashCode(this.id, this.type,this.preference, this.score);  
	  } 
	  
	  @Override
	  public boolean equals(final Object obj)
	  {
	    if (obj instanceof Rating)
	    {
	      final Rating other = (Rating) obj;
	      return Objects.equal(type,      other.type) 
	          && Objects.equal(score,     other.score)
	          && Objects.equal(preference,other.preference)
	          && Objects.equal(choice,     other.choice);     
	    }
	    else
	    {
	      return false;
	    }
	  }

}
