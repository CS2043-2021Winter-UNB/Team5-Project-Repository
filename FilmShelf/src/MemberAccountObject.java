
import java.util.ArrayList;

/******************************************************************************************************************************
 * MemberAccountObject
 * @author Jo
 * Description: Represents a Member account in the database.
 ******************************************************************************************************************************/
public class MemberAccountObject {
	// no point in storing password, db will encrypt passwords which will be difficult to decrypt
	private String username;
	private String firstName;
	private String lastName;
	private ArrayList<MovieObject> topMovies;	// ArrayList or array? ArrayList easy to work with, array can limit number of movies to prevent overflow
	
	public MemberAccountObject(String username, String firstName, String lastName) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		topMovies = new ArrayList<MovieObject>();
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
	
	public ArrayList<MovieObject> getTopMovies() {
		return topMovies;
	}
	
	// pass the top movies in desc order (number1, number2, number3, ...)
	// TODO: either enforce calling methods to only pass ArrayLists with 5 MovieObjects, or reject ArrayLists passed with more than 5 MovieObjects
	public void setTopMovies(ArrayList<MovieObject> movies) {
		topMovies = movies;
	}
}