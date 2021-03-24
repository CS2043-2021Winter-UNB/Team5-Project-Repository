
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
	private int lastTopMovieIndex = 9;
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
	}

	public MemberAccountObject getMember(String username, String password) {
		// Declaring variables
		String uName = "";
		String firstName = "";
		String lastName = "";
		String description = "";
		ArrayList<MovieObject> topMovies = new ArrayList<MovieObject>();
		ArrayList<Integer> movieIds = new ArrayList<Integer>();		//Array to store members top movie IDs

		//SQL query String 
		String sqlQuery = "select * from MemberAccount where username = '" + username +
						  "' and password = sha1('" + password + "');";

		try {
			//Create statement 
			Statement stmt = connection.createStatement();

			//ResultSet 
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//assigning values to memberAccountObject	
			uName = rs.getString(1);
			firstName = rs.getString(3);
			lastName = rs.getString(4);
			description = rs.getString(10);

			//Adding movie IDs to array 
			for(int i = 5; i <= lastTopMovieIndex; i++){
				movieIds.add(rs.getInt(i));
			}
		}
		catch(SQLException e){
			System.out.println("Error querying MemberAccount table");
			System.out.println(e.getMessage());
			return null;
		}
		
		try {
			//Create new statement for new call
			Statement stmt2 = connection.createStatement();

			//Initialize ResultSet 
			ResultSet movies;
			
			//Loop to add movie titles to member object
			for(int i = 0; i < numTopMovies; i++) {
				// ResultSet.getInt(i) returns 0 for null values, only query db for non-null values
				if(movieIds.get(i) != 0) {
					//SQL String Query for movie at index i 
					sqlQuery = "select title from Movie where movieID = " + movieIds.get(i).intValue() + ";";

					//Execute Query to retrieve movie title
					movies = stmt2.executeQuery(sqlQuery);

					//Add movie title to member object
					topMovies.add(new MovieObject(movies.getString(1), movies.getInt(2), movies.getString(3), movies.getString(4), movies.getInt(5), movies.getDouble(6), movies.getInt(7)));
				}
			}
		}
		catch(SQLException e){
			System.out.println("Error querying Movie table");
			System.out.println(e.getMessage());
			return null;
		}
			// Create MemberAccountObject to return
			return new MemberAccountObject(uName, firstName, lastName, description, topMovies);
	}


	public AdminAccountObject getAdmin(String username, String password){
		String uName = "";

		//SQL query String 
		String sqlQuery = "select * from AdminAccount where username = '" + username +
						  "' and password = sha1('" + password + "');";

		try {
			//create statement 
			Statement stmt = connection.createStatement();
			//ResultSet 
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//Assigning values to adminAccountObject
			uName = rs.getString(1);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}

		//return adminAccountObject
		return new AdminAccountObject(uName);
	}

	public boolean addMemberAccount(String username, String password, String firstName, String lastName, String description) {
		//SQL query String 
		String sqlQuery = "insert into MemberAccount(username, password, firstName, lastName, description) values('" + 
							username + "', sha1('" + password + "'), '" + firstName + "', '" + lastName + "', '" + description + "');";

		//ResultSet
		try {
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
	}

	public boolean editMemberAccount(String username, String password, String firstName, String lastName, String description){
		//String query 
		String sqlQuery;

		try {
			//Create Statement 
			Statement stmt = connection.createStatement();

			//If new value is passed in update password
			if(password != null){
				sqlQuery = "update MemberAccount set password = sha1('" + password + "') where username = '" + username + "';";
				stmt.executeQuery(sqlQuery);
			}
			
			//If new value is passed in update firstName
			if(firstName != null){
				sqlQuery = "update MemberAccount set firstName = '" + firstName + "' where username = '" + username + "';";
				stmt.executeQuery(sqlQuery);
			}

			//If new value is passed in update lastName 
			if(lastName != null){
				sqlQuery = "update MemberAccount set lastName = '" + lastName + "' where username = '" + username + "';";
				stmt.executeQuery(sqlQuery);
			}

			//if new value is passed in update description
			if(description != null){
				sqlQuery = "update MemberAccount set description = '" + description + "' where username = '" + username + "';";
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
		//String query 
		String sqlQuery = "delete from MemberAccount where username = '" + username + "';";

		try {
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