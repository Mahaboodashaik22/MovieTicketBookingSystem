import model.Booking;
import model.Movie;
import model.Seat;
import model.Show;
import model.User;

import service.BookingService;
import service.BillingService;
import service.CancellationService;

public class Main {

    public static void main(String[] args) {

        // Create User
        User user = new User(
                1,
                "Rahul",
                "rahul@gmail.com",
                "12345"
        );

        // Create Movie
        Movie movie = new Movie(
                101,
                "Avengers",
                "Action",
                "English",
                180,
                "4.5"
        );

        // Create Show
        Show show = new Show(
                501,
                101,
                "25-09-2026",
                "6:00 PM",
                "Screen 1",
                200
        );

        // Create Seat
        Seat seat = new Seat("A1");

        // Create Booking Service
        BookingService bookingService = new BookingService();

        // Create Booking
        Booking booking = bookingService.createBooking(
                1001,
                user,
                show,
                seat
        );

        // Print Bill
        BillingService billingService = new BillingService();
        billingService.printBill(booking);

        // Cancel Booking
        CancellationService cancellationService =
                new CancellationService();

        cancellationService.cancelBooking(
                booking,
                seat
        );

        // Check Seat Status
        System.out.println();
        System.out.println("Seat Status After Cancellation:");
        System.out.println(
                "Seat " + seat.getSeatNumber()
                        + " booked: " + seat.isBooked()
        );
    }
}