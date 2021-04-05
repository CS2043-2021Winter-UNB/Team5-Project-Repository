
/******************************************************************************************************************************
 * LoginControl
 * @author Rachel
 * Description:	Handles validation of input to LoginUI class.
 ******************************************************************************************************************************/

public class LoginControl {
	//LoginControl needs to have a private reference variable of 
	//currentMember --or-- currentAdmin
	//class to hold the login.ed customer object
	private MemberObject currentMember;
	private AdminObject currentAdmin;
	private DataManager dataManager;

	public LoginControl(DataManager dm) {
		this.dataManager = dm;
	}
	
	//NB: different processLogin method for Member vs. Admin
	public boolean processMemberLogin(String username, String password) {
		// check if username or password is empty/null
		if((username == null) || username.strip().isEmpty()) {
			return false;
		}
		if((password == null) || (password.strip().isEmpty())) {
			return false;
		}
		
		currentMember = dataManager.getMember(username.strip(), password.strip());
		 
		if (currentMember == null) {
			return false;
		} else {
			return true;
		}
	}

	//NB: DIFFERENT processLogin METHOD for Member vs. Admin
	public boolean processAdminLogin(String username, String password) {
		// check if username or password is empty/null
		if((username == null) || username.strip().isEmpty()) {
			return false;
		}
		if((password == null) || (password.strip().isEmpty())) {
			return false;
		}
		
		currentAdmin = dataManager.getAdmin(username.strip(), password.strip());
		 
		if (currentAdmin == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public MemberObject getCurrentMember() {
		return currentMember;
	}

	public AdminObject getCurrentAdmin() {
		return currentAdmin;
	}
	
	// updates the current member's info after a member edits their account details (EditMember)
	public void updateCurrentMemberInfo(String fName, String lName, String desc) {
		currentMember = new MemberObject(currentMember.getUsername(), fName, lName, desc, currentMember.getTopMovies());
	}
	
	// clears currentMember
	// called when member removes their own account
	public void clearCurrentMember() {
		currentMember = null;
	}
}
