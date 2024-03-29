/******************************************************************************************************************************
 * AddMovieUI
 * @author Sharon
 * Description:	Displays add movie form, extracts user input, and displays add movie confirmation or error.
 ******************************************************************************************************************************/


import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

public class AddMovieUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private AddMovieControl addMovieControl;
	private JTextField textFieldTitle;
	private JComboBox<String> comboBoxYear;
	private JComboBox<String> comboBoxGenre;
	private JSpinner spinnerLength; 
	private JLabel labelAddMovieStatus;
	

	public AddMovieUI(AddMovieControl controlAdd) {
		addMovieControl = controlAdd;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{27, 0, 2, 25, 50, 40, 0, 26, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 15, 32, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Color fontColor = UISettings.getFontColor();
		
		//Title label
		JLabel labelTitle = new JLabel("Title: ");
		labelTitle.setForeground(fontColor);
		GridBagConstraints gbc_labelTitle = new GridBagConstraints();
		gbc_labelTitle.insets = new Insets(0, 0, 5, 5);
		gbc_labelTitle.gridx = 1;
		gbc_labelTitle.gridy = 2;
		add(labelTitle, gbc_labelTitle);
		
		//Title textfield
		textFieldTitle = new JTextField();
		textFieldTitle.setDocument(new LengthRestrictedDocument(150));
		GridBagConstraints gbc_textFieldTitle = new GridBagConstraints();
		gbc_textFieldTitle.gridwidth = 4;
		gbc_textFieldTitle.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTitle.gridx = 3;
		gbc_textFieldTitle.gridy = 2;
		add(textFieldTitle, gbc_textFieldTitle);
		textFieldTitle.setColumns(10);
		
		//Year label
		JLabel labelYear = new JLabel("Release Year: ");
		labelYear.setForeground(fontColor);
		GridBagConstraints gbc_labelYear = new GridBagConstraints();
		gbc_labelYear.insets = new Insets(0, 0, 5, 5);
		gbc_labelYear.gridx = 1;
		gbc_labelYear.gridy = 3;
		add(labelYear, gbc_labelYear);
		
		//Initialize valid years (1910 to the current year)
		ArrayList<String> years = new ArrayList<String>();
		years.add("Select a release year");
		for (int year = Calendar.getInstance().get(Calendar.YEAR); year >= 1910; year--)
		{
			years.add(String.valueOf(year));
		}
		
		//Year drop down menu
		comboBoxYear = new JComboBox(years.toArray());
		GridBagConstraints gbc_comboBoxYear = new GridBagConstraints();
		gbc_comboBoxYear.gridwidth = 4;
		gbc_comboBoxYear.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxYear.gridx = 3;
		gbc_comboBoxYear.gridy = 3;
		add(comboBoxYear, gbc_comboBoxYear);
		
		//Genre label
		JLabel labelGenre = new JLabel("Genre: ");
		labelGenre.setForeground(fontColor);
		GridBagConstraints gbc_labelGenre = new GridBagConstraints();
		gbc_labelGenre.insets = new Insets(0, 0, 5, 5);
		gbc_labelGenre.gridx = 1;
		gbc_labelGenre.gridy = 4;
		add(labelGenre, gbc_labelGenre);
		
		//initializing available genres
		String[] genreList = {"Select a genre","Action", "Adventure","Animation","Comedy","Crime","Documentary","Drama","Horror","Thriller","Romance","Science Fiction","Fantasy","War","Western"};
		
		//Genre drop down menu
		comboBoxGenre = new JComboBox(genreList);
		GridBagConstraints gbc_comboBoxGenre = new GridBagConstraints();
		gbc_comboBoxGenre.gridwidth = 4;
		gbc_comboBoxGenre.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxGenre.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxGenre.gridx = 3;
		gbc_comboBoxGenre.gridy = 4;
		add(comboBoxGenre, gbc_comboBoxGenre);
		
		
		//Length label
		JLabel labelLength = new JLabel("Length: ");
		labelLength.setForeground(fontColor);
		GridBagConstraints gbc_labelLength = new GridBagConstraints();
		gbc_labelLength.insets = new Insets(0, 0, 5, 5);
		gbc_labelLength.gridx = 1;
		gbc_labelLength.gridy = 5;
		add(labelLength, gbc_labelLength);
		
		//Movie Length JSpinner
		SpinnerModel model = new SpinnerNumberModel(60, 1, 500,1); 		//SpinnerNumberModel format: initial value, min, max, step
		spinnerLength = new JSpinner(model);
		//make it so only numbers can be entered into the spinner textfield
		spinnerLength.setEditor(new JSpinner.NumberEditor(spinnerLength,"###"));
		JFormattedTextField txt = ((JSpinner.NumberEditor) spinnerLength.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
		
		GridBagConstraints gbc_spinnerLength = new GridBagConstraints();
		gbc_spinnerLength.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerLength.gridx = 4;
		gbc_spinnerLength.gridy = 5;
		add(spinnerLength, gbc_spinnerLength);
		
		JLabel lblNewLabel = new JLabel("mins");
		lblNewLabel.setForeground(fontColor);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 5;
		add(lblNewLabel, gbc_lblNewLabel);
		
		//Another panel for the add movie button and success/error label so they will be centered properly
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 6;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 7;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 22, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		//Add movie button
		JButton buttonAddMovie = new JButton("Add Movie");
		buttonAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extractMovieDetails();
			}
		});
		GridBagConstraints gbc_buttonAddMovie = new GridBagConstraints();
		gbc_buttonAddMovie.insets = new Insets(0, 0, 5, 0);
		gbc_buttonAddMovie.gridx = 0;
		gbc_buttonAddMovie.gridy = 0;
		panel.add(buttonAddMovie, gbc_buttonAddMovie);
		
		//Movie addition success/error label
		labelAddMovieStatus = new JLabel("");
		labelAddMovieStatus.setForeground(fontColor);
		GridBagConstraints gbc_labelAddMovieStatus = new GridBagConstraints();
		gbc_labelAddMovieStatus.gridx = 0;
		gbc_labelAddMovieStatus.gridy = 1;
		panel.add(labelAddMovieStatus, gbc_labelAddMovieStatus);
		
		setOpaque(false);
		setVisible(false);
	}
	
	
	public void displayAddMovieForm() {
		//clear fields before redisplaying
		textFieldTitle.setText("");
		comboBoxYear.setSelectedIndex(0);
		comboBoxGenre.setSelectedIndex(0);
		labelAddMovieStatus.setText("");
		spinnerLength.setValue(Integer.valueOf(60));
		
		//display the form
		setVisible(true);
	}
	
	public void extractMovieDetails() {
		String title = textFieldTitle.getText();
		String year = comboBoxYear.getSelectedItem().toString();
		String genre = comboBoxGenre.getSelectedItem().toString();
		
		//check if fields are blank
		if (title.trim().isEmpty() || year.equals("Select a release year") || genre.equals("Select a genre"))
		{
			labelAddMovieStatus.setText("Fields must non-blank to add a movie");
		}
		else {
			int releaseYear = Integer.parseInt(year);
			int length = (int) spinnerLength.getValue();
			
			boolean addResult = addMovieControl.processAddMovie(title, releaseYear, genre, length);
			if (addResult){
				displayAddMovieConfirmation();
			}
			else {
				displayAddMovieFailure();
			}
		}
	}

	public void displayAddMovieConfirmation() {
		labelAddMovieStatus.setText("Movie successfully added!");
	}
	
	public void displayAddMovieFailure() {
		labelAddMovieStatus.setText("Movie was not added");
	}
}
