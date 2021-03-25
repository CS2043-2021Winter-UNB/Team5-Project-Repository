
/******************************************************************************************************************************
 * ViewAccountControl
 * @author Rachel
 * Description:	Handles validation of input to ViewAccountUI class and initiates searching of the movie table.
 ******************************************************************************************************************************/

public class ViewAccountControl {

	
	private DataManager dataManager;
	//private MemberAccountObject vacMemberAccount;
	
	//What is passed in from UI? MemberName? Id?
	public ViewAccountControl(DataManager dm) {
		this.dataManager = dm;
	}
	
	//NB: Data Manager needs getViewAccount() method
	//Do we need a different kind of MemberAccount object to be returned? 
	//vMemberAccount object?
	public MemberAccountObject retrieveAccount(String accountID) {
		
		//Q: Do we need a new method in data manager called getMemberView()? getMember() requires password...
		MemberAccountObject vMemberAccount = dataManager.getMemberView(accountID);
		
		return vMemberAccount;
		
	}
}