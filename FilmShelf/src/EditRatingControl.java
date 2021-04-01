/******************************************************************************************************************************
 * EditRatingControl
 * @author Rachel
 * Description:	Handles validation of input to editRating class, and initiates movie rating edit in database.
 ******************************************************************************************************************************/

public class EditRatingControl {

	private DataManager dataManager;
	private LoginControl loginControl;

	public EditRatingControl(DataManager dm, LoginControl loginControl) {
		this.dataManager = dm;
		this.loginControl = loginControl;
	}
	
	public boolean processEditRating(int rating, MovieObject movie) {
		MemberObject member = loginControl.getCurrentMember();
	
		// only permit addition of movie if member is logged in
		if (member == null) {
			return false;
		}
			
		return dataManager.editMovieRating(rating, member, movie);
		//NOTE TO FRONT-END: UI needs to call processViewMovie() in Control to update ratings
	}
}