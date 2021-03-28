	import java.awt.EventQueue;

	import javax.swing.JPanel;
	import java.awt.GridBagLayout;
	import javax.swing.JLabel;
	import java.awt.GridBagConstraints;
	import java.awt.Insets;
	import javax.swing.JTextField;
	import javax.swing.JPasswordField;
	import javax.swing.JButton;
	import javax.swing.JRadioButton;
	import javax.swing.JCheckBox;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	import javax.swing.SwingConstants;
	import javax.swing.JFrame;
	import java.awt.Color;
	import javax.swing.JLayeredPane;

public class SearchMovieUI extends JPanel {

	private SearchMovieControl searchMovieControl;
	private JFrame frame;
	private JTextField textFieldSearch;
	private JLabel labelSearchMovieStatus;
	
	public SearchMovieUI(SearchMovieControl control) {
		frame = new JFrame();
		searchMovieControl = control;
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Search Movie:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enterMovieSearchCriteria();
				displaySearchResult();
			}
		});
		GridBagConstraints gbc_textFieldSearch = new GridBagConstraints();
		gbc_textFieldSearch.gridwidth = 3;
		gbc_textFieldSearch.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSearch.gridx = 2;
		gbc_textFieldSearch.gridy = 1;
		frame.getContentPane().add(textFieldSearch, gbc_textFieldSearch);
		textFieldSearch.setColumns(10);
		
		labelSearchMovieStatus = new JLabel("");
		labelSearchMovieStatus.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelSearchMovieStatus = new GridBagConstraints();
		gbc_labelSearchMovieStatus.gridwidth = 6;
		gbc_labelSearchMovieStatus.insets = new Insets(0, 0, 5, 5);
		gbc_labelSearchMovieStatus.gridx = 1;
		gbc_labelSearchMovieStatus.gridy = 7;
		add(labelSearchMovieStatus, gbc_labelSearchMovieStatus);
	}
	
	public void displaySearchForm() {
		// begin-user-code
		// TODO Auto-generated method stub
		setVisible(true);
		// end-user-code
	}

	public void displaySearchResult() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void displayFailedSearchMessage() {
		// begin-user-code
		// TODO Auto-generated method stub
		labelSearchMovieStatus.setText("Movie search was unsuccessful. Information was invalid.");
		// end-user-code
	}

	public void enterMovieSearchCriteria() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}