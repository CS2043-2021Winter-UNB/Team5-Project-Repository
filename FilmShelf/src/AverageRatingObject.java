
/******************************************************************************************************************************
 * AverageRatingObject
 * @author Rachel
 * Description: Represents an averageRatingObject in the database.
 ******************************************************************************************************************************/

public class AverageRatingObject {
	
	private int movieID; //Check: movieID reference table from J-A says this is type int. Confirm.
	private int avgMovieRating; //Q:Should this be type double?
	
	public AverageRatingObject(int movieID, int avgMovieRating) {
		this.movieID = movieID;
		this.avgMovieRating = avgMovieRating;
		
	}
	
	//Q: Do we need to have this method?
	public int getMovieID() {
		return movieID;
	}
	
	public int getAvgMovieRating() {
		return avgMovieRating;
	}
	
}