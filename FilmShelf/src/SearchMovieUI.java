import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import javax.swing.JScrollPane;

/******************************************************************************************************************************
 * SearchMovieUI
 * @author Jo
 * Description: Provides the GUI for the SearchMovie use case.
 ******************************************************************************************************************************/
public class SearchMovieUI extends JPanel {
	private SearchMovieControl searchMovieControl;
	private ViewMovieUI viewMovieUI;
	private JTextField titleSearchField;
	private JLabel labelSearchMovieStatus;
	private JLabel genreLabel;
	private JLabel yearLabel;
	private JLabel lengthLabel;
	private JComboBox<String> comboBoxGenre;
	private JComboBox<String> comboBoxLowerYear;
	private JComboBox<String> comboBoxUpperYear;
	private JSpinner spinnerLengthLowerLimit;
	private JLabel lengthMinToLabel;
	private JLabel yearToLabel;
	private JSpinner spinnerLengthUpperLimit;
	private JLabel lengthMinLabel;
	private JLabel searchMovieLabel;
	private JButton searchButton;
	private JScrollPane scrollPane;
	private JList<String> scrollList;
	private DefaultListModel<String> listModel;
	private Pattern nonNumberPattern;
	private Matcher nonNumberMatcher;
	private JButton viewMovieButton;
	private ArrayList<MovieObject> movies;
	
