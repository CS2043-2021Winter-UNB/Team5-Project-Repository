import javax.swing.JPanel;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewReviewUI extends JPanel {

	private static final long serialVersionUID = -5574002578266475677L;
	private ViewReviewControl viewReviewControl;
	private RemoveReviewUI removeReviewUI;
	private LoginControl loginControl;
	private int movieId;
	private JLabel labelMovieName;
	private ArrayList<ReviewObject> reviews;
	private JList<String> displayList;
	private DefaultListModel<String> model;
	private JButton buttonRemoveReview;
	
	/**
	 * Create the panel.
	 */
	public ViewReviewUI(ViewReviewControl controlViewReview, RemoveReviewUI uiRemoveReview, LoginControl controlLogin) {
		viewReviewControl = controlViewReview;
		loginControl = controlLogin;
		removeReviewUI = uiRemoveReview;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 20, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		labelMovieName = new JLabel("");
		GridBagConstraints gbc_labelMovieName = new GridBagConstraints();
		gbc_labelMovieName.insets = new Insets(0, 0, 5, 5);
		gbc_labelMovieName.gridx = 2;
		gbc_labelMovieName.gridy = 0;
		add(labelMovieName, gbc_labelMovieName);
		
		JLabel labelReviewTitle = new JLabel("Reviews");
		GridBagConstraints gbc_labelReviewTitle = new GridBagConstraints();
		gbc_labelReviewTitle.insets = new Insets(0, 0, 5, 5);
		gbc_labelReviewTitle.gridx = 3;
		gbc_labelReviewTitle.gridy = 0;
		add(labelReviewTitle, gbc_labelReviewTitle);
		
		buttonRemoveReview = new JButton("Remove Review");
		buttonRemoveReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//removeReviewUI.displayRemoveReviewWarning(username,reviewId);
				//TO DO: remove the one removed review from the jlist if removal was successful
			}
		});
		GridBagConstraints gbc_buttonRemoveReview = new GridBagConstraints();
		gbc_buttonRemoveReview.insets = new Insets(0, 0, 5, 0);
		gbc_buttonRemoveReview.gridx = 5;
		gbc_buttonRemoveReview.gridy = 0;
		add(buttonRemoveReview, gbc_buttonRemoveReview);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);

		model = new DefaultListModel<String>();
		displayList = new JList<>(model);
		//make it so you can only select one list at a time
		displayList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(displayList);
		
		setVisible(false);
	}
	
	//displays the reviews for that movie 
	public void displayReview(String movieName, int movieId) {
		//Make the title label display the name of the movie 
		labelMovieName.setText(movieName);
		this.movieId = movieId;
		
		//if there are reviews in the model from the last time it was displayed, clear them
		if (model.getSize() != 0)
		{
			model.removeAllElements();
		}
		reviews = viewReviewControl.processViewReview(movieId);
		if (reviews != null)
		{
			String reviewText;
			for (ReviewObject review : reviews)
			{
				reviewText = review.getUsername() + " " + review.getReviewText();
				model.addElement(reviewText);
			}
		}
		
		setVisible(true);
	}

}
