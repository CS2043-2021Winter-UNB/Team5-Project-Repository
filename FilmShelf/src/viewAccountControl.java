
public class viewAccountControl {

	private MemberAccountObject vacMemberAccount;
	private DataManager dataManager;

	//What is passed in from UI? MemberName? Id?
	public viewAccountControl(DataManager dm) {
		this.dataManager = dm;
	}
	
	//NB: Data Manager needs getViewAccount() method
	//Do we need a different kind of MemberAccount object to be returned? 
	//vMemberAccount object?
	public MemberAccountObject retrieveAccount(String accountID) {
		
		vacMemberAccount.getMember(accountID);
		
	}
}