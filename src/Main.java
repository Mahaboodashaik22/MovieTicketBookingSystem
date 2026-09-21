import model.*;

public class Main {

    public static void main(String[] args) {

        Movie movie = new Movie(
                101,
                "Interstellar",
                "Sci-Fi",
                "English",
                169,
                "8.7"
        );

        User user = new User(
                1,
                "Rahul",
                "rahul@gmail.com",
                "12345"
        );

        Show show = new Show(
                501,
                101,
                "25-09-2026",
                "6:00 PM",
                "Screen 1",
                200
        );

        Seat seat = new Seat("A1");

        Booking booking = new Booking(
                1001,
                user.getUserId(),
                show.getShowId(),
                seat.getSeatNumber(),
                show.getTicketPrice()
        );

        System.out.println(movie);
        System.out.println("User: " + user);
        System.out.println("Show: " + show);
        System.out.println("Seat: " + seat);
        System.out.println(booking);
        Person person = user;

        System.out.println("Role: " + person.getRole());
    }
}
