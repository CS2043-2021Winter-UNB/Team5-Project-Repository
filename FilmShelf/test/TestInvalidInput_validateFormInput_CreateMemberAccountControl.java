import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestInvalidInput_validateFormInput_CreateMemberAccountControl {
	@Parameters(name = "{index}: {0}, {1}, {2}, {3}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{"mrbean 35000vr", "sp33dRun!", "Robert", "Chadwick"},								// test invalid username with special characters
			{"mrbean350000000000000000vr", "sp33dRun!", "Robert", "Chadwick"},					// test invalid username that is longer than 25 characters
			{"mrb", "sp33dRun!", "Robert", "Chadwick"},											// test invalid username that is shorter than 6 characters
			{"mrbean35000vr", "sp33drun!", "Robert", "Chadwick"},								// test invalid password that does not have an uppercase letter
			{"mrbean35000vr", "SP33DRUN!", "Robert", "Chadwick"},								// test invalid password that does not have a lowercase letter
			{"mrbean35000vr", "speedRun!", "Robert", "Chadwick"},								// test invalid password that does not have a number
			{"mrbean35000vr", "sp33dRun", "Robert", "Chadwick"},								// test invalid password that does not have a special character
			{"mrbean35000vr", "sp33d!", "Robert", "Chadwick"},									// test invalid password that is shorter than 8 characters
			{"mrbean35000vr", "sp33dRun!", "Robert2", "Chadwick"},								// test invalid first name with numbers
			{"mrbean35000vr", "sp33dRun!", "Robert?", "Chadwick"},								// test invalid first name with special characters
			{"mrbean35000vr", "sp33dRun!", "RobertRobertRobertRobertRobert", "Chadwick"},		// test invalid first name longer than 25 characters
			{"mrbean35000vr", "sp33dRun!", "", "Chadwick"},										// test invalid first name shorter than 1 character
			{"mrbean35000vr", "sp33dRun!", "Robert", "Chadwick2"},								// test invalid last name with numbers
			{"mrbean35000vr", "sp33dRun!", "Robert", "Chadwick$"},								// test invalid last name with special characters
			{"mrbean35000vr", "sp33dRun!", "Robert", "ChadwickChadwickChadwickChadwick"},		// test invalid last name longer than 25 characters
			{"mrbean35000vr", "sp33dRun!", "Robert", ""},										// test invalid last name shorter than 1 character
			{null, "sp33dRun!", "Robert", "Chadwick"},											// test null username
			{"mrbean35000vr", null, "Robert", "Chadwick"},										// test null password
			{"mrbean35000vr", "sp33dRun!", null, "Chadwick"},									// test null first name
			{"mrbean35000vr", "sp33dRun!", "Robert", null}										// test null last name
		});
	}
	
	private DataManager dataManager;
	private CreateMemberAccountControl createMemberAccountControl;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public TestInvalidInput_validateFormInput_CreateMemberAccountControl(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		dataManager = new DataManager();
		createMemberAccountControl = new CreateMemberAccountControl(dataManager);
	}
	
	@Test
	public void testValidateFormInput() {
		assertFalse(createMemberAccountControl.validateFormInput(username, password, firstName, lastName));
	}

}