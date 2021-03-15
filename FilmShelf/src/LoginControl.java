
public class LoginControl {
	//LoginControl needs to have a private reference variable of 
	//currentMember --or-- currentAdmin
	//class to hold the logined customer object
	private MemberAccountObject currentMember;
	private AdminAccountObject currentAdmin;
	private DataManager dataManager;

	public LoginControl(DataManager dm) {
			this.dataManager = dm;
			}
	
	public void processMemberLogin(String username, int password) {
		//CHECK: METHOD: --- IS IT "GETMEMBER?"
		//Q: WHY IS PASSWORD TYPE INT?
		//NB: DIFFERENT processLogin METHOD for Member vs. Admin
		 currentMember = dataManager.getMember(username, password);
		 return currentMember;

	}

	public void processAdminLogin(String username, int password) {
		//CHECK: METHOD: --- IS IT "GETMEMBER?"
		//Q: WHY IS PASSWORD TYPE INT?
		//NB: DIFFERENT processLogin METHOD for Member vs. Admin
		 currentAdmin = dataManager.getAdmin(username, password);
		 return currentAdmin;

	}
	public MemberAccountObject getCurrentMember() {
	
		return currentMember;
	}

	public AdminAccuntObject getCurrentAdmin() {
		return currentAdmin;
	}
}
