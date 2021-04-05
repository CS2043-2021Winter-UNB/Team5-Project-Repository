/******************************************************************************************************************************
 * RemoveReviewControl
 * @author Jo
 * Description:	Handles initiating removal of reviews from the Review table.
 ******************************************************************************************************************************/
public class RemoveReviewControl {

	private DataManager dataManager;
	private LoginControl loginControl;

	public RemoveReviewControl(DataManager dataManager, LoginControl loginControl) {
		this.dataManager = dataManager;
		this.loginControl = loginControl;
	}
	
	public boolean processRemoveReview(int movieId, String username, int reviewId) {
		if(!verifyRemovePermission(username)) {
			return false;
		}
		
		return dataManager.removeMovieReview(reviewId);
	}
	
	// checks who is requesting the removal and returns a boolean representing if the removal is valid or invalid
	private boolean verifyRemovePermission(String username) {
		boolean hasRemovePermission = false;		// default to false
		MemberObject member = loginControl.getCurrentMember();		// if app needs to be rerun for new user to login, one of these will always be null
		AdminObject admin = loginControl.getCurrentAdmin();			// if a user can logout and another can login, LoginControl can set the logged out Account object to null
		
		// if admin is removing or a member is removing their own account, allow the removal
		if((admin != null) || ((member != null) && username.equals(member.getUsername()))) {
			hasRemovePermission = true;
		}
		
		return hasRemovePermission;
	}
}