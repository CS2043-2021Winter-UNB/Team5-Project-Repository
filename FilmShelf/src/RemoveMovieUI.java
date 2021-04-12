import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RemoveMovieUI extends JPanel {

	private RemoveMovieControl removeMovieControl;
	private MainUI mainUI;
	
	public RemoveMovieUI(RemoveMovieControl controlRemoveMov) {
		removeMovieControl = controlRemoveMov;
	}
	
	public void setMain(MainUI mainUI) {
		this.mainUI = mainUI;
	}
	
	public void displayRemovalMovieWarning(int movie) {
		// begin-user-code
		// TODO Auto-generated method stub
		int response=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete Movie?","Remove Confirm",JOptionPane.YES_NO_OPTION);
		if(response==JOptionPane.YES_OPTION) {
			removeMovieControl.processRemoveMovie(movie); 
			mainUI.changeMovieButtons();
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
		JOptionPane.showMessageDialog(null,"Movie Removed.");
		// end-user-code
	}
}