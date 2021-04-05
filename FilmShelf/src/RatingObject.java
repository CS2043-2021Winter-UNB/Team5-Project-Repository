/******************************************************************************************************************************
 * RatingObject
 * @author Rachel
 * Description: Represents a rating in the database.
 ******************************************************************************************************************************/

public class RatingObject {
	private int ratingScore;
	private String username;
	private int movieID;

	public RatingObject(int ratingScore, String username, int movieID) {
		this.ratingScore = ratingScore;
		this.username = username;
		this.movieID = movieID;
	}
	
	public int getRatingScore() {
		return ratingScore;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getMovieID() {
		return movieID;
	}
}