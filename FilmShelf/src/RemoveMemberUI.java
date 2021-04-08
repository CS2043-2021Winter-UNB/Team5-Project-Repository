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
	private JLabel labelAccountStatus;

		
	
	public RemoveMemberUI(RemoveMemberControl controlRemoveAcc) {
		removeMemberControl = controlRemoveAcc;
		labelAccountStatus = new JLabel("");
		labelAccountStatus.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelAccountStatus = new GridBagConstraints();
		gbc_labelAccountStatus.gridwidth = 6;
		gbc_labelAccountStatus.insets = new Insets(0, 0, 5, 5);
		gbc_labelAccountStatus.gridx = 1;
		gbc_labelAccountStatus.gridy = 7;
		add(labelAccountStatus, gbc_labelAccountStatus);
	}
	

	public void displayRemovalMemberWarning(String username) {
		// begin-user-code
		// TODO Auto-generated method stub
		int response=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete acccount?","Remove Confirm",JOptionPane.YES_NO_OPTION);
		if(response==JOptionPane.YES_OPTION) {
			displayMemberRemovedConfirmation();
			removeMemberControl.processRemoveAccount(username); 
		}
		else if(response==JOptionPane.NO_OPTION){
		}
		// end-user-code
	}

	public void displayMemberRemovedConfirmation() {
		// begin-user-code
		// TODO Auto-generated method stub
		labelAccountStatus.setText("Account Removed.");
		// end-user-code
	}
}