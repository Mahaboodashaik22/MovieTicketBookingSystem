package service;

import model.Booking;
import model.Seat;
import model.Show;
import model.User;

public class BookingService {

    public Booking createBooking(
            int bookingId,
            User user,
            Show show,
            Seat seat) {

        if (seat.isBooked()) {
            System.out.println("Sorry, this seat is already booked.");
            return null;
        }

        seat.setBooked(true);

        double amount = show.getTicketPrice();

        Booking booking = new Booking(
                bookingId,
                user.getUserId(),
                show.getShowId(),
                seat.getSeatNumber(),
                amount
        );

        System.out.println("Booking successful!");
        System.out.println("Seat: " + seat.getSeatNumber());
        System.out.println("Amount: ₹" + amount);

        return booking;
    }
}
