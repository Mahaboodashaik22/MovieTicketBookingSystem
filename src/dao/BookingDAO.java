package dao;

import model.Booking;

public class BookingDAO {

    public void saveBooking(Booking booking) {
        System.out.println("Booking saved to database.");
        System.out.println("Booking ID: " + booking.getBookingId());
        System.out.println("Seat: " + booking.getSeatNumber());
        System.out.println("Amount: " + booking.getTotalAmount());
    }
}
