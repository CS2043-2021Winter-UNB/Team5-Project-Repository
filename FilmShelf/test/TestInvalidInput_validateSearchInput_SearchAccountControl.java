
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestInvalidInput_validateSearchInput_SearchAccountControl {
	@Parameters(name = "{index}: {0}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{"mrbean35000vr OR 1=1"},						// test sql injection attempt
			{"\" or \"\"=\""},								// test sql injection attempt
			{null}											// test null input
		});
	}
	
	private DataManager dataManager;
	private SearchMemberControl searchAccountControl;
	private String username;
	
	public TestInvalidInput_validateSearchInput_SearchAccountControl(String username) {
		this.username = username;
		dataManager = new DataManager();
		searchAccountControl = new SearchMemberControl(dataManager);
	}
	
	@Test
	public void test() {
		assertFalse(searchAccountControl.validateSearchInput(username));
	}
}