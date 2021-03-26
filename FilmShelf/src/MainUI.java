
/******************************************************************************************************************************
 * MainUI
 * @author Sharon
 * Description:	This class is the main JFrame that hosts the use case JPanels. 
 ******************************************************************************************************************************/

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainUI extends JFrame {
	
	private JPanel mainPane;
	private LoginUI loginUI;
	private LoginControl loginControl;
	private CreateMemberUI createMemberUI;
	//private EditAccountUI editAccountUI;
	private ViewMemberUI viewMemberUI;
	private JButton buttonLogin;
	private JButton buttonCreateAccount;
	
	
	/**
	 * Create the frame.
	 */
	public MainUI(LoginUI uiLog, LoginControl controlLog, CreateMemberUI uiCreate, ViewMemberUI uiViewAccount) {
		loginUI = uiLog;
		loginControl = controlLog;
		createMemberUI = uiCreate;
		//editAccountUI = uiEditAccount;
		viewMemberUI = uiViewAccount;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setSize(screenSize.width, screenSize.height);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 166, 90, 200, 45, 0, 0, 0};
		int width = 60;
		gbl_contentPane.rowHeights = new int[]{width, (screenSize.height-width)};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0};
		mainPane.setLayout(gbl_contentPane);
		
		JButton btnNewButton = new JButton("Back");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		mainPane.add(btnNewButton, gbc_btnNewButton);
		
		JLabel labelFilmShelf = new JLabel("FilmShelf");
		labelFilmShelf.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_labelFilmShelf = new GridBagConstraints();
		gbc_labelFilmShelf.insets = new Insets(0, 0, 5, 5);
		gbc_labelFilmShelf.gridx = 3;
		gbc_labelFilmShelf.gridy = 0;
		mainPane.add(labelFilmShelf, gbc_labelFilmShelf);
                
        
        // adding loginUI panel to the main window
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 6;
        gbc_panel.gridheight = 4;
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 1;
        loginUI.setVisible(false);
        mainPane.add(loginUI, gbc_panel);
        
        
        // adding createAccount panel to the main window
        mainPane.add(createMemberUI, gbc_panel);
        createMemberUI.setVisible(false);
        
       /* // adding editAccount panel to the main window
        mainPane.add(editAccountUI, gbc_panel);
        editAccountUI.setVisible(false);*/
        
        //Panel that holds the "Login" and "CreateAccount" buttons
        JPanel panelAccountButtons = new JPanel();
        GridBagConstraints gbc_panel2 = new GridBagConstraints();
        gbc_panel2.gridwidth = 2;
        gbc_panel2.insets = new Insets(0, 0, 5, 5);
        gbc_panel2.fill = GridBagConstraints.BOTH;
        gbc_panel2.gridx = 5;
        gbc_panel2.gridy = 0;
        mainPane.add(panelAccountButtons, gbc_panel2);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panelAccountButtons.setLayout(gbl_panel);
        
        buttonLogin = new JButton("Login");
        buttonLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		loginUI.displayLoginForm();
        	}
        });
        GridBagConstraints gbc_buttonLogin = new GridBagConstraints();
        gbc_buttonLogin.insets = new Insets(0, 0, 0, 5);
        gbc_buttonLogin.gridx = 0;
        gbc_buttonLogin.gridy = 0;
        panelAccountButtons.add(buttonLogin, gbc_buttonLogin);
        
        buttonCreateAccount = new JButton("Create Account");
        buttonCreateAccount.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		createMemberUI.displayCreateAccountForm();
        	}
        });
        GridBagConstraints gbc_buttonCreateAccount = new GridBagConstraints();
        gbc_buttonCreateAccount.gridx = 2;
        gbc_buttonCreateAccount.gridy = 0;
        panelAccountButtons.add(buttonCreateAccount, gbc_buttonCreateAccount);
        
        JButton btnNewButton_1 = new JButton("make buttons switch");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		changeCreateAndLoginButtons();
        	}
        });
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_1.gridx = 1;
        gbc_btnNewButton_1.gridy = 1;
        mainPane.add(btnNewButton_1, gbc_btnNewButton_1);      

        pack();
      
	}
	
	
	private void changeCreateAndLoginButtons()
	{
		if (loginControl.getCurrentMember() != null) { 
			
			//change login button to view member (shows username of member)
			String username = loginControl.getCurrentMember().getUsername();
			buttonLogin.setText(username); 
			buttonLogin.removeActionListener(null);
	        buttonLogin.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		viewMemberUI.displayViewMemberAccount(username);
	        	}
	        });
	        
	        //change create account button to edit account 
			buttonCreateAccount.setText("Edit Account");
			buttonCreateAccount.removeActionListener(null);
	        buttonCreateAccount.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//editAccountUI call here
	        	}
	        });
		}
		else if (loginControl.getCurrentAdmin() != null) {
			buttonLogin.setVisible(false);
			buttonCreateAccount.setText("Add Movie");
			buttonCreateAccount.removeActionListener(null);
	        buttonCreateAccount.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		// addMovieUI.displayAddMovieForm();
	        	}
	        });
		}
	}
	
	/*going to add better method to loginUI itself
	public void removeLoginPanel() {
		loginUI.setVisible(false);
		mainPane.repaint();
	}*/

}
