package service;
import dao.BookingDAO;
import model.Booking;
import model.Seat;
import model.Show;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private BookingDAO bookingDAO = new BookingDAO();
    private List<Booking> bookings;

    public BookingService() {
        bookings = new ArrayList<>();
    }

    public Booking createBooking(
          int bookingId,
          User user,
          Show show,
          Seat seat) {

        if (seat.isBooked()) {
            System.out.println("Sorry, seat "
                    + seat.getSeatNumber()
                    + " is already booked.");

            return null;
        }


        Booking booking = new Booking(
                bookingId,
                user.getUserId(),
                show.getShowId(),
                seat.getSeatNumber(),
                show.getTicketPrice()
        );

        seat.setBooked(true);

        bookings.add(booking);
        bookingDAO.saveBooking(booking);
        System.out.println("Ticket booked successfully!");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Seat: " + seat.getSeatNumber());
        System.out.println("Amount: ₹" + show.getTicketPrice());

        return booking;
    }

    public Booking findBooking(int bookingId) {

        for (Booking booking : bookings) {

            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }

        return null;
    }

    public boolean cancelBooking(int bookingId, Seat seat) {

        Booking booking = findBooking(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
            return false;
        }

        bookings.remove(booking);

        seat.setBooked(false);

        System.out.println("Booking cancelled successfully.");
        System.out.println("Booking ID: " + bookingId);

        return true;
    }

    public void displayAllBookings() {

        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        System.out.println("\n----- All Bookings -----");

        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}