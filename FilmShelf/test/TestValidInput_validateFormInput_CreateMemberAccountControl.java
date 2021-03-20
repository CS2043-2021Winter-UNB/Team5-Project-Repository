import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestValidInput_validateFormInput_CreateMemberAccountControl {
	@Parameters(name = "{index}: {0}, {1}, {2}, {3}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{"mrbean35000vr", "sp33dRun!", "Robert", "Chadwick"},											// test valid input
			{"    mrbean35000vr", "     sp33dRun!", "     Robert", "     Chadwick"},						// test valid input with leading whitespace
			{"mrbean35000vr    ", "sp33dRun!      ", "Robert     ", "Chadwick     "},						// test valid input with trailing whitespace
			{"    mrbean35000vr    ", "     sp33dRun!      ", "     Robert     ", "     Chadwick     "}		// test valid input with leading and trailing whitespace
		});
	}
	
	private DataManager dataManager;
	private CreateMemberAccountControl createMemberAccountControl;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public TestValidInput_validateFormInput_CreateMemberAccountControl(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		dataManager = new DataManager();
		createMemberAccountControl = new CreateMemberAccountControl(dataManager);
	}
	
	@Test
	public void test() {
		assertTrue(createMemberAccountControl.validateFormInput(username, password, firstName, lastName));
	}
}
