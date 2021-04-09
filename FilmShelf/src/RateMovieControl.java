/******************************************************************************************************************************
 * RateMovieControl
 * @author Rachel
 * Description:	Handles input from RateMovieUI class and checks user login and movie info, and then initiates adding rating to the rating table.
 ******************************************************************************************************************************/

public class RateMovieControl {

	private DataManager dataManager;
	private LoginControl loginControl;
	
	public RateMovieControl(DataManager dm, LoginControl loginControl) {
		this.dataManager = dm;
		this.loginControl = loginControl;
	}
	
	//initiate adding rating to database
	public boolean processRating(int movieId, int rating) {
		MemberObject member = loginControl.getCurrentMember();
		
		// only permit rating a movie if member is logged in
		if (member == null) {
			return false;
		}
		
		// only permit rating a movie if the rating is within the range
		if((rating < 1) || (rating > 5)) {
			return false;
		}
		
		return dataManager.addMovieRating(member.getUsername(), movieId, rating);	
		//NOTE TO FRONT-END: UI needs to call processViewMovie() in Control to update ratings
	}
	

	public int getMemberRating(int movieID, MemberObject member) {
		int rating = 0;
		
		RatingObject ratingObject = dataManager.getMovieRatingByMember(member.getUsername(), movieID);
			
		if (ratingObject == null)
			{return rating;} //no rating = 0
		
		rating = ratingObject.getRatingScore();
			{return rating;}//rating of 1-5

	}
	
	
		public double getAverageRating(int movieID) {
			return dataManager.getAverageRating(movieID);
		}
}