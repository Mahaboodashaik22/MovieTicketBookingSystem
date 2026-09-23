package service;

import model.Booking;

public class BillingService {

    // Calculate total amount
    public double calculateTotal(Booking booking) {

        if (booking == null) {
            return 0;
        }

        return booking.getTotalAmount();
    }

    // Print bill
    public void printBill(Booking booking) {

        if (booking == null) {
            System.out.println("No booking available.");
            return;
        }

        System.out.println("\n========== MOVIE TICKET BILL ==========");
        System.out.println("Booking ID : " + booking.getBookingId());
        System.out.println("User ID    : " + booking.getUserId());
        System.out.println("Show ID    : " + booking.getShowId());
        System.out.println("Seat       : " + booking.getSeatNumber());
        System.out.println("---------------------------------------");
        System.out.println("Total      : ₹" + booking.getTotalAmount());
        System.out.println("=======================================");
    }
}
