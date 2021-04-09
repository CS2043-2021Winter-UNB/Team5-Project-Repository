/******************************************************************************************************************************
 * RateMovieUI
 * @author Rachel
 * Description:	Handles input from user and communicates with RateMovieControl and loginControl.
 ******************************************************************************************************************************/

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RateMovieUI extends JPanel {

	private LoginControl loginControl;
	private RateMovieControl rateMovieControl;
	private ViewMovieControl viewMovieControl;
	private MainUI mainUI;
	private JComboBox<String> comboBoxRating; //dropdown
	//private JLabel labelRateMovieStatus;


	public RateMovieUI(RateMovieControl rmControl, LoginControl lControl, ViewMovieControl vControl) {
		
		rateMovieControl = rmControl;
		loginControl = lControl;
		viewMovieControl = vControl;
		
		//get current member
		MemberObject member = loginControl.getCurrentMember();
			
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{27, 0, 2, 25, 50, 40, 0, 26, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 15, 32, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		//Get MovieID from ViewMovieControl
		MovieObject movie = viewMovieControl.getCurrentMovie();
		int movieID = movie.getMovieId();
		
		
		//Ask Control if member has a rating for THIS movie, and THIS member
		if (rateMovieControl.getMemberRating(movieID, member) == 0) //no rating yet
			{	
			
			//-Text: "Rate Movie"
			//	-display dropdown
			//-Text: "Average Rating"
			//	-display average rating 
			
			//Member Rating label
			JLabel labelRating = new JLabel("Rate Movie: ");
			GridBagConstraints gbc_labelRating = new GridBagConstraints();
			gbc_labelRating.insets = new Insets(0, 0, 5, 5);
			gbc_labelRating.gridx = 1;
			gbc_labelRating.gridy = 0;
			add(labelRating, gbc_labelRating);
					
			//Initializing available ratings
			String[] ratingList = {"Select a rating","1", "2","3","4","5"};
			
			//Rating drop down menu
			comboBoxRating = new JComboBox<String>(ratingList);
			
				//Detect selection of rating
				comboBoxRating.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { 
						String sRating = (String) comboBoxRating.getSelectedItem();
					
						int iRating = Integer.parseInt(sRating);
						boolean success = rateMovieControl.processRating(movieID, iRating);
			    
					//IF success is false display error?
					}
				});
			
			
			// display dropdown: "Select a rating","1" to "5") 
			GridBagConstraints gbc_comboBoxRating = new GridBagConstraints();
			gbc_comboBoxRating.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxRating.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxRating.gridx = 2;
			gbc_comboBoxRating.gridy = 0;
			add(comboBoxRating, gbc_comboBoxRating);
			
			}
		else //if member has rating 
			{JLabel labelRating = new JLabel("Your Rating: ");
			GridBagConstraints gbc_labelRating = new GridBagConstraints();
			gbc_labelRating.insets = new Insets(0, 0, 5, 5);
			gbc_labelRating.gridx = 1;
			gbc_labelRating.gridy = 0;
			add(labelRating, gbc_labelRating);
				
			//Initializing available ratings
			String[] ratingList = {"1", "2","3","4","5"};
		
			//Rating drop down menu
			comboBoxRating = new JComboBox<String>(ratingList);
			GridBagConstraints gbc_comboBoxRating = new GridBagConstraints();
			gbc_comboBoxRating.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxRating.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxRating.gridx = 2;
			gbc_comboBoxRating.gridy = 0;
			
			//figure out index... index starts at 0, so int 1 = index 0 in list.
			int rIndex = rateMovieControl.getMemberRating(movieID, member) -1;
			comboBoxRating.setSelectedIndex(rIndex);
			
			//Detect selection of rating
			comboBoxRating.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
					String sRating = (String) comboBoxRating.getSelectedItem();
				
					int iRating = Integer.parseInt(sRating);
					boolean success = rateMovieControl.processRating(movieID,iRating);
		    
				//IF success is false display error?
				}
			});
			
			}
		
			//Average Movie Rating label
			JLabel labelAverageMovieRating = new JLabel("Average Rating: ");
			GridBagConstraints gbc_labelAverageMovieRating = new GridBagConstraints();
			gbc_labelAverageMovieRating.insets = new Insets(0, 0, 5, 5);
			gbc_labelAverageMovieRating.gridx = 1;
			gbc_labelAverageMovieRating.gridy = 1;
			add(labelAverageMovieRating, gbc_labelAverageMovieRating);
			
			
			//Collect average movie rating
			double averageRating = rateMovieControl.getAverageRating(movieID);
			
			String averageRatingString = String.valueOf(averageRating);
			
			JLabel labelAverageMovieRating_Value = new JLabel(averageRatingString);
			//TEST: JLabel labelAverageMovieRating_Value = new JLabel("5.0");
			GridBagConstraints gbc_labelAverageMovieRating_Value = new GridBagConstraints();
			gbc_labelAverageMovieRating_Value.insets = new Insets(0, 0, 5, 5);
			gbc_labelAverageMovieRating_Value.gridx = 2;
			gbc_labelAverageMovieRating_Value.gridy = 1;
			add(labelAverageMovieRating_Value, gbc_labelAverageMovieRating_Value);
		
	}


	public void displayRatingForm(MovieObject movie, MemberObject member) {
		labelRating.setText(movie.getTitle());
	}
	
	public void extractRatingDetails() {
		String memberRating = comboBoxRating.getSelectedItem().toString();
	}
}