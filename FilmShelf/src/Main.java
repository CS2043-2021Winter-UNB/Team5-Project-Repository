
public class Main {
	public static void main(String[] args)
	{	
		DataManager dm = new DataManager();
		
		LoginControl loginControl = new LoginControl(dm);
		LoginUI loginUI = new LoginUI();
		
		MainUI mainUI = new MainUI(loginUI);
		mainUI.setVisible(true);
	}
}
