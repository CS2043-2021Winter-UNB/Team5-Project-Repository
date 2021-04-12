import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/******************************************************************************************************************************
 * SearchMovieControlTest
 * @author Jo
 * Description: Tests valid and invalid input to SearchMovieControl
 ******************************************************************************************************************************/
@RunWith(Parameterized.class)
public class SearchMovieControlTest {
	// define input types for different tests
	enum Type {VALID, INVALID};
	
	@Parameters(name = "{index}: {0}, {1}, {2}, {3}, {4}, {5}, {6}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{Type.VALID, "Ferris Bueller's Day Off", 1986, 1986, "Comedy", 103, 103},			// test expected valid input that exactly matches the movie
			{Type.VALID, "Bueller", 1986, 1986, "Comedy", 103, 103},							// test expected valid input with partially matching title
			{Type.VALID, "", 1986, 1986, "Comedy", 103, 103},									// test expected valid input with no title specified
			{Type.VALID, null, 1986, 1986, "Comedy", 103, 103},									// test expected valid input with null title
			{Type.VALID, "Ferris Bueller's Day Off", 1986, 1986, null, 103, 103},				// test expected valid input with null genre
			{Type.VALID, "Ferris Bueller's Day Off", 1950, 1986, "Comedy", 103, 103},			// test expected valid input with year range: low, exact
			{Type.VALID, "Ferris Bueller's Day Off", 1986, 2006, "Comedy", 103, 103},			// test expected valid input with year range: exact, high
			{Type.VALID, "Ferris Bueller's Day Off", -1, 1986, "Comedy", 103, 103},				// test expected valid input with year range: -1, exact
			{Type.VALID, "Ferris Bueller's Day Off", 1986, -1, "Comedy", 103, 103},				// test expected valid input with year range: exact, -1
			{Type.VALID, "Ferris Bueller's Day Off", -1, -1, "Comedy", 103, 103},				// test expected valid input with year range: -1, -1
			{Type.VALID, "Ferris Bueller's Day Off", 1960, -1, "Comedy", 103, 103},				// test expected valid input with year range: low, -1
			{Type.VALID, "Ferris Bueller's Day Off", -1, 2015, "Comedy", 103, 103},				// test expected valid input with year range: -1, high
			{Type.VALID, "Ferris Bueller's Day Off", 1980, 1990, "Comedy", 103, 103},			// test expected valid input with year range: low, high
			{Type.VALID, "Ferris Bueller's Day Off", 1986, 1986, "Comedy", 30, 103},			// test expected valid input with length range: low, exact
			{Type.VALID, "Ferris Bueller's Day Off", 1986, 1986, "Comedy", 103, 120},			// test expected valid input with length range: exact, high
			{Type.VALID, "Ferris Bueller's Day Off", 1986, 1986, "Comedy", 15, 150},			// test expected valid input with length range: low, high
			{Type.VALID, "Ferris Bueller's Day Off", 1985, 1995, "Comedy", 100, 115},			// test expected valid input with range of years and range of lengths
			{Type.VALID, "Ferris Bueller's Day Off", -1, -1, "Select a genre", -1, -1},			// test expected valid input with only title
			{Type.VALID, "", 1986, 1986, "Select a genre", -1, -1},								// test expected valid input with only year
			{Type.VALID, "", -1, -1, "Comedy", -1, -1},											// test expected valid input with only genre
			{Type.VALID, "", -1, -1, "Select a genre", 103, 103},								// test expected valid input with only length
			{Type.INVALID, "Die Hard", 1986, 1986, "Comedy", 103, 103},						// test invalid input with wrong title
			{Type.INVALID, "Ferris Bueller's Day OffFerris Bueller's Day OffFerris Bueller's Day OffFerris Bueller's Day OffFerris Bueller's Day OffFerris Bueller's Day OffFerris Bueller's Day Off",
				1986, 1986, "Comedy", 103, 103},											// test invalid input with title > 150 characters
			{Type.INVALID, "Ferris Bueller's Day Off", 1986, 1986, "Action", 103, 103},		// test invalid input with wrong genre
			{Type.INVALID, "Ferris Bueller's Day Off", -100, -100, "Comedy", 103, 103},		// test invalid input with invalid year
			{Type.INVALID, "Ferris Bueller's Day Off", 2020, 1980, "Comedy", 103, 103},		// test invalid input with year range: high, low
			{Type.INVALID, "Ferris Bueller's Day Off", 1967, 1980, "Comedy", 103, 103},		// test invalid input with year range: low, low
			{Type.INVALID, "Ferris Bueller's Day Off", 2000, 2021, "Comedy", 103, 103},		// test invalid input with year range: high, high
			{Type.INVALID, "Ferris Bueller's Day Off", 1986, 1980, "Comedy", 103, 103},		// test invalid input with year range: exact, low
			{Type.INVALID, "Ferris Bueller's Day Off", 2020, 1986, "Comedy", 103, 103},		// test invalid input with year range: high, exact
			{Type.INVALID, "Ferris Bueller's Day Off", 2020, -1, "Comedy", 103, 103},		// test invalid input with year range: high, -1
			{Type.INVALID, "Ferris Bueller's Day Off", -1, 1980, "Comedy", 103, 103},		// test invalid input with year range: -1, low
			{Type.INVALID, "Ferris Bueller's Day Off", 1986, 1986, "Comedy", 120, 30},		// test invalid input with length range: high, low
			{Type.INVALID, "Ferris Bueller's Day Off", 1986, 1986, "Comedy", 30, 30},		// test invalid input with length range: low, low
			{Type.INVALID, "Ferris Bueller's Day Off", 1986, 1986, "Comedy", 120, 120},		// test invalid input with length range: high, high
			{Type.INVALID, "Ferris Bueller's Day Off", 1986, 1986, "Comedy", 103, 30},		// test invalid input with length range: exact, low
			{Type.INVALID, "Ferris Bueller's Day Off", 1986, 1986, "Comedy", 120, 103}		// test invalid input with length range: high, exact			
		});
	}
	
	private Type type;
	private DataManager dataManager;
	private SearchMovieControl searchMovieControl;
	private String title;
	private int lowerYear;
	private int upperYear;
	private String genre;
	private int lowerLength;
	private int upperLength;
	private final String MOVIE_TITLE = "Ferris Bueller''s Day Off";
	private final int MOVIE_YEAR = 1986;
	private final String MOVIE_GENRE = "Comedy";
	private final int MOVIE_LENGTH = 103;
	
	public SearchMovieControlTest(Type type, String title, int lowerYear, int upperYear, String genre, int lowerLength, int upperLength) {
		this.type = type;
		this.title = title;
		this.lowerYear = lowerYear;
		this.upperYear = upperYear;
		this.genre = genre;
		this.lowerLength = lowerLength;
		this.upperLength = upperLength;
		dataManager = new DataManager();
		searchMovieControl = new SearchMovieControl(dataManager);
	}
	
	@Before
	public void setUp() throws Exception {
		dataManager.addMovie(MOVIE_TITLE, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH);
	}

	@After
	public void tearDown() throws Exception {
		ArrayList<MovieObject> movie = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
		dataManager.removeMovie(movie.get(0).getMovieId());
	}

	@Test
	public void testValidInput() {
		Assume.assumeTrue(type == Type.VALID);
		ArrayList<MovieObject> expectedList = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
		MovieObject expectedMovie = expectedList.get(0);
		
		ArrayList<MovieObject> movieList = searchMovieControl.processSearchMovie(title, lowerYear, upperYear, genre, lowerLength, upperLength);
		MovieObject testMovie = null;
		
		for(MovieObject movie : movieList) {
			if(movie.equals(expectedMovie)) {
				testMovie = movie;
			}
		}
		assertTrue(testMovie.equals(expectedMovie));
	}
	
	@Test
	public void testInvalidInput() {
		Assume.assumeTrue(type == Type.INVALID);
		boolean isInvalid = false;
		ArrayList<MovieObject> expectedList = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
		MovieObject expectedMovie = expectedList.get(0);
		
		ArrayList<MovieObject> movieList = searchMovieControl.processSearchMovie(title, lowerYear, upperYear, genre, lowerLength, upperLength);
		
		if((movieList == null) || (movieList.size() == 0)) {
			isInvalid = true;
		}
		
		if(!isInvalid) {
			MovieObject testMovie = null;
			
			for(MovieObject movie : movieList) {
				if(movie.equals(expectedMovie)) {
					testMovie = movie;
				}
			}
			
			if(testMovie == null) {
				isInvalid = true;
			}
		}
		assertTrue(isInvalid);
	}
}
