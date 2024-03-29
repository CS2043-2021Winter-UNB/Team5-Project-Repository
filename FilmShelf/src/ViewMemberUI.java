
/******************************************************************************************************************************
 * ViewAccountUI
 * @author Sharon
 * Description:	Displays the account information for the member that was selected. Displays a "Remove Account" button
 * for activating the RemoveAccount use case if the actor is an administrator or they are a member viewing their own 
 * account.
 ******************************************************************************************************************************/


import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewMemberUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private ViewMemberControl viewMemberControl;
	private LoginControl loginControl;
	private EditMemberUI editMemberUI;
	private RemoveMemberUI removeMemberUI;
	private JLabel labelShowUsername;
	private JLabel labelShowFirstName;
	private JLabel labelShowLastName;
	private JLabel labelShowDescription;
	private JLabel[] labelMovies = new JLabel[5];
	private JButton buttonRemoveAccount;
	private JButton buttonEditMember;
	private String username;
	
	/**
	 * Create the panel.
	 */
	public ViewMemberUI(ViewMemberControl controlViewAcc, LoginControl controlLogin, EditMemberUI uiEditMember, RemoveMemberUI uiRemoveMember) {
		viewMemberControl = controlViewAcc;
		loginControl = controlLogin;
		editMemberUI = uiEditMember;
		removeMemberUI = uiRemoveMember;
		
		setVisible(false);
		
		Color fontColor = UISettings.getFontColor();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 141, 0, 0, 80, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 40, 133, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		//Remove account button
		buttonRemoveAccount = new JButton("Remove Account");
		buttonRemoveAccount.setPreferredSize(new Dimension(135,25));
		buttonRemoveAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMemberUI.displayRemovalMemberWarning(username);
			}
		});
		
		buttonEditMember = new JButton("Edit Account");
		buttonEditMember.setPreferredSize(new Dimension(135,25));
		buttonEditMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editMemberUI.displayEditAccountForm();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_buttonEditMember = new GridBagConstraints();
		gbc_buttonEditMember.insets = new Insets(0, 0, 5, 5);
		gbc_buttonEditMember.gridx = 9;
		gbc_buttonEditMember.gridy = 1;
		add(buttonEditMember, gbc_buttonEditMember);
		buttonRemoveAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_buttonRemoveAccount = new GridBagConstraints();
		gbc_buttonRemoveAccount.insets = new Insets(0, 0, 40, 5);
		gbc_buttonRemoveAccount.gridx = 9;
		gbc_buttonRemoveAccount.gridy = 2;
		add(buttonRemoveAccount, gbc_buttonRemoveAccount);
		
		//Username label
		/*JLabel labelUsername = new JLabel("Username:");
		labelUsername.setForeground(fontColor);
		GridBagConstraints gbc_labelUsername = new GridBagConstraints();
		gbc_labelUsername.insets = new Insets(0, 0, 5, 5);
		gbc_labelUsername.gridx = 2;
		gbc_labelUsername.gridy = 2;
		add(labelUsername, gbc_labelUsername);*/
		
		//Label that displays the username of the member
		labelShowUsername = new JLabel("");
		labelShowUsername.setFont(new Font("Ebrima", Font.BOLD, 28));
		labelShowUsername.setForeground(fontColor);
		GridBagConstraints gbc_labelShowUsername = new GridBagConstraints();
		gbc_labelShowUsername.anchor = GridBagConstraints.WEST;
		gbc_labelShowUsername.insets = new Insets(0, 0, 5, 5);
		gbc_labelShowUsername.gridx = 2;
		gbc_labelShowUsername.gridy = 1;
		add(labelShowUsername, gbc_labelShowUsername);
		
		//First name label
		/*JLabel labelFirstName = new JLabel("First Name:");
		labelFirstName.setForeground(fontColor);
		GridBagConstraints gbc_labelFirstName = new GridBagConstraints();
		gbc_labelFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_labelFirstName.gridx = 2;
		gbc_labelFirstName.gridy = 3;
		add(labelFirstName, gbc_labelFirstName);*/
		
		//Label that displays the first name of the member
		labelShowFirstName = new JLabel("");
		labelShowFirstName.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		labelShowFirstName.setForeground(fontColor);
		GridBagConstraints gbc_labelShowFirstName = new GridBagConstraints();
		gbc_labelShowFirstName.anchor = GridBagConstraints.WEST;
		gbc_labelShowFirstName.insets = new Insets(0, 0, 30, 5);
		gbc_labelShowFirstName.gridx = 2;
		gbc_labelShowFirstName.gridy = 2;
		add(labelShowFirstName, gbc_labelShowFirstName);
		
		//Last name label
		/*JLabel labelLastName = new JLabel("Last Name:");
		labelLastName.setForeground(fontColor);
		GridBagConstraints gbc_labelLastName = new GridBagConstraints();
		gbc_labelLastName.insets = new Insets(0, 0, 5, 5);
		gbc_labelLastName.gridx = 2;
		gbc_labelLastName.gridy = 4;
		add(labelLastName, gbc_labelLastName);*/
		
		//Label that displays the last name of the member
		/*labelShowLastName = new JLabel("");
		labelShowLastName.setForeground(fontColor);
		GridBagConstraints gbc_labelShowLastName = new GridBagConstraints();
		gbc_labelShowLastName.insets = new Insets(0, 0, 5, 5);
		gbc_labelShowLastName.gridx = 4;
		gbc_labelShowLastName.gridy = 4;
		add(labelShowLastName, gbc_labelShowLastName);*/
		
		//Description label
		/*JLabel labelDescription = new JLabel("Description:");
		labelDescription.setForeground(fontColor);
		GridBagConstraints gbc_labelDescription = new GridBagConstraints();
		gbc_labelDescription.insets = new Insets(0, 0, 5, 5);
		gbc_labelDescription.gridx = 2;
		gbc_labelDescription.gridy = 5;
		add(labelDescription, gbc_labelDescription);*/
		
		//Label that displays the description of the member
		labelShowDescription = new JLabel("");
		labelShowDescription.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		labelShowDescription.setForeground(fontColor);
		GridBagConstraints gbc_labelShowDescription = new GridBagConstraints();
		gbc_labelShowDescription.anchor = GridBagConstraints.WEST;
		gbc_labelShowDescription.insets = new Insets(0, 0, 15, 5);
		gbc_labelShowDescription.gridwidth = 3;
		gbc_labelShowDescription.gridx = 2;
		gbc_labelShowDescription.gridy = 3;
		add(labelShowDescription, gbc_labelShowDescription);
		
		JLayeredPane layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.insets = new Insets(0, 0, 5, 5);
		gbc_layeredPane.gridwidth = 5;
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 1;
		gbc_layeredPane.gridy = 7;
		add(layeredPane, gbc_layeredPane);
		GridBagLayout gbl_layeredPane = new GridBagLayout();
		gbl_layeredPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_layeredPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_layeredPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_layeredPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		layeredPane.setLayout(gbl_layeredPane);
		
		//Top movies label
		JLabel labelTopMovies = new JLabel("Top Movies:");
		labelTopMovies.setFont(new Font("Ebrima", Font.BOLD, 14));
		labelTopMovies.setForeground(fontColor);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		layeredPane.add(labelTopMovies, gbc_lblNewLabel);
		
		//Label that displays the member's first top movie
		JLabel labelMovie1 = new JLabel("");
		labelMovie1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		labelMovie1.setForeground(fontColor);
		GridBagConstraints gbc_labelMovie1 = new GridBagConstraints();
		//gbc_labelMovie1.anchor = GridBagConstraints.EAST;
		gbc_labelMovie1.insets = new Insets(0, 0, 5, 0);
		gbc_labelMovie1.gridx = 3;
		gbc_labelMovie1.gridy = 1;
		layeredPane.add(labelMovie1, gbc_labelMovie1);
		
		//Label that displays the member's second top movie
		JLabel labelMovie2 = new JLabel("");
		labelMovie2.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		labelMovie2.setForeground(fontColor);
		GridBagConstraints gbc_labelMovie2 = new GridBagConstraints();
		//gbc_labelMovie2.anchor = GridBagConstraints.EAST;
		gbc_labelMovie2.insets = new Insets(0, 0, 5, 0);
		gbc_labelMovie2.gridx = 3;
		gbc_labelMovie2.gridy = 2;
		layeredPane.add(labelMovie2, gbc_labelMovie2);
		
		//Label that displays the member's third top movie
		JLabel labelMovie3 = new JLabel("");
		labelMovie3.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		labelMovie3.setForeground(fontColor);
		GridBagConstraints gbc_labelMovie3 = new GridBagConstraints();
		//gbc_labelMovie3.anchor = GridBagConstraints.EAST;
		gbc_labelMovie3.insets = new Insets(0, 0, 5, 0);
		gbc_labelMovie3.gridx = 3;
		gbc_labelMovie3.gridy = 3;
		layeredPane.add(labelMovie3, gbc_labelMovie3);
		
		//Label that displays the member's fourth top movie
		JLabel labelMovie4 = new JLabel("");
		labelMovie4.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		labelMovie4.setForeground(fontColor);
		GridBagConstraints gbc_labelMovie4 = new GridBagConstraints();
		//gbc_labelMovie4.anchor = GridBagConstraints.EAST;
		gbc_labelMovie4.insets = new Insets(0, 0, 5, 0);
		gbc_labelMovie4.gridx = 3;
		gbc_labelMovie4.gridy = 4;
		layeredPane.add(labelMovie4, gbc_labelMovie4);
		
		//Label that displays the member's fifth top movie
		JLabel labelMovie5 = new JLabel("");
		labelMovie5.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		labelMovie5.setForeground(fontColor);
		GridBagConstraints gbc_labelMovie5 = new GridBagConstraints();
		//gbc_labelMovie5.anchor = GridBagConstraints.EAST;
		gbc_labelMovie5.insets = new Insets(0, 0, 5, 0);
		gbc_labelMovie5.gridx = 3;
		gbc_labelMovie5.gridy = 5;
		layeredPane.add(labelMovie5, gbc_labelMovie5);
		
		//adding top movie labels to the top movie label array
		labelMovies[0] = labelMovie1;
		labelMovies[1] = labelMovie2;
		labelMovies[2] = labelMovie3;
		labelMovies[3] = labelMovie4;
		labelMovies[4] = labelMovie5;
		
		setOpaque(false);
	}
	
	
	public void displayViewMemberAccount(String username)
	{	
			setVisible(true);
			this.username=username;
			MemberObject member = viewMemberControl.getMemberAccount(username);
			labelShowUsername.setText(username);
			labelShowFirstName.setText(member.getFirstName() + " " + member.getLastName());
			//labelShowLastName.setText(member.getLastName());
			labelShowDescription.setText(member.getDescription());
			
			ArrayList<MovieObject> topMovies = member.getTopMovies();
			int i;
			for (i = 0; i < 5; i++) {
				if (i < topMovies.size()) {
					labelMovies[i].setText(topMovies.get(i).getTitle()); // add movie name			
				}
				else {                          
					labelMovies[i].setText(""); //clear any unused movie labels to make sure previous results are gone
				}	
			}
			//check if remove member button should be displayed.
			//It should be displayed if the actor is an administrator or they are viewing their own member account.
			MemberObject logInMember = loginControl.getCurrentMember();
			boolean memberMatch = false;
			if (logInMember != null)
			{
				memberMatch = logInMember.getUsername().equals(username);
			}
	
			boolean adminCheck = (loginControl.getCurrentAdmin() != null);
	
			if (adminCheck){
				buttonRemoveAccount.setVisible(true);
				buttonEditMember.setVisible(false);
			}
			else if (memberMatch){
				buttonRemoveAccount.setVisible(true);
				buttonEditMember.setVisible(true);
			}
			else{
				buttonRemoveAccount.setVisible(false);
				buttonEditMember.setVisible(false);
			}
		}	
}
