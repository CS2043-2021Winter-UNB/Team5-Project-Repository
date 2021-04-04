import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataManager {

	private final int NUM_TOP_MOVIES = 5;
	private final int LAST_TOP_MOVIE_INDEX = 9;
	private Connection connection;

	//Jessie-Anne - set up DataManager and connects to the team5 DB
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


	//Courtney and Jo
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
			System.out.println("Add member error: " + e.getMessage());
			return false;
		}

		return true;
	}


	//Courtney and Jo
	//Make sure null fields aren't null in the database!!!!!
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
		catch(SQLException e) {
			System.out.println("Edit member error: " + e.getMessage());
			return false;
		}

		return true;
	}



	//Courtney and Jo 
	public boolean removeMember(String username) {
		//String query 
		String sqlQuery = "delete from MemberAccount where username = '" + username + "';";

		try {
			//create statement 
			Statement stmt = connection.createStatement();

			//Execute query
			stmt.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.out.println("Remove member error: " + e.getMessage());
			return false;
		}

		return true;
	}


	//Courtney and Jo (make changes based on top movies)
	public MemberObject getMember(String username, String password) {
		// Declaring variables
		String uName = "";
		String firstName = "";
		String lastName = "";
		String description = "";
		ArrayList<MovieObject> topMovies = new ArrayList<MovieObject>();
		ArrayList<Integer> movieIds = new ArrayList<Integer>();		//Array to store members top movie IDs

		//SQL query String 
		String sqlQuery = "select * from MemberAccount where username = '" + username + "' and password = sha1('" + password + "');";

		try {
			//Create statement 
			Statement stmt = connection.createStatement();

			//ResultSet 
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//assigning values to MemberObject	
			rs.next();		// need to call to point cursor to first record
			uName = rs.getString(1);
			firstName = rs.getString(3);
			lastName = rs.getString(4);
			description = rs.getString(5);
			topMovies  = this.getTopMovies(username);

	
		}
		catch(SQLException e) {
			System.out.println();
			System.out.println("Member login error: " + e.getMessage());
			return null;
		}
		
		
			// Create MemberObject to return
			return new MemberObject(uName, firstName, lastName, description, topMovies);
	}


	//NOT DONE!!!
	//getMember for View Member Case
	/** 
	public MemberObject getMember(String username){
		//SQL String Query
		String sqlQuery = "select *  from MemberAccount where username = '" + username + "';";

		try{
			//Create Statement 
			Statement stmt = connection.createStatement();

			//Initialize Result Set



	}
	*/


	//Courtney and Jo
	public AdminObject getAdmin(String username, String password){

		String uName = "";

		//SQL query String 
		String sqlQuery = "select * from AdminAccount where username = '" + username + "' and password = sha1('" + password + "');";

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

	


	//Jessie-Anne 
	public boolean addMovie(String title, int releaseYear, String genre, int length){
		//SQL query String 
		String sqlQuery = "insert into Movie(title, releaseYear, genre, length) values('" +	title + "'," + releaseYear + ", '" + genre + "', " + length + ");";

		//ResultSet
		try {
			//Create statement 
			Statement stmt = connection.createStatement();

			//ResultSet
			int rowsUpdated = stmt.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.out.println("Add movie error: " + e.getMessage());
			return false;
		}

		return true;
	}


	//Jessie-Anne
	public boolean removeMovie(int movieID) {
		//SQL String query 
		String sqlQuery = "delete from Movie where movieID = " + movieID + ";";

		try {
			//Create statement 
			Statement stmt = connection.createStatement();

			//Execute query
			stmt.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.out.println("Remove movie error: " + e.getMessage());
			return false;
		}

		return true;
	}



	//NOT DONE!!!
	//Courtney 
	public ArrayList<MovieObject> getMoviesbyKeywords(ArrayList<String> title, int  minReleaseYear, int maxReleaseYear, String genre, int minLength, int maxLength) {

		//Creating MovieArrayList to return to user
		ArrayList<MovieObject> movieList = new ArrayList<MovieObject>();

		//SQL String Query 
		String sqlQuery = "select from Movie where";

		//Add Desired title to Query (does not need to be exact)
		for (int i=0; i<title.size(); i++) {
			if (i < title.size() - 1)
				sqlQuery = sqlQuery + " title like '%" + title.get(i) + "%' or ";
			else sqlQuery = sqlQuery + " title like '%" + title.get(i) + "%'";
		}

		//Add Desired Genre to Query 
		if(genre != null){
			sqlQuery = sqlQuery + " and genre = '" + genre + "'"; 
		}
		
		//Add Desired Release Year to Query
		if(minReleaseYear != null && maxReleaseYear != null){
			if(minReleaseYear == maxReleaseYear){
				sqlQuery = sqlQuery + " and releaseYear = '" + releaseYear + "'"; 
 			}
			else{
				sqlQuery = sqlQuery + " and releasYear between '" + minReleaseYear + "' and '" + maxReleaseYear = "'";
			}
		
		} 
		


		//Add Desired Length to Query  
		if(minLength != null && maxLength != null){
			if(minLength == maxLength){
				sqlQuery = sqlQuery + " and releaseYear = '" + minLength + "'"; 
 			}
			else{
				sqlQuery = sqlQuery + " and releasYear between '" + minLength + "' and '" + maxLength = "'";
			}
		
		} 
		

		//Add semi-colon to end statement (must be done at end since end may vary)
		sqlQuery = sqlQuery + ";";


		try{
			//Create Statement
			Statement stmt = connection.createStatement();

			
			//Result Set
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//Create Movie Objects and Add to Movie List
			while (rs.next()) {

				MovieObject movie = new MovieObject();
				movie.title = rs.getString(1);
				movie.releaseYear = rs.getInt(2);
				movie.genre = rs.getString(3);
				movie.length = rs.getInt(4);
				movie.averageRating = rs.getDouble(6);
				movieList.add(movie);

			}

		}

		catch(SQLException e){
			System.out.println(e.getMessage());
			return null;		
		}

		return movieList;

	}

	//NOT DONE!!!
	//For View Movie
	public MovieObject getMovie(int movieID){

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
		Rating
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
		// Create ArrayList of ReviewObjects
		ArrayList<ReviewObject> reviewList = new ArrayList<ReviewObject>();


		//SQL String Query 
		String sqlQuery = "select * from Reviews where movieID = " + movieID + ";";

		try{

			//Create Statement 
			Statement stmt = connection.createStatement();

			//ResultSet 
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//Add ReviewObjects to reviewList
			while(rs.next()){

				//Temp ReviewObject
				ReviewObject review = new ReviewObject();
				review.movieId = rs.get
				review.username = 
				review.reviewText = rs.
				review.reviewId = rs.get

				reviewList.add(review);
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}

		return reviewlist;
		

		return null;
		// end-user-code
	}

	//Courtney
	//Helper method for getMember method. 
	private ArrayList<MovieObject> getTopMovies(String username){

		//Create ArrayList of MovieObjects
		ArrayList<MovieObject> topMovies = new ArrayList<MovieObject>();

		//SQL String Query
		String sqlString = "select movieID from Rating where username = '" + username + "' order by"
							+ " ratingScore;";

		ArrayList<Integer> movieIdList = new ArrayList<Integer>(NUM_TOP_MOVIES);
		
		try{

			//Create Statement 
			Statement stmt = connection.createStatement();

			//Result Set
			ResultSet rsId = stmt.executeQuery(sqlQuery);

			int i = 0;

			//Add Top Movie List Ids to MovieIdList
			while(rsID.next() && i < NUM_TOP_MOVIES){
				movieIdList[i] = rsId.getInt();
				i++;
			}


			//New Statement
			Statement stmt2 = connection.createStatement();

			for(int i = 0; i < movieIDS; i++){

				int movieId = movieIdList.get(i);

				sqlString = "select * from Movie where movieID = '" + movieId + "';";
				ResultSet movieInfo = stmt2.executeQuery(sqlString);

				movieInfo.next();

				String title = movieInfo.getString(1);
				int releaseYear = movieInfo.getInt(2);
				String genre = movieInfo.getString(3);
				int length = movieInfo.getInt(4);
				double averageRating = movieInfo.getDouble(6);
				
				 

				MovieObject movie = new Movie(title, releaseYear, genre, length, averageRating, movieId);

				movieList.add(movie)
			}		

		}


		}

		catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}

		return movieList;



	}
}

