package database;

import model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingDAO {

    public void addBooking(Booking booking) {

        String sql = "INSERT INTO bookings " +
                "(user_id, show_id, seat_number, total_amount) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, booking.getUserId());
            statement.setInt(2, booking.getShowId());
            statement.setString(3, booking.getSeatNumber());
            statement.setDouble(4, booking.getTotalAmount());

            statement.executeUpdate();

            System.out.println("Booking added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}