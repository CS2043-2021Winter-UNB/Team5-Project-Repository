
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class AddReviewUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private AddReviewControl leaveReviewControl;
	private JLabel labelAddReview;
	private int movieId;
	private String movieTitle;
	private JLabel labelReview;

	public AddReviewUI(AddReviewControl addReviewUI) {
		leaveReviewControl = addReviewUI;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 25, 200, 25, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 148, 15, 32, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		Color fontColor = UISettings.getFontColor();

		// Title label
		labelReview = new JLabel();
		labelReview.setForeground(fontColor);
		labelReview.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_labelReview = new GridBagConstraints();
		gbc_labelReview.insets = new Insets(0, 0, 5, 5);
		gbc_labelReview.gridx = 1;
		gbc_labelReview.gridy = 1;
		add(labelReview, gbc_labelReview);
		
		//scrollpane for review text area
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		//review text area
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		scrollPane.setPreferredSize(new Dimension(200, HEIGHT));
		textArea.setDocument(new LengthRestrictedDocument(2000));

		labelAddReview = new JLabel("");
		labelAddReview.setForeground(fontColor);
		GridBagConstraints gbc_labelAddReview = new GridBagConstraints();
		gbc_labelAddReview.insets = new Insets(0, 0, 5, 5);
		gbc_labelAddReview.gridx = 1;
		gbc_labelAddReview.gridy = 4;
		add(labelAddReview, gbc_labelAddReview);

		//Add review button
		JButton buttonAddReview = new JButton("Add Review");
		buttonAddReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (leaveReviewControl.processAddReview(movieId, textArea.getText()) == true) {
					displayLeaveReviewConfirmation();
					leaveReviewControl.processAddReview(movieId, textArea.getText());
				} else {
					displayErrorMessage();
				}
			}
		});
		GridBagConstraints gbc_buttonAddReview = new GridBagConstraints();
		gbc_buttonAddReview.insets = new Insets(0, 0, 5, 5);
		gbc_buttonAddReview.gridx = 1;
		gbc_buttonAddReview.gridy = 3;
		add(buttonAddReview, gbc_buttonAddReview);
		
		setOpaque(false);
		setVisible(false);
	}

	public void displayLeaveReviewForm(int movieID, String titleIn) {
		this.movieTitle = titleIn;
		this.movieId = movieID;
		labelReview.setText("Add a review for " + movieTitle + " below:");
		setVisible(true);
	}

	public void displayLeaveReviewConfirmation() {
		labelAddReview.setText("Review added successfully.");
	}

	public void displayErrorMessage() {
		labelAddReview.setText("Account edit was unsuccessful. Account information was invalid.");
	}
}