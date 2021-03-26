
import java.util.ArrayList;

/******************************************************************************************************************************
 * SearchMovieControl
 * @author Jo
 * Description: Validates search input for finding a movie and initiates movie searching process.
 ******************************************************************************************************************************/
public class SearchMovieControl {
	private DataManager dataManager;
	
	// class constructor
	public SearchMovieControl(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	public ArrayList<MovieObject> processSearchMovie(String title, int releaseYear, String genre, int lengthLowerLimit, int lengthUpperLimit) {
		ArrayList<MovieObject> movies = null;
		
		// check that search input is valid
		if(validateSearchInput(title, releaseYear, genre, lengthLowerLimit, lengthUpperLimit)) {
			movies = dataManager.getMoviesbyKeywords(title, releaseYear, genre, lengthLowerLimit, lengthUpperLimit);
		}
		
		return movies;
	}
	
	// helper method, should be private but set to public for now for testing
	// SQL query with all null values or all empty values should return nothing as expected, no need to prevent empty/null search 
	public boolean validateSearchInput(String title, int releaseYear, String genre, int lengthLowerLimit, int lengthUpperLimit) {
		// create list of possible genres (based on genres used by Letterboxd)
		ArrayList<String> genreList = new ArrayList<String>();
		genreList.add("Action");
		genreList.add("Adventure");
		genreList.add("Animation");
		genreList.add("Comedy");
		genreList.add("Crime");
		genreList.add("Documentary");
		genreList.add("Drama");
		genreList.add("Horror");
		genreList.add("Thriller");
		genreList.add("Romance");
		genreList.add("Science Fiction");
		genreList.add("Fantasy");
		genreList.add("War");
		genreList.add("Western");
		
		// check that title isn't longer than 25 characters (length of title in Movie table)
		if(title.length() > 25) {
			return false;
		}
		
		// check that releaseYear is positive
		// the earliest surviving video footage is the Roundhay Garden scene from 1888
		if(releaseYear <= 0) {
			return false;
		}
		
		// check if genre is in genreList
		if(!genreList.stream().anyMatch(genre::equalsIgnoreCase)) {		// will match regardless of case
			return false;
		}
		
		if((lengthLowerLimit > lengthUpperLimit) || (lengthLowerLimit < 0)) {
			return false;
		}
		
		return true;	// if this line is reached, input is valid
	}
}