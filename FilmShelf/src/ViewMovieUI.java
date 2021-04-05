

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
	private JLabel labelShowTitle;
	private JLabel labelShowReleaseYear;
	private JLabel labelShowGenre;
	private JLabel labelShowLength;
	private JButton buttonRemoveMovie;
	
	/**
	 * Create the panel.
	 */
	public ViewMovieUI(ViewMovieControl controlViewMovie, RemoveMovieUI uiRemoveMovie) {
		viewMovieControl = controlViewMovie;
		removeMovieUI = uiRemoveMovie;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 141, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 133, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		//Remove account button
		buttonRemoveMovie = new JButton("Remove Movie");
		buttonRemoveMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMovieUI.displayRemovalMovieWarning(movieId);
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
	}
	
	
	public void displayMovie(MovieObject movie) {
		labelShowTitle.setText(movie.getTitle());
		labelShowReleaseYear.setText(""+movie.getYear());
		labelShowGenre.setText(movie.getGenre());
		labelShowLength.setText(""+movie.getLength());
		
	}
}