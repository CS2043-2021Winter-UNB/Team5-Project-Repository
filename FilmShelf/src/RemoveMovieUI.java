import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RemoveMovieUI extends JPanel {

	private RemoveMovieControl removeMovieControl;
	private JLabel labelMovieStatus;
	
	public RemoveMovieUI() {
		labelMovieStatus = new JLabel("");
		labelMovieStatus.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelMovieStatus = new GridBagConstraints();
		gbc_labelMovieStatus.gridwidth = 6;
		gbc_labelMovieStatus.insets = new Insets(0, 0, 5, 5);
		gbc_labelMovieStatus.gridx = 1;
		gbc_labelMovieStatus.gridy = 7;
		add(labelMovieStatus, gbc_labelMovieStatus);
	}
	public void displayRemovalMovieWarning(int movie) {
		// begin-user-code
		// TODO Auto-generated method stub
		int response=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete Movie?","Remove Confirm",JOptionPane.YES_NO_OPTION);
		if(response==JOptionPane.YES_OPTION) {
			removeMovieControl.processRemoveMovie(movie); 
			displayMovieRemovedConfimation();
		}
		else if(response==JOptionPane.NO_OPTION){
		}
		// end-user-code
	}

	public void displayMovieRemovedConfimation() {
		// begin-user-code
		// TODO Auto-generated method stub
		//labelEditAccountStatus.setText("Account updated successfully.");
		labelMovieStatus.setText("Movie Removed.");
		// end-user-code
	}
}