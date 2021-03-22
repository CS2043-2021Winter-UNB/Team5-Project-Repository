
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
	
	public MemberAccountObject processMemberLogin(String username, String password) {

		//NB: DIFFERENT processLogin METHOD for Member vs. Admin
		 currentMember = dataManager.getMember(username, password);
		 return currentMember;

	}

	public AdminAccountObject processAdminLogin(String username, String password) {
	
		//NB: DIFFERENT processLogin METHOD for Member vs. Admin
		 currentAdmin = dataManager.getAdmin(username, password);
		 return currentAdmin;

	}
	public MemberAccountObject getCurrentMember() {
	
		return currentMember;
	}

	public AdminAccountObject getCurrentAdmin() {
		return currentAdmin;
	}
}
