
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
		gridBagLayout.columnWidths = new int[]{38, 29, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Color fontColor = UISettings.getFontColor();
		Color buttonColor = UISettings.getButtonColor();
		
		removeRatingButton = new JButton("Remove Rating");
		removeRatingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeRatingControl.processRemoveRating(movieId);
				
				ratingGroup.clearSelection();
			}
		});
		
		JLabel rateMovieLabel = new JLabel("Rate Movie:");
		rateMovieLabel.setForeground(fontColor);
		GridBagConstraints gbc_rateMovieLabel = new GridBagConstraints();
		gbc_rateMovieLabel.anchor = GridBagConstraints.WEST;
		gbc_rateMovieLabel.gridwidth = 4;
		gbc_rateMovieLabel.insets = new Insets(0, 0, 5, 5);
		gbc_rateMovieLabel.gridx = 1;
		gbc_rateMovieLabel.gridy = 2;
		add(rateMovieLabel, gbc_rateMovieLabel);
		GridBagConstraints gbc_removeRatingButton = new GridBagConstraints();
		gbc_removeRatingButton.anchor = GridBagConstraints.EAST;
		gbc_removeRatingButton.gridwidth = 5;
		gbc_removeRatingButton.insets = new Insets(0, 0, 5, 0);
		gbc_removeRatingButton.gridx = 9;
		gbc_removeRatingButton.gridy = 2;
		add(removeRatingButton, gbc_removeRatingButton);
		
		JRadioButton oneStarButton = new JRadioButton("");
		oneStarButton.setForeground(fontColor);
		oneStarButton.setOpaque(false);
		oneStarButton.setActionCommand("1");
		GridBagConstraints gbc_oneStarButton = new GridBagConstraints();
		gbc_oneStarButton.insets = new Insets(0, 0, 5, 5);
		gbc_oneStarButton.gridx = 1;
		gbc_oneStarButton.gridy = 4;
		add(oneStarButton, gbc_oneStarButton);
		
		ratingGroup = new ButtonGroup();
		ratingGroup.add(oneStarButton);
		
		actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rateMovieControl.processRating(movieId, Integer.parseInt(e.getActionCommand()));
			}
		};
		
		oneStarButton.addActionListener(actionListener);
		
		setOpaque(false);
		
		JLabel oneStarLabel = new JLabel("");
		oneStarLabel.setIcon(UISettings.getStarsIcon(1, 12, 1));
		GridBagConstraints gbc_oneStarLabel = new GridBagConstraints();
		gbc_oneStarLabel.insets = new Insets(0, 0, 9, 5);
		gbc_oneStarLabel.gridx = 2;
		gbc_oneStarLabel.gridy = 4;
		add(oneStarLabel, gbc_oneStarLabel);
		
		JRadioButton twoStarsButton = new JRadioButton("");
		twoStarsButton.setForeground(fontColor);
		twoStarsButton.setOpaque(false);
		twoStarsButton.setActionCommand("2");
		GridBagConstraints gbc_twoStarsButton = new GridBagConstraints();
		gbc_twoStarsButton.insets = new Insets(0, 0, 5, 5);
		gbc_twoStarsButton.gridx = 3;
		gbc_twoStarsButton.gridy = 4;
		add(twoStarsButton, gbc_twoStarsButton);
		ratingGroup.add(twoStarsButton);
		twoStarsButton.addActionListener(actionListener);
		
		JLabel twoStarLabel = new JLabel("");
		twoStarLabel.setIcon(UISettings.getStarsIcon(2, 12, 1));
		GridBagConstraints gbc_twoStarLabel = new GridBagConstraints();
		gbc_twoStarLabel.insets = new Insets(8, 0, 5, 5);
		gbc_twoStarLabel.gridx = 4;
		gbc_twoStarLabel.gridy = 4;
		add(twoStarLabel, gbc_twoStarLabel);
		
		JRadioButton threeStarsButton = new JRadioButton("");
		threeStarsButton.setForeground(fontColor);
		threeStarsButton.setOpaque(false);
		threeStarsButton.setActionCommand("3");
		GridBagConstraints gbc_threeStarsButton = new GridBagConstraints();
		gbc_threeStarsButton.insets = new Insets(0, 0, 5, 5);
		gbc_threeStarsButton.gridx = 5;
		gbc_threeStarsButton.gridy = 4;
		add(threeStarsButton, gbc_threeStarsButton);
		ratingGroup.add(threeStarsButton);
		threeStarsButton.addActionListener(actionListener);
		
		JLabel threeStarLabel = new JLabel("");
		threeStarLabel.setIcon(UISettings.getStarsIcon(3, 12, 1));
		GridBagConstraints gbc_threeStarLabel = new GridBagConstraints();
		gbc_threeStarLabel.insets = new Insets(20, 0, 5, 5);
		gbc_threeStarLabel.gridx = 6;
		gbc_threeStarLabel.gridy = 4;
		add(threeStarLabel, gbc_threeStarLabel);
		
		JRadioButton fourStarsButton = new JRadioButton("");
		fourStarsButton.setForeground(fontColor);
		fourStarsButton.setOpaque(false);
		fourStarsButton.setActionCommand("4");
		GridBagConstraints gbc_fourStarsButton = new GridBagConstraints();
		gbc_fourStarsButton.insets = new Insets(0, 0, 5, 5);
		gbc_fourStarsButton.gridx = 7;
		gbc_fourStarsButton.gridy = 4;
		add(fourStarsButton, gbc_fourStarsButton);
		ratingGroup.add(fourStarsButton);
		fourStarsButton.addActionListener(actionListener);
		
		JLabel fourStarLabel = new JLabel("");
		fourStarLabel.setIcon(UISettings.getStarsIcon(4, 12, 1));
		GridBagConstraints gbc_fourStarLabel = new GridBagConstraints();
		gbc_fourStarLabel.insets = new Insets(32, 0, 5, 5);
		gbc_fourStarLabel.gridx = 8;
		gbc_fourStarLabel.gridy = 4;
		add(fourStarLabel, gbc_fourStarLabel);
		
		JRadioButton fiveStarsButton = new JRadioButton("");
		fiveStarsButton.setForeground(fontColor);
		fiveStarsButton.setOpaque(false);
		fiveStarsButton.setActionCommand("5");
		GridBagConstraints gbc_fiveStarsButton = new GridBagConstraints();
		gbc_fiveStarsButton.insets = new Insets(0, 0, 5, 5);
		gbc_fiveStarsButton.gridx = 9;
		gbc_fiveStarsButton.gridy = 4;
		add(fiveStarsButton, gbc_fiveStarsButton);
		ratingGroup.add(fiveStarsButton);
		
		JLabel fiveStarLabel = new JLabel("");
		fiveStarLabel.setIcon(UISettings.getStarsIcon(5, 12, 1));
		GridBagConstraints gbc_fiveStarLabel = new GridBagConstraints();
		gbc_fiveStarLabel.insets = new Insets(43, 0, 5, 5);
		gbc_fiveStarLabel.gridx = 10;
		gbc_fiveStarLabel.gridy = 4;
		add(fiveStarLabel, gbc_fiveStarLabel);
		fiveStarsButton.addActionListener(actionListener);
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