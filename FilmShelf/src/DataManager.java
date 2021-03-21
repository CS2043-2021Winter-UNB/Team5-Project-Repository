
import java.util.ArrayList;

public class DataManager {

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 58bf5a74cad30e919378ac10dc87e06b577df9e1
	//private AdminAccountObject adminAccount;
	//private MovieObject movie;
	//private ReviewObject review;
	//private MemberAccountObject member;

=======
	public AdminAccountObject adminAccount;
	public MovieObject movie;
	public ReviewObject review;

	public DataManager() {
		try {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	    } 
		catch(Exception e) {
	    	System.err.println(e.toString());
	    }

		String url = "jdbc:mysql://cs2043.cs.unb.ca:3306/cs2043team5";

		try {
			connection = DriverManager.getConnection(url, "cs2043team5", "E8mP1JDK");
		} 
		catch(SQLException e) {
			System.err.println("Database connection error: " + e);
>>>>>>> parent of 6000dd6... Added the MySQL-JDBC driver to the java build path.
=======
>>>>>>> 58bf5a74cad30e919378ac10dc87e06b577df9e1
		}

		// end-user-code
	}

	public MemberAccountObject getMember(String username, String password) {

		//Create MemberAccountObject 
		MemberAccountObject member = new MemberAccountObject();

		//create statement 
		Statment stmt = connection.createStatement();

		//SQL query String 
		String sqlQuery = "select * from MemberAccount where username = '" + username +
						  "' and password = sha1(" + password ");";

		try{

			//ResultSet 
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//assigning values to memberAccountObject	
			member.username = rs.getString(1);
			memeber.password = rs.getString(2);
			member.firstName = rs.getString(3);
			memeber.lastName = rs.getString(4);
			member.topMovies = rs.getString(5);
			member.description = rs.getString();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}

			return member;
	}
<<<<<<< HEAD

	public AdminAccountObject getAdmin(String username, String password){
		// begin-user-code
		// TODO Auto-generated method stub
		//create statement 
		Statment stmt = conn.createStatement();

		//Create MemberAccountObject 
		AdminAccountObject member = new MemberAccountObject();

		//SQL query String 
		String sqlQuery = "select * from MemberAccount where username = '" + username +
						  "' and password = sha1(" + password ");";

		//ResultSet 
		ResultSet rs = stmt.executeQuery(sqlQuery);

		//Assigning values to adminAccountObject
		admin.username = rs.getString(1);
		admin.password = rs.getString(2);

		//return adminAccountObject
		return admin;
		// end-user-code
	}

	public boolean addMemberAccount(String username, String password, String firstName,
								 String lastName) {
		// begin-user-code
		// TODO Auto-generated method stub
		//create statement 
		Statment stmt = conn.createStatement();

		//SQL query String 
		String sqlQuery = "insert into MemberAccount values('" + username + "', sha1('" + password + "'), '" 
						   + firstName + "', '" + lastName + "');";

		//ResultSet 
		ResultSet rs = stmt.executeQuery(sqlQuery);

		//Create MemberAccountObject 
		MemberAccountObject member = new MemberAccountObject();
	
		return 
		// end-user-code
	}

	public int editMemberAccount(String username, String[] values){
		//String query 
		if(values[1] != null){
			stmt.executeQuery()
		}
		
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