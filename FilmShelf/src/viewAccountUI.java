import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLayeredPane;

public class ViewAccountUI extends JPanel {

	private viewAccountControl viewAccControl;
	/**
	 * Create the panel.
	 */
	public ViewAccountUI(viewAccountControl viewAcc) {
		viewAccountControl viewAccControl = viewAcc;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 141, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel labelUsername = new JLabel("Username:");
		GridBagConstraints gbc_labelUsername = new GridBagConstraints();
		gbc_labelUsername.insets = new Insets(0, 0, 5, 5);
		gbc_labelUsername.gridx = 2;
		gbc_labelUsername.gridy = 2;
		add(labelUsername, gbc_labelUsername);
		
		JLabel labelShowUsername = new JLabel("New label");
		GridBagConstraints gbc_labelShowUsername = new GridBagConstraints();
		gbc_labelShowUsername.insets = new Insets(0, 0, 5, 5);
		gbc_labelShowUsername.gridx = 4;
		gbc_labelShowUsername.gridy = 2;
		add(labelShowUsername, gbc_labelShowUsername);
		
		JLabel labelFirstName = new JLabel("First Name:");
		GridBagConstraints gbc_labelFirstName = new GridBagConstraints();
		gbc_labelFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_labelFirstName.gridx = 2;
		gbc_labelFirstName.gridy = 3;
		add(labelFirstName, gbc_labelFirstName);
		
		JLabel labelShowFirstName = new JLabel("New label");
		GridBagConstraints gbc_labelShowFirstName = new GridBagConstraints();
		gbc_labelShowFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_labelShowFirstName.gridx = 4;
		gbc_labelShowFirstName.gridy = 3;
		add(labelShowFirstName, gbc_labelShowFirstName);
		
		JLabel labelLastName = new JLabel("Last Name:");
		GridBagConstraints gbc_labelLastName = new GridBagConstraints();
		gbc_labelLastName.insets = new Insets(0, 0, 5, 5);
		gbc_labelLastName.gridx = 2;
		gbc_labelLastName.gridy = 4;
		add(labelLastName, gbc_labelLastName);
		
		JLabel labelShowLastName = new JLabel("New label");
		GridBagConstraints gbc_labelShowLastName = new GridBagConstraints();
		gbc_labelShowLastName.insets = new Insets(0, 0, 5, 5);
		gbc_labelShowLastName.gridx = 4;
		gbc_labelShowLastName.gridy = 4;
		add(labelShowLastName, gbc_labelShowLastName);
		
		JLabel LabelDescription = new JLabel("Description:");
		GridBagConstraints gbc_LabelDescription = new GridBagConstraints();
		gbc_LabelDescription.insets = new Insets(0, 0, 5, 5);
		gbc_LabelDescription.gridx = 2;
		gbc_LabelDescription.gridy = 5;
		add(LabelDescription, gbc_LabelDescription);
		
		JLabel labelShowBio = new JLabel("New label");
		GridBagConstraints gbc_labelShowBio = new GridBagConstraints();
		gbc_labelShowBio.insets = new Insets(0, 0, 5, 5);
		gbc_labelShowBio.gridx = 4;
		gbc_labelShowBio.gridy = 5;
		add(labelShowBio, gbc_labelShowBio);
		
		JLayeredPane layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.insets = new Insets(0, 0, 5, 5);
		gbc_layeredPane.gridwidth = 5;
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 1;
		gbc_layeredPane.gridy = 7;
		add(layeredPane, gbc_layeredPane);
		GridBagLayout gbl_layeredPane = new GridBagLayout();
		gbl_layeredPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_layeredPane.rowHeights = new int[]{0, 0};
		gbl_layeredPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_layeredPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		layeredPane.setLayout(gbl_layeredPane);
		
		JLabel lblNewLabel = new JLabel("Favorite Movies");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 0;
		layeredPane.add(lblNewLabel, gbc_lblNewLabel);
	}

	
}
