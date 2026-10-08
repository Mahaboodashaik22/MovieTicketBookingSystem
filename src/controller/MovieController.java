package controller;

import database.MovieDAO;
import model.Movie;

import java.util.List;

public class MovieController {

    private final MovieDAO movieDAO;

    public MovieController() {
        movieDAO = new MovieDAO();
    }

    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }

    public Movie getMovieByTitle(String title) {

        List<Movie> movies = movieDAO.getAllMovies();

        for (Movie movie : movies) {

            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }

        return null;
    }
}