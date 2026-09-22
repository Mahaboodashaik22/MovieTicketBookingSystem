package service;

import model.Movie;

public class MovieService {

    public void displayMovie(Movie movie) {
        System.out.println("Movie Details");
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Genre: " + movie.getGenre());
        System.out.println("Duration: " + movie.getDuration() + " minutes");
    }
}