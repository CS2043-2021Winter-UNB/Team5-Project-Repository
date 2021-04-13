
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
		LoginUI loginUI = new LoginUI(loginControl);
		
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

		RateMovieControl rateMovieControl = new RateMovieControl(dm,loginControl);
		RateMovieUI rateMovieUI= new RateMovieUI(rateMovieControl,loginControl,viewMovieControl);

		RemoveReviewControl removeReviewControl = new RemoveReviewControl(dm, loginControl);
		RemoveReviewUI removeReviewUI = new RemoveReviewUI(removeReviewControl);
		
		RemoveRatingControl removeRatingControl = new RemoveRatingControl(dm, loginControl);
		RemoveRatingUI removeRatingUI = new RemoveRatingUI(removeRatingControl);

		EditRatingControl editRatingControl = new EditRatingControl(dm, loginControl);
		EditRatingUI editRatingUI = new EditRatingUI(editRatingControl, loginControl, viewMovieControl, removeRatingUI);
		
		ViewReviewControl viewReviewControl = new ViewReviewControl(dm);
		ViewReviewUI viewReviewUI = new ViewReviewUI(viewReviewControl, removeReviewUI, viewMemberUI,loginControl);
		
		ViewMovieUI viewMovieUI = new ViewMovieUI(loginControl, viewMovieControl, removeMovieUI, addReviewUI, viewReviewUI, rateMovieUI, editRatingUI);
		
		SearchMovieControl searchMovieControl = new SearchMovieControl(dm);
		SearchMovieUI searchMovieUI = new SearchMovieUI(searchMovieControl, viewMovieUI);
		
		AddMovieControl addMovieControl = new AddMovieControl(dm, loginControl);
		AddMovieUI addMovieUI = new AddMovieUI(addMovieControl);
    
		MainUI mainUI = new MainUI(loginUI, loginControl, createMemberUI, editMemberUI, viewMemberUI, searchMemberUI, addMovieUI, searchMovieUI, addReviewUI, viewMovieUI, viewReviewUI);

		removeMemberUI.setMain(mainUI);
		removeMovieUI.setMain(mainUI);
	}
}