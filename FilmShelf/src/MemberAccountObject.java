
import java.util.ArrayList;

/******************************************************************************************************************************
 * MemberAccountObject
 * @author Jo
 * Description: Represents a Member account in the database.
 ******************************************************************************************************************************/
public class MemberAccountObject {

	public String username;
	public String firstName;
	public String lastName;
	public String email;
	public String description;
	public ArrayList<String> topMovies; // ArrayList or array? ArrayList easy to work with, array can limit number of movies to prevent overflow

	// no point in storing password, db will encrypt passwords which will be difficult to decrypt
	public MemberAccountObject(String username, String firstName, String lastName, String description) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		topMovies = new ArrayList<String>();
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<String> getTopMovies() {
		return topMovies;
	}
	
	// pass the top movies in desc order (number1, number2, number3, ...)
	// TODO: either enforce calling methods to only pass ArrayLists with 5 MovieObjects, or reject ArrayLists passed with more than 5 MovieObjects
	public void setTopMovies(ArrayList<String> movies) {
		topMovies = movies;
	}

}