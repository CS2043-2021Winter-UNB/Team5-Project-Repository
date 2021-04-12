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
import javax.swing.JRadioButton;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

public class EditRatingUI extends JPanel {
	
	private LoginControl loginControl;
	private EditRatingControl editRatingControl;
	private ViewMovieControl viewMovieControl;
	private RemoveRatingUI removeRatingUI;
	private JButton removeRatingButton;
	private final Action action1 = new SwingAction();
	private final Action action2 = new SwingAction_1();
	private final Action action3 = new SwingAction_2();
	private final Action action4 = new SwingAction_3();
	private final Action action5 = new SwingAction_4();
	private int movieID;


	public EditRatingUI(EditRatingControl erControl, LoginControl lControl, ViewMovieControl vControl, RemoveRatingUI uiRemoveRating) {
		editRatingControl = erControl;
		loginControl = lControl;
		viewMovieControl = vControl;
		removeRatingUI = uiRemoveRating;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{38, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel editRatingLabel = new JLabel("Edit Movie Rating:");
		GridBagConstraints gbc_editRatingLabel = new GridBagConstraints();
		gbc_editRatingLabel.insets = new Insets(0, 0, 5, 5);
		gbc_editRatingLabel.gridx = 1;
		gbc_editRatingLabel.gridy = 3;
		add(editRatingLabel, gbc_editRatingLabel);
		
		JRadioButton oneStarButton = new JRadioButton("✰");
		oneStarButton.setAction(action1);
		GridBagConstraints gbc_oneStarButton = new GridBagConstraints();
		gbc_oneStarButton.insets = new Insets(0, 0, 5, 5);
		gbc_oneStarButton.gridx = 1;
		gbc_oneStarButton.gridy = 5;
		add(oneStarButton, gbc_oneStarButton);
		
		JRadioButton twoStarsButton = new JRadioButton("✰✰");
		twoStarsButton.setAction(action2);
		GridBagConstraints gbc_twoStarsButton = new GridBagConstraints();
		gbc_twoStarsButton.insets = new Insets(0, 0, 5, 5);
		gbc_twoStarsButton.gridx = 2;
		gbc_twoStarsButton.gridy = 5;
		add(twoStarsButton, gbc_twoStarsButton);
		
		JRadioButton threeStarsButton = new JRadioButton("✰✰✰");
		threeStarsButton.setAction(action3);
		GridBagConstraints gbc_threeStarsButton = new GridBagConstraints();
		gbc_threeStarsButton.insets = new Insets(0, 0, 5, 5);
		gbc_threeStarsButton.gridx = 3;
		gbc_threeStarsButton.gridy = 5;
		add(threeStarsButton, gbc_threeStarsButton);
		
		JRadioButton fourStarsButton = new JRadioButton("✰✰✰✰");
		fourStarsButton.setAction(action4);
		GridBagConstraints gbc_fourStarsButton = new GridBagConstraints();
		gbc_fourStarsButton.insets = new Insets(0, 0, 5, 5);
		gbc_fourStarsButton.gridx = 4;
		gbc_fourStarsButton.gridy = 5;
		add(fourStarsButton, gbc_fourStarsButton);
		
		JRadioButton fiveStarsButton = new JRadioButton("✰✰✰✰✰");
		fiveStarsButton.setAction(action5);
		GridBagConstraints gbc_fiveStarsButton = new GridBagConstraints();
		gbc_fiveStarsButton.insets = new Insets(0, 0, 5, 5);
		gbc_fiveStarsButton.gridx = 5;
		gbc_fiveStarsButton.gridy = 5;
		add(fiveStarsButton, gbc_fiveStarsButton);
		
		removeRatingButton = new JButton("Remove Rating");
		removeRatingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeRatingUI.displayRemoveRatingForm(movieID);
			}
		});
		
		GridBagConstraints gbc_removeRatingButton = new GridBagConstraints();
		gbc_removeRatingButton.gridwidth = 3;
		gbc_removeRatingButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeRatingButton.gridx = 8;
		gbc_removeRatingButton.gridy = 2;
		add(removeRatingButton, gbc_removeRatingButton);
			
	}
	
	public void displayEditRatingForm(int movieID) {
		this.movieID =movieID;
		setVisible(true);
		
		//check if remove movie button should be displayed.	
		//It should be displayed if the member has a rating already for the movie.
		RatingObject ratingObject = editRatingControl.getRating(movieID);
		boolean check = (ratingObject != null);
		
		if (check) {
			removeRatingButton.setVisible(true);
		}
		else {
			removeRatingButton.setVisible(false);
		}
		
	}
	
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "✰");
			putValue(SHORT_DESCRIPTION, "One Star Rating!");
		}
		public void actionPerformed(ActionEvent e) {
			editRatingControl.processEditRating(movieID, 1);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "✰✰");
			putValue(SHORT_DESCRIPTION, "Two Star Rating!");
		}
		public void actionPerformed(ActionEvent e) {
			editRatingControl.processEditRating(movieID, 2);
		}
	}
	private class SwingAction_2 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_2() {
			putValue(NAME, "✰✰✰");
			putValue(SHORT_DESCRIPTION, "Three Star Rating!");
		}
		public void actionPerformed(ActionEvent e) {
			editRatingControl.processEditRating(movieID, 3);
		}
	}
	private class SwingAction_3 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_3() {
			putValue(NAME, "✰✰✰✰");
			putValue(SHORT_DESCRIPTION, "Four Star Rating");
		}
		public void actionPerformed(ActionEvent e) {
			editRatingControl.processEditRating(movieID, 4);
		}
	}
	private class SwingAction_4 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_4() {
			putValue(NAME, "✰✰✰✰✰");
			putValue(SHORT_DESCRIPTION, "Five Star Rating");
		}
		public void actionPerformed(ActionEvent e) {
			editRatingControl.processEditRating(movieID, 5);
		}
	}
}
