import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RemoveReviewUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private RemoveReviewControl removeReviewControl;
	
	/**
	 * Create the panel.
	 */
	public RemoveReviewUI(RemoveReviewControl controlRemoveReview) {
		removeReviewControl = controlRemoveReview;
	}


	public void displayRemoveReviewWarning(String username, int reviewId) {
		int response=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this review?","Remove Confirm",JOptionPane.YES_NO_OPTION);
		if(response==JOptionPane.YES_OPTION) {
			removeReviewControl.processRemoveReview(username,reviewId);
			displayRemoveReviewConfirmation();
		}
		else if(response==JOptionPane.NO_OPTION){
			
		}
	}

	public void displayRemoveReviewConfirmation() {
		JOptionPane.showMessageDialog(null,"Account Removed.");
	}
}
