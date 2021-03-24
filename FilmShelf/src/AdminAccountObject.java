/******************************************************************************************************************************
 * AdminAccountObject
 * @author Rachel
 * Description: Represents an AdminAccountObject in the database.
 ******************************************************************************************************************************/

public class AdminAccountObject {

  
  public String username;
	public String password;


	public AdminAccountObject(int adminId, String username, String password, String description, String[] topFiveMovies) {
    
		this.username = username;
		this.password = password;
}


	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}



}