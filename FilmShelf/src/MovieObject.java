
/******************************************************************************************************************************
 * MovieObject
 * @author Jo
 * Description: Represents a movie in the database.
 ******************************************************************************************************************************/
public class MovieObject {
	private String title;
	private int year;
	private String genre;
	private String language;
	private int length;
	private double averageRating;
	private int movieId;
	
	public MovieObject(String title, int year, String genre, String language, int length, double averageRating, int movieId) {
		this.title = title;
		this.year = year;
		this.genre = genre;
		this.language = language;
		this.length = length;
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
	
	public String getLanguage() {
		return language;
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