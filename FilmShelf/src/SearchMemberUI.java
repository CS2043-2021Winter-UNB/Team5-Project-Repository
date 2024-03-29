
//import java.awt.EventQueue;

	import javax.swing.JPanel;
	import java.awt.GridBagLayout;
	import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
	import java.awt.Insets;
	import javax.swing.JTextField;
	//import javax.swing.JPasswordField;
	//import javax.swing.JButton;
	//import javax.swing.JRadioButton;
	//import javax.swing.JCheckBox;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	import javax.swing.SwingConstants;
import javax.swing.JButton;
	//import javax.swing.JFrame;
	//import java.awt.Color;
	//import javax.swing.JLayeredPane;
public class SearchMemberUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private SearchMemberControl searchMemberControl;
	private JTextField textFieldSearch;
	private JLabel labelSearchAccountStatus;
	private JButton ViewAccountbutton;
	private ViewMemberUI viewMemberUI;
	private String username;
	
	public SearchMemberUI(SearchMemberControl control,ViewMemberUI uiViewAccount) {
		setVisible(false);
		searchMemberControl = control;
		viewMemberUI = uiViewAccount;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 120, 0, 150, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 15};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 4.9E-324};
		setLayout(gridBagLayout);
		
		Color fontColor = UISettings.getFontColor();
		
		JLabel lblNewLabel = new JLabel("Search Account:");
		lblNewLabel.setForeground(fontColor);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setDocument(new LengthRestrictedDocument(25));
		textFieldSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enterMemberAccountName();
			}
		});
		
		GridBagConstraints gbc_textFieldSearch = new GridBagConstraints();
		gbc_textFieldSearch.gridwidth = 3;
		gbc_textFieldSearch.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSearch.gridx = 2;
		gbc_textFieldSearch.gridy = 1;
		add(textFieldSearch, gbc_textFieldSearch);
		textFieldSearch.setColumns(10);
		
		ViewAccountbutton = new JButton("view");
		ViewAccountbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMemberUI.displayViewMemberAccount(username);
				setVisible(false);
				
			}
		});
		GridBagConstraints gbc_ViewAccountbutton = new GridBagConstraints();
		gbc_ViewAccountbutton.anchor = GridBagConstraints.EAST;
		gbc_ViewAccountbutton.insets = new Insets(0, 0, 0, 5);
		gbc_ViewAccountbutton.gridx = 5;
		gbc_ViewAccountbutton.gridy = 3;
		add(ViewAccountbutton, gbc_ViewAccountbutton);
		ViewAccountbutton.setVisible(false);
		
		labelSearchAccountStatus = new JLabel("");
		labelSearchAccountStatus.setForeground(fontColor);
		labelSearchAccountStatus.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelSearchAccountStatus = new GridBagConstraints();
		gbc_labelSearchAccountStatus.gridwidth = 6;
		gbc_labelSearchAccountStatus.gridx = 1;
		gbc_labelSearchAccountStatus.gridy = 3;
		add(labelSearchAccountStatus, gbc_labelSearchAccountStatus);
		
		setOpaque(false);
	}

	public void displaySearchForm() {
		//clear search fields
		textFieldSearch.setText("");
		labelSearchAccountStatus.setText("");
		ViewAccountbutton.setVisible(false);
		username = "";
		
		setVisible(true);
	}

	public void displaySearchResult(MemberObject member) {
		labelSearchAccountStatus.setText(member.getUsername()+" - "+member.getFirstName()+" "+member.getLastName());
		username = member.getUsername();
		ViewAccountbutton.setVisible(true);
	}

	public void displayFailedSearchMessage() {
		ViewAccountbutton.setVisible(false);
		labelSearchAccountStatus.setText("Account search was unsuccessful. Information was invalid.");
	}

	public void enterMemberAccountName() {
		MemberObject member=searchMemberControl.processSearchAccount(textFieldSearch.getText());
		
		if (member==null) {
			displayFailedSearchMessage();
		}
		else {
			displaySearchResult(member);
		}	
	}
}




















