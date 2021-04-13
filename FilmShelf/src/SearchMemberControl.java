
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

/******************************************************************************************************************************
 * SearchAccountControl
 * @author Jo
 * Description:	Handles validation of search input to SearchAccountUI class and initiates searching the MemberAccount table.
 ******************************************************************************************************************************/
public class SearchMemberControl {

	private DataManager dataManager;
	private MemberObject member;
	//private ArrayList<MemberAccountObject> members;
	private Pattern specialCharacterPattern;
	private Matcher specialCharacterMatcher;
	
	public SearchMemberControl(DataManager dataManager) {
		this.dataManager = dataManager;
		this.member = null;
		//members = new ArrayList<MemberAccountObject>();
		this.specialCharacterPattern = Pattern.compile("[^A-Za-z0-9]");		// matches special characters
	}

	// Jo: could return ArrayList if we want to allow for generalized searches (not just exact matches)
	public MemberObject processSearchAccount(String username) {
		// check validity of input
		if((username != null) && validateSearchInput(username.strip())) {
			// search for member if input valid
			member = dataManager.getMember(username.strip());		// Jo: went with this method name, could also go with getMember(username) since we have getMember(username, password)
		} else {
			// input not valid, set member to null
			member = null;
		}
		
		// return search result
		return member;
	}

	// invalid input includes null and strings containing special characters
	private boolean validateSearchInput(String username) {
		// null input is invalid
		if(username == null) {
			return false;
		}
		
		// no special characters allowed
		specialCharacterMatcher = specialCharacterPattern.matcher(username.strip());
		
		if(specialCharacterMatcher.find()) {
			return false;
		}
		
		return true;	// if this line is reached, input is valid
	}
}