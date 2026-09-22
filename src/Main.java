import model.*;
import service.BookingService;

public class Main {

    public static void main(String[] args) {

        User user = new User(
                1,
                "Rahul",
                "rahul@gmail.com",
                "12345"
        );

        Movie movie = new Movie(
                101,
                "Interstellar",
                "Sci-Fi",
                "English",
                169,
                "8.7"
        );

        Show show = new Show(
                501,
                movie.getMovieId(),
                "25-09-2026",
                "6:00 PM",
                "Screen 1",
                200
        );

        Seat seat = new Seat("A1");

        BookingService bookingService =
                new BookingService();

        Booking booking =
                bookingService.bookTicket(
                        user,
                        show,
                        seat
                );

        bookingService.displayAllBookings();
    }
}
