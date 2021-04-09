
/******************************************************************************************************************************
 * CreateMemberUI
 * @author Sharon
 * Description:	Displays create member form, extracts user input, and displays account creation confirmation or error
 ******************************************************************************************************************************/


import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CreateMemberUI extends JPanel {

	private CreateMemberControl createMemberControl;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JCheckBox checkboxPasswordVisibility;
	private JLabel labelCreateAccountStatus;

	public CreateMemberUI(CreateMemberControl control) {
		setVisible(false);
		createMemberControl = control;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{71, 0, 100, 0, 0, 0, 48, 0};
		gridBagLayout.rowHeights = new int[]{74, 0, 0, 0, 0, 0, 42, 35, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel labelUsername = new JLabel("Username: ");

		GridBagConstraints gbc_labelUsername = new GridBagConstraints();
		gbc_labelUsername.anchor = GridBagConstraints.EAST;
		gbc_labelUsername.insets = new Insets(0, 0, 5, 5);
		gbc_labelUsername.gridx = 1;
		gbc_labelUsername.gridy = 1;
		add(labelUsername, gbc_labelUsername);
		
		//Username textfield
		textFieldUsername = new JTextField();
		//tooltip to show username requirements
		textFieldUsername.setToolTipText("6-25 characters, no special symbols");
		GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
		gbc_textFieldUsername.gridwidth = 3;
		gbc_textFieldUsername.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsername.gridx = 2;
		gbc_textFieldUsername.gridy = 1;
		add(textFieldUsername, gbc_textFieldUsername);
		textFieldUsername.setColumns(10);
		

		JLabel labelPassword = new JLabel("Password: ");
		GridBagConstraints gbc_labelPassword = new GridBagConstraints();
		gbc_labelPassword.anchor = GridBagConstraints.EAST;
		gbc_labelPassword.insets = new Insets(0, 0, 5, 5);
		gbc_labelPassword.gridx = 1;
		gbc_labelPassword.gridy = 2;
		add(labelPassword, gbc_labelPassword);
		
		//Password field
		passwordField = new JPasswordField();
		passwordField.setToolTipText("8 characters minimum, contain uppercase letter, lowercase letter, number, special character");
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		add(passwordField, gbc_passwordField);
		
		
		//Password visibility checkbox
		checkboxPasswordVisibility = new JCheckBox("Show password");
		checkboxPasswordVisibility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePasswordVisibility();
			}
		});
		GridBagConstraints gbc_checkboxPasswordVisibility = new GridBagConstraints();
		gbc_checkboxPasswordVisibility.insets = new Insets(0, 0, 5, 5);
		gbc_checkboxPasswordVisibility.gridx = 5;
		gbc_checkboxPasswordVisibility.gridy = 2;
		add(checkboxPasswordVisibility, gbc_checkboxPasswordVisibility);
		
		JLabel labelFirstName = new JLabel("First Name: ");
		GridBagConstraints gbc_labelFirstName = new GridBagConstraints();
		gbc_labelFirstName.anchor = GridBagConstraints.EAST;
		gbc_labelFirstName.fill = GridBagConstraints.VERTICAL;
		gbc_labelFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_labelFirstName.gridx = 1;
		gbc_labelFirstName.gridy = 3;
		add(labelFirstName, gbc_labelFirstName);
		
		//First name textfield
		textFieldFirstName = new JTextField();
		GridBagConstraints gbc_textFieldFirstName = new GridBagConstraints();
		gbc_textFieldFirstName.gridwidth = 3;
		gbc_textFieldFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFirstName.gridx = 2;
		gbc_textFieldFirstName.gridy = 3;
		add(textFieldFirstName, gbc_textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		JLabel labelLastName = new JLabel("Last Name: ");
		GridBagConstraints gbc_labelLastName = new GridBagConstraints();
		gbc_labelLastName.anchor = GridBagConstraints.EAST;
		gbc_labelLastName.insets = new Insets(0, 0, 5, 5);
		gbc_labelLastName.gridx = 1;
		gbc_labelLastName.gridy = 4;
		add(labelLastName, gbc_labelLastName);
		
		//Last name textfield
		textFieldLastName = new JTextField();
		GridBagConstraints gbc_textFieldLastName = new GridBagConstraints();
		gbc_textFieldLastName.gridwidth = 3;
		gbc_textFieldLastName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLastName.gridx = 2;
		gbc_textFieldLastName.gridy = 4;
		add(textFieldLastName, gbc_textFieldLastName);
		textFieldLastName.setColumns(10);
		
		//Create account button
		JButton buttonCreateAccount = new JButton("Create Account");
		buttonCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extractAccountInfo();
			}
		});
		GridBagConstraints gbc_buttonCreateAccount = new GridBagConstraints();
		gbc_buttonCreateAccount.gridwidth = 2;
		gbc_buttonCreateAccount.insets = new Insets(0, 0, 5, 5);
		gbc_buttonCreateAccount.gridx = 2;
		gbc_buttonCreateAccount.gridy = 6;
		add(buttonCreateAccount, gbc_buttonCreateAccount);
		
		//Account creation success/error label
		labelCreateAccountStatus = new JLabel("");
		labelCreateAccountStatus.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelCreateAccountStatus = new GridBagConstraints();
		gbc_labelCreateAccountStatus.gridwidth = 5;
		gbc_labelCreateAccountStatus.insets = new Insets(0, 0, 5, 5);
		gbc_labelCreateAccountStatus.gridx = 1;
		gbc_labelCreateAccountStatus.gridy = 7;
		add(labelCreateAccountStatus, gbc_labelCreateAccountStatus);

	}

	
	public void displayCreateAccountForm() {
		//clear text fields before displaying
		textFieldUsername.setText("");
		passwordField.setText("");
		textFieldFirstName.setText("");
		textFieldLastName.setText("");
		labelCreateAccountStatus.setText("");
		
		//make the password not visible when typing
		checkboxPasswordVisibility.setSelected(false);
		changePasswordVisibility();
		
		//display the create account panel
		setVisible(true);
	}
	
	private void extractAccountInfo()
	{
		String username = textFieldUsername.getText();
		String password = new String(passwordField.getPassword());
		String firstName = textFieldFirstName.getText();
		String lastName = textFieldLastName.getText();
		
		//check if fields are empty
		if (username.trim().isEmpty() || password.trim().isEmpty() || firstName.trim().isEmpty() || lastName.trim().isEmpty())
		{
			labelCreateAccountStatus.setText("Fields must non-blank to create an account");
		}
		else {
			if (createMemberControl.createMemberAccount(username,password,firstName,lastName)) {
				displayAccountCreationConfirmation();
			}
			else {
				displayAccountCreationError();
			}
		}
	}

	public void displayAccountCreationConfirmation() {
		labelCreateAccountStatus.setText("Account creation was successful");
	}
	
	private void displayAccountCreationError() {
		labelCreateAccountStatus.setText("Account creation was unsuccessful. Account information was invalid.");
	}
	
	private void changePasswordVisibility() {
		if (checkboxPasswordVisibility.isSelected()) {
			passwordField.setEchoChar((char)0);
		}
		else {
			passwordField.setEchoChar('*');
		}
	}
	
}
