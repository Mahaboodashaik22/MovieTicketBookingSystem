package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.User;
import model.Show;
import model.Seat;
import model.Booking;

import service.BookingService;

public class MovieTicketUI extends Application {

    @Override
    public void start(Stage stage) {

        // Title
        Label title = new Label("🎬 Movie Ticket Booking System");
        title.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;"
        );

        // Movie selection
        Label movieLabel = new Label("Select Movie:");

        ComboBox<String> movieBox = new ComboBox<>();

        movieBox.getItems().addAll(
                "Avengers",
                "Leo",
                "Pushpa 2",
                "Kalki 2898 AD"
        );

        movieBox.setPromptText("Choose a movie");

        // Show time selection
        Label showLabel = new Label("Select Show Time:");

        ComboBox<String> showBox = new ComboBox<>();

        showBox.getItems().addAll(
                "10:00 AM",
                "1:00 PM",
                "4:00 PM",
                "7:00 PM"
        );

        showBox.setPromptText("Choose show time");

        // Seat
        Label seatLabel = new Label("Enter Seat Number:");

        TextField seatField = new TextField();
        seatField.setPromptText("Example: A1");

        // Book button
        Button bookButton = new Button("Book Ticket");

        // Result
        Label result = new Label();

        // Button action
        bookButton.setOnAction(e -> {

            String movie = movieBox.getValue();
            String showTime = showBox.getValue();
            String seatNumber = seatField.getText();

            // Validate input
            if (movie == null ||
                    showTime == null ||
                    seatNumber == null ||
                    seatNumber.trim().isEmpty()) {

                result.setText("Please fill all details.");
                return;
            }

            // Create User object
            User user = new User(
                    1,
                    "Guest",
                    "guest@gmail.com",
                    "password"
            );

            // Create Show object
            Show show = new Show(
                    1,
                    1,
                    "2026-09-25",
                    showTime,
                    "Screen 1",
                    200.0
            );

            // Create Seat object
            Seat seat = new Seat(seatNumber.trim());

            // Create BookingService
            BookingService bookingService = new BookingService();

            // Create booking
            Booking booking = bookingService.createBooking(
                    1001,
                    user,
                    show,
                    seat
            );

            // Check whether booking was successful
            if (booking != null) {

                result.setText(
                        "Ticket booked successfully!\n\n" +
                                "Movie: " + movie + "\n" +
                                "Show: " + showTime + "\n" +
                                "Seat: " + seatNumber + "\n" +
                                "Amount: ₹" + show.getTicketPrice()
                );

            } else {

                result.setText(
                        "Booking failed.\n" +
                                "The selected seat is already booked."
                );
            }
        });

        // Layout
        VBox root = new VBox(12);

        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                title,
                movieLabel,
                movieBox,
                showLabel,
                showBox,
                seatLabel,
                seatField,
                bookButton,
                result
        );

        // Scene
        Scene scene = new Scene(root, 500, 600);

        // Stage
        stage.setTitle("Movie Ticket Booking System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}