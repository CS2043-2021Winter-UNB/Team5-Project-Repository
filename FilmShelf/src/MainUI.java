
/******************************************************************************************************************************
 * MainUI
 * @author Sharon
 * Description:	This class is the main JFrame that hosts the use case JPanels. 
 ******************************************************************************************************************************/
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.EventQueue;
//import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
//import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
//import javax.swing.JComboBox;
//import javax.swing.BoxLayout;

public class MainUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPane;
	private LoginUI loginUI;
	//private LoginControl loginControl;
	private CreateMemberUI createMemberUI;
	private EditMemberUI editMemberUI;
	private SearchMemberUI searchMemberUI;
	//private ViewMemberUI viewMemberUI;
	private JButton buttonLogin;
	//private JButton buttonEditMember;
	private JButton buttonCreateAccount;
	private JButton buttonSearchAccount;
	
	/**
	 * Create the frame.
	 */
	public MainUI(LoginUI uiLog, LoginControl controlLog, CreateMemberUI uiCreate, EditMemberUI uiMember, ViewMemberUI uiViewAccount, SearchMemberUI uiSearch) {
		loginUI = uiLog;
		//loginControl = controlLog;
		createMemberUI = uiCreate;
		editMemberUI =uiMember;
		searchMemberUI =uiSearch;
		//viewMemberUI = uiViewAccount;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setSize(751, 521);
		
	    //MAIN PAIN
	    mainPane = new JPanel();
	
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5 ));
		setContentPane(mainPane);
		//int width = 60;
        GridBagLayout gbl_mainPane = new GridBagLayout();
        gbl_mainPane.columnWidths = new int[]{63, 86, 254, 117, 144, 0};
        gbl_mainPane.rowHeights = new int[]{79, 405, 0};
        gbl_mainPane.columnWeights = new double[]{5.0, 5.0, 1.0, 6.0, 6.0, Double.MIN_VALUE};
        gbl_mainPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        mainPane.setLayout(gbl_mainPane);
        
   
        //adding UI into panel to the main window
        GridBagConstraints gbc_Panel = new GridBagConstraints();
        gbc_Panel.gridwidth = 6;
        gbc_Panel.gridheight = 4;
        gbc_Panel.insets = new Insets(0, 0, 5, 5);
        gbc_Panel.gridx = 0;
        gbc_Panel.gridy = 1;
       
        mainPane.add(loginUI, gbc_Panel);
        mainPane.add(createMemberUI, gbc_Panel);
        mainPane.add(searchMemberUI,gbc_Panel);
        mainPane.add(editMemberUI,gbc_Panel);
    
        
	     
        //TITLE
        JLabel labelFilmShelf = new JLabel("FilmShelf");
        labelFilmShelf.setFont(new Font("Tahoma", Font.BOLD, 50));
        GridBagConstraints gbc_labelFilmShelf = new GridBagConstraints();
        gbc_labelFilmShelf.anchor = GridBagConstraints.WEST;
        gbc_labelFilmShelf.insets = new Insets(0, 0, 5, 5);
        gbc_labelFilmShelf.gridx = 0;
        gbc_labelFilmShelf.gridy = 0;
        mainPane.add(labelFilmShelf, gbc_labelFilmShelf);
       
        //NEW PANEL
        JPanel panelAccountButtons = new JPanel();
        
        GridBagConstraints gbc_panel2 = new GridBagConstraints();
        gbc_panel2.gridwidth = 2;
        gbc_panel2.insets = new Insets(0, 0, 5, 0);
        gbc_panel2.fill = GridBagConstraints.BOTH;
        gbc_panel2.gridx = 3;
        gbc_panel2.gridy = 0;
        mainPane.add(panelAccountButtons, gbc_panel2);
        
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelAccountButtons.setLayout(gbl_panel);
	     
        //create account
	     buttonCreateAccount = new JButton("Create Account");
	     buttonCreateAccount.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		createMemberUI.displayCreateAccountForm();
	     		loginUI.setVisible(false);
	     		editMemberUI.setVisible(false);
	     		searchMemberUI.setVisible(false);
	     		}
	     });
	     
	     GridBagConstraints gbc_buttonCreateAccount = new GridBagConstraints();
	     gbc_buttonCreateAccount.insets = new Insets(0, 0, 5, 0);
	     gbc_buttonCreateAccount.anchor = GridBagConstraints.WEST;
	     gbc_buttonCreateAccount.gridx = 4;
	     gbc_buttonCreateAccount.gridy = 1;
	     panelAccountButtons.add(buttonCreateAccount, gbc_buttonCreateAccount);
	     
	     //Extra buttons
	     JButton btnNewButton_2 = new JButton("back");
	     GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
	     gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
	     gbc_btnNewButton_2.gridx = 0;
	     gbc_btnNewButton_2.gridy = 1;
	     panelAccountButtons.add(btnNewButton_2, gbc_btnNewButton_2);
	     
	     buttonSearchAccount = new JButton("Search Account");
	     buttonSearchAccount.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		searchMemberUI.displaySearchForm();
	     		loginUI.setVisible(false);
	     		createMemberUI.setVisible(false);
	      		editMemberUI.setVisible(false);
	     	}
	     });
	     GridBagConstraints gbc_buttonSearch = new GridBagConstraints();
	     gbc_buttonSearch.insets = new Insets(0, 0, 5, 5);
	     gbc_buttonSearch.gridx = 1;
	     gbc_buttonSearch.gridy = 1;
	     panelAccountButtons.add(buttonSearchAccount, gbc_buttonSearch);
	    
	     
        //login 
	     buttonLogin = new JButton("Login");
	     buttonLogin.setHorizontalAlignment(SwingConstants.RIGHT);
	     
	     buttonLogin.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		loginUI.displayLoginForm();
	      		createMemberUI.setVisible(false);
	      		editMemberUI.setVisible(false);
	      		searchMemberUI.setVisible(false);
	      	}
	     });
        
	     GridBagConstraints gbc_buttonLogin = new GridBagConstraints();
	     gbc_buttonLogin.anchor = GridBagConstraints.EAST;
	     gbc_buttonLogin.insets = new Insets(0, 0, 5, 5);
	     gbc_buttonLogin.gridx = 2;
	     gbc_buttonLogin.gridy = 1;
	     panelAccountButtons.add(buttonLogin, gbc_buttonLogin);
	     
        pack();
      
	}
}
