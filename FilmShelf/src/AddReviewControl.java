
/******************************************************************************************************************************
 * AddReviewControl
 * @author Jo
 * Description:	Handles validation of input from AddReviewUI and initiates adding new Review to Review table.
 ******************************************************************************************************************************/
public class AddReviewControl {

	private DataManager dataManager;
	private LoginControl loginControl;

	public AddReviewControl(DataManager dataManager, LoginControl loginControl) {
		this.dataManager = dataManager;
		this.loginControl = loginControl;
	}
	
	public boolean processAddReview(int movieId, String reviewText) {
		MemberObject member = loginControl.getCurrentMember();
		
		// only add a review if a member is logged in
		if(member == null) {
			return false;
		}
		
		// return failure if review is empty or longer than 2000 characters (database limit)
		if((reviewText == null) || (reviewText.strip().isEmpty()) || (reviewText.length() > 2000)) {
			return false;
		}
		
		// double up single quotes for SQL query
		reviewText = reviewText.replace("'", "''");
		
		return dataManager.addMovieReview(movieId, member.getUsername(), reviewText);
	}
}