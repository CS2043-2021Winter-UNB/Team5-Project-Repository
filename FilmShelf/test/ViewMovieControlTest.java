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
 * ViewMovieControlTest
 * @author Jo
 * Description: Tests valid and invalid input to ViewMovieControl
 ******************************************************************************************************************************/
@RunWith(Parameterized.class)
public class ViewMovieControlTest {
	// define input types for different tests
	enum Type {VALID, INVALID};
	
	@Parameters(name = "{index}: {0}, {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{Type.VALID, 0},	// test expected valid case
			{Type.INVALID, 99999}		// test expected invalid input on non-existent movie
		});
	}
	
	private Type type;
	private DataManager dataManager;
	private ViewMovieControl viewMovieControl;
	private int movieId;
	private final String MOVIE_TITLE = "Ferris Bueller''s Day Off";
	private final int MOVIE_YEAR = 1986;
	private final String MOVIE_GENRE = "Comedy";
	private final int MOVIE_LENGTH = 103;
	
	public ViewMovieControlTest(Type type, int movieId) {
		this.type = type;
		this.movieId = movieId;
		dataManager = new DataManager();
		viewMovieControl = new ViewMovieControl(dataManager);
	}
	
	@Before
	public void setUp() {
		dataManager.addMovie(MOVIE_TITLE, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH);
	}
	
	@After
	public void tearDown() {
		ArrayList<MovieObject> movie = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
		dataManager.removeMovie(movie.get(0).getMovieId());
	}
	
	@Test
	public void testValidInput() {
		Assume.assumeTrue(type == Type.VALID);		// only run test for input of VALID type
		ArrayList<MovieObject> expectedList = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
		MovieObject expectedMovie = expectedList.get(0);
		
		ArrayList<MovieObject> movieList = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
		MovieObject movie = viewMovieControl.processViewMovie(movieList.get(0).getMovieId());
		
		assertTrue(movie.equals(expectedMovie));
	}
	
	@Test
	public void testInvalidInput() {
		Assume.assumeTrue(type == Type.INVALID);	// only run test for input of INVALID type
		boolean isInvalid = false;
		ArrayList<MovieObject> expectedList = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
		MovieObject expectedMovie = expectedList.get(0);
		
		MovieObject movie = viewMovieControl.processViewMovie(movieId);
		
		if(movie == null) {
			isInvalid = true;
		} else {
			isInvalid = !movie.equals(expectedMovie);
		}
		
		assertTrue(isInvalid);
	}
}