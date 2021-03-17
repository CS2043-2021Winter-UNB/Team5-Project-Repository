
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************************************************************************************************************
 * EditAccountControl
 * @author Jo
 * Description: 
 ******************************************************************************************************************************/
public class EditAccountControl {

	private DataManager dataManager;
	private LoginControl loginControl;
	private Pattern uppercasePattern;
	private Pattern lowercasePattern;
	private Pattern numberPattern;
	private Pattern specialCharacterPattern;
	private Matcher uppercaseMatcher;
	private Matcher lowercaseMatcher;
	private Matcher numberMatcher;
	private Matcher specialCharacterMatcher;
	
	// class constructor
	public EditAccountControl(DataManager dataManager, LoginControl loginControl) {
		this.dataManager = dataManager;
		this.loginControl = loginControl;
		this.uppercasePattern = Pattern.compile("[A-Z]");					// matches uppercase letters
		this.lowercasePattern = Pattern.compile("[a-z]");					// matches lowercase letters
		this.numberPattern = Pattern.compile("[0-9]");						// matches numbers
		this.specialCharacterPattern = Pattern.compile("[^A-Za-z0-9]");		// matches special characters
	}

	// may need two validation methods with different signatures
	// one to check member details, one to check admin details
	public boolean validateFormInput(String username, String password, String firstName, String lastName) {
		// input is invalid if any null inputs are provided
		if((username == null) || (password == null) || (firstName == null) || (lastName == null)) {
			return false;
		}
		
		// remove leading and trailing whitespace and store in new variable
		String uName = username.strip();
		String pWord = password.strip();
		String fName = firstName.strip();
		String lName = lastName.strip();
	
		// check validity of each parameter
		// check username
			// minimum 6 characters
			// maximum 25 characters
			// no special symbols
		specialCharacterMatcher = specialCharacterPattern.matcher(uName);
	
		if(specialCharacterMatcher.find() || (uName.length() > 25) || (uName.length() < 6)) {
			return false;
		}
	
		// check password
			// minimum 8 characters
			// contain uppercase letter, lowercase letter, number, special character
		uppercaseMatcher = uppercasePattern.matcher(pWord);
		lowercaseMatcher = lowercasePattern.matcher(pWord);
		numberMatcher = numberPattern.matcher(pWord);
		specialCharacterMatcher = specialCharacterPattern.matcher(pWord);
	
		if(!uppercaseMatcher.find() || !lowercaseMatcher.find() || !numberMatcher.find() || !specialCharacterMatcher.find()  || (pWord.length() < 8)) {
			return false;
		}
	
		// check first name
			// minimum 1 character
			// maximum 25 characters
			// no numbers or special characters
		numberMatcher = numberPattern.matcher(fName);
		specialCharacterMatcher = specialCharacterPattern.matcher(fName);
	
		if(numberMatcher.find() || specialCharacterMatcher.find() || (fName.length() > 25) || (fName.length() < 1)) {
			return false;
		}
	
		// check last name
			// minimum 1 character
			// maximum 25 characters
			// no numbers or special characters
		numberMatcher = numberPattern.matcher(lName);
		specialCharacterMatcher = specialCharacterPattern.matcher(lName);
	
		if(numberMatcher.find() || specialCharacterMatcher.find() || (lName.length() > 25) || (lName.length() < 1)) {
			return false;
		}
		
		return true;	// if this line is reached, input is valid
	}

	// Jo: 	how to determine if Member or Admin wants to edit account?
	//		can we assume only one logged in user stored?
	// 		ie. either currentAdmin or currentMember will be uninitialized
	//		what details can users update?
	//			Member: username, password, firstName, lastName
	//			Admin: username, password
	//		if username can be changed, will have to check database to ensure new username doesn't already exist
	//		could be easier to assume only Members change details via app, Admin have details changed directly in database
	public boolean updateAccount(String username, String password, String firstName, String lastName) {
		boolean currentUserIsMember = false;
		boolean inputIsValid = false;
		boolean accountUpdated = false;
		
		// get current user logged in
		MemberAccountObject member = loginControl.getCurrentMember();
		AdminAccountObject admin = loginControl.getCurrentAdmin();
		
		// check if current user is member or admin
		if((admin == null) && (member == null)) {
			return false;
		} else if(admin == null) {
			currentUserIsMember = true;
		} else if(member == null) {
			currentUserIsMember = false;
		}
		
		// only continue with edit account if at least one new input is provided
		if(((username == null) || username.strip().isEmpty() || username.equals(member.getUsername())) &&
			((password == null) || password.strip().isEmpty() || password.equals(member.getPassword())) &&
			((firstName == null) || firstName.strip().isEmpty() || firstName.equals(member.getFirstName())) &&
			((lastName == null) || lastName.strip().isEmpty() || lastName.equals(member.getLastName()))) {
			return false;
		}
		
		// check which details the member wants to change and which stay the same
		// assume values not provided by user indicate fields that stay the same
		if((username == null) || username.strip().isEmpty()) {
			username = member.getUsername();
		}
		if((password == null) || password.strip().isEmpty()) {
			password = member.getPassword();
		}
		if((firstName == null) || firstName.strip().isEmpty()) {
			firstName = member.getFirstName();
		}
		if((lastName == null) || lastName.strip().isEmpty()) {
			lastName = member.getLastName();
		}
		
		// check validity of form input
		if(currentUserIsMember) {
			inputIsValid = validateFormInput(username, password, firstName, lastName);
		} else if(!currentUserIsMember) {
			inputIsValid = validateFormInput(username, password);
		}
		
		// if input invalid, return false
		if(!inputIsValid) {
			accountUpdated = false;
		} else if(inputIsValid) {		// if input valid, call editAccount from DataManager
			// DataManager will have to check which inputs are null
			// Could also use current value for fields that are not updated
			// Ex. Member only wants to update lastName, autofill other fields with current values
			accountUpdated = dataManager.editAccount(username, password, firstName, lastName);
		}
	
		return accountUpdated;	
	}
}