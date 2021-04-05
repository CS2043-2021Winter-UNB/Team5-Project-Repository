
/******************************************************************************************************************************
 * ViewMemberControl
 * @author Rachel
 * Description:	Handles input from ViewMemberUI class and initiates searching of the MemberAccount table.
 ******************************************************************************************************************************/

public class ViewMemberControl {
	
	private DataManager dataManager;
		
	public ViewMemberControl(DataManager dm) {
		this.dataManager = dm;
	}
	
	public MemberObject getMemberAccount(String username) {
		
		//NB: Data Manager needs getViewAccount() method
		MemberObject memberAccount = dataManager.getMember(username);
		
		return memberAccount;	
	}
}