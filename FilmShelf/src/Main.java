public class Main {
	public static void main(String[] args)
	{	
		DataManager dm = new DataManager();
		
		LoginControl loginControl = new LoginControl(dm);
		LoginUI loginUI = new LoginUI(loginControl);
		
		CreateMemberControl createMemberControl = new CreateMemberControl(dm);
		CreateMemberUI createMemberUI = new CreateMemberUI(createMemberControl);
		
		/*EditAccountControl editAccountControl = new EditAccountControl(dm);
		EditAccountUI editAccountUI = new EditAccountUI(editAccountControl);*/
		
		ViewMemberControl viewMemberControl = new ViewMemberControl(dm);
		ViewMemberUI viewMemberUI = new ViewMemberUI(viewMemberControl, loginControl);
		
		MainUI mainUI = new MainUI(loginUI, createMemberUI, viewMemberUI);
		mainUI.setVisible(true);
	}
}