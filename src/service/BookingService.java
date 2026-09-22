package service;

public class BookingService {

    public void bookTicket(String movieTitle, String customerName) {
        System.out.println("Ticket booked successfully!");
        System.out.println("Movie: " + movieTitle);
        System.out.println("Customer: " + customerName);
    }

    public void cancelTicket(String movieTitle, String customerName) {
        System.out.println("Ticket cancelled successfully!");
        System.out.println("Movie: " + movieTitle);
        System.out.println("Customer: " + customerName);
    }
}




