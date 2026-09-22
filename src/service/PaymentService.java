package service;

import model.Booking;

public class PaymentService {

    public double calculateTotal(Booking booking) {

        return booking.getTotalAmount();
    }

    public boolean processPayment(Booking booking) {

        double amount = calculateTotal(booking);

        if (amount <= 0) {
            System.out.println("Invalid payment amount.");
            return false;
        }

        System.out.println("Payment successful!");
        System.out.println("Amount paid: ₹" + amount);

        return true;
    }

    public void generateReceipt(Booking booking) {

        System.out.println("\n===== PAYMENT RECEIPT =====");

        System.out.println("Booking ID: "
                + booking.getBookingId());

        System.out.println("User ID: "
                + booking.getUserId());

        System.out.println("Show ID: "
                + booking.getShowId());

        System.out.println("Seat: "
                + booking.getSeatNumber());

        System.out.println("Total Amount: ₹"
                + booking.getTotalAmount());

        System.out.println("===========================");
    }
}