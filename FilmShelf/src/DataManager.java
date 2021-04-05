import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataManager {

	private final int NUM_TOP_MOVIES = 5;
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
		String sqlQuery = "update MemberAccount set firstName = '" + firstName + "', lastName = '" + lastName 
							+ "', description = '" + description + "'";;

		try {
			//Create Statement 
			Statement stmt = connection.createStatement();

			//If new value is passed in update password
			if(password != null){

				sqlQuery = sqlQuery + ", password = sha1('" + password + "')";

			}


			sqlQuery = sqlQuery + " where username = '" + username + "';";

			int rowsUpdated = stmt.executeUpdate(sqlQuery);

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

		// Initialize Parameters for Constructor
		String uName = "";
		String firstName = "";
		String lastName = "";
		String description = "";
		ArrayList<MovieObject> topMovies;
		

		//SQL query String 
		String sqlQuery = "select * from MemberAccount where username = '" + username + "' and password = sha1('" + password + "');";

		try {
			//Create statement 
			Statement stmt = connection.createStatement();

			//ResultSet 
			ResultSet rs = stmt.executeQuery(sqlQuery);

		

			//Move Cursor to First Row
			rs.next();

			//Assign Values to Parameters 
			uName = rs.getString(1);
			firstName = rs.getString(3);
			lastName = rs.getString(4);
			description = rs.getString(5);
			topMovies = this.getTopMovies(username);

	
		}
		catch(SQLException e) {
			System.out.println();
			System.out.println("Member login error: " + e.getMessage());
			return null;
		}
		
		
		// Create MemberObject to return
		return new MemberObject(uName, firstName, lastName, description, topMovies);

		
	}



	//getMember for View Member Case
	public MemberObject getMember(String username){

		//Initilaize Parameters for Constructor 
		String uName = " ";
		String firstName = " ";
		String lastName = " ";
		String description = " ";
		ArrayList<MovieObject> topMovies;

		//SQL String Query
		String sqlQuery = "select *  from MemberAccount where username = '" + username + "';";

		try{

			//Create Statement 
			Statement stmt = connection.createStatement();

			//Initialize Result Set
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//Move Cursor to First Row
			rs.next();

			//Assign Values to Parameters 
			uName = rs.getString(1);
			firstName = rs.getString(3);
			lastName = rs.getString(4);
			description = rs.getString(5);
			topMovies = this.getTopMovies(username);

		}
		catch(SQLException e){
			System.out.println("View Member Error: " + e.getMessage());
			return null;
		}

		// Create MemberObject to return
		return new MemberObject(uName, firstName, lastName, description, topMovies);


	}


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



	//Courtney 
	public ArrayList<MovieObject> getMoviesbyKeywords(ArrayList<String> title, int  minReleaseYear, int maxReleaseYear, String genre, int minLength, int maxLength) {

		//Creating MovieArrayList to return to user
		ArrayList<MovieObject> movieList = new ArrayList<MovieObject>();

		//Create ArrayList of Integers to Store Movie IDs 
		ArrayList<Integer> movieIdList = new ArrayList<Integer>();

		//SQL String Query 
		String sqlQuery = "select movieID from Movie where";

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
		if(minReleaseYear != -1 && maxReleaseYear != -1){
			if(minReleaseYear == maxReleaseYear){
				sqlQuery = sqlQuery + " and releaseYear = " + releaseYear ; 
 			}
			else{
				sqlQuery = sqlQuery + " and releaseYear between " + minReleaseYear + " and " + maxReleaseYear ;
			}
		
		} 
		


		//Add Desired Length to Query  
		if(minLength != -1 && maxLength != -1){
			if(minLength == maxLength){
				sqlQuery = sqlQuery + " and releaseYear = " + minLength + ""; 
 			}
			else{
				sqlQuery = sqlQuery + " and releasYear between " + minLength + " and " + maxLength ;
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

				movieIdList.add(rs.getInt(1));

			}

			for(int index = 0; index < movieIdList.size(); index++){
				movieList.add(this.getMovie(movieIdList.get(index)));
			}

		}

		catch(SQLException e){
			System.out.println(e.getMessage());
			return null;		
		}

		return movieList;

	}

	//Courtney 
	//For View Movie (and getTopMovies)
	public MovieObject getMovie(int movieID){

		//Initialize Parameters for Constructor 
		String title;
		int releaseYear;
		String genre;
		int length;
		double averageRating;
		int movieId;

		//SQL String Query
		String sqlQuery = "select * from Movie where movieID = " + movieID + ";";

		try{

			//Create Statement 
			Statement stmt = connection.createStatement();

			//Initialize ResultSet
			ResultSet rs = stmt. executeQuery(sqlQuery);

			rs.next();
			title = rs.getString(1);
			releaseYear = rs.getInt(2);
			genre = rs.getString(3);
			length = rs.getInt(4);
			movieId = rs.getInt(5);
			averageRating = (this.getAverageRating(movieId));

			
		}
		catch(SQLException e){
			System.out.println("Get Movie Error: " + e.getMessage());
			return null;
		}

		return new MovieObject(title, releaseYear, genre, length, averageRating, movieId);

	}



	/** 
	//Jessie-Anne
	public void addMovieRating(RatingObject rating) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
	*/




	/** 
	//Jessie-Anne
	public void editMovieRating(){

	}
	*/



	/** 
	//Jessie-Anne
	public void removeMovieRating() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
	*/




	//Courtney 
	public RatingObject getMovieRatingByMember(String username, int movieID) {

		//Initilaize Parameters for Constructor
		int ratingScore;
		String uName = " ";
		int movieId;


		//SQL String query
		String sqlQuery = "select * from Rating where username = '" + username + "' and movieID = " + movieID + ";";

		try{
			//Create Statement
			Statement stmt = connection.createStatement();

			//ResultSet 
			ResultSet rs = stmt.executeQuery(sqlQuery);
			
			//Movie Cursor to first Row
			rs.next();

			ratingScore = rs.getInt(1);
			uName = rs.getString(2);
			movieId = rs.getInt(3);



		}

		catch(SQLException e){
			return null;
		}


		return new RatingObject(ratingScore, uName, movieId);


		// end-user-code
	}


	//Jessie-Anne
	public void addMovieReview(ReviewObject review) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}


	/** 
	//Jessie-Anne
	public void removeMovieReview() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
	*/



	//Courtney 
	public ArrayList<ReviewObject> getMovieReviews(int movieID) {

		//Initialize Parameters for Constructor 
		String uName = " ";
		int movID;
		String revText = " ";
		int revID;

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

				uName = rs.getString(1);
				movID = rs.getInt(2);
				revText = rs.getString(3);
				revID = rs.getInt(4);

				ReviewObject review = new ReviewObject(movID, uName, revText, revID);
				reviewList.add(review);
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}

		return reviewList;

	}


	//Courtney
	//Helper method for getMember method. 
	private ArrayList<MovieObject> getTopMovies(String username){

		
		//List of MovieIDs 
		ArrayList<Integer> movieIdList = new ArrayList<Integer>();

		//SQL String Query
		String sqlQuery = "select movieID from Rating where username = '" + username +
						  "' order by ratingScore desc;";

		try{

			//Create Statement
			Statement stmt = connection.createStatement();

			//Initialize ResultSet
			ResultSet rs = stmt.executeQuery(sqlQuery);

			int numMovie = 0;
			while(rs.next() && numMovie < NUM_TOP_MOVIES){
				movieIdList.add(rs.getInt(1));
				numMovie++;
			}

		}
		catch(SQLException e){
			System.out.println("Get Top Movies Error: " + e.getMessage());
			return null;
		}

		//List of MovieObjects to return to calling method
		ArrayList<MovieObject> movieList = new ArrayList<MovieObject>();


		//Add Movie Objects to Movie List
		for(int index = 0; index < NUM_TOP_MOVIES; index++){
			movieList.add(this.getMovie(movieIdList.get(index)));
		}


		return movieList;

	}

	


	private double getAverageRating(int movieID){

		//Initilaze return Varaiable
		double averageRating; 

		//SQL String Query 
		String sqlQuery = "select avg(ratingScore) from Rating where movieID = " + movieID + ";";

		try{

			//Create Statement 
			Statement stmt = connection.createStatement();

			//Initilize ResultSet 
			ResultSet rs = stmt.executeQuery(sqlQuery);

			rs.next();
			averageRating = rs.getDouble(1);


		}

		catch(SQLException e){
			System.out.println("Get Average Rating Error: " + e.getMessage());
		}

		return averageRating;


	}
}

