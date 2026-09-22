
import model.Movie;
import service.BookingService;

public class Main {
    public static void main(String[] args) {

        Movie movie = new Movie();

        movie.setTitle("Avengers");
        movie.setGenre("Action");
        movie.setDuration(180);

        BookingService bookingService = new BookingService();

        bookingService.bookTicket(
                movie.getTitle(),
                "John"
        );

        bookingService.cancelTicket(
                movie.getTitle(),
                "John"
        );
    }
}
