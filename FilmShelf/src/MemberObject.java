
import java.util.ArrayList;

/******************************************************************************************************************************
 * MemberAccountObject
 * @author Jo
 * Description: Represents a Member account in the database.
 ******************************************************************************************************************************/
public class MemberObject {

	private String username;
	private String firstName;
	private String lastName;
	private String description;
	private ArrayList<MovieObject> topMovies; // ArrayList or array? ArrayList easy to work with, array can limit number of movies to prevent overflow

	// no point in storing password, db will encrypt passwords which will be difficult to decrypt
	public MemberObject(String username, String firstName, String lastName, String description, ArrayList<MovieObject> topMovies) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.topMovies = new ArrayList<>(topMovies);	// copy passed ArrayList
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
	
	public ArrayList<MovieObject> getTopMovies() {
		return topMovies;
	}
	
	// pass the top movies in desc order (number1, number2, number3, ...)
	// TODO: either enforce calling methods to only pass ArrayLists with 5 MovieObjects, or reject ArrayLists passed with more than 5 MovieObjects
	public void setTopMovies(ArrayList<MovieObject> movies) {
		topMovies = movies;
	}
	
	// provides a string representation of a MemberObject
	public String toString() {
		String result = "Member Username: "+username+"\n"+
						"First name: "+firstName+"\n"+
						"Last name: "+lastName+"\n"+
						"Description: "+description+"\n"+
						"TOP MOVIES:\n";
		
		for(MovieObject movie : topMovies) {
			result = result + movie.toString() + "\n";
		}
		
		return result;
	}

}