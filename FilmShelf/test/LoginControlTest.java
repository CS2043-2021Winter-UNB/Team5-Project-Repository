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

@RunWith(Enclosed.class)
public class LoginControlTest {

	// test processMemberLogin()
	@RunWith(Parameterized.class)
	public static class MemberLoginTest {
		// define input types for different tests
		enum Type {VALID, INVALID};
		
		@Parameters(name = "{index}: {0}, {1}, {2}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				{Type.VALID, "mrbean35000vr", "sp33dRun!"},						// expected valid input
				{Type.VALID, "       mrbean35000vr", "       sp33dRun!"},		// expected valid input with leading whitespace
				{Type.VALID, "mrbean35000vr       ", "sp33dRun!       "},		// expected valid input with trailing whitespace
				{Type.VALID, "     mrbean35000vr     ", "     sp33dRun!     "},	// expected valid input with leading and trailing whitespace
				{Type.INVALID, "bean", "sp33dRun!"},				// expected invalid input with nonexistent member
				{Type.INVALID, "mrbean35000vr", "wrongPassword"},	// expected invalid input with wrong password
				{Type.INVALID, "", "sp33dRun!"},					// expected invalid input with empty member
				{Type.INVALID, "mrbean35000vr", ""},				// expected invalid input with empty password
				{Type.INVALID, null, "sp33dRun!"},					// expected invalid input with null member
				{Type.INVALID, "mrbean35000vr", null}				// expected invalid input with null password
			});
		}
		
		// variable declaration
		private Type type;
		private DataManager dataManager;
		private CreateMemberControl createMemberControl;
		private LoginControl loginControl;
		private String username;
		private String password;
		
		// test constructor
		public MemberLoginTest(Type type, String username, String password) {
			this.type = type;
			this.username = username;
			this.password = password;
			dataManager = new DataManager();
			createMemberControl = new CreateMemberControl(dataManager);
			loginControl = new LoginControl(dataManager);
		}
		
		@Before
		public void setUp() {
			createMemberControl.createMemberAccount("mrbean35000vr", "sp33dRun!", "Robert", "Chadwick");
		}
		
		@After
		public void tearDown() {
			dataManager.removeMember("mrbean35000vr");
		}
		
		// test valid input
		@Test
		public void testValidMemberLogin() {
			Assume.assumeTrue(type == Type.VALID);		// only run test for input of VALID type
			assertTrue(loginControl.processMemberLogin(username, password));
		}
		
		// test invalid input
		@Test
		public void testInvalidMemberLogin() {
			Assume.assumeTrue(type == Type.INVALID);		// only run test for input of VALID type
			assertFalse(loginControl.processMemberLogin(username, password));
		}
	}
	
	// test processAdminLogin()
	@RunWith(Parameterized.class)
	public static class AdminLoginTest {
		// define input types for different tests
		enum Type {VALID, INVALID};
		
		@Parameters(name = "{index}: {0}, {1}, {2}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				{Type.VALID, "joboyAdmin", "HeroPizza100"},						// expected valid input
				{Type.VALID, "       joboyAdmin", "       HeroPizza100"},		// expected valid input with leading whitespace
				{Type.VALID, "joboyAdmin       ", "HeroPizza100       "},		// expected valid input with trailing whitespace
				{Type.VALID, "     joboyAdmin     ", "     HeroPizza100     "},	// expected valid input with leading and trailing whitespace
				{Type.INVALID, "joboy", "HeroPizza100"},				// expected invalid input with nonexistent member
				{Type.INVALID, "joboyAdmin", "wrongPassword"},	// expected invalid input with wrong password
				{Type.INVALID, "", "HeroPizza100"},					// expected invalid input with empty member
				{Type.INVALID, "joboyAdmin", ""},				// expected invalid input with empty password
				{Type.INVALID, null, "HeroPizza100"},					// expected invalid input with null member
				{Type.INVALID, "joboyAdmin", null}				// expected invalid input with null password
			});
		}
		
		// variable declaration
		private Type type;
		private DataManager dataManager;
		private CreateMemberControl createMemberControl;
		private LoginControl loginControl;
		private String username;
		private String password;
		
		// test constructor
		public AdminLoginTest(Type type, String username, String password) {
			this.type = type;
			this.username = username;
			this.password = password;
			dataManager = new DataManager();
			createMemberControl = new CreateMemberControl(dataManager);
			loginControl = new LoginControl(dataManager);
		}
		
		// test valid input
		@Test
		public void testValidMemberLogin() {
			Assume.assumeTrue(type == Type.VALID);		// only run test for input of VALID type
			assertTrue(loginControl.processAdminLogin(username, password));
		}
		
		// test invalid input
		@Test
		public void testInvalidMemberLogin() {
			Assume.assumeTrue(type == Type.INVALID);		// only run test for input of VALID type
			assertFalse(loginControl.processAdminLogin(username, password));
		}
	}
}
