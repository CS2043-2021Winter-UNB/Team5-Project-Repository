import javax.swing.JPanel;
import java.awt.GridBagLayout;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollBar;

public class ViewReviewUI extends JPanel {

	private static final long serialVersionUID = -5574002578266475677L;
	private ViewReviewControl viewReviewControl;
	private RemoveReviewUI removeReviewUI;
	private ViewMemberUI viewMemberUI;
	private LoginControl loginControl;
	private int movieId;
	private JLabel labelMovieName;
	private ArrayList<ReviewObject> reviews;
	private DefaultListModel<String> model;
	private JButton buttonRemoveReview;
	private JScrollPane scrollPane;
	private JList<String> list;
	private int selectedIndex;
	private String reviewUsername;
	private int reviewObjectIndex;
	private int reviewId;
	private Color lightblue;
	int check = 0;
	

	public ViewReviewUI(ViewReviewControl controlViewReview, RemoveReviewUI uiRemoveReview, ViewMemberUI uiViewMember, LoginControl controlLogin) {
		viewReviewControl = controlViewReview;
		loginControl = controlLogin;
		removeReviewUI = uiRemoveReview;
		viewMemberUI = uiViewMember;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 20, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{30, 40, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		labelMovieName = new JLabel("");
		GridBagConstraints gbc_labelMovieName = new GridBagConstraints();
		gbc_labelMovieName.insets = new Insets(0, 0, 5, 5);
		gbc_labelMovieName.gridx = 2;
		gbc_labelMovieName.gridy = 0;
		add(labelMovieName, gbc_labelMovieName);
		
		JLabel labelReviewTitle = new JLabel("Reviews");
		GridBagConstraints gbc_labelReviewTitle = new GridBagConstraints();
		gbc_labelReviewTitle.insets = new Insets(0, 0, 5, 5);
		gbc_labelReviewTitle.gridx = 3;
		gbc_labelReviewTitle.gridy = 0;
		add(labelReviewTitle, gbc_labelReviewTitle);
		
		buttonRemoveReview = new JButton("Remove Review");
		buttonRemoveReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean removed = removeReviewUI.displayRemoveReviewWarning(reviewUsername,reviewId);
				if (removed){
					//remove from review list
					reviews.remove(reviewObjectIndex);
					//remove username and review text from Jlist
					model.removeElementAt(2*reviewObjectIndex+1);
					model.removeElementAt(2*reviewObjectIndex);

					buttonRemoveReview.setVisible(false);
					removeReviewUI.displayRemoveReviewConfirmation();
				}
			}
		});
		GridBagConstraints gbc_buttonRemoveReview = new GridBagConstraints();
		gbc_buttonRemoveReview.insets = new Insets(0, 0, 5, 0);
		gbc_buttonRemoveReview.gridx = 5;
		gbc_buttonRemoveReview.gridy = 0;
		add(buttonRemoveReview, gbc_buttonRemoveReview);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
 	    lightblue = new Color(225,246,255);
		
		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		ListSelectionListener listSelectionListener = new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent listSelectionEvent) {
		        int temp = list.getSelectedIndex();
		        if (temp != selectedIndex) {
		        	
		        }
		    }};
		list.addListSelectionListener(listSelectionListener);
		
		//change the look of certain rows 
		list.setCellRenderer(new DefaultListCellRenderer() {
			@Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                  boolean isSelected, boolean cellHasFocus) {
                  Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	              String text = String.valueOf(value);
	              boolean usernameCheck = (index%2 == 0);
	              if (usernameCheck) {
	            	  setFont(new Font ("Serif", Font.BOLD, 12));
	              }
	              else {
	            	  setFont(new Font ("Serif", Font.PLAIN, 12));
	              }
	              
	              check++;
	              System.out.println(check);
	              
	              //set username and review field to light blue if selected
	              if (selectedIndex == index) {
	                  setBackground(lightblue);   
	                  //System.out.println("here1 " + getText());
	              } 
	              else if ((selectedIndex%2 == 0) && (index-selectedIndex) == 1) {
	            	  setBackground(lightblue);   
	            	  //System.out.println("here2 " + getText());
	              }
	              else if ((selectedIndex%2 != 0) && (selectedIndex-index) ==1) {
	            	  setBackground(lightblue);
	            	  //System.out.println("here3 " + getText());
	              }
	              else {
	                   setBackground(Color.WHITE);
	              }
                 return c;
            }

       });
		
		
		scrollPane.setViewportView(list);
		

		MouseListener mouseListener = new MouseAdapter() {
	      public void mouseClicked(MouseEvent mouseEvent) {
	    	  
	        if (list == (JList) mouseEvent.getSource()) {
		        int index = list.locationToIndex(mouseEvent.getPoint());
	        	//if click was within Jlist rows
	        	if (index >= 0) {
	        		selectedIndex = index;
	        		reviewObjectIndex = index/2;
	        		boolean checkUsername = (index%2 == 0);
	        		String reviewUsername;
	        		String review;
	        		if (checkUsername) {
			        	reviewUsername = model.elementAt(index);
			        	review = model.elementAt(index+1);
	        		}
	        		else
	        		{
			        	reviewUsername = model.elementAt(index-1);
			        	review = model.elementAt(index);
	        		}
	        		
	        		MemberObject member = loginControl.getCurrentMember();
	        		boolean memberCheck = false;
	        		if (member != null) {
	        			memberCheck = (reviewUsername.equals(member.getUsername()));
	        		}
	        		if (loginControl.getCurrentAdmin() != null || memberCheck) {
	        			buttonRemoveReview.setVisible(true);
	        			reviewId = reviews.get(reviewObjectIndex).getReviewId();
	        		}
	        		else {
	        			buttonRemoveReview.setVisible(false);
	        		}
	        		
		        	//if username in the review Jlist has been double-clicked, call viewMemberUI for that username.
			        if (mouseEvent.getClickCount() == 2) {
			          if (checkUsername) {    	
			            viewMemberUI.displayViewMemberAccount(reviewUsername);
			          }
			        }
	        	}
	        }
	      }
	    };
	    list.addMouseListener(mouseListener);

		
		setVisible(false);
	}
	
	//displays the reviews for that movie 
	public void displayReview(String movieName, int movieId) {
		//Make the title label display the name of the movie 
		labelMovieName.setText(movieName);
		this.movieId = movieId;
		
		//if there are reviews in the model from the last time it was displayed, clear them
		if (model.getSize() != 0)
		{
			model.removeAllElements();
		}
		reviews = viewReviewControl.processViewReview(movieId);
		if (reviews != null)
		{
			for (ReviewObject review : reviews)
			{
				model.addElement(review.getUsername());
				model.addElement(review.getReviewText());
			}
		}
		
		buttonRemoveReview.setVisible(false);
		
		setVisible(true);
	}

}
