import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainUI extends JFrame {
	
	private JPanel mainPane;
	private LoginUI loginUI;
	private CreateMemberAccountUI createMemberAccountUI;
	
	
	/**
	 * Create the frame.
	 */
	public MainUI(LoginUI ui, CreateMemberAccountUI uiCreate) {
		loginUI = ui;
		createMemberAccountUI = uiCreate;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setSize(screenSize.width, screenSize.height);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{350, 90, 200, 45, 0, 0, 0};
		int width = 60;
		gbl_contentPane.rowHeights = new int[]{width, (screenSize.height-width)};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		mainPane.setLayout(gbl_contentPane);
		
		JLabel labelFilmShelf = new JLabel("FilmShelf");
		labelFilmShelf.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_labelFilmShelf = new GridBagConstraints();
		gbc_labelFilmShelf.insets = new Insets(0, 0, 5, 5);
		gbc_labelFilmShelf.gridx = 1;
		gbc_labelFilmShelf.gridy = 0;
		mainPane.add(labelFilmShelf, gbc_labelFilmShelf);
		
		JButton buttonLogin = new JButton("Login");
		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginUI.setVisible(true);
			}
		});
		GridBagConstraints gbc_buttonLogin = new GridBagConstraints();
		gbc_buttonLogin.insets = new Insets(0, 0, 5, 5);
		gbc_buttonLogin.gridx = 3;
		gbc_buttonLogin.gridy = 0;
		mainPane.add(buttonLogin, gbc_buttonLogin);
		
		JButton buttonCreateAccount = new JButton("Create Account");
		buttonCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMemberAccountUI.setVisible(true);
			}
		});
		GridBagConstraints gbc_buttonCreateAccount = new GridBagConstraints();
		gbc_buttonCreateAccount.insets = new Insets(0, 0, 5, 5);
		gbc_buttonCreateAccount.gridx = 4;
		gbc_buttonCreateAccount.gridy = 0;
		mainPane.add(buttonCreateAccount, gbc_buttonCreateAccount);
                
        
        // adding loginUI panel to the main window
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 6;
        gbc_panel.gridheight = 4;
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 1;
        mainPane.add(loginUI, gbc_panel);
        loginUI.setVisible(false);
        
        // adding createAccount panel to the main window
        mainPane.add(createMemberAccountUI, gbc_panel);
        createMemberAccountUI.setVisible(false);

        pack();
      
	}
	
	
	public void removeLoginPanel() {
		mainPane.remove(loginUI);
		mainPane.repaint();
	}

}
