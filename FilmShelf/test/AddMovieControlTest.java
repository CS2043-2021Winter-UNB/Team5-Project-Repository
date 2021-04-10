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
 * AddMovieControlTest
 * @author Jo
 * Description: Tests valid and invalid input to AddMovieControl
 ******************************************************************************************************************************/
@RunWith(Enclosed.class)
public class AddMovieControlTest {

	// test Admin adding a new movie //////////////////////////////////////////////////////////////////////////////////////////
	@RunWith(Parameterized.class)
	public static class AdminAddMovieControlTest {
		
		// define input types for different tests
		enum Type {VALID, INVALID};
		
		@Parameters(name = "{index}: {0}, {1}, {2}, {3}, {4}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				{Type.VALID, "Ferris Bueller's Day Off", 1986, "Comedy", 103},			// test expected valid input
				{Type.INVALID, "", 1986, "Comedy", 103},									// test invalid input with empty title
				{Type.INVALID, null, 1986, "Comedy", 103},									// test invalid input with null title
				{Type.INVALID, "Ferris Bueller's Day Off", 0, "Comedy", 103},				// test invalid input with releaseYear <= 0
				{Type.INVALID, "Ferris Bueller's Day Off", 1986, "Coming of Age", 103},		// test invalid input with invalid genre
				{Type.INVALID, "Ferris Bueller's Day Off", 1986, "", 103},					// test invalid input with empty genre
				{Type.INVALID, "Ferris Bueller's Day Off", 1986, null, 103},				// test invalid input with null genre
				{Type.INVALID, "Ferris Bueller's Day Off", 1986, "Comedy", 0},				// test invalid input with length <= 0
				{Type.INVALID, "Ferris Bueller's Day OffFerris Bueller's Day OffFerris Bueller's Day OffFerris Bueller's Day OffFerris Bueller's Day OffFerris Bueller's Day OffFerris Bueller's Day Off",
								1986, "Comedy", 103}										// test invalid input with title > 150 characters
			});
		}
		
		private Type type;
		private DataManager dataManager;
		private LoginControl loginControl;
		private AddMovieControl addMovieControl;
		private String title;
		private int releaseYear;
		private String genre;
		private int length;
		private final String ADMIN_USERNAME = "joboyAdmin";
		private final String ADMIN_PASSWORD = "HeroPizza100";
		
		public AdminAddMovieControlTest(Type type, String title, int releaseYear, String genre, int length) {
			this.type = type;
			this.title = title;
			this.releaseYear = releaseYear;
			this.genre = genre;
			this.length = length;
			dataManager = new DataManager();
			loginControl = new LoginControl(dataManager);
			addMovieControl = new AddMovieControl(dataManager, loginControl);
		}
		
		@Before
		public void setUp() throws Exception {
			loginControl.processAdminLogin(ADMIN_USERNAME, ADMIN_PASSWORD);
		}
	
		@Test
		public void testValidInput() {
			Assume.assumeTrue(type == Type.VALID);
			assertTrue(addMovieControl.processAddMovie(title, releaseYear, genre, length));
			ArrayList<MovieObject> movie = dataManager.getMoviesByKeywords(title.replace("'", "''"), releaseYear, releaseYear, genre, length, length);
			dataManager.removeMovie(movie.get(0).getMovieId());
		}
		
		@Test
		public void testInvalidInput() {
			Assume.assumeTrue(type == Type.INVALID);
			assertFalse(addMovieControl.processAddMovie(title, releaseYear, genre, length));
		}
	}
	// END OF TEST ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// test Member adding a new movie //////////////////////////////////////////////////////////////////////////////////////////
	@RunWith(Parameterized.class)
	public static class MemberAddMovieControlTest {
		
		// define input types for different tests
		enum Type {VALID, INVALID};
		
		@Parameters(name = "{index}: {0}, {1}, {2}, {3}, {4}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				{Type.INVALID, "Ferris Bueller's Day Off", 1986, "Comedy", 103}			// test expected valid input
			});
		}
		
		private Type type;
		private DataManager dataManager;
		private CreateMemberControl createMemberControl;
		private LoginControl loginControl;
		private AddMovieControl addMovieControl;
		private String title;
		private int releaseYear;
		private String genre;
		private int length;
		private final String DUMMY_USERNAME = "mrbean35000vr";
		private final String DUMMY_PASSWORD = "sp33dRun!";
		private final String DUMMY_FIRSTNAME = "Robert";
		private final String DUMMY_LASTNAME = "Chadwick";
		
		public MemberAddMovieControlTest(Type type, String title, int releaseYear, String genre, int length) {
			this.type = type;
			this.title = title;
			this.releaseYear = releaseYear;
			this.genre = genre;
			this.length = length;
			dataManager = new DataManager();
			createMemberControl = new CreateMemberControl(dataManager);
			loginControl = new LoginControl(dataManager);
			addMovieControl = new AddMovieControl(dataManager, loginControl);
		}
		
		@Before
		public void setUp() throws Exception {
			createMemberControl.createMemberAccount(DUMMY_USERNAME, DUMMY_PASSWORD, DUMMY_FIRSTNAME, DUMMY_LASTNAME);
			loginControl.processMemberLogin(DUMMY_USERNAME, DUMMY_PASSWORD);
		}
		
		@Test
		public void testInvalidInput() {
			Assume.assumeTrue(type == Type.INVALID);
			assertFalse(addMovieControl.processAddMovie(title, releaseYear, genre, length));
		}
	}
	// END OF TEST ////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
