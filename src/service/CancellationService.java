package service;

import database.BookingDAO;
import model.Booking;
import model.Seat;

public class CancellationService {

    private final BookingDAO bookingDAO;

    public CancellationService() {
        bookingDAO = new BookingDAO();
    }

    // Cancel using Booking and Seat
    public boolean cancelBooking(
            Booking booking,
            Seat seat) {

        if (booking == null) {

            System.out.println(
                    "No booking found."
            );

            return false;
        }

        if (seat == null) {

            System.out.println(
                    "No seat found."
            );

            return false;
        }

        // Make sure the seat belongs to this booking
        if (!booking.getSeatNumber()
                .equals(seat.getSeatNumber())) {

            System.out.println(
                    "Seat does not match the booking."
            );

            return false;
        }

        // Cancel booking in database
        boolean cancelled =
                bookingDAO.cancelBooking(
                        booking.getBookingId()
                );

        if (!cancelled) {

            System.out.println(
                    "Booking cancellation failed."
            );

            return false;
        }

        // Update Java seat status
        seat.setBooked(false);

        System.out.println(
                "Booking cancelled successfully!"
        );

        System.out.println(
                "Seat " +
                        seat.getSeatNumber() +
                        " is now available."
        );

        return true;
    }

    // Find booking using booking ID
    public Booking getBookingById(
            int bookingId) {

        return bookingDAO.getBookingById(
                bookingId
        );
    }

    // Cancel directly using booking ID
    public boolean cancelBookingById(
            int bookingId) {

        Booking booking =
                bookingDAO.getBookingById(
                        bookingId
                );

        if (booking == null) {

            System.out.println(
                    "Booking ID not found."
            );

            return false;
        }

        return bookingDAO.cancelBooking(
                bookingId
        );
    }
}