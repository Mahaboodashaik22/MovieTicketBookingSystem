package database;

import model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // =========================
    // CHECK ONE SEAT
    // =========================

    public boolean isSeatBooked(
            int showId,
            String seatNumber) {

        String sql =
                "SELECT COUNT(*) FROM bookings " +
                        "WHERE show_id = ? AND seat_number = ?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, showId);
            statement.setString(2, seatNumber);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // GET ALL BOOKED SEATS
    // =========================

    public List<String> getBookedSeats(
            int showId) {

        List<String> bookedSeats =
                new ArrayList<>();

        String sql =
                "SELECT seat_number FROM bookings " +
                        "WHERE show_id = ?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, showId);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                bookedSeats.add(
                        resultSet.getString(
                                "seat_number"
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookedSeats;
    }


    // =========================
    // ADD ONE BOOKING
    // =========================

    public void addBooking(
            Booking booking) {

        String sql =
                "INSERT INTO bookings " +
                        "(user_id, show_id, seat_number, total_amount) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    booking.getUserId()
            );

            statement.setInt(
                    2,
                    booking.getShowId()
            );

            statement.setString(
                    3,
                    booking.getSeatNumber()
            );

            statement.setDouble(
                    4,
                    booking.getTotalAmount()
            );

            statement.executeUpdate();

            System.out.println(
                    "Booking added successfully!"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // =========================
    // ADD MULTIPLE BOOKINGS
    // =========================

    public boolean addBookings(
            List<Booking> bookings) {

        String sql =
                "INSERT INTO bookings " +
                        "(user_id, show_id, seat_number, total_amount) " +
                        "VALUES (?, ?, ?, ?)";

        Connection connection = null;

        try {

            connection =
                    DBConnection.getConnection();

            connection.setAutoCommit(false);

            try (PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                for (Booking booking : bookings) {

                    statement.setInt(
                            1,
                            booking.getUserId()
                    );

                    statement.setInt(
                            2,
                            booking.getShowId()
                    );

                    statement.setString(
                            3,
                            booking.getSeatNumber()
                    );

                    statement.setDouble(
                            4,
                            booking.getTotalAmount()
                    );

                    statement.addBatch();
                }

                statement.executeBatch();

                connection.commit();

                System.out.println(
                        "Multiple bookings added successfully!"
                );

                return true;

            } catch (SQLException e) {

                connection.rollback();

                e.printStackTrace();

                return false;
            }

        } catch (SQLException e) {

            e.printStackTrace();

            return false;

        } finally {

            if (connection != null) {

                try {
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public List<Booking> getBookingsByUser(int userId) {

        List<Booking> bookings =
                new ArrayList<>();

        String sql =
                "SELECT booking_id, user_id, show_id, " +
                        "seat_number, total_amount " +
                        "FROM bookings " +
                        "WHERE user_id = ? " +
                        "ORDER BY booking_id DESC";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Booking booking =
                        new Booking(
                                resultSet.getInt("booking_id"),
                                resultSet.getInt("user_id"),
                                resultSet.getInt("show_id"),
                                resultSet.getString("seat_number"),
                                resultSet.getDouble("total_amount")
                        );

                bookings.add(booking);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return bookings;
    }
    public boolean cancelBooking(int bookingId) {

        String sql =
                "DELETE FROM bookings " +
                        "WHERE booking_id = ?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    bookingId
            );

            int rows =
                    statement.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Booking cancelled successfully!"
                );

                return true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }
    public Booking getBookingById(int bookingId) {

        String sql =
                "SELECT booking_id, user_id, show_id, " +
                        "seat_number, total_amount " +
                        "FROM bookings " +
                        "WHERE booking_id = ?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    bookingId
            );

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return new Booking(
                        resultSet.getInt("booking_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("show_id"),
                        resultSet.getString("seat_number"),
                        resultSet.getDouble("total_amount")
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }
}