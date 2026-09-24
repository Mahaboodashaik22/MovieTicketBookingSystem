package database;

import model.Booking;

public class TestBooking {

    public static void main(String[] args) {

        Booking booking = new Booking(
                0,
                1,
                1,
                "A1",
                150.0
        );

        BookingDAO bookingDAO = new BookingDAO();

        bookingDAO.addBooking(booking);
    }
}