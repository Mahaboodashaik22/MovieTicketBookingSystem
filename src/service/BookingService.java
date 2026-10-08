package service;

import database.BookingDAO;
import model.Booking;
import model.Seat;
import model.Show;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private final BookingDAO bookingDAO;

    public BookingService() {
        bookingDAO = new BookingDAO();
    }


    // =========================
    // BOOK ONE SEAT
    // =========================

    public Booking createBooking(
            int bookingId,
            User user,
            Show show,
            Seat seat) {

        // Check whether seat is already booked
        if (bookingDAO.isSeatBooked(
                show.getShowId(),
                seat.getSeatNumber())) {

            System.out.println(
                    "Sorry, this seat is already booked."
            );

            return null;
        }

        // Mark seat as booked
        seat.setBooked(true);

        // Ticket price
        double amount =
                show.getTicketPrice();

        // Create booking
        Booking booking =
                new Booking(
                        bookingId,
                        user.getUserId(),
                        show.getShowId(),
                        seat.getSeatNumber(),
                        amount
                );

        // Save to database
        bookingDAO.addBooking(
                booking
        );

        System.out.println(
                "Booking successful!"
        );

        System.out.println(
                "Seat: " +
                        seat.getSeatNumber()
        );

        System.out.println(
                "Amount: ₹" +
                        amount
        );

        return booking;
    }


    // =========================
    // BOOK MULTIPLE SEATS
    // =========================

    public List<Booking> createMultipleBookings(
            int startingBookingId,
            User user,
            Show show,
            List<Seat> seats) {

        // Check if seats list is empty
        if (seats == null || seats.isEmpty()) {

            System.out.println(
                    "No seats selected."
            );

            return new ArrayList<>();
        }


        // =========================
        // CHECK ALL SEATS FIRST
        // =========================

        for (Seat seat : seats) {

            if (bookingDAO.isSeatBooked(
                    show.getShowId(),
                    seat.getSeatNumber())) {

                System.out.println(
                        "Seat " +
                                seat.getSeatNumber() +
                                " is already booked."
                );

                // Do not book any seat
                return new ArrayList<>();
            }
        }


        // =========================
        // CREATE BOOKINGS
        // =========================

        List<Booking> bookings =
                new ArrayList<>();

        double ticketPrice =
                show.getTicketPrice();

        int bookingId =
                startingBookingId;

        for (Seat seat : seats) {

            // Mark seat as booked
            seat.setBooked(true);

            Booking booking =
                    new Booking(
                            bookingId,
                            user.getUserId(),
                            show.getShowId(),
                            seat.getSeatNumber(),
                            ticketPrice
                    );

            bookings.add(
                    booking
            );

            bookingId++;
        }


        // =========================
        // SAVE ALL BOOKINGS
        // =========================

        boolean saved =
                bookingDAO.addBookings(
                        bookings
                );

        if (!saved) {

            // Database insertion failed
            for (Seat seat : seats) {
                seat.setBooked(false);
            }

            System.out.println(
                    "Multiple booking failed."
            );

            return new ArrayList<>();
        }


        // =========================
        // SUCCESS
        // =========================

        double totalAmount =
                ticketPrice * seats.size();

        System.out.println(
                "Multiple booking successful!"
        );

        System.out.println(
                "Number of seats: " +
                        seats.size()
        );

        System.out.println(
                "Total amount: ₹" +
                        totalAmount
        );

        return bookings;
    }
}