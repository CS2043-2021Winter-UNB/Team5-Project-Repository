import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestInvalidInput_validateFormInput_EditAccountControl {
	@Parameters(name = "{index}: {0}, {1}, {2}, {3}, {4}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDslaw", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},		// test invalid password with no special characters
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "tartarANDsl@w", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},		// test invalid password with no numbers
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4randsl@w", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},		// test invalid password with no uppercase letters
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "T4RT4RANDSL@W", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},		// test invalid password with no lowercase letters
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4r", "Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},				// test invalid password with less than 8 characters
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "JosephJosephJosephJosephJoseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},		// test invalid first name longer than 25 characters
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "Joseph4", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},		// test invalid first name with numbers
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "@Joseph", "Green", "Jones BBQ and Foot Massage (708-224-6191)"},		// test invalid first name with special characters
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "Joseph", "GreenGreenGreenGreenGreenGreen", "Jones BBQ and Foot Massage (708-224-6191)"},		// test invalid last name longer than 25 characters
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "Joseph", "Green7", "Jones BBQ and Foot Massage (708-224-6191)"},		// test invalid last name with numbers
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "Joseph", "Green*", "Jones BBQ and Foot Massage (708-224-6191)"},		// test invalid last name with special characters
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "t4rt4rANDsl@w", "Joseph", "Green", "BLACK.The rumble of a lonely FOGHORN. Low. " +
									"Faint.TITLE: T H E L I G H T H O U S EEXT. ATLANTIC OCEAN - DAYEXTREMELY WIDE SHOT: Fog. Nothing else in sight. Slowly, aSMALL STEAM BOAT emerges:" +
									" A LIGHTHOUSE TENDER. It chugsalong, a tiny blip in a vast ocean. Black smoke puffs fromits crooked chimney."},										// test invalid description longer than 280 characters
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), null, null, null, null},																// test all null parameters
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "", "", "", null},																		// test empty password, first name, last name, and null description
			{new MemberAccountObject("joboy","Jo","Sulaiman","You can't judge a fish by its chips"), "", "Jo", "Sulaiman", null}															// test empty password, same first name and last name, and null description
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
	
	public TestInvalidInput_validateFormInput_EditAccountControl(MemberAccountObject member, String password, String firstName, String lastName, String description) {
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
		assertFalse(editAccountControl.validateFormInput(member, password, firstName, lastName, description));
	}

}
