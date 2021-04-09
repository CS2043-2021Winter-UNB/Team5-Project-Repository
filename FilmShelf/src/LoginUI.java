/******************************************************************************************************************************
 * LoginUI
 * @author Sharon
 * Description:	Displays login form, extracts user input, and displays login confirmation or error.
 ******************************************************************************************************************************/

/******************************************************************************************************************************
 * LoginUI
 * @author Sharon
 * Description:	Displays login form, extracts user input, and displays login confirmation or error.
 ******************************************************************************************************************************/

import javax.swing.JPanel;
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
import javax.swing.JCheckBox;

public class LoginUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginControl loginControl;
	private MainUI mainUI;
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
		gridBagLayout.columnWidths = new int[]{50, 96, 19, 88, 0, 0, 0, 65, 57, 59, 1, 0};
		gridBagLayout.rowHeights = new int[]{ 23, 0, 0, 0, 0, 0, 0, 28, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		//Username label
		labelUsername = new JLabel("Username: ");
		labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelUsername = new GridBagConstraints();
		gbc_labelUsername.anchor = GridBagConstraints.EAST;
		gbc_labelUsername.insets = new Insets(0, 0, 5, 5);
		gbc_labelUsername.gridx = 1;
		gbc_labelUsername.gridy = 3;
		add(labelUsername, gbc_labelUsername);
		
		//Username text field
		textFieldUsername = new JTextField();
		GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
		gbc_textFieldUsername.gridwidth = 6;
		gbc_textFieldUsername.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsername.gridx = 2;
		gbc_textFieldUsername.gridy = 3;
		add(textFieldUsername, gbc_textFieldUsername);
		textFieldUsername.setColumns(10);
		
		//Password label
		labelPassword = new JLabel("Password: ");
		labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelPassword = new GridBagConstraints();
		gbc_labelPassword.anchor = GridBagConstraints.EAST;
		gbc_labelPassword.insets = new Insets(0, 0, 5, 5);
		gbc_labelPassword.gridx = 1;
		gbc_labelPassword.gridy = 4;
		add(labelPassword, gbc_labelPassword);
		
		//Password field
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 6;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 4;
		add(passwordField, gbc_passwordField);
		
		//Password visibility checkbox
		checkboxPasswordVisibility = new JCheckBox("Show Password");
		checkboxPasswordVisibility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePasswordVisibility();
			}
		});
		GridBagConstraints gbc_checkboxPasswordVisibility = new GridBagConstraints();
		gbc_checkboxPasswordVisibility.insets = new Insets(0, 0, 5, 5);
		gbc_checkboxPasswordVisibility.gridx = 8;
		gbc_checkboxPasswordVisibility.gridy = 4;
		add(checkboxPasswordVisibility, gbc_checkboxPasswordVisibility);
		
		//Member radio button
		radioButtonMember = new JRadioButton("Member");
		radioButtonMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member = true;
			}
		});
		radioButtonMember.setHorizontalAlignment(SwingConstants.CENTER);
		radioButtonMember.setSelected(true);
		GridBagConstraints gbc_radioButtonMember = new GridBagConstraints();
		gbc_radioButtonMember.gridwidth = 2;
		gbc_radioButtonMember.anchor = GridBagConstraints.NORTHWEST;
		gbc_radioButtonMember.insets = new Insets(0, 0, 5, 5);
		gbc_radioButtonMember.gridx = 2;
		gbc_radioButtonMember.gridy = 5;
		add(radioButtonMember, gbc_radioButtonMember);
		
		//Admin radio button
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
		gbc_radioButtonAdmin.gridx = 7;
		gbc_radioButtonAdmin.gridy = 5;
		add(radioButtonAdmin, gbc_radioButtonAdmin);
		
		//Add member and admin radio button to group so only one radio button can be selected at a time.
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonMember);
		buttonGroup.add(radioButtonAdmin);
		
		//Login button
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extractLoginCredentials();
			}
		});
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.gridx = 5;
		gbc_loginButton.gridy = 6;
		add(loginButton, gbc_loginButton);
		
		//Login success/error label
		labelLoginStatus = new JLabel("");
		labelLoginStatus.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelLoginStatus = new GridBagConstraints();
		gbc_labelLoginStatus.gridwidth = 8;
		gbc_labelLoginStatus.insets = new Insets(0, 0, 0, 5);
		gbc_labelLoginStatus.gridx = 1;
		gbc_labelLoginStatus.gridy = 7;
		add(labelLoginStatus, gbc_labelLoginStatus);
		
		setVisible(false);
	}
	
	public void setMain(MainUI mainUI) {
		this.mainUI = mainUI;
	}
	
	public void displayLoginForm() {
		//clear login fields/radio button before redisplaying
		checkboxPasswordVisibility.setSelected(false);
		radioButtonMember.setSelected(true);
		labelLoginStatus.setText("");
		
		//display the login UI JPanel
		setVisible(true);
	}
	
	public void extractLoginCredentials() {
		String username = textFieldUsername.getText();
		String password = new String(passwordField.getPassword());
		
		//check if fields are blank
		if (username.trim().isEmpty() || password.trim().isEmpty()) {
			labelLoginStatus.setText("Fields must non-blank to login");
		}
		else {
			boolean loginStatus;
			if (member){
				displayLoginErrorMessage();
				loginStatus = loginControl.processMemberLogin(username, password);
			}
			else{
				loginStatus = loginControl.processAdminLogin(username, password);
			}
			if(loginStatus) {
				mainUI.changeCreateAndLoginButtons();
				setVisible(false);
			
			} else {
				displayLoginErrorMessage();
			}
		}
	}
	
	private void displayLoginErrorMessage() {
		if (member) {
			labelLoginStatus.setText("Login was unsuccessful. Login credentials did not match any existing member accounts.");
		}
		else {
			labelLoginStatus.setText("Login was unsuccessful. Login credentials did not match any existing administrator accounts.");
		}

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
