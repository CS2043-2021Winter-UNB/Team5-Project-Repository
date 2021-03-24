
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataManager {



	//private AdminAccountObject adminAccount;
	//private MovieObject movie;
	//private ReviewObject review;
	//private MemberAccountObject member;

	public AdminAccountObject adminAccount;
	public MovieObject movie;
	public ReviewObject review;
	private int numTopMovies = 5;
	private Connection connection;

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
		}

		// end-user-code
	}

	public MemberAccountObject getMember(String username, String password) {

		//Create MemberAccountObject 
		MemberAccountObject member = new MemberAccountObject();

		

		//SQL query String 
		String sqlQuery = "select * from MemberAccount where username = '" + username +
						  "' and password = sha1('" + password + "');";

		try{

			//Create statement 
			Statement stmt = connection.createStatement();

			//ResultSet 
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//assigning values to memberAccountObject	
			member.username = rs.getString(1);
			member.password = rs.getString(2);
			member.firstName = rs.getString(3);
			member.lastName = rs.getString(4);

			//Array to store members top movie IDs
			ArrayList<Integer> movieId = [numTopMovies];

	
			//Adding movie IDs to array 
			for(int i = 5; i < i + numTopMovies; i++){
				movieId.add(rs.getInt(i));
			}

			//Create new statement for new call
			Statement stmt2 = connection.createStatement();

			//Initialize ResultSet 
			ResultSet movies;

			//Loop to add movie titles to member object
			for(int i = 0; i < numTopMovies; i++){

				//SQL String Query for movie at index i 
				sqlQuery = "select title from Movie where movieID = " + movieId[i] + ";";

				//Execute Query to retrieve movie title
				movies = stmt2.executeQuery(sqlQuery);

				//Add movie title to member object
				member.topMovies.add(movies.getString(1));
				
			}

		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}

			//return member object
			return member;
	}


	public AdminAccountObject getAdmin(String username, String password){
		// begin-user-code
		// TODO Auto-generated method stub
		

		//Create MemberAccountObject 
		AdminAccountObject admin = new AdminAccountObject();

		//SQL query String 
		String sqlQuery = "select * from AdminAccount where username = '" + username +
						  "' and password = sha1('" + password + "');";


		try{

			//create statement 
			Statement stmt = connection.createStatement();
			//ResultSet 
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//Assigning values to adminAccountObject
			admin.username = rs.getString(1);
			admin.password = rs.getString(2);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}

		//return adminAccountObject
		return admin;
		// end-user-code
	}

	public boolean addMemberAccount(String username, String password, String firstName,
								 String lastName) {
		// begin-user-code
		// TODO Auto-generated method stub

		

		//SQL query String 
		String sqlQuery = "insert into MemberAccount values('" + username + "', sha1('" + password + "'), '" 
						   + firstName + "', '" + lastName + "');";

		//ResultSet
		try{

			//Create statement 
			Statement stmt = connection.createStatement();

			//ResultSet
			ResultSet rs = stmt.executeQuery(sqlQuery);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}

		return true;
		// end-user-code
	}

	public boolean editMemberAccount(String username, String[] values){

	

		//String query 
		String sqlQuery;

		try{

			//Create Statement 
			Statement stmt = connection.createStatement();

			//If new value is passed in update firstName
			if(values[1] != null){
				sqlQuery = "update MemberAccount set firstName = '" + values[1] + "' where username = '"
						  	+ username + "';";
						
				stmt.executeQuery(sqlQuery);
			}

			//If new value is passed in update lastName 
			if(values[2] != null){
				sqlQuery = "update MemberAccount set lastName = '" + values[2] + "' where username = '"
						  	+ username + "';";
				stmt.executeQuery(sqlQuery);
			}

			//if new value is passed in update description
			if(values[3] != null){

				sqlQuery = "update MemberAccount set description = '" + values[3] + "' where username = '"
						  	+ username + "';";
				stmt.executeQuery(sqlQuery);

			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}

			
		return true;
		
		
	}

	public boolean removeMemberAccount(String username) {
		// begin-user-code

		//String query 
		String sqlQuery = "delete from MemberAccount where username = '" + username + "';";

		try{

			//create statement 
			Statement stmt = connection.createStatement();

			//Execute query
			stmt.executeQuery(sqlQuery);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}

		return true;
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