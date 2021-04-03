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
 * RemoveMemberControlTest
 * @author Jo
 * Description:
 * 		Tests processRemoveAccount() in RemoveMemberControl for the cases where:
 * 			1. a Member removes their own account
 * 			2. an Admin removes a Member
 ******************************************************************************************************************************/
@RunWith(Enclosed.class)
public class RemoveMemberControlTest {

	// test processRemoveAccount where a Member is removing their own account /////////////////////////////////////////////////
	@RunWith(Parameterized.class)
	public static class MemberRemovingMemberTest {
		// define input types for different tests
		enum Type {VALID, INVALID};
		
		@Parameters(name = "{index}: {0}, {1}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				{Type.VALID, "mrbean35000vr"},				// test expected valid input
				{Type.VALID, "      mrbean35000vr"},		// test valid input with leading whitespace
				{Type.VALID, "mrbean35000vr      "},		// test valid input with trailing whitespace
				{Type.VALID, "    mrbean35000vr    "},		// test valid input with leading and trailing whitespace
				{Type.INVALID, "user404"},			// test expected invalid input on non-existent user
				{Type.INVALID, ""},					// test invalid empty input
				{Type.INVALID, null}				// test invalid null input
			});
		}
		
		private Type type;
		private DataManager dataManager;
		private CreateMemberControl createMemberControl;
		private LoginControl loginControl;
		private RemoveMemberControl removeMemberControl;
		private String username;
		private final String DUMMY_USERNAME = "mrbean35000vr";
		private final String DUMMY_PASSWORD = "sp33dRun!";
		private final String DUMMY_FIRSTNAME = "Robert";
		private final String DUMMY_LASTNAME = "Chadwick";
		
		public MemberRemovingMemberTest(Type type, String username) {
			this.type = type;
			this.username = username;
			dataManager = new DataManager();
			createMemberControl = new CreateMemberControl(dataManager);
			loginControl = new LoginControl(dataManager);
			removeMemberControl = new RemoveMemberControl(dataManager, loginControl);
		}
		
		@Before
		public void setUp() {
			createMemberControl.createMemberAccount(DUMMY_USERNAME, DUMMY_PASSWORD, DUMMY_FIRSTNAME, DUMMY_LASTNAME);
			loginControl.processMemberLogin(DUMMY_USERNAME, DUMMY_PASSWORD);
		}
		
		@After
		public void tearDown() {
			dataManager.removeMember(DUMMY_USERNAME);
		}
		
		@Test
		public void testValidInput() {
			Assume.assumeTrue(type == Type.VALID);		// only run test for input of VALID type
			assertTrue(removeMemberControl.processRemoveAccount(username));
		}
		
		@Test
		public void testInvalidInput() {
			Assume.assumeTrue(type == Type.INVALID);	// only run test for input of INVALID type
			assertFalse(removeMemberControl.processRemoveAccount(username));
		}
	}
	// END OF TEST ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// test processRemoveAccount where an Admin is removing a Member //////////////////////////////////////////////////////////
	@RunWith(Parameterized.class)
	public static class AdminRemovingMemberTest {
		// define input types for different tests
		enum Type {VALID, INVALID};
		
		@Parameters(name = "{index}: {0}, {1}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				{Type.VALID, "mrbean35000vr"},				// test expected valid input
				{Type.VALID, "      mrbean35000vr"},		// test valid input with leading whitespace
				{Type.VALID, "mrbean35000vr      "},		// test valid input with trailing whitespace
				{Type.VALID, "    mrbean35000vr    "},		// test valid input with leading and trailing whitespace
				{Type.INVALID, "user404"},			// test expected invalid input on non-existent user
				{Type.INVALID, ""},					// test invalid empty input
				{Type.INVALID, null}				// test invalid null input
			});
		}
		
		private Type type;
		private DataManager dataManager;
		private CreateMemberControl createMemberControl;
		private LoginControl loginControl;
		private RemoveMemberControl removeMemberControl;
		private String username;
		private final String DUMMY_USERNAME = "mrbean35000vr";
		private final String DUMMY_PASSWORD = "sp33dRun!";
		private final String DUMMY_FIRSTNAME = "Robert";
		private final String DUMMY_LASTNAME = "Chadwick";
		private final String ADMIN_USERNAME = "joboyAdmin";
		private final String ADMIN_PASSWORD = "HeroPizza100";
		
		public AdminRemovingMemberTest(Type type, String username) {
			this.type = type;
			this.username = username;
			dataManager = new DataManager();
			createMemberControl = new CreateMemberControl(dataManager);
			loginControl = new LoginControl(dataManager);
			removeMemberControl = new RemoveMemberControl(dataManager, loginControl);
		}
		
		@Before
		public void setUp() throws Exception {
			createMemberControl.createMemberAccount(DUMMY_USERNAME, DUMMY_PASSWORD, DUMMY_FIRSTNAME, DUMMY_LASTNAME);
			loginControl.processAdminLogin(ADMIN_USERNAME, ADMIN_PASSWORD);
		}
		
		@After
		public void tearDown() throws Exception {
			dataManager.removeMember(DUMMY_USERNAME);
		}
		
		@Test
		public void testValidInput() {
			Assume.assumeTrue(type == Type.VALID);		// only run test for input of VALID type
			assertTrue(removeMemberControl.processRemoveAccount(username));
		}
		
		@Test
		public void testInvalidInput() {
			Assume.assumeTrue(type == Type.INVALID);	// only run test for input of INVALID type
			assertFalse(removeMemberControl.processRemoveAccount(username));
		}
	}
	// END OF TEST ////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

