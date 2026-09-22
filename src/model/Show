package model;

public class Show {

    private int showId;
    private int movieId;
    private String showDate;
    private String showTime;
    private String screenName;
    private double ticketPrice;

    public Show(int showId, int movieId,
                String showDate, String showTime,
                String screenName, double ticketPrice) {

        this.showId = showId;
        this.movieId = movieId;
        this.showDate = showDate;
        this.showTime = showTime;
        this.screenName = screenName;
        this.ticketPrice = ticketPrice;
    }

    public int getShowId() {
        return showId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getShowDate() {
        return showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public String getScreenName() {
        return screenName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return showId + " - " + showDate +
                " - " + showTime +
                " - " + screenName +
                " - ₹" + ticketPrice;
    }
}