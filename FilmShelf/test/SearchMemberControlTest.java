import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

/******************************************************************************************************************************
 * SearchMemberControlTest
 * @author Jo
 * Description: Tests valid and invalid input to SearchMemberControl
 ******************************************************************************************************************************/
@RunWith(Parameterized.class)
public class SearchMemberControlTest {
	// define input types for different tests
	enum Type {VALID, INVALID};
	
	@Parameters(name = "{index}: {0}, {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{Type.VALID, "mrbean35000vr"},								// test expected valid input
			{Type.VALID, "    mrbean35000vr"},							// test expected valid input with leading whitespace
			{Type.VALID, "mrbean35000vr    "},							// test expected valid input with trailing whitespace
			{Type.VALID, "    mrbean35000vr    "},						// test expected valid input with leading and trailing whitespace
			{Type.INVALID, ""},											// test empty input
			{Type.INVALID, "mrbean35000vr OR 1=1"},			// test sql injection attempt
			{Type.INVALID, "\" or \"\"=\""},				// test sql injection attempt
			{Type.INVALID, null}							// test null input
		});
	}
	
	private Type type;
	private DataManager dataManager;
	private CreateMemberControl createMemberControl;
	private SearchMemberControl searchAccountControl;
	private String username;
	private final String DUMMY_USERNAME = "mrbean35000vr";
	private final String DUMMY_PASSWORD = "sp33dRun!";
	private final String DUMMY_FIRSTNAME = "Robert";
	private final String DUMMY_LASTNAME = "Chadwick";
	
	public SearchMemberControlTest(Type type, String username) {
		this.type = type;
		this.username = username;
		dataManager = new DataManager();
		createMemberControl = new CreateMemberControl(dataManager);
		searchAccountControl = new SearchMemberControl(dataManager);
	}
	
	@Before
	public void setUp() {
		createMemberControl.createMemberAccount(DUMMY_USERNAME, DUMMY_PASSWORD, DUMMY_FIRSTNAME, DUMMY_LASTNAME);
	}
	
	@After
	public void tearDown() {
		dataManager.removeMember(DUMMY_USERNAME);
	}
	
	@Test
	public void testValidInput() {
		Assume.assumeTrue(type == Type.VALID);		// only run test for input of VALID type
		MemberObject expectedMember = dataManager.getMember(DUMMY_USERNAME);
		
		MemberObject member = searchAccountControl.processSearchAccount(username);
		
		assertTrue(member.equals(expectedMember));
	}
	
	@Test
	public void testInvalidInput() {
		Assume.assumeTrue(type == Type.INVALID);	// only run test for input of INVALID type
		boolean isInvalid = false;
		MemberObject expectedMember = null;
		
		MemberObject member = searchAccountControl.processSearchAccount(username);
		
		if(member == null) {
			isInvalid = true;
		}
		
		assertTrue(isInvalid);
	}
}