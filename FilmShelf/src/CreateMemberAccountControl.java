
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************************************************************************************************************
 * CreateMemberAccountControl
 * @author Jo
 * Description:	Handles validation of input to CreateMemberAccountUI class and initiates adding new Member to MemberAccount table.
 ******************************************************************************************************************************/
public class CreateMemberAccountControl {

	private DataManager dataManager;
	private Pattern uppercasePattern;
	private Pattern lowercasePattern;
	private Pattern numberPattern;
	private Pattern specialCharacterPattern;
	private Matcher uppercaseMatcher;
	private Matcher lowercaseMatcher;
	private Matcher numberMatcher;
	private Matcher specialCharacterMatcher;
	
	// class constructor
	public CreateMemberAccountControl(DataManager dataManager) {
		this.dataManager = dataManager;
		this.uppercasePattern = Pattern.compile("[A-Z]");					// matches uppercase letters
		this.lowercasePattern = Pattern.compile("[a-z]");					// matches lowercase letters
		this.numberPattern = Pattern.compile("[0-9]");						// matches numbers
		this.specialCharacterPattern = Pattern.compile("[^A-Za-z0-9]");		// matches special characters
	}

	// Jo: parameters based on MemberAccount table attributes
	public boolean validateFormInput(String username, String password, String firstName, String lastName) {
		boolean isInputValid = false;
		
		// only verify input if non-null values are provided
		if((username != null) && (password != null) && (firstName != null) && (lastName != null)) {
			// remove leading and trailing whitespace and store in new variable
			String uName = username.strip();
			String pWord = password.strip();
			String fName = firstName.strip();
			String lName = lastName.strip();
		
			// check validity of each parameter
			// check username
				// limit of 25 characters
				// no special symbols
			specialCharacterMatcher = specialCharacterPattern.matcher(uName);
		
			if(specialCharacterMatcher.find() || (uName.length() > 25) || (uName.length() < 6)) {
				isInputValid = false;
			} else {
				isInputValid = true;	// only set true here. After, only set false to avoid false positives
			}
		
			// check password
				// at least 8 characters long
				// contain uppercase letter, lowercase letter, number, special character
			if(isInputValid) {
				uppercaseMatcher = uppercasePattern.matcher(pWord);
				lowercaseMatcher = lowercasePattern.matcher(pWord);
				numberMatcher = numberPattern.matcher(pWord);
				specialCharacterMatcher = specialCharacterPattern.matcher(pWord);
			
				if(!uppercaseMatcher.find() || !lowercaseMatcher.find() || !numberMatcher.find() || !specialCharacterMatcher.find()  || (pWord.length() < 8)) {
					isInputValid = false;
				}
			}
		
			// check first name
				// limit of 25 characters
				// no numbers or special characters
			if(isInputValid) {
				numberMatcher = numberPattern.matcher(fName);
				specialCharacterMatcher = specialCharacterPattern.matcher(fName);
			
				if(numberMatcher.find() || specialCharacterMatcher.find() || (fName.length() > 25) || (fName.length() < 1)) {
					isInputValid = false;
				}
			}
		
			// check last name
				// limit of 25 characters
				// no numbers or special characters
			if(isInputValid) {
				numberMatcher = numberPattern.matcher(lName);
				specialCharacterMatcher = specialCharacterPattern.matcher(lName);
			
				if(numberMatcher.find() || specialCharacterMatcher.find() || (lName.length() > 25) || (lName.length() < 1)) {
					isInputValid = false;
				}
			}
		}
		
		return isInputValid;	// return validity of input
	}
	
	// Jo: 	returns boolean to indicate success/failure
	//		parameters based on MemberAccount table attributes
	public boolean createMemberAccount(String username, String password, String firstName, String lastName) {
		boolean isInputValid = false;
		boolean memberAdded = false;
		
		// check validity of form input
		isInputValid = validateFormInput(username, password, firstName, lastName);
		
		// if input invalid, return false
		if(!isInputValid) {
			memberAdded = false;
		} else if(isInputValid) {		// if input not invalid, call addMemberAccount from DataManager to add new member account
			memberAdded = dataManager.addMemberAccount(username, password, firstName, lastName);		// Jo: addMemberAccount should return boolean to signal success/failure
		}
		
		return memberAdded;
	}
}