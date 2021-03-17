
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
	public boolean validateFormInput(MemberAccountObject member, String username, String password, String firstName, String lastName, String description) {
		String uName = "";
		String pWord = "";
		String fName = "";
		String lName = "";
		String desc = "";
		
		// input is invalid if no new details are provided
		if(((username == null) || username.strip().isEmpty() || username.equals(member.getUsername())) &&
			((password == null) || password.strip().isEmpty()) &&
			((firstName == null) || firstName.strip().isEmpty() || firstName.equals(member.getFirstName())) &&
			((lastName == null) || lastName.strip().isEmpty() || lastName.equals(member.getLastName())) &&
			((description == null) || (description.equals(member.getDescription())))) {
			return false;
		}
		
		// remove leading and trailing whitespace and store in new variable for non-null parameters
		if(username != null) {
			uName = username.strip();
		}
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
		// check username
			// minimum 6 characters
			// maximum 25 characters
			// no special symbols
		if(!uName.strip().isEmpty() && !uName.equals(member.getUsername())) {
			specialCharacterMatcher = specialCharacterPattern.matcher(uName);
			
			if(specialCharacterMatcher.find() || (uName.length() > 25) || (uName.length() < 6)) {
				return false;
			}
		}
		
	
		// check password
			// minimum 8 characters
			// contain uppercase letter, lowercase letter, number, special character
		if(!pWord.strip().isEmpty()) {		// password needs to be handled separately
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
		if(desc.equals(member.getDescription()) || (desc.length() > 280)) {
			return false;
		}
		
		return true;	// if this line is reached, input is valid
	}

	// Jo:	how to tell UI why edit failed?
	//		TODO: if username can be changed, will have to check database to ensure new username doesn't already exist
	// 		TODO: will need special case for changing password, as encrypted passwords can't be decrypted easily
	//		UI could check if user is logged in before displaying edit account form and even pass member to Control
	public boolean updateAccount(String username, String password, String firstName, String lastName, String description) {
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
		inputIsValid = validateFormInput(member, username, password, firstName, lastName, description);
		
		// if input invalid, return false
		if(!inputIsValid) {
			accountUpdated = false;
		} else if(inputIsValid) {		// if input valid, call editAccount from DataManager
			// DataManager will have to check which inputs are null
			// Could also use current value for fields that are not updated
			// Ex. Member only wants to update lastName, autofill other fields with current values
			
			// default values to pass to DataManager in case of null/empty inputs
			String uName = member.getUsername();
			String pWord = null;
			String fName = member.getFirstName();
			String lName = member.getLastName();
			String desc = member.getDescription();
			
			if(username != null) {
				uName = username.strip();
			}
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
			
			accountUpdated = dataManager.editAccount(uName, pWord, fName, lName, desc);
		}
	
		return accountUpdated;	
	}
}