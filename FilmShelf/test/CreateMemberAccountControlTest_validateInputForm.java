import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CreateMemberAccountControlTest_validateInputForm {
	// define input types for different tests
	enum Type {VALID, INVALID};
	
	@Parameters(name = "{index}: {0}, {1}, {2}, {3}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{Type.VALID, "mrbean35000vr", "sp33dRun!", "Robert", "Chadwick"},											// test valid input
			{Type.VALID, "    mrbean35000vr", "     sp33dRun!", "     Robert", "     Chadwick"},						// test valid input with leading whitespace
			{Type.VALID, "mrbean35000vr    ", "sp33dRun!      ", "Robert     ", "Chadwick     "},						// test valid input with trailing whitespace
			{Type.VALID, "    mrbean35000vr    ", "     sp33dRun!      ", "     Robert     ", "     Chadwick     "},	// test valid input with leading and trailing whitespace
			{Type.INVALID, "mrbean 35000vr", "sp33dRun!", "Robert", "Chadwick"},							// test invalid username with special characters
			{Type.INVALID, "mrbean350000000000000000vr", "sp33dRun!", "Robert", "Chadwick"},				// test invalid username that is longer than 25 characters
			{Type.INVALID, "mrb", "sp33dRun!", "Robert", "Chadwick"},										// test invalid username that is shorter than 6 characters
			{Type.INVALID, "mrbean35000vr", "sp33drun!", "Robert", "Chadwick"},								// test invalid password that does not have an uppercase letter
			{Type.INVALID, "mrbean35000vr", "SP33DRUN!", "Robert", "Chadwick"},								// test invalid password that does not have a lowercase letter
			{Type.INVALID, "mrbean35000vr", "speedRun!", "Robert", "Chadwick"},								// test invalid password that does not have a number
			{Type.INVALID, "mrbean35000vr", "sp33dRun", "Robert", "Chadwick"},								// test invalid password that does not have a special character
			{Type.INVALID, "mrbean35000vr", "sp33d!", "Robert", "Chadwick"},								// test invalid password that is shorter than 8 characters
			{Type.INVALID, "mrbean35000vr", "sp33dRun!", "Robert2", "Chadwick"},							// test invalid first name with numbers
			{Type.INVALID, "mrbean35000vr", "sp33dRun!", "Robert?", "Chadwick"},							// test invalid first name with special characters
			{Type.INVALID, "mrbean35000vr", "sp33dRun!", "RobertRobertRobertRobertRobert", "Chadwick"},		// test invalid first name longer than 25 characters
			{Type.INVALID, "mrbean35000vr", "sp33dRun!", "", "Chadwick"},									// test invalid first name shorter than 1 character
			{Type.INVALID, "mrbean35000vr", "sp33dRun!", "Robert", "Chadwick2"},							// test invalid last name with numbers
			{Type.INVALID, "mrbean35000vr", "sp33dRun!", "Robert", "Chadwick$"},							// test invalid last name with special characters
			{Type.INVALID, "mrbean35000vr", "sp33dRun!", "Robert", "ChadwickChadwickChadwickChadwick"},		// test invalid last name longer than 25 characters
			{Type.INVALID, "mrbean35000vr", "sp33dRun!", "Robert", ""},										// test invalid last name shorter than 1 character
			{Type.INVALID, null, "sp33dRun!", "Robert", "Chadwick"},										// test null username
			{Type.INVALID, "mrbean35000vr", null, "Robert", "Chadwick"},									// test null password
			{Type.INVALID, "mrbean35000vr", "sp33dRun!", null, "Chadwick"},									// test null first name
			{Type.INVALID, "mrbean35000vr", "sp33dRun!", "Robert", null}									// test null last name
		});
	}
	
	private Type type;
	private DataManager dataManager;
	private CreateMemberControl createMemberAccountControl;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public CreateMemberAccountControlTest_validateInputForm(Type type, String username, String password, String firstName, String lastName) {
		this.type = type;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		dataManager = new DataManager();
		createMemberAccountControl = new CreateMemberControl(dataManager);
	}
	
	@Test
	public void testValidInput() {
		Assume.assumeTrue(type == Type.VALID);		// only run test for input of VALID type
		assertTrue(createMemberAccountControl.validateFormInput(username, password, firstName, lastName));
	}
	
	@Test
	public void testInvalidInput() {
		Assume.assumeTrue(type == Type.INVALID);	// only run test for input of INVALID type
		assertFalse(createMemberAccountControl.validateFormInput(username, password, firstName, lastName));
	}

}

