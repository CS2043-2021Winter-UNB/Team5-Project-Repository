
/******************************************************************************************************************************
 * Main
 * @author Sharon
 * Description:	Creates the initial control/UI objects and makes the main window (MainUI) visible
 ******************************************************************************************************************************/

public class Main {
	public static void main(String[] args)
	{	
		DataManager dm = new DataManager();
		
		CreateMemberControl createMemberControl = new CreateMemberControl(dm);
		CreateMemberUI createMemberUI = new CreateMemberUI(createMemberControl);
		
		LoginControl loginControl = new LoginControl(dm);
		
		EditMemberControl editMemberControl = new EditMemberControl(dm,loginControl);
		EditMemberUI editMemberUI = new EditMemberUI(editMemberControl);
		
		RemoveMemberControl removeMemberControl = new RemoveMemberControl(dm, loginControl);
		RemoveMemberUI removeMemberUI = new RemoveMemberUI();
		
		ViewMemberControl viewMemberControl = new ViewMemberControl(dm);
		ViewMemberUI viewMemberUI = new ViewMemberUI(viewMemberControl, loginControl,editMemberUI, removeMemberUI);
		
		SearchMemberControl searchMemberControl = new SearchMemberControl(dm);
		SearchMemberUI searchMemberUI = new SearchMemberUI(searchMemberControl);
	
		RemoveMovieControl removeMovieControl = new RemoveMovieControl(dm, loginControl);
		RemoveMovieUI removeMovieUI = new RemoveMovieUI();
		
		LoginUI loginUI = new LoginUI(loginControl);
		
		ViewMovieControl viewMovieControl = new ViewMovieControl(dm);
		ViewMovieUI viewMovieUI = new ViewMovieUI(viewMovieControl, removeMovieUI);
		
		SearchMovieControl searchMovieControl = new SearchMovieControl(dm);
		SearchMovieUI searchMovieUI = new SearchMovieUI(searchMovieControl);
		
		AddMovieControl addMovieControl = new AddMovieControl(dm, loginControl);
		AddMovieUI addMovieUI = new AddMovieUI(addMovieControl);
		
		RateMovieControl rateMovieControl = new RateMovieControl(dm, loginControl);
		RateMovieUI rateMovieUI = new RateMovieUI(rateMovieControl, loginControl, viewMovieControl);
    
		MainUI mainUI = new MainUI(loginUI, loginControl, createMemberUI, editMemberUI, viewMemberUI, searchMemberUI,addMovieUI, rateMovieUI);
		mainUI.setVisible(true);
		loginUI.setMain(mainUI);
	}
}