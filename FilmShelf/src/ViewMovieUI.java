
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ViewMovieUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private ViewMovieControl viewMovieControl;
	private RemoveMovieUI removeMovieUI;
	private AddReviewUI addReviewUI;
	private JLabel labelShowTitle;
	private JLabel labelShowReleaseYear;
	private JLabel labelShowGenre;
	private JLabel labelShowLength;
	private JLabel averageRatingLabel;
	private JButton buttonRemoveMovie;
	private LoginControl loginControl;
	private JButton reviewButton;
	private int movieID;

	private JButton rateButton;
	private RateMovieUI rateMovieUI;
	private RateMovieControl rateMovieControl;
	private MemberObject member;
	private JLayeredPane layeredPane_1;
	double rating;
	private JButton viewReviewButton;
	private String title;
	private ViewReviewUI viewReviewUI;
	private JLayeredPane layeredPane;
	private JLabel userRatingLabel;
	private JLabel userRatingNumLabel;

	public ViewMovieUI(LoginControl controlLogin, ViewMovieControl controlViewMovie, RemoveMovieUI uiRemoveMovie,  AddReviewUI uiAddReview, ViewReviewUI uiViewReview, RateMovieUI uiRateMovie, RateMovieControl rmControl) {
		loginControl = controlLogin;
		viewMovieControl = controlViewMovie;
		removeMovieUI = uiRemoveMovie;
		addReviewUI = uiAddReview;
		viewReviewUI = uiViewReview;
		rateMovieUI = uiRateMovie;
		rateMovieControl = rmControl;
		setVisible(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 10, 78, 30, 150, 0, 0, 0, 170, 10, 0};
		gridBagLayout.rowHeights = new int[]{0, 18, 21, 16, 0, 0, 0, 42, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Color fontColor = UISettings.getFontColor();
		
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
		gbc_buttonRemoveMovie.anchor = GridBagConstraints.EAST;
		gbc_buttonRemoveMovie.gridwidth = 4;
		gbc_buttonRemoveMovie.insets = new Insets(0, 0, 5, 5);
		gbc_buttonRemoveMovie.gridx = 5;
		gbc_buttonRemoveMovie.gridy = 0;
		add(buttonRemoveMovie, gbc_buttonRemoveMovie);
		
		//Label that displays the title of the movie
		labelShowTitle = new JLabel("");
		labelShowTitle.setFont(new Font("Elephant", Font.BOLD, 34));
		labelShowTitle.setForeground(fontColor);
		GridBagConstraints gbc_labelShowTitle = new GridBagConstraints();
		gbc_labelShowTitle.anchor = GridBagConstraints.WEST;
		gbc_labelShowTitle.insets = new Insets(0, 0, 5, 5);
		gbc_labelShowTitle.gridx = 2;
		gbc_labelShowTitle.gridy = 1;
		add(labelShowTitle, gbc_labelShowTitle);
		
		//Label that displays the release year of the movie
		labelShowReleaseYear = new JLabel("");
		labelShowReleaseYear.setForeground(fontColor);
		labelShowReleaseYear.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		GridBagConstraints gbc_labelShowReleaseYear = new GridBagConstraints();
		gbc_labelShowReleaseYear.insets = new Insets(12, 5, 5, 5);
		gbc_labelShowReleaseYear.gridx = 3;
		gbc_labelShowReleaseYear.gridy = 1;
		add(labelShowReleaseYear, gbc_labelShowReleaseYear);
		
		//Label that displays the genre of the movie
		labelShowGenre = new JLabel("");
		labelShowGenre.setFont(new Font("Ebrima", Font.PLAIN, 14));
		labelShowGenre.setForeground(fontColor);
		GridBagConstraints gbc_labelShowGenre = new GridBagConstraints();
		gbc_labelShowGenre.anchor = GridBagConstraints.WEST;
		gbc_labelShowGenre.insets = new Insets(0, 5, 5, 5);
		gbc_labelShowGenre.gridx = 2;
		gbc_labelShowGenre.gridy = 2;
		add(labelShowGenre, gbc_labelShowGenre);
		
		//Label that displays the Length of the movie
		labelShowLength = new JLabel("");
		labelShowLength.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		labelShowLength.setForeground(fontColor);
		GridBagConstraints gbc_labelShowLength = new GridBagConstraints();
		gbc_labelShowLength.anchor = GridBagConstraints.WEST;
		gbc_labelShowLength.insets = new Insets(0, 5, 5, 5);
		gbc_labelShowLength.gridx = 2;
		gbc_labelShowLength.gridy = 3;
		add(labelShowLength, gbc_labelShowLength);
		
		layeredPane_1 = new JLayeredPane();
		GridBagConstraints gbc_layeredPane_1 = new GridBagConstraints();
		gbc_layeredPane_1.anchor = GridBagConstraints.EAST;
		gbc_layeredPane_1.gridwidth = 4;
		gbc_layeredPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_layeredPane_1.fill = GridBagConstraints.VERTICAL;
		gbc_layeredPane_1.gridx = 5;
		gbc_layeredPane_1.gridy = 2;
		add(layeredPane_1, gbc_layeredPane_1);
		GridBagLayout gbl_layeredPane_1 = new GridBagLayout();
		gbl_layeredPane_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_layeredPane_1.rowHeights = new int[]{0, 7, 0};
		gbl_layeredPane_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_layeredPane_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		layeredPane_1.setLayout(gbl_layeredPane_1);
		
		JLabel movieRateLabel = new JLabel("Average Rating:");
		movieRateLabel.setFont(new Font("Ebrima", Font.PLAIN, 14));
		movieRateLabel.setForeground(fontColor);
		GridBagConstraints gbc_movieRateLabel = new GridBagConstraints();
		gbc_movieRateLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_movieRateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_movieRateLabel.gridx = 1;
		gbc_movieRateLabel.gridy = 0;
		layeredPane_1.add(movieRateLabel, gbc_movieRateLabel);

		
		averageRatingLabel = new JLabel(""+rating);
		averageRatingLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		averageRatingLabel.setForeground(fontColor);
		GridBagConstraints gbc_rateLabelnum = new GridBagConstraints();
		gbc_rateLabelnum.anchor = GridBagConstraints.NORTH;
		gbc_rateLabelnum.insets = new Insets(1, 0, 0, 5);
		gbc_rateLabelnum.gridx = 2;
		gbc_rateLabelnum.gridy = 0;
		layeredPane_1.add(averageRatingLabel, gbc_rateLabelnum);
		
		layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.anchor = GridBagConstraints.NORTHEAST;
		gbc_layeredPane.gridwidth = 4;
		gbc_layeredPane.insets = new Insets(0, 0, 5, 5);
		gbc_layeredPane.gridx = 5;
		gbc_layeredPane.gridy = 1;
		add(layeredPane, gbc_layeredPane);
		GridBagLayout gbl_layeredPane = new GridBagLayout();
		gbl_layeredPane.columnWidths = new int[]{-1, 120, 32, 0};
		gbl_layeredPane.rowHeights = new int[]{0, 6, 0};
		gbl_layeredPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_layeredPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		layeredPane.setLayout(gbl_layeredPane);
		
		userRatingLabel = new JLabel("User Rating:");
		userRatingLabel.setFont(new Font("Ebrima", Font.PLAIN, 14));
		userRatingLabel.setForeground(fontColor);
		GridBagConstraints gbc_movieRateLabel_1 = new GridBagConstraints();
		gbc_movieRateLabel_1.anchor = GridBagConstraints.NORTHEAST;
		gbc_movieRateLabel_1.insets = new Insets(20, 0, 5, 5);
		gbc_movieRateLabel_1.gridx = 1;
		gbc_movieRateLabel_1.gridy = 0;
		layeredPane.add(userRatingLabel, gbc_movieRateLabel_1);
		
		userRatingNumLabel = new JLabel("");
		userRatingNumLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		userRatingNumLabel.setForeground(fontColor);
		GridBagConstraints gbc_rateLabelnum_1 = new GridBagConstraints();
		gbc_rateLabelnum_1.anchor = GridBagConstraints.NORTHEAST;
		gbc_rateLabelnum_1.insets = new Insets(21, 0, 0, 5);
		gbc_rateLabelnum_1.gridx = 2;
		gbc_rateLabelnum_1.gridy = 0;
		layeredPane.add(userRatingNumLabel, gbc_rateLabelnum_1);
		
		reviewButton = new JButton("Review Movie");
		reviewButton.setPreferredSize(new Dimension(120,25));
		reviewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addReviewUI.displayLeaveReviewForm(movieID,title);
				setVisible(false);
			}
		});
		GridBagConstraints gbc_reviewButton = new GridBagConstraints();
		gbc_reviewButton.anchor = GridBagConstraints.EAST;
		gbc_reviewButton.gridwidth = 4;
		gbc_reviewButton.insets = new Insets(0, 0, 5, 5);
		gbc_reviewButton.gridx = 5;
		gbc_reviewButton.gridy = 4;
		add(reviewButton, gbc_reviewButton);
		
		rateButton = new JButton("Rate Movie");
		rateButton.setPreferredSize(new Dimension(120,25));
		rateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rateMovieUI.displayRatingForm(movieID);
				setVisible(false);
			}
		});
		GridBagConstraints gbc_rateButton = new GridBagConstraints();
		gbc_rateButton.anchor = GridBagConstraints.EAST;
		gbc_rateButton.gridwidth = 4;
		gbc_rateButton.insets = new Insets(0, 0, 5, 5);
		gbc_rateButton.gridx = 5;
		gbc_rateButton.gridy = 3;
		add(rateButton, gbc_rateButton);
		
		setOpaque(false);
		
		viewReviewButton = new JButton("View Reviews");
		viewReviewButton.setPreferredSize(new Dimension(120,25));
		viewReviewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		viewReviewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				viewReviewUI.displayReview(title,movieID);
				setVisible(false);
			}
		});
		GridBagConstraints gbc_viewReviewButton = new GridBagConstraints();
		gbc_viewReviewButton.anchor = GridBagConstraints.EAST;
		gbc_viewReviewButton.insets = new Insets(0, 0, 5, 5);
		gbc_viewReviewButton.gridx = 8;
		gbc_viewReviewButton.gridy = 5;
		add(viewReviewButton, gbc_viewReviewButton);
		setVisible(false);
	}
	
	public void displayMovie(MovieObject targetMovie) {
		MovieObject movie = viewMovieControl.processViewMovie(targetMovie.getMovieId());
		RatingObject rating = rateMovieControl.getRating(targetMovie.getMovieId());
		
		this.movieID = movie.getMovieId();
		this.member = loginControl.getCurrentMember();
		this.rating = movie.getAverageRating();
		this.title = movie.getTitle();
		labelShowTitle.setText(movie.getTitle());
		labelShowReleaseYear.setText(""+movie.getYear());
		labelShowGenre.setText(movie.getGenre());
		labelShowLength.setText(movie.getLength()+" mins");
		
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		averageRatingLabel.setText(df.format(movie.getAverageRating()));
		
		
		if(rating != null) {
			userRatingNumLabel.setText(String.valueOf(rating.getRatingScore()));
		} else {
			userRatingNumLabel.setText("");
		}
	
		//check if remove member button should be displayed.
		//It should be displayed if the actor is an administrator or they are viewing their own member account.
		MemberObject logInMember = loginControl.getCurrentMember();
		boolean memberMatch = false;
		if (logInMember != null)
		{
			memberMatch = logInMember.getUsername().equals(member.getUsername());
		}

		boolean adminCheck = (loginControl.getCurrentAdmin() != null);

		if(adminCheck) {
			buttonRemoveMovie.setVisible(true);
			reviewButton.setVisible(false);
			rateButton.setVisible(false);
			viewReviewButton.setVisible(true);
			userRatingLabel.setVisible(false);
			userRatingNumLabel.setVisible(false);
		} else if (memberMatch) {
			buttonRemoveMovie.setVisible(false);
			reviewButton.setVisible(true);
			rateButton.setVisible(true);
			viewReviewButton.setVisible(true);
			userRatingLabel.setVisible(true);
			userRatingNumLabel.setVisible(true);
		} else {
			buttonRemoveMovie.setVisible(false);
			reviewButton.setVisible(false);
			rateButton.setVisible(false);
			viewReviewButton.setVisible(true);
			userRatingLabel.setVisible(false);
			userRatingNumLabel.setVisible(false);
		}
		
		setVisible(true);
	}
}