/******************************************************************************************************************************
 * AdminObject
 * @author Rachel
 * Description: Represents an AdminObject in the database.
 ******************************************************************************************************************************/
public class AdminObject {
	private String username;

	public AdminObject(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	public String toString() {
		return "Admin Username: " + username + "\n";
	}
}