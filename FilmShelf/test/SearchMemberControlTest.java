
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SearchMemberControlTest {
	// define input types for different tests
	enum Type {VALID, INVALID};
	
	@Parameters(name = "{index}: {0}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{Type.VALID, "mrbean35000vr"},								// test expected valid input
			{Type.VALID, "    mrbean35000vr"},							// test expected valid input with leading whitespace
			{Type.VALID, "mrbean35000vr    "},							// test expected valid input with trailing whitespace
			{Type.VALID, "    mrbean35000vr    "},						// test expected valid input with leading and trailing whitespace
			{Type.VALID, "mrbean35000vrmrbean35000vrmrbean35000vr"},	// test input longer than 25 characters
			{Type.VALID, ""},											// test empty input
			{Type.VALID, "1234567890"},									// test number-only input
			{Type.VALID, "alllowercase"},								// test lowercase-only input
			{Type.VALID, "ALLUPPERCASE"},								// test uppercase-only input
			{Type.INVALID, "mrbean35000vr OR 1=1"},			// test sql injection attempt
			{Type.INVALID, "\" or \"\"=\""},				// test sql injection attempt
			{Type.INVALID, null}							// test null input
		});
	}
	
	private Type type;
	private DataManager dataManager;
	private SearchMemberControl searchAccountControl;
	private String username;
	
	public SearchMemberControlTest(Type type, String username) {
		this.type = type;
		this.username = username;
		dataManager = new DataManager();
		searchAccountControl = new SearchMemberControl(dataManager);
	}
	
	@Test
	public void testValidInput() {
		Assume.assumeTrue(type == Type.VALID);		// only run test for input of VALID type
		assertTrue(searchAccountControl.validateSearchInput(username));
	}
	
	@Test
	public void testInvalidInput() {
		Assume.assumeTrue(type == Type.INVALID);	// only run test for input of INVALID type
		assertFalse(searchAccountControl.validateSearchInput(username));
	}
}