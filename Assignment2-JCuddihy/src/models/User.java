package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import utils.ToJsonString;
import com.google.common.base.Objects;


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
	
	 public User(String firstName, String surname, String gender, String age, String occupation)
	 {
	 this.id = counter++;
	 this.firstName = firstName;
	 this.surname = surname;
	 this.gender = gender;
	 this.age = age;
	 this.occupation = occupation;
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
}