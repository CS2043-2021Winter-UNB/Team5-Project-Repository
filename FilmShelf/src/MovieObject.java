
/******************************************************************************************************************************
 * MovieObject
 * @author Jo
 * Description: Represents a movie in the database.
 ******************************************************************************************************************************/
public class MovieObject {
	
	private String title;
	private int year;
	private String genre;
	private int length;
	private double averageRating;
	private int movieId;
	
	public MovieObject(String title, int year, String genre, int length, double averageRating, int movieId) {
		this.title = title;
		this.year = year;
		this.genre = genre;
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
	
	public int getLength() {
		return length;
	}
	
	public double getAverageRating() {
		return averageRating;
	}
	
	public int getMovieId() {
		return movieId;
	}
	
	@Override
	public String toString() {
		return "Title: " + title + "\n" +
				"Year: " + year + "\n" +
				"Genre: " + genre + "\n" +
				"Length: " + length + "\n" +
				"Average rating: " + averageRating + "\n" +
				"movieId: " + movieId;
	}
	
	@Override
	public boolean equals(Object object) {
		if(this == object) {
			return true;
		}
		if(object == null || getClass() != object.getClass()) {
			return false;
		}
		
		MovieObject movie = (MovieObject) object;
		
		if(!this.getTitle().equals(movie.getTitle())) {
			return false;
		}
		
		if(this.getYear() != movie.getYear()) {
			return false;
		}
		
		if(!this.getGenre().equalsIgnoreCase(movie.getGenre())) {
			return false;
		}
		
		if(this.getLength() != movie.getLength()) {
			return false;
		}
		
		if(this.getAverageRating() != movie.getAverageRating()) {
			return false;
		}
		
		if(this.getMovieId() != movie.getMovieId()) {
			return false;
		}
		
		return true;
	}
}