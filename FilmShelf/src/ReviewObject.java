
/******************************************************************************************************************************
 * ReviewObject
 * @author Jo
 * Description: Represents a review in the database.
 ******************************************************************************************************************************/
public class ReviewObject {
	
	private int movieId;
	private String username;
	private String reviewText;
	private int reviewId;
	
	public ReviewObject(int movieId, String username, String reviewText, int reviewId) {
		this.movieId = movieId;
		this.username = username;
		this.reviewText = reviewText;
		this.reviewId = reviewId;
	}
	
	public int getMovieId() {
		return movieId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getReviewText() {
		return reviewText;
	}
	
	public int getReviewId() {
		return reviewId;
	}
	
	// returns string representation of ReviewObject
	public String toString() {
		return "reviewId: " + reviewId + "\n" +
				"movieId: " + movieId + "\n" +
				"username: " + username + "\n" +
				"reviewText: " + reviewText + "\n";
	}
}