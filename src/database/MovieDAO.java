package database;

import model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    // =========================
    // ADD MOVIE
    // =========================

    public void addMovie(Movie movie) {

        String sql = "INSERT INTO movies " +
                "(movie_id, title, genre, language, duration, rating) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, movie.getMovieId());
            statement.setString(2, movie.getTitle());
            statement.setString(3, movie.getGenre());
            statement.setString(4, movie.getLanguage());
            statement.setInt(5, movie.getDuration());
            statement.setString(6, movie.getRating());

            statement.executeUpdate();

            System.out.println("Movie added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // =========================
    // GET ALL MOVIES
    // =========================

    public List<Movie> getAllMovies() {

        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT movie_id, title, genre, language, duration, rating " +
                "FROM movies";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                Movie movie = new Movie(
                        resultSet.getInt("movie_id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getString("language"),
                        resultSet.getInt("duration"),
                        resultSet.getString("rating")
                );

                movies.add(movie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }
}