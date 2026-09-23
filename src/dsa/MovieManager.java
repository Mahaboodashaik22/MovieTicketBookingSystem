package dsa;

import model.Movie;

import java.util.ArrayList;

public class MovieManager {

    private ArrayList<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void displayMovies() {

        if (movies.isEmpty()) {
            System.out.println("No movies available.");
            return;
        }

        System.out.println("Available Movies:");

        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}