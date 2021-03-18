import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUI extends JPanel {
	private LoginControl loginControl;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JRadioButton radioButtonAdmin;
	private JRadioButton radioButtonMember;
	private JButton loginButton;
	private JLabel labelUsername;
	private JLabel labelPassword;
	private boolean member = true;
	private JLabel labelLoginStatus;

	/**
	 * Create the panel.
	 */
	public LoginUI(LoginControl control) {
		loginControl = control;
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(36dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(11dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(64dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(122dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(53dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		labelUsername = new JLabel("Username");
		labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelUsername, "4, 4, 3, 1");
		
		textFieldUsername = new JTextField();
		add(textFieldUsername, "8, 4, 3, 1, fill, default");
		textFieldUsername.setColumns(10);
		
		labelPassword = new JLabel("Password");
		labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelPassword, "4, 6, 3, 1");
		
		passwordField = new JPasswordField();
		add(passwordField, "8, 6, 3, 1, fill, default");
		
		radioButtonMember = new JRadioButton("Member");
		radioButtonMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member = true;
			}
		});
		radioButtonMember.setHorizontalAlignment(SwingConstants.CENTER);
		radioButtonMember.setSelected(true);
		add(radioButtonMember, "6, 8");
		
		radioButtonAdmin = new JRadioButton("Admin");
		radioButtonAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member = false;
			}
		});
		radioButtonAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		add(radioButtonAdmin, "8, 8");
		
		//make so only one radio button can be selected at a time.
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonMember);
		buttonGroup.add(radioButtonAdmin);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extractLoginCredentials();
			}
		});
		add(loginButton, "8, 12");
		
		labelLoginStatus = new JLabel("");
		labelLoginStatus.setEnabled(false);
		add(labelLoginStatus, "6, 14, 3, 1");
	}
	
	public void displayLoginForm() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
	
	public void extractLoginCredentials()
	{
		String username = textFieldUsername.getText();
		String password = new String(passwordField.getPassword());
		if (member)
		{
			displayFailedLoginMessage();
			//loginControl.processMemberLogin(username, password);
		}
		else
		{
			//loginControl.processAdminLogin(username, password);
		}
	}
	
	public void displayLoginConfirmation() {

	}
	
	public void displayFailedLoginMessage() {
		labelLoginStatus.setText("Login was unsuccessful. Login credentials did not match any existing accounts");
	}

}
