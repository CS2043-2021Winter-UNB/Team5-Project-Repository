public class Main {
	public static void main(String[] args)
	{	
		DataManager dm = new DataManager();
		
		LoginControl loginControl = new LoginControl(dm);
		LoginUI loginUI = new LoginUI(loginControl);
		
		CreateMemberControl createMemberControl = new CreateMemberControl(dm);
		CreateMemberUI createMemberUI = new CreateMemberUI(createMemberControl);
		
		EditMemberControl editMemberControl = new EditMemberControl(dm,loginControl);
		EditMemberUI editMemberUI = new EditMemberUI(editMemberControl);
		
		ViewMemberControl viewMemberControl = new ViewMemberControl(dm);
		ViewMemberUI viewMemberUI = new ViewMemberUI(viewMemberControl, loginControl);
		
		SearchMemberControl searchMemberControl = new SearchMemberControl(dm);
		SearchMemberUI searchMemberUI = new SearchMemberUI(searchMemberControl);
		
		//SearchMovieControl searchMovieControl = new SearchMovieControl();
		//SearchMovierUI searchMovieUI = new SearchMovieUI(searchMovieControl);
		
		
		MainUI mainUI = new MainUI(loginUI, loginControl, createMemberUI, editMemberUI, viewMemberUI);
		mainUI.setVisible(true);
	}
}