	public SearchMovieUI(SearchMovieControl controlSearch, ViewMovieUI viewMovieUI) {
		nonNumberPattern = Pattern.compile("[^0-9]");		// matches characters that are not numbers
		
		searchMovieControl = controlSearch;
		this.viewMovieUI = viewMovieUI;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 44, 60, 0, 60, 76, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		searchMovieLabel = new JLabel("Search Movie");
		searchMovieLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_searchMovieLabel = new GridBagConstraints();
		gbc_searchMovieLabel.anchor = GridBagConstraints.NORTH;
		gbc_searchMovieLabel.gridwidth = 3;
		gbc_searchMovieLabel.insets = new Insets(0, 0, 5, 5);
		gbc_searchMovieLabel.gridx = 2;
		gbc_searchMovieLabel.gridy = 0;
		add(searchMovieLabel, gbc_searchMovieLabel);
		
		JLabel titleLabel = new JLabel("Title:");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.EAST;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 1;
		add(titleLabel, gbc_titleLabel);
		
		titleSearchField = new JTextField();
		GridBagConstraints gbc_textFieldSearch = new GridBagConstraints();
		gbc_textFieldSearch.gridwidth = 5;
		gbc_textFieldSearch.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSearch.gridx = 2;
		gbc_textFieldSearch.gridy = 1;
		add(titleSearchField, gbc_textFieldSearch);
		titleSearchField.setColumns(10);
		
		genreLabel = new JLabel("Genre:");
		GridBagConstraints gbc_genreLabel = new GridBagConstraints();
		gbc_genreLabel.anchor = GridBagConstraints.EAST;
		gbc_genreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_genreLabel.gridx = 1;
		gbc_genreLabel.gridy = 2;
		add(genreLabel, gbc_genreLabel);
		
		comboBoxGenre = new JComboBox<String>();
		comboBoxGenre.setModel(new DefaultComboBoxModel<String>(new String[] {"Select a genre","Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama", "Fantasy", "Horror", "Romance", "Science Fiction", "Thriller", "War", "Western"}));
		GridBagConstraints gbc_comboBoxGenre = new GridBagConstraints();
		gbc_comboBoxGenre.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxGenre.gridwidth = 3;
		gbc_comboBoxGenre.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxGenre.gridx = 2;
		gbc_comboBoxGenre.gridy = 2;
		add(comboBoxGenre, gbc_comboBoxGenre);
		
		//Initialize valid years (1910 to the current year)
		ArrayList<String> years = new ArrayList<String>();
		years.add("Year");
		for (int year = Calendar.getInstance().get(Calendar.YEAR); year >= 1900; year--)
		{
			years.add(String.valueOf(year));
		}
		
		yearLabel = new JLabel("Year:");
		yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_yearLabel = new GridBagConstraints();
		gbc_yearLabel.anchor = GridBagConstraints.EAST;
		gbc_yearLabel.insets = new Insets(0, 0, 5, 5);
		gbc_yearLabel.gridx = 1;
		gbc_yearLabel.gridy = 3;
		add(yearLabel, gbc_yearLabel);
		
		// Lower year drop down menu
		comboBoxLowerYear = new JComboBox(years.toArray());
		GridBagConstraints gbc_comboBoxLowerYear = new GridBagConstraints();
		gbc_comboBoxLowerYear.anchor = GridBagConstraints.WEST;
		gbc_comboBoxLowerYear.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxLowerYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxLowerYear.gridx = 2;
		gbc_comboBoxLowerYear.gridy = 3;
		add(comboBoxLowerYear, gbc_comboBoxLowerYear);
		
		yearToLabel = new JLabel("to");
		GridBagConstraints gbc_yearToLabel = new GridBagConstraints();
		gbc_yearToLabel.insets = new Insets(0, 0, 5, 5);
		gbc_yearToLabel.gridx = 3;
		gbc_yearToLabel.gridy = 3;
		add(yearToLabel, gbc_yearToLabel);
		
		// Lower year drop down menu
		comboBoxUpperYear = new JComboBox(years.toArray());
		GridBagConstraints gbc_comboBoxUpperYear = new GridBagConstraints();
		gbc_comboBoxUpperYear.anchor = GridBagConstraints.WEST;
		gbc_comboBoxUpperYear.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxUpperYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUpperYear.gridx = 4;
		gbc_comboBoxUpperYear.gridy = 3;
		add(comboBoxUpperYear, gbc_comboBoxUpperYear);
		
		lengthLabel = new JLabel("Length:");
		GridBagConstraints gbc_lengthLabel = new GridBagConstraints();
		gbc_lengthLabel.anchor = GridBagConstraints.EAST;
		gbc_lengthLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lengthLabel.gridx = 1;
		gbc_lengthLabel.gridy = 4;
		add(lengthLabel, gbc_lengthLabel);
		
		SpinnerModel lowerLengthModel = new SpinnerNumberModel(0,0,999,1);
		spinnerLengthLowerLimit = new JSpinner(lowerLengthModel);
		spinnerLengthLowerLimit.setEditor(new JSpinner.NumberEditor(spinnerLengthLowerLimit,"###"));
		JFormattedTextField lowerLengthTxt = ((JSpinner.NumberEditor) spinnerLengthLowerLimit.getEditor()).getTextField();
		((NumberFormatter) lowerLengthTxt.getFormatter()).setAllowsInvalid(false);
		GridBagConstraints gbc_spinnerLengthLowerLimit = new GridBagConstraints();
		gbc_spinnerLengthLowerLimit.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLengthLowerLimit.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerLengthLowerLimit.gridx = 2;
		gbc_spinnerLengthLowerLimit.gridy = 4;
		add(spinnerLengthLowerLimit, gbc_spinnerLengthLowerLimit);
		
		lengthMinToLabel = new JLabel("min. to");
		GridBagConstraints gbc_lengthMinToLabel = new GridBagConstraints();
		gbc_lengthMinToLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lengthMinToLabel.gridx = 3;
		gbc_lengthMinToLabel.gridy = 4;
		add(lengthMinToLabel, gbc_lengthMinToLabel);
		
		SpinnerModel upperLengthModel = new SpinnerNumberModel(150,0,999,1);
		spinnerLengthUpperLimit = new JSpinner(upperLengthModel);
		spinnerLengthUpperLimit.setEditor(new JSpinner.NumberEditor(spinnerLengthUpperLimit,"###"));
		JFormattedTextField upperLengthTxt = ((JSpinner.NumberEditor) spinnerLengthUpperLimit.getEditor()).getTextField();
		((NumberFormatter) upperLengthTxt.getFormatter()).setAllowsInvalid(false);
		GridBagConstraints gbc_spinnerLengthUpperLimit = new GridBagConstraints();
		gbc_spinnerLengthUpperLimit.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLengthUpperLimit.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerLengthUpperLimit.gridx = 4;
		gbc_spinnerLengthUpperLimit.gridy = 4;
		add(spinnerLengthUpperLimit, gbc_spinnerLengthUpperLimit);
		
		lengthMinLabel = new JLabel("min.");
		GridBagConstraints gbc_lengthMinLabel = new GridBagConstraints();
		gbc_lengthMinLabel.anchor = GridBagConstraints.WEST;
		gbc_lengthMinLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lengthMinLabel.gridx = 5;
		gbc_lengthMinLabel.gridy = 4;
		add(lengthMinLabel, gbc_lengthMinLabel);
		
		searchButton = new JButton("Search");
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.anchor = GridBagConstraints.WEST;
		gbc_searchButton.gridwidth = 2;
		gbc_searchButton.insets = new Insets(0, 0, 5, 5);
		gbc_searchButton.gridx = 2;
		gbc_searchButton.gridy = 5;
		add(searchButton, gbc_searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extractSearchInput();
			}
		});
		
		listModel = new DefaultListModel<String>();
		// JList setup
		scrollList = new JList<String>(listModel);
		scrollList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollList.setLayoutOrientation(JList.VERTICAL);
		scrollList.setVisibleRowCount(-1);
		
		scrollPane = new JScrollPane(scrollList);
		scrollPane.setPreferredSize(new Dimension(250, 80));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 6;
		add(scrollPane, gbc_scrollPane);
		scrollPane.setVisible(false);
		
		viewMovieButton = new JButton("View Movie");
		viewMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = scrollList.getSelectedIndex();
				if(selectedIndex != -1) {
					viewMovieUI.displayMovie(movies.get(selectedIndex));
				}
			}
		});
		GridBagConstraints gbc_viewMovieButton = new GridBagConstraints();
		gbc_viewMovieButton.anchor = GridBagConstraints.WEST;
		gbc_viewMovieButton.gridwidth = 2;
		gbc_viewMovieButton.insets = new Insets(0, 0, 0, 5);
		gbc_viewMovieButton.gridx = 1;
		gbc_viewMovieButton.gridy = 9;
		add(viewMovieButton, gbc_viewMovieButton);
		viewMovieButton.setVisible(false);
		
		labelSearchMovieStatus = new JLabel("");
		labelSearchMovieStatus.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelSearchMovieStatus = new GridBagConstraints();
		gbc_labelSearchMovieStatus.gridwidth = 6;
		gbc_labelSearchMovieStatus.insets = new Insets(0, 0, 5, 5);
		gbc_labelSearchMovieStatus.gridx = 1;
		gbc_labelSearchMovieStatus.gridy = 10;
		add(labelSearchMovieStatus, gbc_labelSearchMovieStatus);
		
		setVisible(false);
	}
	
	public void displaySearchForm() {
		// clear fields before displaying
		titleSearchField.setText("");
		
		// display the form
		setVisible(true);
	}
	
	private void extractSearchInput() {
		String title = titleSearchField.getText();
		String genre = comboBoxGenre.getSelectedItem().toString();
		
		int lowerYear = 0;
		int upperYear = 0;
		String lowerYearStr = comboBoxLowerYear.getSelectedItem().toString();
		String upperYearStr = comboBoxUpperYear.getSelectedItem().toString();
		
		try {
			nonNumberMatcher = nonNumberPattern.matcher(lowerYearStr);
			
			// read lower limit of year range
			if(!lowerYearStr.isBlank() && !nonNumberMatcher.find()) {
				lowerYear = Integer.parseInt(comboBoxLowerYear.getSelectedItem().toString());
			} else {
				lowerYear = -1;		// no year selected
			}
			
			nonNumberMatcher = nonNumberPattern.matcher(upperYearStr);
			
			// read upper limit of year range
			if(!upperYearStr.isBlank() && !nonNumberMatcher.find()) {
				upperYear = Integer.parseInt(comboBoxUpperYear.getSelectedItem().toString());
			} else {
				upperYear = -1;		// no year selected
			}
		} catch(NumberFormatException nfe) {
			System.err.println("Year range input error: " + nfe.getMessage());
		}
		
		try {
			spinnerLengthLowerLimit.commitEdit();
			spinnerLengthUpperLimit.commitEdit();
		} catch(ParseException e) {
			System.err.println("Spinner input error: " + e.getMessage());
		}
		int lowerLength = (Integer)spinnerLengthLowerLimit.getValue();
		int upperLength = (Integer)spinnerLengthUpperLimit.getValue();
		
		// retrive movies matching search parameters
		movies = searchMovieControl.processSearchMovie(title, lowerYear, upperYear, genre, lowerLength, upperLength);
		
		if(movies == null) {
			displayFailedSearchMessage();
			displaySearchResult(movies);
		} else {
			labelSearchMovieStatus.setText("");
			displaySearchResult(movies);
		}
	}

	public void displaySearchResult(ArrayList<MovieObject> movies) {
		listModel.removeAllElements();
		
		if(movies != null) {
			for(MovieObject movie : movies) {
				listModel.addElement(movie.getTitle() + " (" + movie.getYear() + ")");
			}
		}
		
		// make results visible
		revalidate();
		repaint();
		scrollPane.setVisible(true);
		viewMovieButton.setVisible(true);
	}

	public void displayFailedSearchMessage() {
		labelSearchMovieStatus.setForeground(Color.RED);
		labelSearchMovieStatus.setText("Invalid movie search.");
	}
}