package ui;

import javafx.application
        .Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.Show;
import model.Seat;
import service.BookingService;
public class MovieTicketUI extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("🎬 Movie Ticket Booking System");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label movieLabel = new Label("Select Movie:");

        ComboBox<String> movieBox = new ComboBox<>();
        movieBox.getItems().addAll(
                "Avengers",
                "Leo",
                "Pushpa 2",
                "Kalki 2898 AD"
        );
        movieBox.setPromptText("Choose a movie");

        Label showLabel = new Label("Select Show Time:");

        ComboBox<String> showBox = new ComboBox<>();
        showBox.getItems().addAll(
                "10:00 AM",
                "1:00 PM",
                "4:00 PM",
                "7:00 PM"
        );
        showBox.setPromptText("Choose show time");

        Label seatLabel = new Label("Enter Seat Number:");

        TextField seatField = new TextField();
        seatField.setPromptText("Example: A1");

        Button bookButton = new Button("Book Ticket");

        Label result = new Label();

        bookButton.setOnAction(e -> {

            String movie = movieBox.getValue();
            String show = showBox.getValue();
            String seat = seatField.getText();

            if (movie == null || show == null || seat.isEmpty()) {
                result.setText("Please fill all details.");
            } else {

                User user = new User(
                        1,
                        "Guest",
                        "guest@gmail.com",
                        "password"
                );

                Show showObj = new Show(
                        1,
                        1,
                        "2026-09-24",
                        show,
                        "Screen 1",
                        200.0
                );

                Seat seatObj = new Seat(seat);

                BookingService bookingService = new BookingService();

                bookingService.bookTicket(user, showObj, seatObj);

                result.setText(
                        "Ticket booked successfully!\n" +
                                "Movie: " + movie + "\n" +
                                "Show: " + show + "\n" +
                                "Seat: " + seat
                );
            }
        });

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

        Scene scene = new Scene(root, 500, 600);

        stage.setTitle("Movie Ticket Booking System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}