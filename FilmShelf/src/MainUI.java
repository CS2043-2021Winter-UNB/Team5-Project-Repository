
/******************************************************************************************************************************
 * MainUI
 * @author Sharon, Alejandra
 * Description:	This class is the main JFrame that hosts the use case JPanels. 
 ******************************************************************************************************************************/

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
	
	private static final long serialVersionUID = 1L;
	private JPanel mainPane;
	private LoginUI loginUI;
	private LoginControl loginControl;
	private CreateMemberUI createMemberUI;
	private EditMemberUI editMemberUI;
	private SearchMemberUI searchMemberUI;
	private ViewMemberUI viewMemberUI;
	private AddMovieUI addMovieUI;
	private ViewMovieUI viewMovieUI;
	private JButton buttonLogin;
	private JButton buttonCreateAccount;
	private JButton buttonSearchAccount;
	private JButton buttonSearchMovie;
	
	/**
	 * Create the frame.
	 */
	public MainUI(LoginUI uiLog, LoginControl controlLog, CreateMemberUI uiCreate, EditMemberUI uiMember, ViewMemberUI uiViewAccount, SearchMemberUI uiSearch,AddMovieUI uiAddMovie,ViewMovieUI uiViewMovie) {
		loginUI = uiLog;
		loginControl = controlLog;
		createMemberUI = uiCreate;
		editMemberUI =uiMember;
		searchMemberUI =uiSearch;
		viewMemberUI = uiViewAccount;
		addMovieUI = uiAddMovie;
		viewMovieUI = uiViewMovie;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setSize(751, 521);
		
	    //MAIN PANE
	    mainPane = new JPanel();
	    
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5 ));
		setContentPane(mainPane);
		//int width = 60;
        GridBagLayout gbl_mainPane = new GridBagLayout();
        gbl_mainPane.columnWidths = new int[]{63, 86, 0, 254, 0, 117, 144, 0};
        gbl_mainPane.rowHeights = new int[]{79, 405, 0};
        gbl_mainPane.columnWeights = new double[]{5.0, 5.0, 0.0, 1.0, 0.0, 6.0, 6.0, Double.MIN_VALUE};
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
        mainPane.add(addMovieUI,gbc_Panel);
        mainPane.add(viewMemberUI,gbc_Panel);
            
            	     
	     //Extra buttons
	     JButton btnNewButton_2 = new JButton("back");
	     btnNewButton_2.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		
	     	}
	     });
	     GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
	     gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
	     gbc_btnNewButton_2.gridx = 0;
	     gbc_btnNewButton_2.gridy = 0;
	     mainPane.add(btnNewButton_2, gbc_btnNewButton_2);
        
            
	     
        //TITLE
        JLabel labelFilmShelf = new JLabel("FilmShelf");
        labelFilmShelf.setFont(new Font("Tahoma", Font.BOLD, 50));
        GridBagConstraints gbc_labelFilmShelf = new GridBagConstraints();
        gbc_labelFilmShelf.anchor = GridBagConstraints.WEST;
        gbc_labelFilmShelf.insets = new Insets(0, 0, 5, 5);
        gbc_labelFilmShelf.gridx = 3;
        gbc_labelFilmShelf.gridy = 0;
        mainPane.add(labelFilmShelf, gbc_labelFilmShelf);

        //NEW PANEL
        JPanel panelAccountButtons = new JPanel();
        
        GridBagConstraints gbc_panel2 = new GridBagConstraints();
        gbc_panel2.gridwidth = 2;
        gbc_panel2.insets = new Insets(0, 0, 5, 0);
        gbc_panel2.fill = GridBagConstraints.BOTH;
        gbc_panel2.gridx = 5;
        gbc_panel2.gridy = 0;
        mainPane.add(panelAccountButtons, gbc_panel2);
        
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 7};
        gbl_panel.rowHeights = new int[]{14, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0};
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
	     		viewMemberUI.setVisible(false);
	     		}
	     });
	     
	     buttonSearchAccount = new JButton("Search Account");
	     buttonSearchAccount.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		searchMemberUI.displaySearchForm();
	     		loginUI.setVisible(false);
	     		createMemberUI.setVisible(false);
	      		editMemberUI.setVisible(false);
	      		viewMemberUI.setVisible(false);
	     	}
	     });
	     GridBagConstraints gbc_buttonSearch = new GridBagConstraints();
	     gbc_buttonSearch.insets = new Insets(0, 0, 5, 5);
	     gbc_buttonSearch.gridx = 0;
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
	       		viewMemberUI.setVisible(false);
	       	}
	      });
	      

	      GridBagConstraints gbc_buttonLogin = new GridBagConstraints();
	      gbc_buttonLogin.insets = new Insets(0, 0, 5, 5);
	      gbc_buttonLogin.gridx = 2;
	      gbc_buttonLogin.gridy = 1;
	      panelAccountButtons.add(buttonLogin, gbc_buttonLogin);
	     
	     GridBagConstraints gbc_buttonCreateAccount = new GridBagConstraints();
	     gbc_buttonCreateAccount.insets = new Insets(0, 0, 5, 0);
	     gbc_buttonCreateAccount.anchor = GridBagConstraints.WEST;
	     gbc_buttonCreateAccount.gridx = 4;
	     gbc_buttonCreateAccount.gridy = 1;
	     panelAccountButtons.add(buttonCreateAccount, gbc_buttonCreateAccount);
	     
	     buttonSearchMovie = new JButton("Search Movie");
	     buttonSearchMovie.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     	}
	     });
	     GridBagConstraints gbc_buttonSearchMovie = new GridBagConstraints();
	     gbc_buttonSearchMovie.insets = new Insets(0, 0, 0, 5);
	     gbc_buttonSearchMovie.gridx = 0;
	     gbc_buttonSearchMovie.gridy = 2;
	     panelAccountButtons.add(buttonSearchMovie, gbc_buttonSearchMovie);
	     
        pack();
      
	}
	
	
	public void changeCreateAndLoginButtons()
	{
		if (loginControl.getCurrentMember() != null) { 
			
			//change login button to view member (shows username of member)
			String username = loginControl.getCurrentMember().getUsername();
			buttonLogin.setText(username); 
			ActionListener[] al1 = buttonLogin.getActionListeners();
			buttonLogin.removeActionListener(al1[0]);
	        buttonLogin.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		viewMemberUI.displayViewMemberAccount(username);
	        	}
	        });
	        
	        //get rid of create account button
			buttonCreateAccount.setVisible(false);
			ActionListener[] al2 = buttonCreateAccount.getActionListeners();
			buttonCreateAccount.removeActionListener(al2[0]);
		}
		else if (loginControl.getCurrentAdmin() != null) {
			
			//hide the login button
			buttonLogin.setVisible(false);
			
			//change the create account button to the add movie button
			buttonCreateAccount.setText("Add Movie");
			ActionListener[] al = buttonCreateAccount.getActionListeners();
			buttonCreateAccount.removeActionListener(al[0]);
	        buttonCreateAccount.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		addMovieUI.displayAddMovieForm();
	        		searchMemberUI.setVisible(false);
	        	}
	        });
		}
	}
	
}
