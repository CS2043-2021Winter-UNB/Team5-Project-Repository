import javax.swing.JOptionPane;

public class RemoveMemberUI {
	private RemoveMemberControl removeMemberControl;
	private MainUI mainUI;

	public RemoveMemberUI(RemoveMemberControl controlRemoveAcc) {
		removeMemberControl = controlRemoveAcc;
	}

	public void setMain(MainUI mainUI) {
		this.mainUI = mainUI;
	}

	public void displayRemovalMemberWarning(String username) {
		int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?",
													"Remove Confirm", JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			removeMemberControl.processRemoveAccount(username);
			mainUI.changeAccountButtons();
			mainUI.hideViewMemberUI();
			displayMemberRemovedConfirmation(username);
		}
	}

	public void displayMemberRemovedConfirmation(String username) {
		JOptionPane.showMessageDialog(null, "Account Removed.");
	}
}