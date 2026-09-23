package service;

import model.Booking;
import model.Seat;

public class CancellationService {

    public void cancelBooking(
            Booking booking,
            Seat seat) {

        if (booking == null) {
            System.out.println("No booking found.");
            return;
        }

        if (!seat.isBooked()) {
            System.out.println("This seat is already available.");
            return;
        }

        seat.setBooked(false);

        System.out.println("Booking cancelled successfully!");
        System.out.println(
                "Seat " + seat.getSeatNumber()
                        + " is now available."
        );
    }
}