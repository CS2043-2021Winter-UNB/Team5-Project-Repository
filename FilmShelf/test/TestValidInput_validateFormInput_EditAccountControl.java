import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestValidInput_validateFormInput_EditAccountControl {
	@Parameters(name = "{index}: {0}, {1}, {2}, {3}, {4}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},									// test valid input with all new values
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "    t4rt4rANDsl@w", "     Joseph", "     Green", "Jones BBQ and Foot Massage (708-224-6191)"},					// test valid input with leading whitespace
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w    ", "Joseph      ", "Green     ", "Jones BBQ and Foot Massage (708-224-6191)"},					// test valid input with trailing whitespace
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "    t4rt4rANDsl@w    ", "     Joseph      ", "     Green     ", "Jones BBQ and Foot Massage (708-224-6191)"},		// test valid input with leading and trailing whitespace
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "Jo", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},										// test valid input with same first name
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "Joseph", "Sulaiman", "Jones BBQ and Foot Massage (708-224-6191)"},								// test valid input with same last name
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "Joseph", "Green", "You can't judge a fish by its chips"},										// test valid input with same description
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "Joseph", "Green", ""},																			// test valid input with empty description
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},												// test valid input with empty password
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},										// test valid input with empty first name
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "Joseph", "", "Jones BBQ and Foot Massage (708-224-6191)"},										// test valid input with empty last name
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "", "", null},																					// test valid input with only password, others empty/null
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", null, null, null},																				// test valid input with only password, others null
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "", "Joseph", null, null},																							// test valid input with only first name
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), null, "", "Green", null},																							// test valid input with only last name
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), null, null, null, "Jones BBQ and Foot Massage (708-224-6191)"},													// test valid input with only description
		});
	}
	
	private DataManager dataManager;
	private LoginControl loginControl;
	private EditAccountControl editAccountControl;
	private MemberAccountObject member;
	private String password;
	private String firstName;
	private String lastName;
	private String description;
	
	public TestValidInput_validateFormInput_EditAccountControl(MemberAccountObject member, String password, String firstName, String lastName, String description) {
		this.member = member;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		dataManager = new DataManager();
		editAccountControl = new EditAccountControl(dataManager, loginControl);
	}
	
	@Test
	public void test() {
		assertTrue(editAccountControl.validateFormInput(member, password, firstName, lastName, description));
	}

}
