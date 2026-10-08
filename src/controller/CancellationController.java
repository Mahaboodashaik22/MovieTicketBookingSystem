package controller;

import model.Booking;
import model.Seat;
import service.CancellationService;

public class CancellationController {

    private final CancellationService cancellationService;

    public CancellationController() {

        cancellationService =
                new CancellationService();
    }

    // Cancel using Booking and Seat
    public boolean cancelBooking(
            Booking booking,
            Seat seat) {

        return cancellationService.cancelBooking(
                booking,
                seat
        );
    }

    // Get booking using Booking ID
    public Booking getBookingById(
            int bookingId) {

        return cancellationService.getBookingById(
                bookingId
        );
    }

    // Cancel directly using Booking ID
    public boolean cancelBookingById(
            int bookingId) {

        return cancellationService.cancelBookingById(
                bookingId
        );
    }
}