/******************************************************************************************************************************
 * AdminAccountObject
 * @author Rachel
 * Description: Represents an AdminAccountObject in the database.
 ******************************************************************************************************************************/
public class AdminAccountObject {
	public String username;

	public AdminAccountObject(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}