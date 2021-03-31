
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestValidInput_validateSearchInput_SearchAccountControl {
	@Parameters(name = "{index}: {0}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{"mrbean35000vr"},								// test expected valid input
			{"    mrbean35000vr"},							// test expected valid input with leading whitespace
			{"mrbean35000vr    "},							// test expected valid input with trailing whitespace
			{"    mrbean35000vr    "},						// test expected valid input with leading and trailing whitespace
			{"mrbean35000vrmrbean35000vrmrbean35000vr"},	// test input longer than 25 characters
			{""},											// test empty input
			{"1234567890"},									// test number-only input
			{"alllowercase"},								// test lowercase-only input
			{"ALLUPPERCASE"}								// test uppercase-only input
		});
	}
	
	private DataManager dataManager;
	private SearchMemberControl searchAccountControl;
	private String username;
	
	public TestValidInput_validateSearchInput_SearchAccountControl(String username) {
		this.username = username;
		dataManager = new DataManager();
		searchAccountControl = new SearchMemberControl(dataManager);
	}
	
	@Test
	public void test() {
		assertTrue(searchAccountControl.validateSearchInput(username));
	}
}