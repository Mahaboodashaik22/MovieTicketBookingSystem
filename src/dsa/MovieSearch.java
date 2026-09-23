package dsa;

import model.Movie;

import java.util.ArrayList;

public class MovieSearch {

    public Movie searchByTitle(ArrayList<Movie> movies, String title) {

        for (Movie movie : movies) {

            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }

        return null;
    }
}