package database;

import model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public void addMovie(Movie movie) {

        String sql = "INSERT INTO movies " +
                "(title, genre, language, duration, rating) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getGenre());
            statement.setString(3, movie.getLanguage());
            statement.setInt(4, movie.getDuration());
            statement.setString(5, movie.getRating());

            statement.executeUpdate();

            System.out.println("Movie added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> getAllMovies() {

        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT * FROM movies";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Movie movie = new Movie(
                        resultSet.getInt("id"),
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