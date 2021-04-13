import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/******************************************************************************************************************************
 * RemoveMemberControlTest
 * @author Jo
 * Description:
 * 		Tests processRemoveMovie() in RemoveMovieControl for the cases where:
 * 			1. a Member attempts to remove a Movie
 * 			2. an Admin removes a Movie
 ******************************************************************************************************************************/
@RunWith(Enclosed.class)
public class RemoveMovieControlTest {

	// test processRemoveMovie where a Member is removing a movie /////////////////////////////////////////////////////////////
	@RunWith(Parameterized.class)
	public static class MemberRemovingMovieTest {
		// define input types for different tests
		enum Type {VALID, INVALID};
		
		@Parameters(name = "{index}: {0}, {1}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				{Type.INVALID, 1}		// test expected invalid case where a member is removing a movie
			});
		}
		
		private Type type;
		private DataManager dataManager;
		private CreateMemberControl createMemberControl;
		private LoginControl loginControl;
		private RemoveMovieControl removeMovieControl;
		private int movieId;
		private final String DUMMY_USERNAME = "mrbean35000vr";
		private final String DUMMY_PASSWORD = "sp33dRun!";
		private final String DUMMY_FIRSTNAME = "Robert";
		private final String DUMMY_LASTNAME = "Chadwick";
		private final String MOVIE_TITLE = "Ferris Bueller''s Day Off";
		private final int MOVIE_YEAR = 1986;
		private final String MOVIE_GENRE = "Comedy";
		private final int MOVIE_LENGTH = 103;
		
		public MemberRemovingMovieTest(Type type, int movieId) {
			this.type = type;
			this.movieId = movieId;
			dataManager = new DataManager();
			createMemberControl = new CreateMemberControl(dataManager);
			loginControl = new LoginControl(dataManager);
			removeMovieControl = new RemoveMovieControl(dataManager, loginControl);
		}
		
		@Before
		public void setUp() {
			dataManager.addMovie(MOVIE_TITLE, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH);
			createMemberControl.createMemberAccount(DUMMY_USERNAME, DUMMY_PASSWORD, DUMMY_FIRSTNAME, DUMMY_LASTNAME);
			loginControl.processMemberLogin(DUMMY_USERNAME, DUMMY_PASSWORD);
		}
		
		@After
		public void tearDown() {
			ArrayList<MovieObject> movieList = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
			MovieObject movie = movieList.get(0);
			dataManager.removeMovie(movie.getMovieId());
			dataManager.removeMember(DUMMY_USERNAME);
		}
		
		@Test
		public void testInvalidInput() {
			Assume.assumeTrue(type == Type.INVALID);	// only run test for input of INVALID type
			assertFalse(removeMovieControl.processRemoveMovie(movieId));
		}
	}
	// END OF TEST ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// test processRemoveMovie where an Admin is removing a Movie /////////////////////////////////////////////////////////////
	@RunWith(Parameterized.class)
	public static class AdminRemovingMovieTest {
		// define input types for different tests
		enum Type {VALID, INVALID};
		
		@Parameters(name = "{index}: {0}, {1}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				{Type.VALID, 0},	// test expected valid case
				{Type.INVALID, 999}		// test expected invalid input on non-existent movie
			});
		}
		
		private Type type;
		private DataManager dataManager;
		private LoginControl loginControl;
		private RemoveMovieControl removeMovieControl;
		private int movieId;
		private final String ADMIN_USERNAME = "joboyAdmin";
		private final String ADMIN_PASSWORD = "HeroPizza100";
		private final String MOVIE_TITLE = "Ferris Bueller''s Day Off";
		private final int MOVIE_YEAR = 1986;
		private final String MOVIE_GENRE = "Comedy";
		private final int MOVIE_LENGTH = 103;
		
		public AdminRemovingMovieTest(Type type, int movieId) {
			this.type = type;
			this.movieId = movieId;
			dataManager = new DataManager();
			loginControl = new LoginControl(dataManager);
			removeMovieControl = new RemoveMovieControl(dataManager, loginControl);
		}
		
		@Before
		public void setUp() throws Exception {
			dataManager.addMovie(MOVIE_TITLE, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH);
			loginControl.processAdminLogin(ADMIN_USERNAME, ADMIN_PASSWORD);
		}
		
		@After
		public void tearDown() throws Exception {
			ArrayList<MovieObject> movieList = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
			if(movieList.size() > 0) {
				MovieObject movie = movieList.get(0);
				dataManager.removeMovie(movie.getMovieId());
			}
		}
		
		@Test
		public void testValidInput() {
			Assume.assumeTrue(type == Type.VALID);		// only run test for input of VALID type
			ArrayList<MovieObject> movieList = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
			MovieObject movie = movieList.get(0);
			assertTrue(removeMovieControl.processRemoveMovie(movie.getMovieId()));
		}
		
		@Test
		public void testInvalidInput() {
			Assume.assumeTrue(type == Type.INVALID);	// only run test for input of INVALID type
			assertFalse(removeMovieControl.processRemoveMovie(movieId));
		}
	}
	// END OF TEST ////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

