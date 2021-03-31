public class Main {
	public static void main(String[] args)
	{	
		DataManager dm = new DataManager();
		
		CreateMemberControl createMemberControl = new CreateMemberControl(dm);
		CreateMemberUI createMemberUI = new CreateMemberUI(createMemberControl);
		
		LoginControl loginControl = new LoginControl(dm);
		
		EditMemberControl editMemberControl = new EditMemberControl(dm,loginControl);
		EditMemberUI editMemberUI = new EditMemberUI(editMemberControl);
		
		ViewMemberControl viewMemberControl = new ViewMemberControl(dm);
		ViewMemberUI viewMemberUI = new ViewMemberUI(viewMemberControl, loginControl);
		
		//SearchMemberControl searchMemberControl = new SearchMemberControl(dm);
		//SearchMemberUI searchMemberUI = new SearchMemberUI(searchMemberControl);
	
		LoginUI loginUI = new LoginUI(loginControl,editMemberUI);
		
		
		//SearchMovieControl searchMovieControl = new SearchMovieControl();
		//SearchMovierUI searchMovieUI = new SearchMovieUI(searchMovieControl);
		
		
		MainUI mainUI = new MainUI(loginUI, loginControl, createMemberUI, editMemberUI, viewMemberUI);
		mainUI.setVisible(true);
	}
}