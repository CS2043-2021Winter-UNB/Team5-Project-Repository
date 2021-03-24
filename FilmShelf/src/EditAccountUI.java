
import java.awt.EventQueue;

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
	

public class EditAccountUI extends JPanel {
	private EditAccountControl editAccountControl;
	private JPasswordField passwordField;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JCheckBox checkboxPasswordVisibility;
	private JLabel labelCreateAccountStatus;

	public EditAccountUI(EditAccountControl control) {
		setBackground(new Color(153, 102, 102));
		editAccountControl = control;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{71, 0, 100, 0, 0, 0, 48, 0};
		gridBagLayout.rowHeights = new int[]{74, 0, 0, 0, 0, 0, 42, 35, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel labelUsername = new JLabel("Username");
		labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelUsername = new GridBagConstraints();
		gbc_labelUsername.anchor = GridBagConstraints.EAST;
		gbc_labelUsername.insets = new Insets(0, 0, 5, 5);
		gbc_labelUsername.gridx = 1;
		gbc_labelUsername.gridy = 1;
		add(labelUsername, gbc_labelUsername);
		
		JLabel labelPassword = new JLabel("New Password");
		GridBagConstraints gbc_labelPassword = new GridBagConstraints();
		gbc_labelPassword.anchor = GridBagConstraints.EAST;
		gbc_labelPassword.insets = new Insets(0, 0, 5, 5);
		gbc_labelPassword.gridx = 1;
		gbc_labelPassword.gridy = 2;
		add(labelPassword, gbc_labelPassword);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		add(passwordField, gbc_passwordField);
		
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
		
		JLabel labelFirstName = new JLabel("Update First Name");
		GridBagConstraints gbc_labelFirstName = new GridBagConstraints();
		gbc_labelFirstName.anchor = GridBagConstraints.EAST;
		gbc_labelFirstName.fill = GridBagConstraints.VERTICAL;
		gbc_labelFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_labelFirstName.gridx = 1;
		gbc_labelFirstName.gridy = 3;
		add(labelFirstName, gbc_labelFirstName);
		
		textFieldFirstName = new JTextField();
		GridBagConstraints gbc_textFieldFirstName = new GridBagConstraints();
		gbc_textFieldFirstName.gridwidth = 3;
		gbc_textFieldFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFirstName.gridx = 2;
		gbc_textFieldFirstName.gridy = 3;
		add(textFieldFirstName, gbc_textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		JLabel labelLastName = new JLabel("Update Last Name");
		GridBagConstraints gbc_labelLastName = new GridBagConstraints();
		gbc_labelLastName.anchor = GridBagConstraints.EAST;
		gbc_labelLastName.insets = new Insets(0, 0, 5, 5);
		gbc_labelLastName.gridx = 1;
		gbc_labelLastName.gridy = 4;
		add(labelLastName, gbc_labelLastName);
		
		textFieldLastName = new JTextField();
		GridBagConstraints gbc_textFieldLastName = new GridBagConstraints();
		gbc_textFieldLastName.gridwidth = 3;
		gbc_textFieldLastName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLastName.gridx = 2;
		gbc_textFieldLastName.gridy = 4;
		add(textFieldLastName, gbc_textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JButton buttonEditAccount = new JButton("Edit Account");
		buttonEditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extractAccountInfo();
			}
		});
		GridBagConstraints gbc_buttonEditAccount = new GridBagConstraints();
		gbc_buttonEditAccount.gridwidth = 5;
		gbc_buttonEditAccount.insets = new Insets(0, 0, 5, 5);
		gbc_buttonEditAccount.gridx = 1;
		gbc_buttonEditAccount.gridy = 6;
		add(buttonEditAccount, gbc_buttonEditAccount);
		
		labelCreateAccountStatus = new JLabel("");
		labelCreateAccountStatus.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelEditAccountStatus = new GridBagConstraints();
		gbc_labelEditAccountStatus.gridwidth = 5;
		gbc_labelEditAccountStatus.insets = new Insets(0, 0, 5, 5);
		gbc_labelEditAccountStatus.gridx = 1;
		gbc_labelEditAccountStatus.gridy = 7;
		add(labelCreateAccountStatus, gbc_labelEditAccountStatus);

	}

	
	public void displayEditAccountForm() {
	}
	
	private void extractAccountInfo(){
		String password = new String(passwordField.getPassword());
		String firstName = textFieldFirstName.getText();
		String lastName = textFieldLastName.getText();
		String description = textFieldLastName.getText();
		
		if (editAccountControl.updateAccount(password,firstName,lastName,description)) {
			displayAccountEditConfirmation();
		}
		else {
			displayAccountEditError();
		}
	}

	public void displayAccountEditConfirmation() {

	}
	
	private void displayAccountEditError() {
		labelCreateAccountStatus.setText("Account edit was unsuccessful. Account information was invalid.");
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



