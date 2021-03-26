/******************************************************************************************************************************
 * AddMovieUI
 * @author Sharon
 * Description:	Displays add movie form, extracts user input, and displays add movie confirmation or error.
 ******************************************************************************************************************************/


import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddMovieUI extends JPanel {
	private AddMovieControl addMovieControl;
	private JTextField textFieldTitle;
	private JComboBox<Integer> comboBoxYear;
	private JComboBox<String> comboBoxGenre;
	
	/**
	 * Create the panel.
	 */
	public AddMovieUI(AddMovieControl controlAdd) {
		addMovieControl = controlAdd;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 2, 0, 8, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 41, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		//Title label
		JLabel labelTitle = new JLabel("Title:");
		GridBagConstraints gbc_labelTitle = new GridBagConstraints();
		gbc_labelTitle.insets = new Insets(0, 0, 5, 5);
		gbc_labelTitle.gridx = 3;
		gbc_labelTitle.gridy = 2;
		add(labelTitle, gbc_labelTitle);
		
		//Title textfield
		textFieldTitle = new JTextField();
		GridBagConstraints gbc_textFieldTitle = new GridBagConstraints();
		gbc_textFieldTitle.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTitle.gridx = 5;
		gbc_textFieldTitle.gridy = 2;
		add(textFieldTitle, gbc_textFieldTitle);
		textFieldTitle.setColumns(10);
		
		//Year label
		JLabel labelYear = new JLabel("Year");
		GridBagConstraints gbc_labelYear = new GridBagConstraints();
		gbc_labelYear.insets = new Insets(0, 0, 5, 5);
		gbc_labelYear.gridx = 3;
		gbc_labelYear.gridy = 3;
		add(labelYear, gbc_labelYear);
		
		//Initialize valid years (1910 to the current year)
		ArrayList<Integer> years = new ArrayList<Integer>();
		for (int year = 1910; year <= Calendar.getInstance().get(Calendar.YEAR); year++)
		{
			years.add(Integer.valueOf(year));
		}
		
		//Year drop down menu
		comboBoxYear = new JComboBox(years.toArray());
		GridBagConstraints gbc_comboBoxYear = new GridBagConstraints();
		gbc_comboBoxYear.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxYear.gridx = 5;
		gbc_comboBoxYear.gridy = 3;
		add(comboBoxYear, gbc_comboBoxYear);
		
		//Genre label
		JLabel labelGenre = new JLabel("Genre:");
		GridBagConstraints gbc_labelGenre = new GridBagConstraints();
		gbc_labelGenre.insets = new Insets(0, 0, 5, 5);
		gbc_labelGenre.gridx = 3;
		gbc_labelGenre.gridy = 4;
		add(labelGenre, gbc_labelGenre);
		
		//initializing available genres
		String[] genreList = {"Action", "Adventure","Animation","Comedy","Crime","Documentary","Drama","Horror","Thriller","Romance","Science Fiction","Fantasy","War","Western"};
		
		//Genre drop down menu
		comboBoxGenre = new JComboBox(genreList); 
		GridBagConstraints gbc_comboBoxGenre = new GridBagConstraints();
		gbc_comboBoxGenre.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxGenre.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxGenre.gridx = 5;
		gbc_comboBoxGenre.gridy = 4;
		add(comboBoxGenre, gbc_comboBoxGenre);
		
		
		//Length label
		JLabel labelLength = new JLabel("Length");
		GridBagConstraints gbc_labelLength = new GridBagConstraints();
		gbc_labelLength.insets = new Insets(0, 0, 5, 5);
		gbc_labelLength.gridx = 3;
		gbc_labelLength.gridy = 5;
		add(labelLength, gbc_labelLength);
		
		//Another panel for the add movie button and success/error label so they will be centered properly
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 6;
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
		JLabel labelAddMovieStatus = new JLabel("");
		GridBagConstraints gbc_labelAddMovieStatus = new GridBagConstraints();
		gbc_labelAddMovieStatus.gridx = 0;
		gbc_labelAddMovieStatus.gridy = 1;
		panel.add(labelAddMovieStatus, gbc_labelAddMovieStatus);
	}
	
	
	public void displayAddMovieForm() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
	
	public void extractMovieDetails() {
		//get input out of textfields/comboboxes
		//processAddMovie(title, year, genre, yearOfRelease);
	}

	public void displayAddMovieConfirmation() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

}
