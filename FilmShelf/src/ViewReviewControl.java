
import java.util.ArrayList;

/******************************************************************************************************************************
 * ViewReviewControl
 * @author Jo
 * Description:	Initiates retrieving reviews for a specified movie from the Review table.
 ******************************************************************************************************************************/
public class ViewReviewControl {

	private DataManager dataManager;

	public ViewReviewControl(DataManager dataManager) {
		this.dataManager = dataManager;
	}
	
	// return an ArrayList of ReviewObjects to ViewReviewUI
	public ArrayList<ReviewObject> processViewReview(int movieId) {
		// only get reviews given a valid movieId
		if(movieId < 1) {
			return null;
		}
		
		// retrieve an ArrayList of reviews for a specified movie from the Review table
		return dataManager.getMovieReviews(movieId);
	}
}