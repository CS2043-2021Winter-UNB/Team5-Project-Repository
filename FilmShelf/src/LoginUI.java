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
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;

public class LoginUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public LoginUI(LoginControl control) {
		loginControl = control;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 96, 19, 130, 0, 65, 57, 59, 1, 0};
		gridBagLayout.rowHeights = new int[]{ 23, 0, 0, 0, 0, 0, 0, 28, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Color fontColor = UISettings.getFontColor();
		
		//Username label
		labelUsername = new JLabel("Username: ");
		labelUsername.setOpaque(false);
		labelUsername.setForeground(fontColor);
		labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelUsername = new GridBagConstraints();
		gbc_labelUsername.anchor = GridBagConstraints.EAST;
		gbc_labelUsername.insets = new Insets(0, 0, 5, 5);
		gbc_labelUsername.gridx = 1;
		gbc_labelUsername.gridy = 3;
		add(labelUsername, gbc_labelUsername);
		
		//Username text field
		textFieldUsername = new JTextField();
		textFieldUsername.setDocument(new LengthRestrictedDocument(25));
		GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
		gbc_textFieldUsername.gridwidth = 4;
		gbc_textFieldUsername.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsername.gridx = 2;
		gbc_textFieldUsername.gridy = 3;
		add(textFieldUsername, gbc_textFieldUsername);
		textFieldUsername.setColumns(10);
		
		//Password label
		labelPassword = new JLabel("Password: ");
		labelPassword.setOpaque(false);
		labelPassword.setForeground(fontColor);
		labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelPassword = new GridBagConstraints();
		gbc_labelPassword.anchor = GridBagConstraints.EAST;
		gbc_labelPassword.insets = new Insets(0, 0, 5, 5);
		gbc_labelPassword.gridx = 1;
		gbc_labelPassword.gridy = 4;
		add(labelPassword, gbc_labelPassword);
		
		//Password field
		passwordField = new JPasswordField();
		passwordField.setDocument(new LengthRestrictedDocument(25));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 4;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 4;
		add(passwordField, gbc_passwordField);
		
		//Password visibility checkbox
		checkboxPasswordVisibility = new JCheckBox("Show Password");
		checkboxPasswordVisibility.setOpaque(false);
		checkboxPasswordVisibility.setForeground(fontColor);
		checkboxPasswordVisibility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePasswordVisibility();
			}
		});
		GridBagConstraints gbc_checkboxPasswordVisibility = new GridBagConstraints();
		gbc_checkboxPasswordVisibility.insets = new Insets(0, 0, 5, 5);
		gbc_checkboxPasswordVisibility.gridx = 6;
		gbc_checkboxPasswordVisibility.gridy = 4;
		add(checkboxPasswordVisibility, gbc_checkboxPasswordVisibility);
		
		//Member radio button
		radioButtonMember = new JRadioButton("Member");
		radioButtonMember.setOpaque(false);
		radioButtonMember.setForeground(fontColor);
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
		radioButtonAdmin.setOpaque(false);
		radioButtonAdmin.setForeground(fontColor);
		radioButtonAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member = false;
			}
		});
		radioButtonAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_radioButtonAdmin = new GridBagConstraints();
		gbc_radioButtonAdmin.anchor = GridBagConstraints.NORTHWEST;
		gbc_radioButtonAdmin.insets = new Insets(0, 0, 5, 5);
		gbc_radioButtonAdmin.gridx = 5;
		gbc_radioButtonAdmin.gridy = 5;
		add(radioButtonAdmin, gbc_radioButtonAdmin);
		
		//Add member and admin radio button to group so only one radio button can be selected at a time.
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonMember);
		buttonGroup.add(radioButtonAdmin);
		
		panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 6;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{59, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		//Login button
		loginButton = new JButton("Login");
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.anchor = GridBagConstraints.NORTH;
		gbc_loginButton.gridx = 0;
		gbc_loginButton.gridy = 0;
		panel.add(loginButton, gbc_loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extractLoginCredentials();
			}
		});
		
		//Login success/error label
		labelLoginStatus = new JLabel("");
		labelLoginStatus.setForeground(fontColor);
		labelLoginStatus.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelLoginStatus = new GridBagConstraints();
		gbc_labelLoginStatus.gridwidth = 6;
		gbc_labelLoginStatus.insets = new Insets(0, 0, 0, 5);
		gbc_labelLoginStatus.gridx = 1;
		gbc_labelLoginStatus.gridy = 7;
		add(labelLoginStatus, gbc_labelLoginStatus);
		
		setOpaque(false);
		setVisible(false);
	}
	
	public void displayLoginForm() {
		//clear login fields/radio button before redisplaying
		textFieldUsername.setText("");
		passwordField.setText("");
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

		//make the password hide again
		passwordField.setText("");
		if (checkboxPasswordVisibility.isSelected())
		{
			passwordField.setEchoChar('*');
			checkboxPasswordVisibility.setSelected(false);
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
