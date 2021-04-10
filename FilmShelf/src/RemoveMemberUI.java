import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;


public class RemoveMemberUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RemoveMemberControl removeMemberControl;
	private MainUI mainUI;
	
	public RemoveMemberUI(RemoveMemberControl controlRemoveAcc) {
		removeMemberControl = controlRemoveAcc;
	}
	
	public void setMain(MainUI mainUI) {
		this.mainUI = mainUI;
	}
	

	public void displayRemovalMemberWarning(String username) {
		int response=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete acccount?","Remove Confirm",JOptionPane.YES_NO_OPTION);
		if(response==JOptionPane.YES_OPTION) {
			
			removeMemberControl.processRemoveAccount(username);
			mainUI.changeAccountButtons();
			displayMemberRemovedConfirmation(username); 
		}
		else if(response==JOptionPane.NO_OPTION){
			
		}
	}

	public void displayMemberRemovedConfirmation(String username) {
		JOptionPane.showMessageDialog(null,"Account Removed.");
	}
	
}