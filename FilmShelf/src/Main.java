public class Main {
	public static void main(String[] args)
	{	
		DataManager dm = new DataManager();
		
		LoginControl loginControl = new LoginControl(dm);
		LoginUI loginUI = new LoginUI(loginControl);
		
		CreateMemberAccountControl createMemberAccountControl = new CreateMemberAccountControl(dm);
		CreateMemberAccountUI createMemberAccountUI = new CreateMemberAccountUI(createMemberAccountControl);
		
		MainUI mainUI = new MainUI(loginUI, createMemberAccountUI);
		mainUI.setVisible(true);
	}
}
