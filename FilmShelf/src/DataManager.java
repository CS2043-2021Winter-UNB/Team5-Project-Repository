
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataManager {

	private MemberObject member;
	private AdminObject admin;
	private MovieObject movie;
	private ReviewObject review;
	private int numTopMovies = 5;
	private int lastTopMovieIndex = 9;
	private Connection connection;


	//Jessie-Anne
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

<<<<<<< HEAD

	//Courtney and Jo
	public boolean addMemberAccount(String username, String password, String firstName, String lastName, String description) {
		//SQL query String 
		String sqlQuery = "insert into MemberAccount(username, password, firstName, lastName, description) values('" + 
							username + "', sha1('" + password + "'), '" + firstName + "', '" + lastName + "', '" + description + "');";

		//ResultSet
		try {
			//Create statement 
			Statement stmt = connection.createStatement();

			//ResultSet
			int rowsUpdated = stmt.executeUpdate(sqlQuery);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}


	//Courtney and Jo
	public boolean editMemberAccount(String username, String password, String firstName, String lastName, String description){
		//String query 
		String sqlQuery;

		try {
			//Create Statement 
			Statement stmt = connection.createStatement();

			//If new value is passed in update password
			if(password != null){
				sqlQuery = "update MemberAccount set password = sha1('" + password + "') where username = '" + username + "';";
				stmt.executeUpdate(sqlQuery);
			}
			
			//If new value is passed in update firstName
			if(firstName != null){
				sqlQuery = "update MemberAccount set firstName = '" + firstName + "' where username = '" + username + "';";
				stmt.executeUpdate(sqlQuery);
			}

			//If new value is passed in update lastName 
			if(lastName != null){
				sqlQuery = "update MemberAccount set lastName = '" + lastName + "' where username = '" + username + "';";
				stmt.executeUpdate(sqlQuery);
			}

			//if new value is passed in update description
			if(description != null){
				sqlQuery = "update MemberAccount set description = '" + description + "' where username = '" + username + "';";
				stmt.executeUpdate(sqlQuery);
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}



	//Courtney and Jo 
	public boolean removeMemberAccount(String username) {
		//String query 
		String sqlQuery = "delete from MemberAccount where username = '" + username + "';";

		try {
			//create statement 
			Statement stmt = connection.createStatement();

			//Execute query
			stmt.executeUpdate(sqlQuery);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}


	//Courtney and Jo (make changes based on top movies)
	public MemberAccountObject getMember(String username, String password) {

		//Get Members top movies!!! (Needs to be added). 
=======
	public MemberObject getMember(String username, String password) {
>>>>>>> edebabd87eea0631a97b1c265efa72198b11adde
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
			rs.next();		// need to call to point cursor to first record
			uName = rs.getString(1);
			firstName = rs.getString(3);
			lastName = rs.getString(4);
			description = rs.getString(10);

			//Adding movie IDs to array 
			for(int i = 5; i <= lastTopMovieIndex; i++){
				movieIds.add(rs.getInt(i));
			}
		}
		catch(SQLException e) {
			System.out.println();
			System.out.println("Member login error: " + e.getMessage());
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
					movies.next();		// need to call to point cursor to first record
					topMovies.add(new MovieObject(movies.getString(1), movies.getInt(2), movies.getString(3), movies.getString(4), movies.getInt(5), movies.getDouble(6), movies.getInt(7)));
				}
			}
		}
		catch(SQLException e) {
			System.out.println("Top movies retrieval error: " + e.getMessage());
			return null;
		}
			// Create MemberAccountObject to return
			return new MemberObject(uName, firstName, lastName, description, topMovies);
	}


<<<<<<< HEAD
	//Courtney and Jo
	public AdminAccountObject getAdmin(String username, String password){
=======
	public AdminObject getAdmin(String username, String password){
>>>>>>> edebabd87eea0631a97b1c265efa72198b11adde
		String uName = "";

		//SQL query String 
		String sqlQuery = "select * from AdminAccount where username = '" + username +
						  "' and password = sha1('" + password + "');";

		try {
			//create statement 
			Statement stmt = connection.createStatement();
			//ResultSet 
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//Assigning values to adminObject
			rs.next();		// need to call to point cursor to first record
			uName = rs.getString(1);
		}
		catch(SQLException e) {
			System.out.println("Admin login error: " + e.getMessage());
		}

		//return adminObject
		return new AdminObject(uName);
	}

<<<<<<< HEAD
=======
	public boolean addMember(String username, String password, String firstName, String lastName, String description) {
		//SQL query String 
		String sqlQuery = "insert into MemberAccount(username, password, firstName, lastName, description) values('" + 
							username + "', sha1('" + password + "'), '" + firstName + "', '" + lastName + "', '" + description + "');";

		//ResultSet
		try {
			//Create statement 
			Statement stmt = connection.createStatement();

			//ResultSet
			int rowsUpdated = stmt.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.out.println("New member creation error: " + e.getMessage());
			return false;
		}

		return true;
	}

	public boolean editMember(String username, String password, String firstName, String lastName, String description){
		//String query 
		String sqlQuery;

		try {
			//Create Statement 
			Statement stmt = connection.createStatement();

			//If new value is passed in update password
			if(password != null){
				sqlQuery = "update MemberAccount set password = sha1('" + password + "') where username = '" + username + "';";
				stmt.executeUpdate(sqlQuery);
			}
			
			//If new value is passed in update firstName
			if(firstName != null){
				sqlQuery = "update MemberAccount set firstName = '" + firstName + "' where username = '" + username + "';";
				stmt.executeUpdate(sqlQuery);
			}
>>>>>>> edebabd87eea0631a97b1c265efa72198b11adde


<<<<<<< HEAD
=======
			//if new value is passed in update description
			if(description != null){
				sqlQuery = "update MemberAccount set description = '" + description + "' where username = '" + username + "';";
				stmt.executeUpdate(sqlQuery);
			}
		}
		catch(SQLException e) {
			System.out.println("Editing member details error: " + e.getMessage());
			return false;
		}
>>>>>>> edebabd87eea0631a97b1c265efa72198b11adde

	//Jessie-Anne 
	public boolean addMovie(){
		//begin-user-code
		//end-user-code
	}

<<<<<<< HEAD
=======
	public boolean removeMember(String username) {
		//String query 
		String sqlQuery = "delete from MemberAccount where username = '" + username + "';";
>>>>>>> edebabd87eea0631a97b1c265efa72198b11adde


<<<<<<< HEAD
=======
			//Execute query
			stmt.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.out.println("Member removal error: " + e.getMessage());
			return false;
		}
>>>>>>> edebabd87eea0631a97b1c265efa72198b11adde

	//Jessie-Anne
	public void removeMovie(int movieID) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}




	//Courtney 
	public ArrayList<MovieObject> getMoviesbyKeywords(String title, String releaseYear, String genre, int length,
			String language) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}



	//Jessie-Anne
	public void addMovieRating(RatingObject rating) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}





	//Jessie-Anne
	public void editMovieRating(){

	}



	//Jessie-Anne
	public void removeMovieRating() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}




	//Courtney 
	public int getMovieRatingByMember(String username, int movieID) {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}


	//Jessie-Anne
	public void addMovieReview(ReviewObject review) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}


	//Jessie-Anne
	public void removeMovieReview() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}



	//Courtney 
	public ArrayList<ReviewObject> getMovieReviews(int movieID) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}


}