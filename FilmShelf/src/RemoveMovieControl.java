/******************************************************************************************************************************
 * RemoveMovieControl
 * @author Rachel
 * Description:	Handles validation of input to removeMovie class, including user (admin), and initiates movie removal in database.
 ******************************************************************************************************************************/
public class RemoveMovieControl {

	private DataManager dataManager;
	private LoginControl loginControl;

	public RemoveMovieControl(DataManager dm, LoginControl loginControl) {
		this.dataManager = dm;
		this.loginControl = loginControl;
	}
	
	public boolean processRemoveMovie(int movieId) {
		
		//check method name
		AdminObject admin = loginControl.getCurrentAdmin();
		
		// only permit addition of movie if an admin is logged in
		if (admin == null) {
			return false;
		}
		
		//Call data manager to remove movie
		return dataManager.removeMovie(movieId);	
	}
}