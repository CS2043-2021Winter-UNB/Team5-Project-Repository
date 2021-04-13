
import javax.swing.JPanel;
	import java.awt.GridBagLayout;
	import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
	import java.awt.Insets;
	import javax.swing.JButton;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class AddReviewUI extends JPanel {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//private AddMovieControl addMovieControl;
		private AddReviewControl leaveReviewControl;
		private JLabel labelAddReview;
		int movieId;
		String movieTitle;
		/**
		 * Create the panel.
		 */
		public AddReviewUI(AddReviewControl addReviewUI) {
			leaveReviewControl = addReviewUI;
			
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{56, 0, 48, 0, 0, 0, 0, 0, 50, 40, 0, 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 148, 0, 15, 32, 0, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			setLayout(gridBagLayout);
			
			Color fontColor = UISettings.getFontColor();
			
			//Title label
			JLabel labelReview = new JLabel("Add review for the movie below:");
			labelReview.setForeground(fontColor);
			labelReview.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_labelReview = new GridBagConstraints();
			gbc_labelReview.gridwidth = 3;
			gbc_labelReview.insets = new Insets(0, 0, 5, 5);
			gbc_labelReview.gridx = 2;
			gbc_labelReview.gridy = 1;
			add(labelReview, gbc_labelReview);
			
			JTextArea textArea = new JTextArea();
			GridBagConstraints gbc_textArea = new GridBagConstraints();
			gbc_textArea.gridheight = 2;
			gbc_textArea.gridwidth = 7;
			gbc_textArea.insets = new Insets(0, 0, 5, 5);
			gbc_textArea.fill = GridBagConstraints.BOTH;
			gbc_textArea.gridx = 2;
			gbc_textArea.gridy = 2;
			add(textArea, gbc_textArea);
			
			JButton buttonAddReview = new JButton("Add Review");
			buttonAddReview.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					if(leaveReviewControl.processAddReview(movieId,textArea.getText())==true) {
						displayLeaveReviewConfirmation();
						leaveReviewControl.processAddReview(movieId,textArea.getText());
					}
					else {
						displayErrorMessage();
					}
				}
			});
			GridBagConstraints gbc_buttonAddReview = new GridBagConstraints();
			gbc_buttonAddReview.insets = new Insets(0, 0, 5, 5);
			gbc_buttonAddReview.gridx = 4;
			gbc_buttonAddReview.gridy = 4;
			add(buttonAddReview, gbc_buttonAddReview);
			
			
			labelAddReview = new JLabel("");
			labelAddReview.setForeground(fontColor);
			GridBagConstraints gbc_labelAddReview = new GridBagConstraints();
			gbc_labelAddReview.insets = new Insets(0, 0, 5, 5);
			gbc_labelAddReview.gridx = 4;
			gbc_labelAddReview.gridy = 5;
			add(labelAddReview, gbc_labelAddReview);
			
			setOpaque(false);
			setVisible(false);
		}
	public void displayLeaveReviewForm(int movieID,String titleIn) {
		// begin-user-code
		// TODO Auto-generated method stub
		//display the form
		this.movieTitle=titleIn;
		this.movieId=movieID;
		setVisible(true);
	}

	public void displayLeaveReviewConfirmation() {
		// begin-user-code
		// TODO Auto-generated method stub
		labelAddReview.setText("Review added successfully.");
		// end-user-code
	}

	public void displayErrorMessage() {
		// begin-user-code
		// TODO Auto-generated method stub
		labelAddReview.setText("Account edit was unsuccessful. Account information was invalid.");
		// end-user-code
	}
}