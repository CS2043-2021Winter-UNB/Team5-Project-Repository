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
	private boolean statusConfirm;
	
	public RemoveMemberUI(RemoveMemberControl controlRemoveAcc) {
		removeMemberControl = controlRemoveAcc;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{225, 1, 0};
		gridBagLayout.rowHeights = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		labelAccountStatus = new JLabel("");
		labelAccountStatus.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelAccountStatus = new GridBagConstraints();
		gbc_labelAccountStatus.gridwidth = 5;
		gbc_labelAccountStatus.insets = new Insets(0, 0, 5, 5);
		gbc_labelAccountStatus.gridx = 0;
		gbc_labelAccountStatus.gridy = 5;
		add(labelAccountStatus,gbc_labelAccountStatus);
		
	}
	

	public void displayRemovalMemberWarning(String username) {
		// begin-user-code
		// TODO Auto-generated method stub
		int response=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete acccount?","Remove Confirm",JOptionPane.YES_NO_OPTION);
		if(response==JOptionPane.YES_OPTION) {
			if(!statusConfirm) {
				removeMemberControl.processRemoveAccount(username);
				displayMemberRemovedConfirmation();
			}
			else {
				
			}
			 
		}
		else if(response==JOptionPane.NO_OPTION){	
			statusConfirm=true;
		}
		// end-user-code
	}

	public void displayMemberRemovedConfirmation() {
		// begin-user-code
		// TODO Auto-generated method stub
		labelAccountStatus.setText("Account Removed.");
		statusConfirm=false;
		// end-user-code
	}
	
	public boolean status() {
		return statusConfirm;
	}
}