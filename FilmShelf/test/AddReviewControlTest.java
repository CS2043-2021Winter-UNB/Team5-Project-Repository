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
 * AddReviewControlTest
 * @author Jo
 * Description: Tests valid and invalid input to AddReviewControl
 ******************************************************************************************************************************/
@RunWith(Enclosed.class)
public class AddReviewControlTest {

	// test Admin adding a new review /////////////////////////////////////////////////////////////////////////////////////////
	@RunWith(Parameterized.class)
	public static class AdminAddReviewControlTest {
		
		// define input types for different tests
		enum Type {VALID, INVALID_W_VALID_MOVIE, INVALID_W_INVALID_MOVIE};
		
		@Parameters(name = "{index}: {0}, {1}, {2}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				{Type.INVALID_W_VALID_MOVIE, 0, "10/10 great flick"},		// test invalid case where Admin attempts to add a review
			});
		}
		
		private Type type;
		private DataManager dataManager;
		private LoginControl loginControl;
		private AddReviewControl addReviewControl;
		private int movieId;
		private String reviewText;
		private final String ADMIN_USERNAME = "joboyAdmin";
		private final String ADMIN_PASSWORD = "HeroPizza100";
		private final String MOVIE_TITLE = "Ferris Bueller''s Day Off";
		private final int MOVIE_YEAR = 1986;
		private final String MOVIE_GENRE = "Comedy";
		private final int MOVIE_LENGTH = 103;
		
		public AdminAddReviewControlTest(Type type, int movieId, String reviewText) {
			this.type = type;
			this.movieId = movieId;
			this.reviewText = reviewText;
			dataManager = new DataManager();
			loginControl = new LoginControl(dataManager);
			addReviewControl = new AddReviewControl(dataManager, loginControl);
		}
		
		@Before
		public void setUp() throws Exception {
			dataManager.addMovie(MOVIE_TITLE, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH);
			loginControl.processAdminLogin(ADMIN_USERNAME, ADMIN_PASSWORD);
		}
		
		@After
		public void tearDown() throws Exception {
			ArrayList<MovieObject> movie = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
			dataManager.removeMovie(movie.get(0).getMovieId());
		}
	
		@Test
		public void testInvalidInput() {
			Assume.assumeTrue(type == Type.INVALID_W_VALID_MOVIE);
			ArrayList<MovieObject> movieList = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
			movieId = movieList.get(0).getMovieId();
			assertFalse(addReviewControl.processAddReview(movieId, reviewText));
		}
	}
	// END OF TEST ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// test Member adding a review ////////////////////////////////////////////////////////////////////////////////////////////
	@RunWith(Parameterized.class)
	public static class MemberAddReviewControlTest {
		
		// define input types for different tests
		enum Type {VALID, INVALID_W_VALID_MOVIE, INVALID_W_INVALID_MOVIE};
		
		@Parameters(name = "{index}: {0}, {1}, {2}, {3}, {4}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				{Type.VALID, 0, "All-time classic from the 80's"},			// test expected valid input
				{Type.INVALID_W_VALID_MOVIE, 0, "INT. MTROPOLITAN POLICE STATION. FRONT DESK M DAY\r\n"
						+ "POLICE CONSTABLE NICHOLAS ANGEL bursts through the entrance\r\n"
						+ "of a eity police station and [U+FB02]ashes his warrant card.\r\n"
						+ "MALE VOICE (V.0.}\r\n"
						+ "Police Constable Nicholas Angel.\r\n"
						+ "INT. METROPOLITAN POLICE STATION - DAY\r\n"
						+ "ANGEL strides down a corridor. His collar number reads 777.\r\n"
						+ "MALE VOICE (V.O.)\r\n"
						+ "Born and schooled in London.\r\n"
						+ "Graduated from Canterbury\r\n"
						+ "University in 1993 with a double\r\n"
						+ "[U+FB02]rst in politics and\r\n"
						+ "sociology.\r\n"
						+ "INSERT: ANGEL at training college standing amongst dopey\r\n"
						+ "looking trainees. They wear navy tee shirts and shorts.\r\n"
						+ "MALE VOICE (V.0.}\r\n"
						+ "Attended police training college,\r\n"
						+ "displaying an impressive aptitude\r\n"
						+ "in both field training and\r\n"
						+ "theoretical studies.\r\n"
						+ "INSERT: ANGEL running in riot gear down an alley, dodging\r\n"
						+ "petrol bombs, storming a fake hostage situation, finishing\r\n"
						+ "an exam and holding the paper aloft.\r\n"
						+ "MALE VOICE (V.0.} (cont’d)\r\n"
						+ "Excelled way beyond peers, passed\r\n"
						+ "into the Metropolitan Police\r\n"
						+ "ServiceINSERT: ANGEL surrounded by the same dopey faces as before,\r\n"
						+ "this time in full uniform, at a graduation parade.\r\n"
						+ "MALE VOICE (V.O.)\r\n"
						+ "-and soon proved worth as an\r\n"
						+ "officer. Establishing both a\r\n"
						+ "popularity and an effectiveness in\r\n"
						+ "the communityINSERT: ANGEL talking with elderly people, a Chinese family\r\n"
						+ "in their native tongue, young offenders in a hall.\r\n"
						+ "MALE VOICE (V.0.}\r\n"
						+ "-Furthering his skills with\r\n"
						+ "elective training courses in\r\n"
						+ "advanced driving-\r\n"
						+ "2.\r\n"
						+ "INSERT: ANGEL doing an elaborate skid in a police car.\r\n"
						+ "MALE VOICE (V.O.)\r\n"
						+ "-as well as pioneering the use of\r\n"
						+ "the mountain bicycleINSERT: ANGEL doing an elaborate skid on a police bike.\r\n"
						+ "MALE VOICE (V.O.) (cont’d)\r\n"
						+ "-and raising of[U+FB02]oe morale\r\n"
						+ "with an inventive use of desktop\r\n"
						+ "publishingINSERT: ANGEL pinning up various notices in bright colours;\r\n"
						+ "they read ’BIKE SHED’, ’CANTEEN’, ’HATE CRIMES’.\r\n"
						+ "MALE VOICE (V.O.) (cont’d)\r\n"
						+ "-Also became heavily involved in\r\n"
						+ "many extra curricular activities\r\n"
						+ "and to this day holds the Met\r\n"
						+ "record for the 100 metre dash.\r\n"
						+ "INSERT: ANGEL fencing, doing judo, playing chess, bursts\r\n"
						+ "through a finishing tape at speed.\r\n"
						+ "MALE VOICE (V.0.}\r\n"
						+ "-In 2001 began operations in a\r\n"
						+ "North London armed response unit,\r\n"
						+ "Whiskey, Bravo 7-\r\n"
						+ "INSERT: ANGEL bursts into a stairwell of an apartment block\r\n"
						+ "as part of a heavily armed response team.\r\n"
						+ "MALE VOICE (V.O.)\r\n"
						+ "-and received a bravery award for\r\n"
						+ "efforts in the resolution of\r\n"
						+ "Operation CrackdcwnINSERT: ANGEL storms a room where a wild eyed CRACKHEAD\r\n"
						+ "holds a family hostage with a KALISHNIKOV. ANGEL responds\r\n"
						+ "fast, firing a short burst. His expression is one of shock.\r\n"
						+ "MALE VOICE (V.O.) (cont’d)\r\n"
						+ "In the last twelve months alone,\r\n"
						+ "has received nine special\r\n"
						+ "commendations, achieved the highest\r\n"
						+ "arrest record for any officer in\r\n"
						+ "the borough and sustained three\r\n"
						+ "injuries in the line of duty, most\r\n"
						+ "recently in December when wounded\r\n"
						+ "by a man dressed as Eather\r\n"
						+ "Christmas."},					// test invalid input with review length > 2000
				{Type.INVALID_W_INVALID_MOVIE, 99999, "terrible"},									// test invalid input with null title
			});
		}
		
		private Type type;
		private DataManager dataManager;
		private LoginControl loginControl;
		private AddReviewControl addReviewControl;
		private int movieId;
		private String reviewText;
		private final String DUMMY_USERNAME = "mrbean35000vr";
		private final String DUMMY_PASSWORD = "sp33dRun!";
		private final String DUMMY_FIRSTNAME = "Robert";
		private final String DUMMY_LASTNAME = "Chadwick";
		private final String MOVIE_TITLE = "Ferris Bueller''s Day Off";
		private final int MOVIE_YEAR = 1986;
		private final String MOVIE_GENRE = "Comedy";
		private final int MOVIE_LENGTH = 103;
		
		public MemberAddReviewControlTest(Type type, int movieId, String reviewText) {
			this.type = type;
			this.movieId = movieId;
			this.reviewText = reviewText;
			dataManager = new DataManager();
			loginControl = new LoginControl(dataManager);
			addReviewControl = new AddReviewControl(dataManager, loginControl);
		}
		
		@Before
		public void setUp() throws Exception {
			dataManager.addMovie(MOVIE_TITLE, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH);
			dataManager.addMember(DUMMY_USERNAME, DUMMY_PASSWORD, DUMMY_FIRSTNAME, DUMMY_LASTNAME, "Test");
			loginControl.processMemberLogin(DUMMY_USERNAME, DUMMY_PASSWORD);
		}
		
		@After
		public void tearDown() throws Exception {
			ArrayList<MovieObject> movie = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
			dataManager.removeMovie(movie.get(0).getMovieId());
			dataManager.removeMember(DUMMY_USERNAME);
		}
	
		@Test
		public void testValidInput() {
			Assume.assumeTrue(type == Type.VALID);
			ArrayList<MovieObject> movieList = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
			movieId = movieList.get(0).getMovieId();
			assertTrue(addReviewControl.processAddReview(movieId, reviewText));
		}
		
		@Test
		public void testInvalidInputWithValidMovie() {
			Assume.assumeTrue(type == Type.INVALID_W_VALID_MOVIE);
			ArrayList<MovieObject> movieList = dataManager.getMoviesByKeywords(MOVIE_TITLE, MOVIE_YEAR, MOVIE_YEAR, MOVIE_GENRE, MOVIE_LENGTH, MOVIE_LENGTH);
			movieId = movieList.get(0).getMovieId();
			assertFalse(addReviewControl.processAddReview(movieId, reviewText));
		}
		
		@Test
		public void testInvalidInputWithInvalidMovie() {
			Assume.assumeTrue(type == Type.INVALID_W_INVALID_MOVIE);
			assertFalse(addReviewControl.processAddReview(movieId, reviewText));
		}
	}
	// END OF TEST ////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
