package model;

public class Booking {

    private int bookingId;
    private int userId;
    private int showId;
    private String seatNumber;
    private double totalAmount;

    public Booking(int bookingId, int userId,
                   int showId, String seatNumber,
                   double totalAmount) {

        this.bookingId = bookingId;
        this.userId = userId;
        this.showId = showId;
        this.seatNumber = seatNumber;
        this.totalAmount = totalAmount;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public int getShowId() {
        return showId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                " | User ID: " + userId +
                " | Show ID: " + showId +
                " | Seat: " + seatNumber +
                " | Amount: ₹" + totalAmount;
    }
}
