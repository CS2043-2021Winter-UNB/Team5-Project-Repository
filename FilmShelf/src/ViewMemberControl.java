
/******************************************************************************************************************************
 * ViewAccountControl
 * @author Rachel
 * Description:	Handles validation of input to ViewAccountUI class and initiates searching of the movie table.
 ******************************************************************************************************************************/

public class ViewMemberControl {

	
	private DataManager dataManager;
	//private MemberAccountObject vacMemberAccount;
	
	//What is passed in from UI? MemberName? Id?
	public ViewMemberControl(DataManager dm) {
		this.dataManager = dm;
	}
	
	//NB: Data Manager needs getViewAccount() method
	//Do we need a different kind of MemberAccount object to be returned? 
	//vMemberAccount object?
	public MemberObject retrieveAccount(String accountID) {
		
		//Q: Do we need a new method in data manager called getMemberView()? getMember() requires password...
		MemberObject vMember = dataManager.getMemberView(accountID);
		
		return vMember;
		
	}
}