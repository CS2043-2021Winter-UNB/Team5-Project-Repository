
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************************************************************************************************************
 * EditAccountControl
 * @author Jo
 * Description: Validates received input for editing account and initiates account edit process.
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

	public boolean validateFormInput(MemberAccountObject member, String password, String firstName, String lastName, String description) {
		String pWord = "";
		String fName = "";
		String lName = "";
		String desc = "";
		
		// input is invalid if no new details are provided
		if(((password == null) || password.strip().isEmpty()) &&
			((firstName == null) || firstName.strip().isEmpty() || firstName.equals(member.getFirstName())) &&
			((lastName == null) || lastName.strip().isEmpty() || lastName.equals(member.getLastName())) &&
			((description == null) || (description.equals(member.getDescription())))) {
			return false;
		}
		
		// remove leading and trailing whitespace and store in new variable for non-null parameters
		if(password != null) {
			pWord = password.strip();
		}
		if(firstName != null) {
			fName = firstName.strip();
		}
		if(lastName != null) {
			lName = lastName.strip();
		}
		if(description != null) {
			desc = description.strip();
		}
		
		// check validity of each parameter
		// check password
			// minimum 8 characters
			// contain uppercase letter, lowercase letter, number, special character
		if(!pWord.strip().isEmpty()) {
			uppercaseMatcher = uppercasePattern.matcher(pWord);
			lowercaseMatcher = lowercasePattern.matcher(pWord);
			numberMatcher = numberPattern.matcher(pWord);
			specialCharacterMatcher = specialCharacterPattern.matcher(pWord);
		
			if(!uppercaseMatcher.find() || !lowercaseMatcher.find() || !numberMatcher.find() || !specialCharacterMatcher.find()  || (pWord.length() < 8)) {
				return false;
			}
		}
		
		// check first name
			// minimum 1 character
			// maximum 25 characters
			// no numbers or special characters
		if(!fName.strip().isEmpty() && !fName.equals(member.getFirstName())) {
			numberMatcher = numberPattern.matcher(fName);
			specialCharacterMatcher = specialCharacterPattern.matcher(fName);
		
			if(numberMatcher.find() || specialCharacterMatcher.find() || (fName.length() > 25) || (fName.length() < 1)) {
				return false;
			}
		}
	
		// check last name
			// minimum 1 character
			// maximum 25 characters
			// no numbers or special characters
		if(!lName.strip().isEmpty() && !lName.equals(member.getLastName())) {
			numberMatcher = numberPattern.matcher(lName);
			specialCharacterMatcher = specialCharacterPattern.matcher(lName);
		
			if(numberMatcher.find() || specialCharacterMatcher.find() || (lName.length() > 25) || (lName.length() < 1)) {
				return false;
			}
		}
		
		// check description
			// maximum 280 characters
			// minimum 0 characters
		if(desc.length() > 280) {
			return false;
		}
		
		return true;	// if this line is reached, input is valid
	}

	// Jo:	how to tell UI why edit failed?
	//		UI could check if user is logged in before displaying edit account form and even pass member to Control
	public boolean updateAccount(String password, String firstName, String lastName, String description) {
		boolean currentUserIsMember = false;
		boolean inputIsValid = false;
		boolean accountUpdated = false;
		
		// get current user logged in
		MemberAccountObject member = loginControl.getCurrentMember();
		
		// if member not logged in, return false
		if(member == null) {
			return false;
		}
		
		// check validity of form input
		inputIsValid = validateFormInput(member, password, firstName, lastName, description);
		
		// if input invalid, return false
		if(!inputIsValid) {
			accountUpdated = false;
		} else if(inputIsValid) {		// if input valid, call editAccount from DataManager
			// default values to pass to DataManager in case of null/empty inputs
			String pWord = null;
			String fName = member.getFirstName();
			String lName = member.getLastName();
			String desc = member.getDescription();
			
			// prepare to pass inputs that aren't null/empty
			if(password != null && !password.strip().isEmpty()) {
				pWord = password.strip();
			}
			if(firstName != null && !firstName.strip().isEmpty()) {
				fName = firstName.strip();
			}
			if(lastName != null && !lastName.strip().isEmpty()) {
				lName = lastName.strip();
			}
			if(description != null) {
				desc = description.strip();
			}
			
			accountUpdated = dataManager.editMemberAccount(member.getUsername(), pWord, fName, lName, desc);		// DataManager will have to check for null password
			
			// update logged in member's info in LoginControl
			if(accountUpdated) {
				loginControl.updateCurrentMemberInfo(fName, lName, desc);
			}
		}
	
		return accountUpdated;	
	}
}