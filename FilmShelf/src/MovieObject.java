
/******************************************************************************************************************************
 * MovieObject
 * @author Jo
 * Description: Represents a movie in the database.
 ******************************************************************************************************************************/
public class MovieObject {
	public String title;
	public int year;
	public String genre;
	public String language;
	public double averageRating;
	public int movieId;
	
	public MovieObject(String title, int year, String genre, String language, double averageRating, int movieId) {
		this.title = title;
		this.year = year;
		this.genre = genre;
		this.language = language;
		this.averageRating = averageRating;
		this.movieId = movieId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getGenre() {
		return genre;
	}
	
	
	public int getLength() {
		return length;
	}
	
	public double getAverageRating() {
		return averageRating;
	}
	
	public int getMovieId() {
		return movieId;
	}
}