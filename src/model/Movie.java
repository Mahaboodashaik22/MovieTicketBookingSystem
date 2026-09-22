package model;

public class Movie {
    private int movieId;
    private String title;
    private String genre;
    private int duration;

    public Movie(String genre, int duration, String title, int movieId) {
        this.genre = genre;
        this.duration = duration;
        this.title = title;
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
