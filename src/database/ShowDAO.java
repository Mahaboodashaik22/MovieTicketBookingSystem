package database;

import model.Show;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShowDAO {

    public void addShow(Show show) {

        String sql = "INSERT INTO shows " +
                "(movie_id, show_date, show_time, screen_name, ticket_price) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, show.getMovieId());
            statement.setString(2, show.getShowDate());
            statement.setString(3, show.getShowTime());
            statement.setString(4, show.getScreenName());
            statement.setDouble(5, show.getTicketPrice());

            statement.executeUpdate();

            System.out.println("Show added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}