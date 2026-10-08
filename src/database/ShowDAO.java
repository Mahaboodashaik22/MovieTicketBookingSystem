package database;

import model.Show;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowDAO {

    // ADD SHOW
    public void addShow(Show show) {

        String sql =
                "INSERT INTO shows " +
                        "(movie_id, show_date, show_time, screen_name, ticket_price) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    show.getMovieId()
            );

            statement.setString(
                    2,
                    show.getShowDate()
            );

            statement.setString(
                    3,
                    show.getShowTime()
            );

            statement.setString(
                    4,
                    show.getScreenName()
            );

            statement.setDouble(
                    5,
                    show.getTicketPrice()
            );

            statement.executeUpdate();

            System.out.println(
                    "Show added successfully!"
            );

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // GET SHOWS FOR A MOVIE
    public List<Show> getShowsByMovieId(
            int movieId) {

        List<Show> shows =
                new ArrayList<>();

        String sql =
                "SELECT show_id, movie_id, show_date, " +
                        "show_time, screen_name, ticket_price " +
                        "FROM shows " +
                        "WHERE movie_id = ? " +
                        "ORDER BY show_date, show_time";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    movieId
            );

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Show show =
                        new Show(
                                resultSet.getInt(
                                        "show_id"
                                ),
                                resultSet.getInt(
                                        "movie_id"
                                ),
                                resultSet.getString(
                                        "show_date"
                                ),
                                resultSet.getString(
                                        "show_time"
                                ),
                                resultSet.getString(
                                        "screen_name"
                                ),
                                resultSet.getDouble(
                                        "ticket_price"
                                )
                        );

                shows.add(show);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return shows;
    }
}