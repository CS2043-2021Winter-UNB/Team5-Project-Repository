
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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;

public class LoginUI extends JPanel {
	private LoginControl loginControl;
	private JRadioButton radioButtonAdmin;
	private JRadioButton radioButtonMember;
	private JButton loginButton;
	private JLabel labelUsername;
	private JLabel labelPassword;
	private boolean member = true;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JLabel labelLoginStatus;
	private JCheckBox checkboxPasswordVisibility;

	/**
	 * Create the panel.
	 */
	public LoginUI(LoginControl control) {
		loginControl = control;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 96, 91, 7, 65, 57, 59, 1, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 28, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		labelUsername = new JLabel("Username");
		labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelUsername = new GridBagConstraints();
		gbc_labelUsername.anchor = GridBagConstraints.EAST;
		gbc_labelUsername.insets = new Insets(0, 0, 5, 5);
		gbc_labelUsername.gridx = 1;
		gbc_labelUsername.gridy = 3;
		add(labelUsername, gbc_labelUsername);
		
		textFieldUsername = new JTextField();
		GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
		gbc_textFieldUsername.gridwidth = 3;
		gbc_textFieldUsername.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsername.gridx = 2;
		gbc_textFieldUsername.gridy = 3;
		add(textFieldUsername, gbc_textFieldUsername);
		textFieldUsername.setColumns(10);
		
		labelPassword = new JLabel("Password");
		labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelPassword = new GridBagConstraints();
		gbc_labelPassword.anchor = GridBagConstraints.EAST;
		gbc_labelPassword.insets = new Insets(0, 0, 5, 5);
		gbc_labelPassword.gridx = 1;
		gbc_labelPassword.gridy = 4;
		add(labelPassword, gbc_labelPassword);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 4;
		add(passwordField, gbc_passwordField);
		
		radioButtonMember = new JRadioButton("Member");
		radioButtonMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member = true;
			}
		});
		
		checkboxPasswordVisibility = new JCheckBox("Show Password");
		checkboxPasswordVisibility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePasswordVisibility();
			}
		});
		GridBagConstraints gbc_checkboxPasswordVisibility = new GridBagConstraints();
		gbc_checkboxPasswordVisibility.insets = new Insets(0, 0, 5, 5);
		gbc_checkboxPasswordVisibility.gridx = 5;
		gbc_checkboxPasswordVisibility.gridy = 4;
		add(checkboxPasswordVisibility, gbc_checkboxPasswordVisibility);
		
		radioButtonMember.setHorizontalAlignment(SwingConstants.CENTER);
		radioButtonMember.setSelected(true);
		GridBagConstraints gbc_radioButtonMember = new GridBagConstraints();
		gbc_radioButtonMember.gridwidth = 2;
		gbc_radioButtonMember.anchor = GridBagConstraints.NORTHWEST;
		gbc_radioButtonMember.insets = new Insets(0, 0, 5, 5);
		gbc_radioButtonMember.gridx = 2;
		gbc_radioButtonMember.gridy = 5;
		add(radioButtonMember, gbc_radioButtonMember);
		
		radioButtonAdmin = new JRadioButton("Admin");
		radioButtonAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member = false;
			}
		});
		radioButtonAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_radioButtonAdmin = new GridBagConstraints();
		gbc_radioButtonAdmin.anchor = GridBagConstraints.NORTHWEST;
		gbc_radioButtonAdmin.insets = new Insets(0, 0, 5, 5);
		gbc_radioButtonAdmin.gridx = 4;
		gbc_radioButtonAdmin.gridy = 5;
		add(radioButtonAdmin, gbc_radioButtonAdmin);
		
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
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.gridx = 3;
		gbc_loginButton.gridy = 6;
		add(loginButton, gbc_loginButton);
		
		labelLoginStatus = new JLabel("");
		labelLoginStatus.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelLoginStatus = new GridBagConstraints();
		gbc_labelLoginStatus.gridwidth = 5;
		gbc_labelLoginStatus.insets = new Insets(0, 0, 0, 5);
		gbc_labelLoginStatus.gridx = 1;
		gbc_labelLoginStatus.gridy = 7;
		add(labelLoginStatus, gbc_labelLoginStatus);

	}
	
	public void displayLoginForm() {
		//TO DO: clear login fields/radio button here before redisplaying
		setVisible(true);
	}
	
	private void extractLoginCredentials() {
		String username = textFieldUsername.getText();
		String password = new String(passwordField.getPassword());
		boolean loginStatus;
		if (member)
		{
			displayLoginErrorMessage();
			//loginStatus = loginControl.processMemberLogin(username, password);
		}
		else
		{
			//loginStatus = loginControl.processAdminLogin(username, password);
		}
	}
	
	public void displayLoginConfirmation() {
        
	}
	
	private void displayLoginErrorMessage() {
		labelLoginStatus.setText("Login was unsuccessful. Login credentials did not match any existing accounts");
		passwordField.setText("");
		if (checkboxPasswordVisibility.isSelected())
		{
			passwordField.setEchoChar('*');
		}
	}
	
	private void changePasswordVisibility() {
		if (checkboxPasswordVisibility.isSelected()) {
			passwordField.setEchoChar((char)0);
		}
		else
		{
			passwordField.setEchoChar('*');
		}
	}
}
