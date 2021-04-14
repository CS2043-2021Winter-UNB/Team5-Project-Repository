
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class RateMovieUI extends JPanel {
	private RateMovieControl rateMovieControl;
	private RemoveRatingControl removeRatingControl;
	private JButton removeRatingButton;
	private ButtonGroup ratingGroup;
	private ActionListener actionListener;
	private int movieId;

	public RateMovieUI(RateMovieControl rmControl, RemoveRatingControl rrControl) {
		rateMovieControl = rmControl;
		removeRatingControl = rrControl;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{38, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Color fontColor = UISettings.getFontColor();
		Color buttonColor = UISettings.getButtonColor();
		
		JLabel rateMovieLabel = new JLabel("Rate Movie:");
		rateMovieLabel.setForeground(fontColor);
		GridBagConstraints gbc_rateMovieLabel = new GridBagConstraints();
		gbc_rateMovieLabel.insets = new Insets(0, 0, 5, 5);
		gbc_rateMovieLabel.gridx = 1;
		gbc_rateMovieLabel.gridy = 3;
		add(rateMovieLabel, gbc_rateMovieLabel);
		
		JRadioButton oneStarButton = new JRadioButton("*");
		oneStarButton.setForeground(fontColor);
		oneStarButton.setOpaque(false);
		oneStarButton.setActionCommand("1");
		GridBagConstraints gbc_oneStarButton = new GridBagConstraints();
		gbc_oneStarButton.insets = new Insets(0, 0, 5, 5);
		gbc_oneStarButton.gridx = 1;
		gbc_oneStarButton.gridy = 5;
		add(oneStarButton, gbc_oneStarButton);
		
		JRadioButton twoStarsButton = new JRadioButton("* *");
		twoStarsButton.setForeground(fontColor);
		twoStarsButton.setOpaque(false);
		twoStarsButton.setActionCommand("2");
		GridBagConstraints gbc_twoStarsButton = new GridBagConstraints();
		gbc_twoStarsButton.insets = new Insets(0, 0, 5, 5);
		gbc_twoStarsButton.gridx = 2;
		gbc_twoStarsButton.gridy = 5;
		add(twoStarsButton, gbc_twoStarsButton);
		
		JRadioButton threeStarsButton = new JRadioButton("* * *");
		threeStarsButton.setForeground(fontColor);
		threeStarsButton.setOpaque(false);
		threeStarsButton.setActionCommand("3");
		GridBagConstraints gbc_threeStarsButton = new GridBagConstraints();
		gbc_threeStarsButton.insets = new Insets(0, 0, 5, 5);
		gbc_threeStarsButton.gridx = 3;
		gbc_threeStarsButton.gridy = 5;
		add(threeStarsButton, gbc_threeStarsButton);
		
		JRadioButton fourStarsButton = new JRadioButton("* * * *");
		fourStarsButton.setForeground(fontColor);
		fourStarsButton.setOpaque(false);
		fourStarsButton.setActionCommand("4");
		GridBagConstraints gbc_fourStarsButton = new GridBagConstraints();
		gbc_fourStarsButton.insets = new Insets(0, 0, 5, 5);
		gbc_fourStarsButton.gridx = 4;
		gbc_fourStarsButton.gridy = 5;
		add(fourStarsButton, gbc_fourStarsButton);
		
		JRadioButton fiveStarsButton = new JRadioButton("* * * * *");
		fiveStarsButton.setForeground(fontColor);
		fiveStarsButton.setOpaque(false);
		fiveStarsButton.setActionCommand("5");
		GridBagConstraints gbc_fiveStarsButton = new GridBagConstraints();
		gbc_fiveStarsButton.insets = new Insets(0, 0, 5, 5);
		gbc_fiveStarsButton.gridx = 5;
		gbc_fiveStarsButton.gridy = 5;
		add(fiveStarsButton, gbc_fiveStarsButton);
		
		ratingGroup = new ButtonGroup();
		ratingGroup.add(oneStarButton);
		ratingGroup.add(twoStarsButton);
		ratingGroup.add(threeStarsButton);
		ratingGroup.add(fourStarsButton);
		ratingGroup.add(fiveStarsButton);
		
		actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rateMovieControl.processRating(movieId, Integer.parseInt(e.getActionCommand()));
			}
		};
		
		oneStarButton.addActionListener(actionListener);
		twoStarsButton.addActionListener(actionListener);
		threeStarsButton.addActionListener(actionListener);
		fourStarsButton.addActionListener(actionListener);
		fiveStarsButton.addActionListener(actionListener);
		
		removeRatingButton = new JButton("Remove Rating");
		removeRatingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeRatingControl.processRemoveRating(movieId);
				
				ratingGroup.clearSelection();
			}
		});
		GridBagConstraints gbc_removeRatingButton = new GridBagConstraints();
		gbc_removeRatingButton.gridwidth = 3;
		gbc_removeRatingButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeRatingButton.gridx = 8;
		gbc_removeRatingButton.gridy = 2;
		add(removeRatingButton, gbc_removeRatingButton);
		
		setOpaque(false);
		setVisible(false);
	}
	
	public void displayRatingForm(int movieID) {
		this.movieId = movieID;
		
		RatingObject userRating = rateMovieControl.getRating(movieId);
		
		if(userRating != null) {
			for(Enumeration<AbstractButton> buttons = ratingGroup.getElements(); buttons.hasMoreElements();) {
				AbstractButton button = buttons.nextElement();
				
				if(Integer.parseInt(button.getActionCommand()) == userRating.getRatingScore()) {
					button.setSelected(true);
				}
			}
		}
		
		setVisible(true);
	}
}