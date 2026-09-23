package dsa;

public class SeatManager {

    private String[] seats = {
            "A1", "A2", "A3", "A4", "A5",
            "B1", "B2", "B3", "B4", "B5"
    };

    private boolean[] booked = new boolean[10];

    public void displaySeats() {

        System.out.println("Available Seats:");

        for (int i = 0; i < seats.length; i++) {

            if (!booked[i]) {
                System.out.println(seats[i] + " - Available");
            } else {
                System.out.println(seats[i] + " - Booked");
            }
        }
    }

    public boolean bookSeat(String seatNumber) {

        for (int i = 0; i < seats.length; i++) {

            if (seats[i].equalsIgnoreCase(seatNumber)) {

                if (!booked[i]) {
                    booked[i] = true;
                    return true;
                }

                return false;
            }
        }

        return false;
    }
}