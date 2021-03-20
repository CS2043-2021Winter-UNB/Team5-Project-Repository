
import java.sql.*;
import java.util.ArrayList;

public class DataManager {

<<<<<<< HEAD
	//private AdminAccountObject adminAccount;
	//private MovieObject movie;
	//private ReviewObject review;
	//private MemberAccountObject member;
=======
	public AdminAccountObject adminAccount;
	public MovieObject movie;
	public ReviewObject review;
	public Connection connection = null;

	public DataManager() {
		 try {
	         Class.forName("com.mysql.jdbc.Driver").newInstance();
	     } catch (Exception e) {
	      System.err.println(e.toString());
	     }
		String url = "jdbc:mysql://cs2043.cs.unb.ca:3306/cs2043team5";
		try {
		connection = DriverManager.getConnection(url, "cs2043team5", "E8mP1JDK");
		} catch (SQLException e) {
		System.err.println("Database connection error: " + e);
		}
	}
	
	
>>>>>>> 1f17b7175fc8a9f52405f1e67f05e17ae12fc47f

	
	public MemberAccountObject getMember(String username, String password) {

		//create statement 
		Statment stmt = conn.createStatement();

		//SQL query String 
		String sqlQuery = "select * from MemberAccount where username = '" + username +
						  "' and password = sha1(" + password ");";

		//ResultSet 
		ResultSet rs = stmt.executeQuery(sqlQuery);

		//Create MemberAccountObject 
		MemberAccountObject member = new MemberAccountObject();
		member.username = rs.getString(1);
		memeber.password = rs.getString(2);
		member.simething = rs.getString(3);
		memeber. something = rs.getString(4);
		member. somethingelse = rs.getString(5);

		return member;
		// end-user-code
	}

	public AdminAccountObject getAdmin(String username, String password) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public int getMovieRatingByMember(String username, int movieID) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	public ArrayList<ReviewObject> getMovieReviews(int movieID) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public ArrayList<MovieObject> getMoviesbyKeywords(String title, String releaseYear, String genre, int length,
			String language) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public void addMemberAccount(String username, String password, String email) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void addMovieRequest(Object movieRequest) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void addMovieReview(ReviewObject review) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void addMovieRating(RatingObject rating) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void removeMemberAccount(String username) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void removeMovieRequest(int requestID) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void removeRating() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void removeMovie(int movieID) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void removeReview() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public AverageRatingObject getAverageMovieRating(int movieID) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}