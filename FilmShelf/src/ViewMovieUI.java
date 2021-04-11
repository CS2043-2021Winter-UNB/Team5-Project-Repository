

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ViewMovieUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ViewMovieControl viewMovieControl;
	private RemoveMovieUI removeMovieUI;
	private SearchMovieUI searchMovieUI;
	private AddReviewUI addReviewUI;
	private JLabel labelShowTitle;
	private JLabel labelShowReleaseYear;
	private JLabel labelShowGenre;
	private JLabel labelShowLength;
	private JButton buttonRemoveMovie;
	private LoginControl loginControl;
	private JButton reviewButton;
	private int movieID;

	private JButton rateButton;
	private RateMovieUI rateMovie;
	private MemberObject member;
	private JLayeredPane layeredPane_1;
	double rating;
	private JButton viewReviewButton;
	private String title;
	private ViewReviewUI viewReviewUI;
	
	/**
	 * Create the panel.
	 */
	public ViewMovieUI(ViewMovieControl controlViewMovie, RemoveMovieUI uiRemoveMovie, LoginControl controlLogin, AddReviewUI uiAddReview,RateMovieUI uiRateMovie, ViewReviewUI uiViewReview) {
		viewMovieControl = controlViewMovie;
		removeMovieUI = uiRemoveMovie;
		loginControl = controlLogin;
		addReviewUI = uiAddReview;
		rateMovie=uiRateMovie;
		viewReviewUI=uiViewReview;
		setVisible(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 44, 190, 0, 0, 0, 0, 0, 0, 0, 113, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 133, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		//Remove account button
		buttonRemoveMovie = new JButton("Remove Movie");
		buttonRemoveMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMovieUI.displayRemovalMovieWarning(movieID);
			}
		});
		
		buttonRemoveMovie.setVisible(false);
		
		buttonRemoveMovie.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_buttonRemoveMovie = new GridBagConstraints();
		gbc_buttonRemoveMovie.gridwidth = 3;
		gbc_buttonRemoveMovie.insets = new Insets(0, 0, 5, 5);
		gbc_buttonRemoveMovie.gridx = 8;
		gbc_buttonRemoveMovie.gridy = 1;
		add(buttonRemoveMovie, gbc_buttonRemoveMovie);
		
		//title label
		JLabel labelTitle = new JLabel("Title:");
		GridBagConstraints gbc_labelTitle = new GridBagConstraints();
		gbc_labelTitle.insets = new Insets(0, 0, 5, 5);
		gbc_labelTitle.gridx = 2;
		gbc_labelTitle.gridy = 2;
		add(labelTitle, gbc_labelTitle);
		
		//Label that displays the title of the movie
		labelShowTitle = new JLabel("");
		GridBagConstraints gbc_labelShowTitle= new GridBagConstraints();
		gbc_labelShowTitle.insets = new Insets(0, 0, 5, 5);
		gbc_labelShowTitle.gridx = 4;
		gbc_labelShowTitle.gridy = 2;
		add(labelShowTitle, gbc_labelShowTitle);
		
		rateButton = new JButton("Rate Movie");
		rateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rateMovie.displayRatingForm(movieID);
			}
		});
		GridBagConstraints gbc_rateButton = new GridBagConstraints();
		gbc_rateButton.gridwidth = 3;
		gbc_rateButton.insets = new Insets(0, 0, 5, 5);
		gbc_rateButton.gridx = 8;
		gbc_rateButton.gridy = 2;
		add(rateButton, gbc_rateButton);
		
		//Release year label
		JLabel labelReleaseYear = new JLabel("Release year:");
		GridBagConstraints gbc_labelReleaseYear = new GridBagConstraints();
		gbc_labelReleaseYear.insets = new Insets(0, 0, 5, 5);
		gbc_labelReleaseYear.gridx = 2;
		gbc_labelReleaseYear.gridy = 3;
		add(labelReleaseYear, gbc_labelReleaseYear);
		
		//Label that displays the release year of the movie
		labelShowReleaseYear = new JLabel("");
		GridBagConstraints gbc_labelShowReleaseYear = new GridBagConstraints();
		gbc_labelShowReleaseYear.insets = new Insets(0, 0, 5, 5);
		gbc_labelShowReleaseYear.gridx = 4;
		gbc_labelShowReleaseYear.gridy = 3;
		add(labelShowReleaseYear, gbc_labelShowReleaseYear);
		
		reviewButton = new JButton("Review Movie");
		reviewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			addReviewUI.displayLeaveReviewForm(movieID,title);
			setVisible(false);
			}
		});
		GridBagConstraints gbc_reviewButton = new GridBagConstraints();
		gbc_reviewButton.gridwidth = 3;
		gbc_reviewButton.insets = new Insets(0, 0, 5, 5);
		gbc_reviewButton.gridx = 8;
		gbc_reviewButton.gridy = 3;
		add(reviewButton, gbc_reviewButton);
		
		//Genre label
		JLabel labelGenre = new JLabel("Genre:");
		GridBagConstraints gbc_labelGenre = new GridBagConstraints();
		gbc_labelGenre.insets = new Insets(0, 0, 5, 5);
		gbc_labelGenre.gridx = 2;
		gbc_labelGenre.gridy = 4;
		add(labelGenre, gbc_labelGenre);
		
		//Label that displays the genre of the movie
		labelShowGenre = new JLabel("");
		GridBagConstraints gbc_labelShowGenre = new GridBagConstraints();
		gbc_labelShowGenre.insets = new Insets(0, 0, 5, 5);
		gbc_labelShowGenre.gridx = 4;
		gbc_labelShowGenre.gridy = 4;
		add(labelShowGenre, gbc_labelShowGenre);
		
		//Length label
		JLabel LabelLength = new JLabel("Length:");
		GridBagConstraints gbc_LabelLength = new GridBagConstraints();
		gbc_LabelLength.insets = new Insets(0, 0, 5, 5);
		gbc_LabelLength.gridx = 2;
		gbc_LabelLength.gridy = 5;
		add(LabelLength, gbc_LabelLength);
		
		//Label that displays the Length of the movie
		labelShowLength = new JLabel("");
		GridBagConstraints gbc_labelShowLength = new GridBagConstraints();
		gbc_labelShowLength.insets = new Insets(0, 0, 5, 5);
		gbc_labelShowLength.gridx = 4;
		gbc_labelShowLength.gridy = 5;
		add(labelShowLength, gbc_labelShowLength);
		
		layeredPane_1 = new JLayeredPane();
		GridBagConstraints gbc_layeredPane_1 = new GridBagConstraints();
		gbc_layeredPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_layeredPane_1.fill = GridBagConstraints.BOTH;
		gbc_layeredPane_1.gridx = 2;
		gbc_layeredPane_1.gridy = 7;
		add(layeredPane_1, gbc_layeredPane_1);
		GridBagLayout gbl_layeredPane_1 = new GridBagLayout();
		gbl_layeredPane_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_layeredPane_1.rowHeights = new int[]{0, 54, 0};
		gbl_layeredPane_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_layeredPane_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		layeredPane_1.setLayout(gbl_layeredPane_1);
		
		JLabel movieRateLabel = new JLabel("Movie Rate:");
		GridBagConstraints gbc_movieRateLabel = new GridBagConstraints();
		gbc_movieRateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_movieRateLabel.gridx = 1;
		gbc_movieRateLabel.gridy = 0;
		layeredPane_1.add(movieRateLabel, gbc_movieRateLabel);
		
		//JLabel rateLabelnum = new JLabel(""+movie.getRatingScore());
		JLabel rateLabelnum = new JLabel(""+rating);
		GridBagConstraints gbc_rateLabelnum = new GridBagConstraints();
		gbc_rateLabelnum.insets = new Insets(0, 0, 0, 5);
		gbc_rateLabelnum.gridx = 1;
		gbc_rateLabelnum.gridy = 1;
		layeredPane_1.add(rateLabelnum, gbc_rateLabelnum);
		
		viewReviewButton = new JButton("View Reviews");
		viewReviewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//viewReviewUI.displayLeaveReviewForm(movieID);
			}
		});
		GridBagConstraints gbc_viewReviewButton = new GridBagConstraints();
		gbc_viewReviewButton.insets = new Insets(0, 0, 5, 5);
		gbc_viewReviewButton.gridx = 9;
		gbc_viewReviewButton.gridy = 7;
		add(viewReviewButton, gbc_viewReviewButton);

	}
	
	
	public void displayMovie(MovieObject movie) {
		this.movieID=movie.getMovieId();
		this.member=loginControl.getCurrentMember();
		this.rating=movie.getAverageRating();
		this.title=movie.getTitle();
		labelShowTitle.setText(movie.getTitle());
		labelShowReleaseYear.setText(""+movie.getYear());
		labelShowGenre.setText(movie.getGenre());
		labelShowLength.setText(""+movie.getLength());
	
		//check if remove member button should be displayed.
		//It should be displayed if the actor is an administrator or they are viewing their own member account.
		MemberObject logInMember = loginControl.getCurrentMember();
		boolean memberMatch = false;
		if (logInMember != null)
		{
			memberMatch = logInMember.getUsername().equals(member.getUsername());
		}

		boolean adminCheck = (loginControl.getCurrentAdmin() != null);

		if (adminCheck){
			buttonRemoveMovie.setVisible(true);
			reviewButton.setVisible(true);
			rateButton.setVisible(true);

		}
		else if (memberMatch) {
			buttonRemoveMovie.setVisible(false);
			reviewButton.setVisible(true);
			rateButton.setVisible(true);
		}
		else
		{
			buttonRemoveMovie.setVisible(false);
			reviewButton.setVisible(false);
			rateButton.setVisible(false);
		}
		
		setVisible(true);
	}
}