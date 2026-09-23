package dsa;

import model.Booking;

import java.util.ArrayList;

public class BookingManager {

    private ArrayList<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void displayBookings() {

        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("All Bookings:");
        

        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
}