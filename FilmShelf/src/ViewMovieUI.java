

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewMovieUI extends JPanel {

	private ViewMovieControl viewMovieControl;
	private RemoveMovieUI removeMovieUI;
	private SearchMovieUI searchMovieUI;
	private JLabel labelShowTitle;
	private JLabel labelShowReleaseYear;
	private JLabel labelShowGenre;
	private JLabel labelShowLength;
	private JButton buttonRemoveMovie;
	private LoginControl loginControl;
	int movieID;
	private JButton reviewButton;
	private JLayeredPane layeredPane;
	private JLabel labelTopReviews;
	private JLabel labelReview1;
	private JLabel labelReview2;
	private JLabel labelReview3;
	private JLabel labelReview4;
	private JLabel labelReview5;
	/**
	 * Create the panel.
	 */
	public ViewMovieUI(ViewMovieControl controlViewMovie, RemoveMovieUI uiRemoveMovie, LoginControl controlLogin) {
		viewMovieControl = controlViewMovie;
		removeMovieUI = uiRemoveMovie;
		loginControl = controlLogin;
		
		setVisible(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 141, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 133, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbc_buttonRemoveMovie.insets = new Insets(0, 0, 5, 5);
		gbc_buttonRemoveMovie.gridx = 9;
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
		
		reviewButton = new JButton("Review Movie");
		GridBagConstraints gbc_reviewButton = new GridBagConstraints();
		gbc_reviewButton.insets = new Insets(0, 0, 5, 5);
		gbc_reviewButton.gridx = 9;
		gbc_reviewButton.gridy = 2;
		add(reviewButton, gbc_reviewButton);
		
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
		
		layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.gridwidth = 7;
		gbc_layeredPane.insets = new Insets(0, 0, 5, 5);
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 2;
		gbc_layeredPane.gridy = 7;
		add(layeredPane, gbc_layeredPane);
		GridBagLayout gbl_layeredPane = new GridBagLayout();
		gbl_layeredPane.columnWidths = new int[]{0, 0, 0, 148, 0};
		gbl_layeredPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_layeredPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_layeredPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		layeredPane.setLayout(gbl_layeredPane);
		
		labelTopReviews = new JLabel("Top Reviews");
		GridBagConstraints gbc_labelTopReviews = new GridBagConstraints();
		gbc_labelTopReviews.gridwidth = 2;
		gbc_labelTopReviews.insets = new Insets(0, 0, 5, 0);
		gbc_labelTopReviews.gridx = 2;
		gbc_labelTopReviews.gridy = 0;
		layeredPane.add(labelTopReviews, gbc_labelTopReviews);
		
		labelReview1 = new JLabel("");
		GridBagConstraints gbc_labelReview1 = new GridBagConstraints();
		gbc_labelReview1.insets = new Insets(0, 0, 5, 0);
		gbc_labelReview1.gridx = 3;
		gbc_labelReview1.gridy = 1;
		layeredPane.add(labelReview1, gbc_labelReview1);
		
		labelReview2 = new JLabel("");
		GridBagConstraints gbc_labelReview2 = new GridBagConstraints();
		gbc_labelReview2.insets = new Insets(0, 0, 5, 0);
		gbc_labelReview2.gridx = 3;
		gbc_labelReview2.gridy = 2;
		layeredPane.add(labelReview2, gbc_labelReview2);
		
		labelReview3 = new JLabel("");
		GridBagConstraints gbc_labelReview3 = new GridBagConstraints();
		gbc_labelReview3.insets = new Insets(0, 0, 5, 0);
		gbc_labelReview3.gridx = 3;
		gbc_labelReview3.gridy = 3;
		layeredPane.add(labelReview3, gbc_labelReview3);
		
		labelReview4 = new JLabel("");
		GridBagConstraints gbc_labelReview4 = new GridBagConstraints();
		gbc_labelReview4.insets = new Insets(0, 0, 5, 0);
		gbc_labelReview4.gridx = 3;
		gbc_labelReview4.gridy = 4;
		layeredPane.add(labelReview4, gbc_labelReview4);
		
		labelReview5 = new JLabel("");
		GridBagConstraints gbc_labelReview5 = new GridBagConstraints();
		gbc_labelReview5.gridx = 3;
		gbc_labelReview5.gridy = 5;
		layeredPane.add(labelReview5, gbc_labelReview5);
	}
	
	
	public void displayMovie(MovieObject movie) {
		this.movieID=movie.getMovieId();
		labelShowTitle.setText(movie.getTitle());
		labelShowReleaseYear.setText(""+movie.getYear());
		labelShowGenre.setText(movie.getGenre());
		labelShowLength.setText(""+movie.getLength());
		
		if ((loginControl.getCurrentAdmin() != null)){
			buttonRemoveMovie.setVisible(true);
		}
		setVisible(true);
	}
}