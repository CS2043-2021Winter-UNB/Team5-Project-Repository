import java.util.ArrayList;

/******************************************************************************************************************************
 * AddMovieControl
 * @author Jo
 * Description: Validates received input for adding a movie and initiates add movie process.
 ******************************************************************************************************************************/
public class AddMovieControl {

	private DataManager dataManager;
	private LoginControl loginControl;
	
	// class constructor
	public AddMovieControl(DataManager dataManager, LoginControl loginControl) {
		this.dataManager = dataManager;
		this.loginControl = loginControl;
	}

	// return true for successful add, false for failure
	public boolean processAddMovie(String title, int releaseYear, String genre, int length) {
		AdminObject admin = loginControl.getCurrentAdmin();
		
		// only permit addition of movie if an admin is logged in
		if(admin == null) {
			return false;
		}
		
		// check if input is valid
		if(!validateFormInput(title, releaseYear, genre, length)) {
			return false;
		}
		
		// double up single quotes for SQL query
		title = title.replace("'", "''");
		
		// call DataManager to add movie and return add result
		return dataManager.addMovie(title.strip(), releaseYear, genre.strip(), length);		// addMovie should return true/false for add result
	}
	
	// helper method, should be private but set to public for now for testing
	private boolean validateFormInput(String title, int releaseYear, String genre, int length) {
		// check that all fields are provided
		if((title == null) || title.strip().isEmpty()) {
			return false; 
		}
		if(releaseYear <= 0) {
			return false;
		}
		if((genre == null) || genre.strip().isEmpty()) {
			return false;
		}
		if(length <= 0) {
			return false;
		}
		
		// check that title isn't longer than 150 characters (length of title in Movie table)
		if(title.strip().length() > 150) {
			return false;
		}
		
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
		
		// check if genre is in genreList
		if(!genreList.stream().anyMatch(genre.strip()::equalsIgnoreCase)) {		// will match regardless of case
			return false;
		}
		
		return true;	// if this line is reached, input is valid
	}
}