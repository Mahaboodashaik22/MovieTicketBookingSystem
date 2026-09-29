package service;
import dao.BookingDAO;
import model.Booking;
import model.Seat;
import model.Show;
import model.User;

public class BookingService {
    private BookingDAO bookingDAO = new BookingDAO();
    private List<Booking> bookings;

    public BookingService() {
        bookings = new ArrayList<>();
    }

    public Booking bookTicket(User user, Show show, Seat seat) {

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

        seat.setBooked(true);

        bookings.add(booking);
        bookingDAO.saveBooking(booking);
        System.out.println("Ticket booked successfully!");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Booking successful!");
        System.out.println("Seat: " + seat.getSeatNumber());
        System.out.println("Amount: ₹" + amount);

        return booking;
    }
}
