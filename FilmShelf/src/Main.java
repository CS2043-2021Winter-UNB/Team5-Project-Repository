
/******************************************************************************************************************************
 * Main
 * @author Sharon, Alejandra, Jo
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
		RemoveMemberUI removeMemberUI = new RemoveMemberUI(removeMemberControl);
		
		ViewMemberControl viewMemberControl = new ViewMemberControl(dm);
		ViewMemberUI viewMemberUI = new ViewMemberUI(viewMemberControl, loginControl,editMemberUI, removeMemberUI);
		
		SearchMemberControl searchMemberControl = new SearchMemberControl(dm);
		SearchMemberUI searchMemberUI = new SearchMemberUI(searchMemberControl,viewMemberUI);
	
		RemoveMovieControl removeMovieControl = new RemoveMovieControl(dm, loginControl);
		RemoveMovieUI removeMovieUI = new RemoveMovieUI(removeMovieControl);
		
		AddReviewControl addReviewControl = new AddReviewControl(dm, loginControl);
		AddReviewUI addReviewUI = new AddReviewUI(addReviewControl);
		
		ViewMovieControl viewMovieControl = new ViewMovieControl(dm);
		
		ViewReviewControl viewReviewControl= new ViewReviewControl(dm);
		ViewReviewUI viewReviewUI = new ViewReviewUI();
		
		RateMovieControl rateMovieControl = new RateMovieControl(dm,loginControl);
		RateMovieUI addRatingUI= new RateMovieUI(rateMovieControl,loginControl,viewMovieControl);
		
		ViewMovieUI viewMovieUI = new ViewMovieUI(viewMovieControl, removeMovieUI,loginControl,addReviewUI,addRatingUI,viewReviewUI);
		
		LoginUI loginUI = new LoginUI(loginControl);
		
		SearchMovieControl searchMovieControl = new SearchMovieControl(dm);
		SearchMovieUI searchMovieUI = new SearchMovieUI(searchMovieControl, viewMovieUI);
		
		AddMovieControl addMovieControl = new AddMovieControl(dm, loginControl);
		AddMovieUI addMovieUI = new AddMovieUI(addMovieControl);
    
		MainUI mainUI = new MainUI(loginUI, loginControl, createMemberUI, editMemberUI, viewMemberUI, searchMemberUI, addMovieUI, searchMovieUI,addReviewUI, viewMovieUI);

		mainUI.setVisible(true);
		loginUI.setMain(mainUI);
		removeMemberUI.setMain(mainUI);
	}
}