import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class EditAccountControlTest_validateFormInput {
	// define input types for different tests
	enum Type {VALID, INVALID};
	
	@Parameters(name = "{index}: {0}, {1}, {2}, {3}, {4}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},								// test valid input with all new values
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "    t4rt4rANDsl@w", "     Joseph", "     Green", "Jones BBQ and Foot Massage (708-224-6191)"},					// test valid input with leading whitespace
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w    ", "Joseph      ", "Green     ", "Jones BBQ and Foot Massage (708-224-6191)"},					// test valid input with trailing whitespace
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "    t4rt4rANDsl@w    ", "     Joseph      ", "     Green     ", "Jones BBQ and Foot Massage (708-224-6191)"},	// test valid input with leading and trailing whitespace
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Jo", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},									// test valid input with same first name
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "Sulaiman", "Jones BBQ and Foot Massage (708-224-6191)"},								// test valid input with same last name
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "Green", "You can't judge a fish by its chips"},										// test valid input with same description
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "Green", ""},																			// test valid input with empty description
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},												// test valid input with empty password
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},										// test valid input with empty first name
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "", "Jones BBQ and Foot Massage (708-224-6191)"},										// test valid input with empty last name
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "", "", null},																					// test valid input with only password, others empty/null
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", null, null, null},																				// test valid input with only password, others null
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "", "Joseph", null, null},																						// test valid input with only first name
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), null, "", "Green", null},																						// test valid input with only last name
			{Type.VALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), null, null, null, "Jones BBQ and Foot Massage (708-224-6191)"},													// test valid input with only description
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDslaw", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},							// test invalid password with no special characters
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "tartarANDsl@w", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},							// test invalid password with no numbers
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4randsl@w", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},							// test invalid password with no uppercase letters
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "T4RT4RANDSL@W", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},							// test invalid password with no lowercase letters
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4r", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},									// test invalid password with less than 8 characters
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "JosephJosephJosephJosephJoseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},	// test invalid first name longer than 25 characters
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph4", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},							// test invalid first name with numbers
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "@Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},							// test invalid first name with special characters
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "GreenGreenGreenGreenGreenGreen", "Jones BBQ and Foot Massage (708-224-6191)"},	// test invalid last name longer than 25 characters
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "Green7", "Jones BBQ and Foot Massage (708-224-6191)"},							// test invalid last name with numbers
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "Green*", "Jones BBQ and Foot Massage (708-224-6191)"},							// test invalid last name with special characters
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "Green*", ""},																	// test invalid input with empty description
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "t4rt4rANDsl@w", "Joseph", "Green", "BLACK.The rumble of a lonely FOGHORN. Low. " +
									"Faint.TITLE: T H E L I G H T H O U S EEXT. ATLANTIC OCEAN - DAYEXTREMELY WIDE SHOT: Fog. Nothing else in sight. Slowly, aSMALL STEAM BOAT emerges:" +
									" A LIGHTHOUSE TENDER. It chugsalong, a tiny blip in a vast ocean. Black smoke puffs fromits crooked chimney."},					// test invalid description longer than 280 characters
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), null, null, null, null},		// test all null parameters
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "", "", "", null},				// test empty password, first name, last name, and null description
			{Type.INVALID, new MemberObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips",new ArrayList<MovieObject>()), "", "Jo", "Sulaiman", null}	// test empty password, same first name and last name, and null description
		});
	}
	
	private Type type;
	private DataManager dataManager;
	private LoginControl loginControl;
	private EditMemberControl editAccountControl;
	private MemberObject member;
	private String password;
	private String firstName;
	private String lastName;
	private String description;
	
	public EditAccountControlTest_validateFormInput(Type type, MemberObject member, String password, String firstName, String lastName, String description) {
		this.type = type;
		this.member = member;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		dataManager = new DataManager();
		editAccountControl = new EditMemberControl(dataManager, loginControl);
	}
	
	@Test
	public void testValidInput() {
		Assume.assumeTrue(type == Type.VALID);		// only run test for input of VALID type
		assertTrue(editAccountControl.validateFormInput(member, password, firstName, lastName, description));
	}
	
	@Test
	public void testInvalidInput() {
		Assume.assumeTrue(type == Type.INVALID);	// only run test for input of INVALID type
		assertFalse(editAccountControl.validateFormInput(member, password, firstName, lastName, description));
	}

}
