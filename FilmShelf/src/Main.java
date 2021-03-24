
public class Main {
	public static void main(String[] args)
	{	
		DataManager dm = new DataManager();
		
		LoginControl loginControl = new LoginControl(dm);
		LoginUI loginUI = new LoginUI(loginControl);
		
		CreateMemberAccountControl createMemberAccountControl = new CreateMemberAccountControl(dm);
		CreateMemberAccountUI createMemberAccountUI = new CreateMemberAccountUI(createMemberAccountControl);
		
		/*EditAccountControl editAccountControl = new EditAccountControl(dm);
		EditAccountUI editAccountUI = new EditAccountUI(editAccountControl);*/
		
		viewAccountControl viewAccControl = new viewAccountControl(dm);
		ViewAccountUI viewAccountUI = new ViewAccountUI(viewAccControl);
		
		MainUI mainUI = new MainUI(loginUI, createMemberAccountUI, viewAccountUI);
		mainUI.setVisible(true);
	}
}

