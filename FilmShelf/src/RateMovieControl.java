/******************************************************************************************************************************
 * RateMovieControl
 * @author Rachel
 * Description:	Handles input from RateMovieUI class and checks user login and movie info, and then initiates adding rating to the rating table.
 ******************************************************************************************************************************/

public class RateMovieControl {

	private DataManager dataManager;
	private LoginControl loginControl;
	
	public RateMovieControl(DataManager dm, LoginControl loginControl, ViewMovieControl viewMovieControl) {
		this.dataManager = dm;
		this.loginControl = loginControl;
	}
	
	//initiate adding rating to database
	public boolean processRating(int rating, MovieObject movie) {
		MemberObject member = loginControl.getCurrentMember();
		
		// only permit addition of movie if member is logged in
		if (member == null) {
			return false;
		}
		
		return dataManager.addMovieRating(member, movie, rating);	
	//NOTE TO FRONT-END: UI needs to call processViewMovie() in Control to update ratings
		
	}
	
}