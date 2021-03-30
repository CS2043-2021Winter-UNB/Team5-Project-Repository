import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.ArrayList;

@RunWith(Parameterized.class)
public class TestValidInput_validateFormInput_EditAccountControl {
	@Parameters(name = "{index}: {0}, {1}, {2}, {3}, {4}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},									// test valid input with all new values
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "    t4rt4rANDsl@w", "     Joseph", "     Green", "Jones BBQ and Foot Massage (708-224-6191)"},					// test valid input with leading whitespace
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w    ", "Joseph      ", "Green     ", "Jones BBQ and Foot Massage (708-224-6191)"},					// test valid input with trailing whitespace
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "    t4rt4rANDsl@w    ", "     Joseph      ", "     Green     ", "Jones BBQ and Foot Massage (708-224-6191)"},		// test valid input with leading and trailing whitespace
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Jo", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},										// test valid input with same first name
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "Sulaiman", "Jones BBQ and Foot Massage (708-224-6191)"},								// test valid input with same last name
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "Green", "You can't judge a fish by its chips"},										// test valid input with same description
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "Green", ""},																			// test valid input with empty description
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},												// test valid input with empty password
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},										// test valid input with empty first name
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "", "Jones BBQ and Foot Massage (708-224-6191)"},										// test valid input with empty last name
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "", "", null},																					// test valid input with only password, others empty/null
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", null, null, null},																				// test valid input with only password, others null
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "", "Joseph", null, null},																							// test valid input with only first name
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), null, "", "Green", null},																							// test valid input with only last name
			{new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), null, null, null, "Jones BBQ and Foot Massage (708-224-6191)"}													// test valid input with only description
		});
	}
	
	private DataManager dataManager;
	private LoginControl loginControl;
	private EditMemberControl editAccountControl;
	private MemberObject member;
	private String password;
	private String firstName;
	private String lastName;
	private String description;
	
	public TestValidInput_validateFormInput_EditAccountControl(MemberObject member, String password, String firstName, String lastName, String description) {
		this.member = member;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		dataManager = new DataManager();
		editAccountControl = new EditMemberControl(dataManager, loginControl);
	}
	
	@Test
	public void test() {
		assertTrue(editAccountControl.validateFormInput(member, password, firstName, lastName, description));
	}

}
