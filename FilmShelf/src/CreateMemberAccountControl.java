/**
 * CreateMemberAccountControl
 * @author Jo
 *
 */
public class CreateMemberAccountControl {

	private DataManager dataManager;
	private LoginControl loginControl;		// Jo: not sure if LoginControl is needed, CreateAccount can be done without the need to login
	
	public CreateMemberAccountControl(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	// Jo: 	needs parameters from CreateAccountForm
	// 		parameters passed as variables or in MemberObject?
	//		minimum parameters needed: username, password, email
	public boolean validateFormInput(String username, String password, String email) {
		boolean result = false;
		
		// check validity of each parameter
		// check username
		
		
		// check password
		
		
		// check email
		
		
		// if input is invalid, return false
		
		
		// if no invalid input detected, return true
		
		
	}
	
	// Jo: return type can be int or boolean
	public int createMemberAccount(String username, String password, String email) {
		boolean isInputValid = false;
		int result = 0;
		
		// check validity of form input
		isInputValid = validateFormInput(username, password, email);
		
		// if input invalid, return false (0)
		if(!isInputValid) {
			result = 0;
		} else if(isInputValid) {		// if input not invalid, call addMemberAccount from DataManager to add new member account
			result = dataManager.addMemberAccount(username, password, email);		// Jo: addMemberAccount should return int or boolean to signal success/failure
		}
		
		return result;
	}

	// Jo:	not sure if needed, message could be stored in CreateMemberAccountUI for display
	public void returnAccountCreationConfirmation() {
		
	}
}