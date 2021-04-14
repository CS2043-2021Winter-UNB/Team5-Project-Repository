
/******************************************************************************************************************************
 * MainUI
 * @author Sharon, Alejandra, Jo
 * Description:	This class is the main JFrame that hosts the use case JPanels. 
 ******************************************************************************************************************************/
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

/******************************************************************************************************************************
 * MainUI
 * 
 * @author Sharon, Alejandra, Jo Description: This class is the main JFrame that
 *         hosts the use case JPanels.
 ******************************************************************************************************************************/
public class MainUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel mainPane;
	private LoginUI loginUI;
	private LoginControl loginControl;
	private CreateMemberUI createMemberUI;
	private EditMemberUI editMemberUI;
	private SearchMemberUI searchMemberUI;
	private AddReviewUI addReviewUI;
	private ViewMemberUI viewMemberUI;
	private AddMovieUI addMovieUI;
	private SearchMovieUI searchMovieUI;
	private ViewMovieUI viewMovieUI;
	private ViewReviewUI viewReviewUI;
	private RateMovieUI rateMovieUI;
	private JButton buttonLogin;
	private JButton buttonCreateAccount;
	private JButton buttonSearchAccount;
	private JButton buttonSearchMovie;
	private ActionListener listenerCreateAccount;
	private List<JPanel> uiPanelList;
	private JLabel labelFiveStars;

	// Create the frame.
	public MainUI(LoginUI uiLog, LoginControl controlLog, CreateMemberUI uiCreate, EditMemberUI uiMember,
			ViewMemberUI uiViewAccount, SearchMemberUI uiSearch, AddMovieUI uiAddMovie, SearchMovieUI uiSearchMovie,
			AddReviewUI uiAddReview, ViewMovieUI uiViewMovie, ViewReviewUI uiViewReview, RateMovieUI uiRateMovie) {
		// save the UI classes
		loginUI = uiLog;
		loginControl = controlLog;
		createMemberUI = uiCreate;
		editMemberUI = uiMember;
		searchMemberUI = uiSearch;
		viewMemberUI = uiViewAccount;
		addMovieUI = uiAddMovie;
		searchMovieUI = uiSearchMovie;
		addReviewUI = uiAddReview;
		viewMovieUI = uiViewMovie;
		viewReviewUI = uiViewReview;
		rateMovieUI = uiRateMovie;

		uiPanelList = new ArrayList<JPanel>();
		uiPanelList.add(loginUI);
		uiPanelList.add(createMemberUI);
		uiPanelList.add(editMemberUI);
		uiPanelList.add(searchMemberUI);
		uiPanelList.add(viewMemberUI);
		uiPanelList.add(addMovieUI);
		uiPanelList.add(searchMovieUI);
		uiPanelList.add(addReviewUI);
		uiPanelList.add(viewMovieUI);
		uiPanelList.add(viewReviewUI);
		uiPanelList.add(rateMovieUI);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(751, 521);

		Image backgroundImage = UISettings.getBackgroundImage();

		// MAIN PANE
		mainPane = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, null);
			}
		};

		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);

		Color buttonColor = UISettings.getButtonColor();
		Color fontColor = UISettings.getFontColor();

		// int width = 60;
		GridBagLayout gbl_mainPane = new GridBagLayout();
		gbl_mainPane.columnWidths = new int[] { 3, 148, 18, 0, 20, 117, 3, 0 };
		gbl_mainPane.rowHeights = new int[] { 79, 20, 0, 0, 357, 0 };
		gbl_mainPane.columnWeights = new double[] { 0.0, 5.0, 100.0, 1.0, 100.0, 6.0, 0.0, Double.MIN_VALUE };
		gbl_mainPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		mainPane.setLayout(gbl_mainPane);

		// adding UI into panel to the main window
		GridBagConstraints gbc_Panel = new GridBagConstraints();
		gbc_Panel.gridwidth = 6;
		gbc_Panel.gridheight = 3;
		gbc_Panel.insets = new Insets(0, 0, 5, 5);
		gbc_Panel.gridx = 0;
		gbc_Panel.gridy = 2;

		mainPane.add(loginUI, gbc_Panel);
		mainPane.add(createMemberUI, gbc_Panel);
		mainPane.add(searchMemberUI, gbc_Panel);
		mainPane.add(viewMemberUI, gbc_Panel);
		mainPane.add(editMemberUI, gbc_Panel);
		mainPane.add(addMovieUI, gbc_Panel);
		mainPane.add(searchMovieUI, gbc_Panel);
		mainPane.add(viewMovieUI, gbc_Panel);
		mainPane.add(addReviewUI, gbc_Panel);
		mainPane.add(viewReviewUI, gbc_Panel);
		mainPane.add(rateMovieUI, gbc_Panel);

		// Causes the account related buttons in the top right to hide or show depending
		// on if a panel is visible
		ComponentListener accountListener = new ComponentAdapter() {
			public void componentShown(ComponentEvent evt) {
				buttonCreateAccount.setVisible(false);
			}

			public void componentHidden(ComponentEvent evt) {
				buttonCreateAccount.setVisible(true);
			}
		};
		addMovieUI.addComponentListener(accountListener);
		viewMemberUI.addComponentListener(accountListener);

		// Causes the account related buttons in the top right to hide or show depending
		// on if a panel is visible
		ComponentListener loginListener = new ComponentAdapter() {
			public void componentShown(ComponentEvent evt) {
				buttonCreateAccount.setVisible(false);
				buttonLogin.setVisible(false);
			}

			public void componentHidden(ComponentEvent evt) {
				changeAccountButtons();
			}
		};
		loginUI.addComponentListener(loginListener);

		// Causes the account related buttons in the top right to hide or show depending
		// on if createMemberUI is visible
		ComponentListener createListener = new ComponentAdapter() {
			public void componentShown(ComponentEvent evt) {
				buttonCreateAccount.setVisible(false);
				buttonLogin.setVisible(false);
			}

			public void componentHidden(ComponentEvent evt) {
				buttonCreateAccount.setVisible(true);
				buttonLogin.setVisible(true);
			}
		};
		createMemberUI.addComponentListener(createListener);

		// Extra button
		JButton btnNewButton_2 = new JButton("back to FilmShelf");
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setBackground(buttonColor);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelVisibilityFalse();
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 0;
		mainPane.add(btnNewButton_2, gbc_btnNewButton_2);

		// TITLE
		JLabel labelFilmShelf = new JLabel("FilmShelf");
		labelFilmShelf.setFont(new Font("Ebrima", Font.BOLD, 50));
		labelFilmShelf.setForeground(fontColor);
		GridBagConstraints gbc_labelFilmShelf = new GridBagConstraints();
		gbc_labelFilmShelf.insets = new Insets(0, 0, 5, 5);
		gbc_labelFilmShelf.gridx = 3;
		gbc_labelFilmShelf.gridy = 0;
		mainPane.add(labelFilmShelf, gbc_labelFilmShelf);

		// Panel for account buttons
		JPanel panelAccountButtons = new JPanel();
		panelAccountButtons.setOpaque(false);
		GridBagConstraints gbc_panel2 = new GridBagConstraints();
		gbc_panel2.gridheight = 2;
		gbc_panel2.insets = new Insets(0, 0, 5, 5);
		gbc_panel2.fill = GridBagConstraints.BOTH;
		gbc_panel2.gridx = 5;
		gbc_panel2.gridy = 0;
		mainPane.add(panelAccountButtons, gbc_panel2);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 7 };
		gbl_panel.rowHeights = new int[] { 14, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelAccountButtons.setLayout(gbl_panel);

		// create account button
		buttonCreateAccount = new JButton("Create Account");
		buttonCreateAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		buttonCreateAccount.setBackground(buttonColor);
		listenerCreateAccount = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelVisibilityFalse();
				createMemberUI.displayCreateAccountForm();
			}
		};
		buttonCreateAccount.addActionListener(listenerCreateAccount);

		// search account button
		buttonSearchAccount = new JButton("Search Account");
		buttonSearchAccount.setPreferredSize(new Dimension(130,25));
		buttonSearchAccount.setBackground(buttonColor);
		buttonSearchAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelVisibilityFalse();
				searchMemberUI.displaySearchForm();
			}
		});
		GridBagConstraints gbc_buttonSearch = new GridBagConstraints();
		gbc_buttonSearch.anchor = GridBagConstraints.EAST;
		gbc_buttonSearch.insets = new Insets(0, 0, 5, 5);
		gbc_buttonSearch.gridx = 0;
		gbc_buttonSearch.gridy = 1;
		panelAccountButtons.add(buttonSearchAccount, gbc_buttonSearch);

		// login button
		buttonLogin = new JButton("Login");
		buttonLogin.setBackground(buttonColor);
		buttonLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelVisibilityFalse();
				loginUI.displayLoginForm();
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

		// search movie button
		buttonSearchMovie = new JButton("Search Movie");
		buttonSearchMovie.setPreferredSize(new Dimension(130,25));
		buttonSearchMovie.setBackground(buttonColor);
		buttonSearchMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllPanelVisibilityFalse();
				searchMovieUI.displaySearchForm();
			}
		});
		GridBagConstraints gbc_buttonSearchMovie = new GridBagConstraints();
		gbc_buttonSearchMovie.anchor = GridBagConstraints.EAST;
		gbc_buttonSearchMovie.insets = new Insets(0, 0, 0, 5);
		gbc_buttonSearchMovie.gridx = 0;
		gbc_buttonSearchMovie.gridy = 2;
		panelAccountButtons.add(buttonSearchMovie, gbc_buttonSearchMovie);

		labelFiveStars = new JLabel();
		labelFiveStars.setVerticalAlignment(SwingConstants.TOP);
		labelFiveStars.setIcon(UISettings.getStarsIcon(5, 15, 13));
		GridBagConstraints gbc_labelFiveStars = new GridBagConstraints();
		gbc_labelFiveStars.insets = new Insets(0, 0, 5, 5);
		gbc_labelFiveStars.gridx = 3;
		gbc_labelFiveStars.gridy = 1;
		mainPane.add(labelFiveStars, gbc_labelFiveStars);

		// searchMovieUI.setVisible(true);

		// display the mainUI and make the background image visible
		pack();
		setVisible(true);
		repaint();
	}

	public void changeAccountButtons() {
		// if a member is logged in
		if (loginControl.getCurrentMember() != null) {

			// hide the login button
			buttonLogin.setVisible(false);

			// change the create account button to view member (shows username of member)
			String username = loginControl.getCurrentMember().getUsername();
			buttonCreateAccount.setText(username);
			buttonCreateAccount.setVisible(true);
			ActionListener[] al1 = buttonCreateAccount.getActionListeners();
			buttonCreateAccount.removeActionListener(al1[0]);
			buttonCreateAccount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setAllPanelVisibilityFalse();
					viewMemberUI.displayViewMemberAccount(username);
				}
			});
		}
		// if an admin is logged in
		else if (loginControl.getCurrentAdmin() != null) {

			// hide the login button
			buttonLogin.setVisible(false);

			// change the create account button to the add movie button
			buttonCreateAccount.setVisible(true);
			buttonCreateAccount.setText("Add Movie");
			ActionListener[] al = buttonCreateAccount.getActionListeners();
			buttonCreateAccount.removeActionListener(al[0]);
			buttonCreateAccount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setAllPanelVisibilityFalse();
					addMovieUI.displayAddMovieForm();
				}
			});
		}
		// if member is logged out, set the buttons back to default
		else if (loginControl.getCurrentAdmin() == null) {

			// make the login button visible again
			buttonLogin.setVisible(true);

			// set the create account button back to "create account"
			buttonCreateAccount.setText("CreateAccount");
			ActionListener[] al = buttonCreateAccount.getActionListeners();
			buttonCreateAccount.removeActionListener(al[0]); // remove the previous button listener
			buttonCreateAccount.addActionListener(listenerCreateAccount); // add the create account listener back
			buttonCreateAccount.setVisible(true); // make it visible

			// viewMemberUI was used to remove and log the user out, change viewMemberUI to
			// not visible
			viewMemberUI.setVisible(false);
		}
	}

	public void changeMovieButtons() {
		viewMovieUI.setVisible(false);
	}

	private void setAllPanelVisibilityFalse() {
		for (JPanel panel : uiPanelList) {
			panel.setVisible(false);
		}
	}
}
