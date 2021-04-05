/******************************************************************************************************************************
 * ViewMovieControl
 * @author Rachel
 * Description:	Handles input from ViewMovieUI class and initiates searching of the Movie table.
 ******************************************************************************************************************************/

import java.util.ArrayList;

public class ViewMovieControl {

	private DataManager dataManager;
	private MovieObject movie;

	public ViewMovieControl(DataManager dm) {
		this.dataManager = dm;
	}
	
	public MovieObject processViewMovie(String movieId) {
		//NB: Need getMovie(String movieId) in dataManager
		movie = dataManager.getMovie(movieId);
		
		return movie;
	}

	public MovieObject getCurrentMovie() {
		return movie;
	}
}