
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************************************************************************************************************
 * CreateMemberAccountControl
 * @author Jo
 * Description:	Handles validation of input to CreateMemberAccountUI class and initiates adding new Member to MemberAccount table.
 ******************************************************************************************************************************/
public class CreateMemberControl {

	private DataManager dataManager;
	private Pattern uppercasePattern;
	private Pattern lowercasePattern;
	private Pattern numberPattern;
	private Pattern specialCharacterPattern;
	private Pattern namePattern;
	private Matcher uppercaseMatcher;
	private Matcher lowercaseMatcher;
	private Matcher numberMatcher;
	private Matcher specialCharacterMatcher;
	private Matcher nameMatcher;
	
	// class constructor
	public CreateMemberControl(DataManager dataManager) {
		this.dataManager = dataManager;
		this.uppercasePattern = Pattern.compile("[A-Z]");					// matches uppercase letters
		this.lowercasePattern = Pattern.compile("[a-z]");					// matches lowercase letters
		this.numberPattern = Pattern.compile("[0-9]");						// matches numbers
		this.specialCharacterPattern = Pattern.compile("[^A-Za-z0-9]");		// matches special characters
		this.namePattern = Pattern.compile("[^A-Za-z0-9\\-]");				// matches special characters except -
	}

	// Jo: parameters based on MemberAccount table attributes
	private boolean validateFormInput(String username, String password, String firstName, String lastName) {
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
		nameMatcher = namePattern.matcher(fName);
	
		if(numberMatcher.find() || nameMatcher.find() || (fName.length() > 25) || (fName.length() < 1)) {
			return false;
		}
	
		// check last name
			// minimum 1 character
			// maximum 25 characters
			// no numbers or special characters
		numberMatcher = numberPattern.matcher(lName);
		nameMatcher = namePattern.matcher(lName);
	
		if(numberMatcher.find() || nameMatcher.find() || (lName.length() > 25) || (lName.length() < 1)) {
			return false;
		}
		
		return true;	// if this line is reached, input is valid
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
			// remove leading and trailing whitespace and store in new variables
			String uName = username.strip();
			String pWord = password.strip();
			String fName = firstName.strip();
			String lName = lastName.strip();
			String description = "Welcome to my page!";
			memberAdded = dataManager.addMember(uName, pWord, fName, lName, description);		// Jo: addMemberAccount should return boolean to signal success/failure
		}
		
		return memberAdded;
	}
}