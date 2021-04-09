import java.awt.EventQueue;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLayeredPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import javax.swing.JScrollPane;

public class SearchMovieUI extends JPanel {

	private SearchMovieControl searchMovieControl;
	private JFrame frame;
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
	private JPanel scrollPanel;
	
	public SearchMovieUI(SearchMovieControl control) {
		frame = new JFrame();
		searchMovieControl = control;
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 44, 60, 0, 60, 76, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 31, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		searchMovieLabel = new JLabel("Search Movie");
		searchMovieLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_searchMovieLabel = new GridBagConstraints();
		gbc_searchMovieLabel.anchor = GridBagConstraints.NORTH;
		gbc_searchMovieLabel.gridwidth = 3;
		gbc_searchMovieLabel.insets = new Insets(0, 0, 5, 5);
		gbc_searchMovieLabel.gridx = 3;
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
		gbc_textFieldSearch.gridwidth = 3;
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
		comboBoxGenre.setModel(new DefaultComboBoxModel<String>(new String[] {"Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama", "Fantasy", "Horror", "Romance", "Science Fiction", "Thriller", "War", "Western"}));
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
		for (int year = Calendar.getInstance().get(Calendar.YEAR); year >= 1910; year--)
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
		
		spinnerLengthLowerLimit = new JSpinner();
		spinnerLengthLowerLimit.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
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
		
		spinnerLengthUpperLimit = new JSpinner();
		spinnerLengthUpperLimit.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
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
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extractSearchInput();
			}
		});
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.anchor = GridBagConstraints.WEST;
		gbc_searchButton.gridwidth = 2;
		gbc_searchButton.insets = new Insets(0, 0, 5, 5);
		gbc_searchButton.gridx = 2;
		gbc_searchButton.gridy = 5;
		add(searchButton, gbc_searchButton);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 6;
		add(scrollPane, gbc_scrollPane);
		scrollPane.setVisible(false);
		
		labelSearchMovieStatus = new JLabel("");
		labelSearchMovieStatus.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelSearchMovieStatus = new GridBagConstraints();
		gbc_labelSearchMovieStatus.gridwidth = 6;
		gbc_labelSearchMovieStatus.insets = new Insets(0, 0, 0, 5);
		gbc_labelSearchMovieStatus.gridx = 1;
		gbc_labelSearchMovieStatus.gridy = 9;
		add(labelSearchMovieStatus, gbc_labelSearchMovieStatus);
	}
	
	public void displaySearchForm() {
		// begin-user-code
		// TODO Auto-generated method stub
		setVisible(true);
	}
	
	private void extractSearchInput() {
		String title = titleSearchField.getText();
		String genre = comboBoxGenre.getSelectedItem().toString();
		
		int lowerYear = 0;
		int upperYear = 0;
		try {
			lowerYear = Integer.parseInt(comboBoxLowerYear.getSelectedItem().toString());
			upperYear = Integer.parseInt(comboBoxUpperYear.getSelectedItem().toString());
		} catch(NumberFormatException nfe) {
			System.err.println("Year range input error: " + nfe.getMessage());
		}
		
		try {
			spinnerLengthLowerLimit.commitEdit();
			spinnerLengthUpperLimit.commitEdit();
		} catch(java.text.ParseException e) {
			System.err.println("Lower length spinner input error: " + e.getMessage());
		}
		int lowerLength = (Integer)spinnerLengthLowerLimit.getValue();
		int upperLength = (Integer)spinnerLengthUpperLimit.getValue();
		
		System.out.println("Title: " + title);
		System.out.println("Genre: " + genre);
		System.out.println("Year range: " + lowerYear + " to " + upperYear);
		System.out.println("Length range: " + lowerLength + "min. to " + upperLength + "min.");
		
		// retrive movies matching search parameters
		//ArrayList<MovieObject> movies = searchMovieControl.processSearchMovie(title, lowerYear, upperYear, genre, lowerLength, upperLength);
		
		// TEMP: Delete when SearchMovie is implemented in DataManager, and uncomment above line
		ArrayList<MovieObject> movies = new ArrayList<MovieObject>();
		movies.add(new MovieObject("Ferris Bueller's Day Off",1986,"Comedy",103,5,1));
		movies.add(new MovieObject("12 Angry Men",1957,"Drama",97,5,2));
		movies.add(new MovieObject("My Life as a Zucchini",2016,"Animation",66,4,3));
		movies.add(new MovieObject("Train to Busan",2016,"Thriller",118,4,4));
		
		if(movies == null) {
			displayFailedSearchMessage();
		} else {
			displaySearchResult(movies);
		}
	}

	public void displaySearchResult(ArrayList<MovieObject> movies) {
		// TODO
		System.out.println("Here's where we display search results");
		
		scrollPanel = new JPanel();
		
		for(MovieObject movie : movies) {
			JLabel movieInfo = new JLabel(movie.getTitle() + " (" + movie.getYear() + ")");
			
			scrollPanel.add(movieInfo);
		}
		scrollPane.setViewportView(scrollPanel);
		
		/*
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				Point mousePointer = MouseInfo.getPointerInfo().getLocation();
				SwingUtilities.convertPointFromScreen(mousePointer,scrollPanel);
				Component movieLabel = scrollPanel.getComponentAt(mousePointer);
				
			}
		});*/
		
		// make results visible
		scrollPane.setVisible(true);
	}

	public void displayFailedSearchMessage() {
		// begin-user-code
		// TODO Auto-generated method stub
		labelSearchMovieStatus.setText("Movie search was unsuccessful. Information was invalid.");
		// end-user-code
	}

	public void enterMovieSearchCriteria() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}