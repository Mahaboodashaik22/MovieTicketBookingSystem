package controller;

import model.Booking;
import model.Seat;
import model.Show;
import model.User;
import service.BookingService;

import java.util.List;

public class BookingController {

    private final BookingService bookingService;

    public BookingController() {
        bookingService = new BookingService();
    }

    // Book one ticket
    public Booking bookTicket(
            int bookingId,
            User user,
            Show show,
            Seat seat) {

        return bookingService.createBooking(
                bookingId,
                user,
                show,
                seat
        );
    }

    // Book multiple tickets
    public List<Booking> bookMultipleTickets(
            int startingBookingId,
            User user,
            Show show,
            List<Seat> seats) {

        return bookingService.createMultipleBookings(
                startingBookingId,
                user,
                show,
                seats
        );
    }
}