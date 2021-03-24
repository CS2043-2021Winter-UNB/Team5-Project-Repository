
/******************************************************************************************************************************
 * LoginControl
 * @author Rachel
 * Description:	Handles validation of input to LoginUI class.
 ******************************************************************************************************************************/

public class LoginControl {
	//LoginControl needs to have a private reference variable of 
	//currentMember --or-- currentAdmin
	//class to hold the login.ed customer object
	private MemberAccountObject currentMember;
	private AdminAccountObject currentAdmin;
	private DataManager dataManager;

	public LoginControl(DataManager dm) {
			this.dataManager = dm;
			}
	
	//Should this return a boolean value? Somehow boolean needs to be returned
	//to LoginUI.
	public boolean processMemberLogin(String username, String password) {

		//NB: different processLogin method for Member vs. Admin
		 currentMember = dataManager.getMember(username, password);
		 
		 if (currentMember == null)
			{return false;} 
		 
		else 
			{return true;}

	}

	//Should this return a boolean value? Somehow boolean needs to be returned
	//to LoginUI.
	public boolean processAdminLogin(String username, String password) {
	
		//NB: DIFFERENT processLogin METHOD for Member vs. Admin
		 currentAdmin = dataManager.getAdmin(username, password);
		 
		 if (currentAdmin == null)
		 	{return false;}
		 	
		 else {return true;}

	}
	
	public MemberAccountObject getCurrentMember() {
	
		return currentMember;
	}

	public AdminAccountObject getCurrentAdmin() {
		return currentAdmin;
	}
}
