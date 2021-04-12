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

public class RemoveRatingUI extends JPanel {
	private RemoveRatingControl removeRatingControl;
	private final Action action1 = new SwingAction();
	private int movieId;	
	
	public RemoveRatingUI(RemoveRatingControl rrControl) {
		removeRatingControl = rrControl;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{38, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel removeRatingLabel = new JLabel("Remove Movie Rating:");
		GridBagConstraints gbc_removeRatingLabel = new GridBagConstraints();
		gbc_removeRatingLabel.insets = new Insets(0, 0, 5, 5);
		gbc_removeRatingLabel.gridx = 1;
		gbc_removeRatingLabel.gridy = 3;
		add(removeRatingLabel, gbc_removeRatingLabel);
		
		JRadioButton removeRatingButton = new JRadioButton("✰");
		removeRatingButton.setAction(action1);
		GridBagConstraints gbc_removeRatingButton = new GridBagConstraints();
		gbc_removeRatingButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeRatingButton.gridx = 1;
		gbc_removeRatingButton.gridy = 5;
		add(removeRatingButton, gbc_removeRatingButton);
	}
			
	public void	displayRemoveRatingForm(int movieId) {
		this.movieId = movieId;
		setVisible(true);
	}
		
	private class SwingAction extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "");
			putValue(SHORT_DESCRIPTION, "Remove Rating!");
		}
		public void actionPerformed(ActionEvent e) {
			removeRatingControl.processRemoveRating(movieId);
		}
	}
}