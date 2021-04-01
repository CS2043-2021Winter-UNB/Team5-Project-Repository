/******************************************************************************************************************************
 * RemoveRatingControl
 * @author Rachel
 * Description:	Handles validation of input to removeRating class, and initiates movie rating removal in database.
 ******************************************************************************************************************************/

public class RemoveRatingControl {

	private DataManager dataManager;
	private LoginControl loginControl;
	
	public RemoveRatingControl(DataManager dm, LoginControl loginControl) {
		this.dataManager = dm;
		this.loginControl = loginControl;
	}
	
	public boolean processRemoveRating(int movieId) {
		MemberObject member = loginControl.getCurrentMember();
	
		// only permit removal of rating if member is logged in
		if (member == null) {
			return false;
		}
			
		return dataManager.removeMovieRating(member.getUsername(), movieId);
		//NOTE TO FRONT-END: UI needs to call processViewMovie() in Control to update ratings
	}
}