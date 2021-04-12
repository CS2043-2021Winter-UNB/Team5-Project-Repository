
import java.util.ArrayList;
import java.util.Calendar;

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


	public ArrayList<MovieObject> processSearchMovie(String title, int lowerYear, int upperYear, String genre, int lengthLowerLimit, int lengthUpperLimit) {
		ArrayList<MovieObject> movies = null;
		
		// check that search input is valid
		if(!validateSearchInput(title, lowerYear, upperYear, genre, lengthLowerLimit, lengthUpperLimit)) {
			return null;
		}
		
		// double up single quotes for SQL query
		title = title.replace("'", "''");
		
		// handle genre not specified
		if(genre.equals("Select a genre")) {
			genre = null;
		}
		
		// handle year limits not specified
		if(lowerYear == -1 && upperYear != -1) {
			lowerYear = 0;
		} else if(lowerYear != -1 && upperYear == -1) {
			// set limit to current year if non-number in dropdown
			upperYear = Calendar.getInstance().get(Calendar.YEAR);
		}
		
		movies = dataManager.getMoviesByKeywords(title, lowerYear, upperYear, genre, lengthLowerLimit, lengthUpperLimit);
		
		return movies;
	}
	
	public boolean validateSearchInput(String title, int lowerYear, int upperYear, String genre, int lengthLowerLimit, int lengthUpperLimit) {
		// check that title isn't longer than 25 characters (length of title in Movie table)
		if((title != null) && (title.strip().length() > 25)) {
			return false;
		}
		
		// check that lowerYear and upperYear are positive and lowerYear is not greater than upperYear
		// the earliest surviving video footage is the Roundhay Garden scene from 1888
		if((lowerYear < -1) || (upperYear < -1) || ((lowerYear > upperYear) && ((lowerYear != -1) && (upperYear != -1)))) {
			return false;
		}
		
		// create list of possible genres (based on genres used by Letterboxd)
		ArrayList<String> genreList = new ArrayList<String>();
		genreList.add("Select a genre");
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
		
		// check if genre is in genreList
		if((genre != null) && !genreList.stream().anyMatch(genre.strip()::equalsIgnoreCase)) {		// will match regardless of case
			return false;
		}
		
		if((lengthLowerLimit > lengthUpperLimit) || (lengthLowerLimit < 0) || (lengthUpperLimit < 0)) {
			return false;
		}
		
		return true;	// if this line is reached, input is valid
	}
}