
/******************************************************************************************************************************
 * RemoveAccountControl
 * @author Jo
 * Description:	Handles removal of Member accounts by Admin or removal of a Member's account by that Member.
 ******************************************************************************************************************************/
public class RemoveMemberControl {

	private DataManager dataManager;
	private LoginControl loginControl;

	// class constructor
	public RemoveMemberControl(DataManager dataManager, LoginControl loginControl) {
		this.dataManager = dataManager;
		this.loginControl = loginControl;
	}
	
	// assuming remove account option is only displayed to admin or a logged in member on their own account page
	// username should be passed internally from system variable, so no input validation required
	public boolean processRemoveAccount(String username) {
		// return false for empty/null username
		if((username == null) || username.isBlank()) {
			return false;
		}
		
		// verify that the user performing the removal has removal permission
		if(!verifyRemovePermission(username.strip())) {
			return false;
		}
		
		// call removeMemberAccount from DataManager
		if(!dataManager.removeMember(username.strip())) {
			return false;
		}
		
		loginControl.clearCurrentMember();		// clear currentMember on success
		return true;
	}
	
	// checks who is requesting the removal and returns a boolean representing if the removal is valid or invalid
	private boolean verifyRemovePermission(String username) {
		boolean hasRemovePermission = false;
		MemberObject member = loginControl.getCurrentMember();		// if app needs to be rerun for new user to login, one of these will always be null
		AdminObject admin = loginControl.getCurrentAdmin();			// if a user can logout and another can login, LoginControl can set the logged out Account object to null
		
		// if a member is removing an account, check if that member is attempting to remove their own account
		if((member != null) && !username.equals(member.getUsername())) {
			hasRemovePermission = false;
		} else if((admin != null) || ((member != null) && username.equals(member.getUsername()))) {		// if admin is removing or a member is removing their own account, allow the removal
			hasRemovePermission = true;
		}
		
		return hasRemovePermission;
	}
}