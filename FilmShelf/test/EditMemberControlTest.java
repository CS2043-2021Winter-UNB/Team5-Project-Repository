import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

/******************************************************************************************************************************
 * EditMemberControlTest
 * @author Jo
 * Description: Tests valid and invalid input to EditMemberControl
 ******************************************************************************************************************************/
@RunWith(Enclosed.class)
public class EditMemberControlTest {

	// test a Member editing their own account ////////////////////////////////////////////////////////////////////////////////
	@RunWith(Parameterized.class)
	public static class MemberEditMemberControlTest {
		// define input types for different tests
		enum Type {VALID, INVALID};
		
		@Parameters(name = "{index}: {0}, {1}, {2}, {3}, {4}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				{Type.VALID, "t4rt4rANDsl@w", "Matt", "Turk", "You can't judge a fish by its chips"},									// test valid input with all new values
				{Type.VALID, "    t4rt4rANDsl@w", "     Matt", "     Turk", "You can't judge a fish by its chips"},						// test valid input with leading whitespace
				{Type.VALID, "t4rt4rANDsl@w    ", "Matt      ", "Turk     ", "You can't judge a fish by its chips"},					// test valid input with trailing whitespace
				{Type.VALID, "    t4rt4rANDsl@w    ", "     Matt      ", "     Turk     ", "You can't judge a fish by its chips"},		// test valid input with leading and trailing whitespace
				{Type.VALID, "t4rt4rANDsl@w", "Robert", "Turk", "You can't judge a fish by its chips"},									// test valid input with same first name
				{Type.VALID, "t4rt4rANDsl@w", "Matt", "Chadwick", "You can't judge a fish by its chips"},								// test valid input with same last name
				{Type.VALID, "t4rt4rANDsl@w", "Matt", "Turk", "Welcome to my page!"},													// test valid input with same description
				{Type.VALID, "t4rt4rANDsl@w", "Matt", "Turk", ""},																		// test valid input with empty description
				{Type.VALID, "", "Matt", "Turk", "You can't judge a fish by its chips"},												// test valid input with empty password
				{Type.VALID, "t4rt4rANDsl@w", "", "Turk", "You can't judge a fish by its chips"},										// test valid input with empty first name
				{Type.VALID, "t4rt4rANDsl@w", "Matt", "", "You can't judge a fish by its chips"},										// test valid input with empty last name
				{Type.VALID, "t4rt4rANDsl@w", "Matt", "Turk", ""},																		// test valid input with empty description
				{Type.VALID, "t4rt4rANDsl@w", "", "", null},																			// test valid input with only password, others empty/null
				{Type.VALID, "t4rt4rANDsl@w", null, null, null},																		// test valid input with only password, others null
				{Type.VALID, "", "Matt", null, null},																					// test valid input with only first name
				{Type.VALID, null, "", "Turk", null},																					// test valid input with only last name
				{Type.VALID, null, null, null, "You can't judge a fish by its chips"},													// test valid input with only description
				{Type.INVALID, "t4rt4rANDslaw", "Matt", "Turk", "You can't judge a fish by its chips"},								// test invalid password with no special characters
				{Type.INVALID, "tartarANDsl@w", "Matt", "Turk", "You can't judge a fish by its chips"},								// test invalid password with no numbers
				{Type.INVALID, "t4rt4randsl@w", "Matt", "Turk", "You can't judge a fish by its chips"},								// test invalid password with no uppercase letters
				{Type.INVALID, "T4RT4RANDSL@W", "Matt", "Turk", "You can't judge a fish by its chips"},								// test invalid password with no lowercase letters
				{Type.INVALID, "t4rt4r", "Matt", "Turk", "You can't judge a fish by its chips"},									// test invalid password with less than 8 characters
				{Type.INVALID, "t4rt4rANDsl@w", "MattMattMattMattMattMattMattMatt", "Turk", "You can't judge a fish by its chips"},	// test invalid first name longer than 25 characters
				{Type.INVALID, "t4rt4rANDsl@w", "Matt4", "Turk", "You can't judge a fish by its chips"},							// test invalid first name with numbers
				{Type.INVALID, "t4rt4rANDsl@w", "@Matt", "Turk", "You can't judge a fish by its chips"},							// test invalid first name with special characters
				{Type.INVALID, "t4rt4rANDsl@w", "Matt", "TurkTurkTurkTurkTurkTurkTurkTurk", "You can't judge a fish by its chips"},	// test invalid last name longer than 25 characters
				{Type.INVALID, "t4rt4rANDsl@w", "Matt", "Turk7", "You can't judge a fish by its chips"},							// test invalid last name with numbers
				{Type.INVALID, "t4rt4rANDsl@w", "Matt", "Turk*", "You can't judge a fish by its chips"},							// test invalid last name with special characters
				{Type.INVALID, "t4rt4rANDsl@w", "Matt", "Turk", "BLACK. The rumble of a lonely FOGHORN. Low. " +
										"Faint. TITLE: T H E L I G H T H O U S E EXT. ATLANTIC OCEAN - DAY EXTREMELY WIDE SHOT: Fog. Nothing else in sight. Slowly, a SMALL STEAM BOAT emerges:" +
										" A LIGHTHOUSE TENDER. It chugs along, a tiny blip in a vast ocean. Black smoke puffs from its crooked chimney."},					// test invalid description longer than 280 characters
				{Type.INVALID, null, null, null, null},			// test all null parameters
				{Type.INVALID, "", "", "", ""},					// test empty password, first name, last name, and empty description
				{Type.INVALID, "", "Robert", "Chadwick", ""}	// test empty password, same first name and last name, and empty description
			});
		}
		
		private Type type;
		private DataManager dataManager;
		private CreateMemberControl createMemberControl;
		private LoginControl loginControl;
		private EditMemberControl editMemberControl;
		private String password;
		private String firstName;
		private String lastName;
		private String description;
		private final String DUMMY_USERNAME = "mrbean35000vr";
		private final String DUMMY_PASSWORD = "sp33dRun!";
		private final String DUMMY_FIRSTNAME = "Robert";
		private final String DUMMY_LASTNAME = "Chadwick";
		
		public MemberEditMemberControlTest(Type type, String password, String firstName, String lastName, String description) {
			this.type = type;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.description = description;
			dataManager = new DataManager();
			createMemberControl = new CreateMemberControl(dataManager);
			loginControl = new LoginControl(dataManager);
			editMemberControl = new EditMemberControl(dataManager, loginControl);
		}
		
		@Before
		public void setUp() throws Exception {
			createMemberControl.createMemberAccount(DUMMY_USERNAME, DUMMY_PASSWORD, DUMMY_FIRSTNAME, DUMMY_LASTNAME);
			loginControl.processMemberLogin(DUMMY_USERNAME, DUMMY_PASSWORD);
		}
		
		@After
		public void tearDown() throws Exception {
			dataManager.removeMember(DUMMY_USERNAME);
		}
		
		@Test
		public void testValidInput() {
			Assume.assumeTrue(type == Type.VALID);		// only run test for input of VALID type
			assertTrue(editMemberControl.updateAccount(password, firstName, lastName, description));
		}
		
		@Test
		public void testInvalidInput() {
			Assume.assumeTrue(type == Type.INVALID);	// only run test for input of INVALID type
			assertFalse(editMemberControl.updateAccount(password, firstName, lastName, description));
		}
	}
	// END OF TEST ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// test an Admin attempting to edit an account ////////////////////////////////////////////////////////////////////////////
	@RunWith(Parameterized.class)
	public static class AdminEditMemberControlTest {
		// define input types for different tests
		enum Type {VALID, INVALID};
		
		@Parameters(name = "{index}: {0}, {1}, {2}, {3}, {4}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				{Type.INVALID, "t4rt4rANDsl@w", "Matt", "Turk", "You can't judge a fish by its chips"},									// test valid input with all new values
			});
		}
		
		private Type type;
		private DataManager dataManager;
		private LoginControl loginControl;
		private EditMemberControl editMemberControl;
		private String password;
		private String firstName;
		private String lastName;
		private String description;
		private final String ADMIN_USERNAME = "joboyAdmin";
		private final String ADMIN_PASSWORD = "HeroPizza100";
		
		public AdminEditMemberControlTest(Type type, String password, String firstName, String lastName, String description) {
			this.type = type;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.description = description;
			dataManager = new DataManager();
			loginControl = new LoginControl(dataManager);
			editMemberControl = new EditMemberControl(dataManager, loginControl);
		}
		
		// Admin are unable to edit account details
		@Test
		public void testInvalidInput() {
			Assume.assumeTrue(type == Type.INVALID);	// only run test for input of INVALID type
			loginControl.processAdminLogin(ADMIN_USERNAME, ADMIN_PASSWORD);
			assertFalse(editMemberControl.updateAccount(password, firstName, lastName, description));
		}
	}
	// END OF TEST ////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
