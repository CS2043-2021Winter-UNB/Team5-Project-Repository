/******************************************************************************************************************************
 * AdminAccountObject
 * @author Rachel
 * Description: Represents an AdminAccountObject in the database.
 ******************************************************************************************************************************/

public class AdminAccountObject {
	private int adminId;
	private String username;
	private String password;
	private String description;
	private String[] topFiveMovies;


	public AdminAccountObject(int adminId, String username, String password, String description, String[] topFiveMovies) {
		this.adminId = adminId;
		this.username = username;
		this.password = password;
		this.description = description;
		this.topFiveMovies = topFiveMovies;
}

	public int getAdminId() {
		return adminId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDescription() {
		return description;
	}

	public String[] getTopFiveMovies() {
		return topFiveMovies;
	}
}