package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import utils.ToJsonString;
import com.google.common.base.Objects;

/**
 * User class 
 * @author John Cuddihy
 * @version 2
 * @date 11/12/2016
 *
 */
@Entity
@Table(name="my_user")



public class User
{
	 static Long counter = 0l;
	 public Long id;
	 public String firstName;
	 public String surname;
	 public String gender;
	 public String age;
	 public String occupation;
	
	 public List<Rating> rating = new ArrayList<>();
	
	 /**
		 * Constructors.
		 * Used when a user is being parsed at the data input
		 * @param id
		 * @param firstName
		 * @param lastName
		 * @param gender
		 * @param age
		 * @param occupation
		 */
	 
	 public User(String firstName, String surname, String gender, String age, String occupation)
	 {
		 
		 if(firstName == null || firstName == "" || surname == null
					|| surname == ""){
				throw new IllegalArgumentException();
		 }
				 this.id = counter++;
				 this.firstName = firstName;
				 this.surname = surname;
				 this.gender = gender;
				 this.age = age;
				 this.occupation = occupation;
		 
	 }
	 public User(){
	
     }
	
	 public String toString()
	 {
	 return new ToJsonString(getClass(), this).toString();
	 }
	
	 @Override
	 public int hashCode()
	 {
	 return Objects.hashCode(this.surname, this.firstName, this.gender, this.age, this.occupation);
	 }
	
	 @Override
	 public boolean equals(final Object obj)
	 {
	 if (obj instanceof User)
	 {
	 final User other = (User) obj;
	 return Objects.equal(firstName, other.firstName)
	 && Objects.equal(surname, other.surname)
	 && Objects.equal(gender, other.gender)
	 && Objects.equal(age, other.age)
	 && Objects.equal(occupation, other.occupation)
	 && Objects.equal(rating, other.rating);
	 }
	 else
	 {
	 return false;
	 }
	 }
	 
	 public void addRating(Rating score){
			rating.add(score);
		}
		
		public List<Rating> getRatings(){
			return rating;
		}
		
		/**
		 * Setters and getters
		 * @param ratings
		 */
		
		public void setRatings(List<Rating> rating){
			this.rating = rating;
		}
		
		public Long getId(){
			return id;
		}
		
		public void setId(Long id){
			this.id = id;
		}
		
		public String getFirstName(){
			return firstName;
		}
		
		public void setFirstName(String firstName){
			this.firstName = firstName;
		}
		
		public String getLastName(){
			return surname;
		}
		
		public void setLastName(String lastName){
			this.surname = lastName;
		}
		
		public String getAge(){
			return age;
		}
		
		public void setAge(String age){
			this.age = age;
		}
		
		public String getOccupation(){
			return occupation;
		}
		
		public void setOccupation(String occupation){
			this.occupation = occupation;
		}
	 
}