/******************************************************************************************************************************
 * RatingObject
 * @author Rachel
 * Description: Represents a rating in the database.
 ******************************************************************************************************************************/

public class RatingObject {
	//ratingId not listed in reference table from J-A
	private String ratingID;
	//could rename ratingScore userRating?
	private int ratingScore;
	//memberID not listed in reference table from J-A
	private String memberID;
	//movieID reference table from J-A says this is type int. Confirm.
	private int movieID;

	public RatingObject(String ratingID, int ratingScore, String memberID, int movieID) {
		this.ratingID = ratingID;
		this.ratingScore = ratingScore;
		this.memberID = memberID;
		this.movieID = movieID;
	}
	
	public String getRatingID() {
		return ratingID;
	}
	
	public int getRatingScore() {
		return ratingScore;
	}
	
	public String getmemberID() {
		return memberID;
	}
	
	public int getMovieID() {
		return movieID;
	}
}