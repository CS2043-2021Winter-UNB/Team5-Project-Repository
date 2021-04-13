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
	
	public boolean processEditRating(int movieId, int rating) {
		MemberObject member = loginControl.getCurrentMember();
	
		// only permit addition of movie if member is logged in
		if (member == null) {
			return false;
		}
		
		// only permit rating a movie if the rating is within the range
		if((rating < 1) || (rating > 5)) {
			return false;
		}
			
		return dataManager.editMovieRating(member.getUsername(), movieId, rating);
		//NOTE TO FRONT-END: UI needs to call processViewMovie() in Control to update ratings
	}
	
	public RatingObject getRating(int movieID) {
		MemberObject member = loginControl.getCurrentMember();
		return dataManager.getMovieRatingByMember(member.getUsername(), movieID);
	}
}