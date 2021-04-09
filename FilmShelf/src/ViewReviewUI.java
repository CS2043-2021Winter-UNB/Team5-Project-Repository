import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;

public class ViewReviewUI extends JPanel {

	private static final long serialVersionUID = -5574002578266475677L;
	private ViewReviewControl viewReviewControl;
	private LoginControl loginControl;
	private int movieId;
	private JLabel labelMovieName;
	private ArrayList<ReviewObject> reviews;
	private JList<String> displayList;
	
	/**
	 * Create the panel.
	 */
	public ViewReviewUI(ViewReviewControl controlViewReview, LoginControl loginControl) {
		viewReviewControl = controlViewReview;
		loginControl = controlLogin;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel labelMovieName = new JLabel("");
		GridBagConstraints gbc_labelMovieName = new GridBagConstraints();
		gbc_labelMovieName.insets = new Insets(0, 0, 5, 5);
		gbc_labelMovieName.gridx = 2;
		gbc_labelMovieName.gridy = 1;
		add(labelMovieName, gbc_labelMovieName);
		
		JLabel labelReviewTitle = new JLabel("Reviews");
		GridBagConstraints gbc_labelReviewTitle = new GridBagConstraints();
		gbc_labelReviewTitle.insets = new Insets(0, 0, 5, 5);
		gbc_labelReviewTitle.gridx = 3;
		gbc_labelReviewTitle.gridy = 1;
		add(labelReviewTitle, gbc_labelReviewTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		displayList = new JList<String>();
		//make it so you can only select one list at a time
		displayList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(displayList);
	}
	
	//displays the reviews for that movie 
	public void displayReview(String movieName, int movieId) {
		//Make the title label display the name of the movie 
		labelMovieName.setText(movieName);
		
		this.movieId = movieId;
		reviews = viewReviewControl.processViewReview(movieId);
		if (reviews != null)
		{
			String reviewText;
			for (ReviewObject review : reviews)
			{
				reviewText = review.getUsername() + "\n" + review.getReviewText();
				displayList.add(reviewText);
			}
		}

	}

}
