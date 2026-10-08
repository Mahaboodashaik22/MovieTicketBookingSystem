package ui;

import controller.BookingController;
import controller.CancellationController;
import controller.MovieController;
import controller.ShowController;
import database.BookingDAO;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import model.Booking;
import model.Movie;
import model.Seat;
import model.Show;
import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieTicketUI extends Application {

    private final Set<String> selectedSeats =
            new HashSet<>();

    @Override
    public void start(Stage stage) {

        // =========================
        // HEADER
        // =========================

        Label title =
                new Label("🎬 Movie Ticket Booking");

        title.setFont(
                Font.font("Arial", 28)
        );

        title.setTextFill(Color.WHITE);

        Label subtitle =
                new Label(
                        "Book your favourite movie in a few clicks"
                );

        subtitle.setTextFill(Color.LIGHTGRAY);

        VBox header =
                new VBox(5, title, subtitle);

        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20));


        // =========================
        // MOVIE
        // =========================

        Label movieLabel =
                new Label("🎥 Select Movie");

        movieLabel.setTextFill(Color.WHITE);
        movieLabel.setFont(
                Font.font("Arial", 15)
        );

        ComboBox<String> movieBox =
                new ComboBox<>();

        MovieController movieController =
                new MovieController();

        List<Movie> movies =
                movieController.getAllMovies();

        for (Movie movie : movies) {

            movieBox.getItems().add(
                    movie.getTitle()
            );
        }

        movieBox.setPromptText(
                "Choose a movie"
        );

        movieBox.setMaxWidth(
                Double.MAX_VALUE
        );


        // =========================
        // DATE
        // =========================

        Label dateLabel =
                new Label("📅 Select Date");

        dateLabel.setTextFill(Color.WHITE);
        dateLabel.setFont(
                Font.font("Arial", 15)
        );

        DatePicker datePicker =
                new DatePicker();

        datePicker.setMaxWidth(
                Double.MAX_VALUE
        );


        // =========================
        // TIME
        // =========================

        Label timeLabel =
                new Label("🕐 Show Time");

        timeLabel.setTextFill(Color.WHITE);
        timeLabel.setFont(
                Font.font("Arial", 15)
        );

        ComboBox<String> timeBox =
                new ComboBox<>();

        ShowController showController =
                new ShowController();

        movieBox.setOnAction(e -> {

            timeBox.getItems().clear();

            Movie selectedMovie =
                    movieController.getMovieByTitle(
                            movieBox.getValue()
                    );

            if (selectedMovie != null) {

                List<Show> shows =
                        showController.getShowsByMovieId(
                                selectedMovie.getMovieId()
                        );

                for (Show show : shows) {

                    timeBox.getItems().add(
                            show.getShowTime()
                    );
                }
            }
        });

        timeBox.setPromptText(
                "Choose show time"
        );

        timeBox.setMaxWidth(
                Double.MAX_VALUE
        );


        // =========================
        // SEAT TITLE
        // =========================

        Label seatLabel =
                new Label("🎟 Select Your Seats");

        seatLabel.setTextFill(Color.WHITE);

        seatLabel.setFont(
                Font.font("Arial", 17)
        );


        // =========================
        // SCREEN
        // =========================

        Label screenLabel =
                new Label("SCREEN");

        screenLabel.setTextFill(Color.LIGHTGRAY);

        screenLabel.setFont(
                Font.font("Arial", 14)
        );

        screenLabel.setAlignment(
                Pos.CENTER
        );

        screenLabel.setMaxWidth(
                Double.MAX_VALUE
        );

        screenLabel.setStyle(
                "-fx-background-color: #555555;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10;"
        );


        // =========================
        // SEAT GRID
        // =========================

        GridPane seatGrid =
                new GridPane();

        seatGrid.setHgap(8);
        seatGrid.setVgap(8);
        seatGrid.setAlignment(Pos.CENTER);


        // =========================
        // SEAT NAMES
        // =========================

        String[] seatNumbers = {

                "A1", "A2", "A3", "A4", "A5", "A6",
                "A7", "A8", "A9", "A10", "A11", "A12",

                "B1", "B2", "B3", "B4", "B5", "B6",
                "B7", "B8", "B9", "B10", "B11", "B12",

                "C1", "C2", "C3", "C4", "C5", "C6",
                "C7", "C8", "C9", "C10", "C11", "C12",

                "D1", "D2", "D3", "D4", "D5", "D6",
                "D7", "D8", "D9", "D10", "D11", "D12",

                "E1", "E2", "E3", "E4", "E5", "E6",
                "E7", "E8", "E9", "E10", "E11", "E12",

                "F1", "F2", "F3", "F4", "F5", "F6",
                "F7", "F8", "F9", "F10", "F11", "F12",

                "G1", "G2", "G3", "G4", "G5", "G6",
                "G7", "G8", "G9", "G10", "G11", "G12",

                "H1", "H2", "H3", "H4", "H5", "H6",
                "H7", "H8", "H9", "H10", "H11", "H12",

                "I1", "I2", "I3", "I4", "I5", "I6",
                "I7", "I8", "I9", "I10", "I11", "I12"
        };


        // =========================
        // GET BOOKED SEATS
        // =========================
        BookingDAO bookingDAO =
                new BookingDAO();

        List<String> bookedSeats =
                bookingDAO.getBookedSeats(501);




        // =========================
        // CREATE SEATS
        // =========================

        for (int i = 0;
             i < seatNumbers.length;
             i++) {

            String seatNumber =
                    seatNumbers[i];

            ToggleButton seatButton =
                    new ToggleButton(
                            seatNumber
                    );

            seatButton.setPrefSize(
                    55,
                    38
            );

            int row = i / 12;
            int column = i % 12;


            // =========================
            // BOOKED SEAT
            // =========================

            if (bookedSeats.contains(
                    seatNumber
            )) {

                seatButton.setDisable(true);

                seatButton.setStyle(
                        "-fx-background-color: #e74c3c;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 8;"
                );

            } else {

                seatButton.setStyle(
                        "-fx-background-color: #2ecc71;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 8;"
                );


                // =========================
                // SEAT SELECTION
                // =========================

                seatButton.setOnAction(e -> {

                    if (seatButton.isSelected()) {

                        selectedSeats.add(
                                seatNumber
                        );

                        seatButton.setStyle(
                                "-fx-background-color: #3498db;" +
                                        "-fx-text-fill: white;" +
                                        "-fx-font-weight: bold;" +
                                        "-fx-background-radius: 8;"
                        );

                    } else {

                        selectedSeats.remove(
                                seatNumber
                        );

                        seatButton.setStyle(
                                "-fx-background-color: #2ecc71;" +
                                        "-fx-text-fill: white;" +
                                        "-fx-font-weight: bold;" +
                                        "-fx-background-radius: 8;"
                        );
                    }
                });
            }


            // =========================
            // CENTER AISLE
            // =========================

            if (column == 6) {

                seatGrid.setMargin(
                        seatButton,
                        new Insets(
                                0, 0, 0, 25
                        )
                );
            }


            seatGrid.add(
                    seatButton,
                    column,
                    row
            );
        }


        // =========================
        // LEGEND
        // =========================

        Label availableLabel =
                new Label("🟢 Available");

        availableLabel.setTextFill(
                Color.LIGHTGREEN
        );

        Label bookedLabel =
                new Label("🔴 Booked");

        bookedLabel.setTextFill(
                Color.SALMON
        );

        Label selectedLabel =
                new Label("🔵 Selected");

        selectedLabel.setTextFill(
                Color.LIGHTBLUE
        );

        HBox legend =
                new HBox(
                        20,
                        availableLabel,
                        bookedLabel,
                        selectedLabel
                );

        legend.setAlignment(
                Pos.CENTER
        );


        // =========================
        // SELECTED SEATS
        // =========================

        Label selectedSeatsLabel =
                new Label(
                        "Selected Seats: None"
                );

        selectedSeatsLabel.setTextFill(
                Color.WHITE
        );

        selectedSeatsLabel.setFont(
                Font.font("Arial", 15)
        );


        // =========================
        // PRICE
        // =========================

        Label priceLabel =
                new Label(
                        "Ticket Price: ₹200 per seat"
                );

        priceLabel.setTextFill(
                Color.WHITE
        );

        priceLabel.setFont(
                Font.font("Arial", 17)
        );


        // =========================
        // TOTAL
        // =========================

        Label totalLabel =
                new Label("Total: ₹0");

        totalLabel.setTextFill(
                Color.LIGHTGREEN
        );

        totalLabel.setFont(
                Font.font("Arial", 20)
        );


        // =========================
        // RESULT
        // =========================

        Label result =
                new Label();

        result.setWrapText(true);
        result.setTextFill(Color.LIGHTGREEN);
        result.setAlignment(Pos.CENTER);


        // =========================
        // UPDATE SELECTION
        // =========================

        Runnable updateSelection =
                () -> {

                    if (selectedSeats.isEmpty()) {

                        selectedSeatsLabel.setText(
                                "Selected Seats: None"
                        );

                        totalLabel.setText(
                                "Total: ₹0"
                        );

                    } else {

                        selectedSeatsLabel.setText(
                                "Selected Seats: " +
                                        String.join(
                                                ", ",
                                                selectedSeats
                                        )
                        );

                        totalLabel.setText(
                                "Total: ₹" +
                                        (selectedSeats.size() * 200)
                        );
                    }
                };


        // =========================
        // ADD LISTENERS TO SEATS
        // =========================

        for (javafx.scene.Node node :
                seatGrid.getChildren()) {

            if (node instanceof ToggleButton) {

                ToggleButton button =
                        (ToggleButton) node;

                button.selectedProperty()
                        .addListener(
                                (obs, oldValue, newValue) ->
                                        updateSelection.run()
                        );
            }
        }


        // =========================
        // CONTINUE BUTTON
        // =========================

        Button continueButton =
                new Button(
                        "💳 Continue to Payment"
                );

        continueButton.setMaxWidth(
                Double.MAX_VALUE
        );

        continueButton.setPrefHeight(45);

        continueButton.setStyle(
                "-fx-background-color: #e67e22;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;"
        );
        // =========================
// BOOKING HISTORY BUTTON
// =========================

        Button historyButton =
                new Button(
                        "📋 Booking History"
                );

        historyButton.setMaxWidth(
                Double.MAX_VALUE
        );

        historyButton.setPrefHeight(45);

        historyButton.setStyle(
                "-fx-background-color: #34495e;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;"
        );


// =========================
// BOOKING HISTORY ACTION
// =========================

        historyButton.setOnAction(e -> {

            BookingDAO historyDAO =
                    new BookingDAO();

            List<Booking> history =
                    historyDAO.getBookingsByUser(1);

            Alert historyAlert =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );

            historyAlert.setTitle(
                    "Booking History"
            );

            historyAlert.setHeaderText(
                    "📋 Your Booking History"
            );

            if (history.isEmpty()) {

                historyAlert.setContentText(
                        "No bookings found."
                );

            } else {

                StringBuilder historyText =
                        new StringBuilder();

                for (Booking booking : history) {

                    historyText.append(
                            "🎟 Booking ID: "
                    ).append(
                            booking.getBookingId()
                    );

                    historyText.append(
                            "\n🎬 Show ID: "
                    ).append(
                            booking.getShowId()
                    );

                    historyText.append(
                            "\n💺 Seat: "
                    ).append(
                            booking.getSeatNumber()
                    );

                    historyText.append(
                            "\n💰 Amount: ₹"
                    ).append(
                            booking.getTotalAmount()
                    );

                    historyText.append(
                            "\n────────────────────\n"
                    );
                }

                TextArea historyArea =
                        new TextArea(
                                historyText.toString()
                        );

                historyArea.setEditable(false);

                historyArea.setWrapText(false);

                historyArea.setPrefWidth(500);

                historyArea.setPrefHeight(600);

                historyAlert.getDialogPane()
                        .setContent(historyArea);
            }

            historyAlert.showAndWait();
        });
        // =========================
// CANCEL BOOKING BUTTON
// =========================

        Button cancellationButton =
                new Button("❌ Cancel Booking");

        cancellationButton.setMaxWidth(
                Double.MAX_VALUE
        );

        cancellationButton.setPrefHeight(45);

        cancellationButton.setStyle(
                "-fx-background-color: #c0392b;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;"
        );

// =========================
// CANCELLATION ACTION
// =========================

        cancellationButton.setOnAction(e -> {

                    TextInputDialog dialog =
                            new TextInputDialog();

                    dialog.setTitle("Cancel Booking");

                    dialog.setHeaderText(
                            "❌ Cancel Your Booking"
                    );

                    dialog.setContentText(
                            "Enter Booking ID:"
                    );

                    dialog.showAndWait().ifPresent(
                            bookingIdText -> {

                                try {

                                    int bookingId =
                                            Integer.parseInt(
                                                    bookingIdText.trim()
                                            );

                                    CancellationController
                                            cancellationController =
                                            new CancellationController();

                                    boolean cancelled =
                                            cancellationController
                                                    .cancelBookingById(
                                                            bookingId
                                                    );

                                    if (cancelled) {

                                        Alert success =
                                                new Alert(
                                                        Alert.AlertType.INFORMATION
                                                );

                                        success.setTitle(
                                                "Cancellation Successful"
                                        );

                                        success.setHeaderText(
                                                "✅ Booking Cancelled"
                                        );

                                        success.setContentText(
                                                "Booking ID " +
                                                        bookingId +
                                                        " has been cancelled successfully."
                                        );

                                        success.showAndWait();

                                        result.setText(
                                                "✅ Booking " +
                                                        bookingId +
                                                        " cancelled successfully!"
                                        );

                                        result.setTextFill(
                                                Color.LIGHTGREEN
                                        );

                                    } else {

                                        Alert error =
                                                new Alert(
                                                        Alert.AlertType.ERROR
                                                );

                                        error.setTitle(
                                                "Cancellation Failed"
                                        );

                                        error.setHeaderText(
                                                "❌ Unable to Cancel"
                                        );

                                        error.setContentText(
                                                "Booking ID " +
                                                        bookingId +
                                                        " was not found."
                                        );

                                        error.showAndWait();

                                        result.setText(
                                                "❌ Booking cancellation failed."
                                        );

                                        result.setTextFill(
                                                Color.RED
                                        );
                                    }

                                } catch (NumberFormatException ex) {

                                    Alert error =
                                            new Alert(
                                                    Alert.AlertType.ERROR
                                            );

                                    error.setTitle(
                                            "Invalid Booking ID"
                                    );

                                    error.setHeaderText(
                                            "❌ Invalid Input"
                                    );

                                    error.setContentText(
                                            "Please enter a valid numeric Booking ID."
                                    );

                                    error.showAndWait();
                                }
                            }
                    );
        });



                    // =========================
                    // PAYMENT ACTION
                    // =========================

                    continueButton.setOnAction(e -> {

                        // Check movie
                        if (movieBox.getValue() == null) {

                            result.setText(
                                    "⚠ Please select a movie."
                            );

                            result.setTextFill(
                                    Color.ORANGE
                            );

                            return;
                        }


                        // Check date
                        if (datePicker.getValue() == null) {

                            result.setText(
                                    "⚠ Please select a date."
                            );

                            result.setTextFill(
                                    Color.ORANGE
                            );

                            return;
                        }


                        // Check time
                        if (timeBox.getValue() == null) {

                            result.setText(
                                    "⚠ Please select a show time."
                            );

                            result.setTextFill(
                                    Color.ORANGE
                            );

                            return;
                        }


                        // Check seats
                        if (selectedSeats.isEmpty()) {

                            result.setText(
                                    "⚠ Please select at least one seat."
                            );

                            result.setTextFill(
                                    Color.ORANGE
                            );

                            return;
                        }


                        // =========================
                        // PAYMENT DIALOG
                        // =========================

                        Dialog<ButtonType> paymentDialog =
                                new Dialog<>();

                        paymentDialog.setTitle(
                                "Payment"
                        );

                        paymentDialog.setHeaderText(
                                "💳 Complete Your Payment"
                        );


                        // =========================
                        // PAYMENT DETAILS
                        // =========================

                        VBox paymentBox =
                                new VBox(12);

                        paymentBox.setPadding(
                                new Insets(20)
                        );

                        Label moviePayment =
                                new Label(
                                        "🎬 Movie: " +
                                                movieBox.getValue()
                                );

                        Label seatsPayment =
                                new Label(
                                        "💺 Seats: " +
                                                String.join(
                                                        ", ",
                                                        selectedSeats
                                                )
                                );

                        Label ticketsPayment =
                                new Label(
                                        "🎫 Tickets: " +
                                                selectedSeats.size()
                                );

                        double totalAmount =
                                selectedSeats.size() * 200.0;
                        Label amountPayment =
                                new Label(
                                        "💰 Total Amount: ₹" +
                                                totalAmount
                                );

                        amountPayment.setFont(
                                Font.font(
                                        "Arial",
                                        18
                                )
                        );


                        // =========================
                        // PAYMENT METHOD
                        // =========================

                        Label methodLabel =
                                new Label(
                                        "Select Payment Method:"
                                );

                        RadioButton upi =
                                new RadioButton("UPI");

                        RadioButton card =
                                new RadioButton("Card");

                        RadioButton cash =
                                new RadioButton(
                                        "Cash at Counter"
                                );

                        ToggleGroup paymentGroup =
                                new ToggleGroup();

                        upi.setToggleGroup(
                                paymentGroup
                        );

                        card.setToggleGroup(
                                paymentGroup
                        );

                        cash.setToggleGroup(
                                paymentGroup
                        );

                        upi.setSelected(true);


                        HBox methods =
                                new HBox(
                                        15,
                                        upi,
                                        card,
                                        cash
                                );


                        paymentBox.getChildren().addAll(
                                moviePayment,
                                seatsPayment,
                                ticketsPayment,
                                amountPayment,
                                new Separator(),
                                methodLabel,
                                methods
                        );


                        paymentDialog.getDialogPane()
                                .setContent(
                                        paymentBox
                                );


                        ButtonType payButton =
                                new ButtonType(
                                        "PAY NOW",
                                        ButtonBar.ButtonData.OK_DONE
                                );

                        ButtonType cancelButton =
                                new ButtonType(
                                        "CANCEL",
                                        ButtonBar.ButtonData.CANCEL_CLOSE
                                );

                        paymentDialog.getDialogPane()
                                .getButtonTypes()
                                .addAll(
                                        payButton,
                                        cancelButton
                                );


                        // =========================
                        // PAYMENT RESULT
                        // =========================

                        paymentDialog.showAndWait()
                                .ifPresent(response -> {

                                    if (response ==
                                            payButton) {

                                        // Make sure payment method selected
                                        if (paymentGroup
                                                .getSelectedToggle()
                                                == null) {

                                            result.setText(
                                                    "⚠ Please select a payment method."
                                            );

                                            result.setTextFill(
                                                    Color.ORANGE
                                            );

                                            return;
                                        }


                                        // =========================
                                        // USER
                                        // =========================

                                        User user =
                                                new User(
                                                        1,
                                                        "Test User",
                                                        "test@gmail.com",
                                                        "test123"
                                                );


                                        // =========================
                                        // MOVIE
                                        // =========================

                                        Movie movie =
                                                movieController
                                                        .getMovieByTitle(
                                                                movieBox.getValue()
                                                        );

                                        if (movie == null) {

                                            result.setText(
                                                    "❌ Movie not found."
                                            );

                                            result.setTextFill(
                                                    Color.RED
                                            );

                                            return;
                                        }


                                        // =========================
                                        // SHOW
                                        // =========================

                                        Show selectedShow = null;

                                        List<Show> availableShows =
                                                showController.getShowsByMovieId(
                                                        movie.getMovieId()
                                                );

                                        for (Show s : availableShows) {

                                            if (s.getShowTime()
                                                    .equals(timeBox.getValue())) {

                                                selectedShow = s;
                                                break;
                                            }
                                        }

                                        if (selectedShow == null) {

                                            result.setText(
                                                    "❌ Show not found."
                                            );

                                            result.setTextFill(
                                                    Color.RED
                                            );

                                            return;
                                        }

                                        Show show = selectedShow;


                                        // =========================
                                        // CREATE SEATS
                                        // =========================

                                        List<Seat> seats =
                                                new ArrayList<>();

                                        for (
                                                String seatNumber :
                                                selectedSeats
                                        ) {

                                            seats.add(
                                                    new Seat(
                                                            seatNumber
                                                    )
                                            );
                                        }


                                        // =========================
                                        // CONTROLLER
                                        // =========================

                                        BookingController controller =
                                                new BookingController();


                                        // =========================
                                        // SAVE BOOKINGS
                                        // =========================

                                        List<Booking> bookings =
                                                controller
                                                        .bookMultipleTickets(
                                                                1001,
                                                                user,
                                                                show,
                                                                seats
                                                        );


                                        // =========================
                                        // SUCCESS
                                        // =========================

                                        if (bookings != null &&
                                                !bookings.isEmpty()) {

                                            String paymentMethod =
                                                    ((RadioButton)
                                                            paymentGroup
                                                                    .getSelectedToggle())
                                                            .getText();


                                            // =========================
                                            // BOOKING RECEIPT
                                            // =========================

                                            Alert ticketAlert =
                                                    new Alert(
                                                            Alert.AlertType.INFORMATION
                                                    );

                                            ticketAlert.setTitle(
                                                    "Booking Confirmed"
                                            );

                                            ticketAlert.setHeaderText(
                                                    "🎟 BOOKING CONFIRMED!"
                                            );

                                            String ticketDetails =
                                                    "━━━━━━━━━━━━━━━━━━━━━━\n" +
                                                            "       🎬 MOVIE TICKET\n" +
                                                            "━━━━━━━━━━━━━━━━━━━━━━\n\n" +

                                                            "🎬 Movie: " +
                                                            movie.getTitle() + "\n\n" +

                                                            "📅 Date: " +
                                                            datePicker.getValue() + "\n\n" +

                                                            "🕐 Time: " +
                                                            timeBox.getValue() + "\n\n" +

                                                            "🎥 Screen: Screen 1\n\n" +

                                                            "💺 Seats: " +
                                                            String.join(
                                                                    ", ",
                                                                    selectedSeats
                                                            ) + "\n\n" +

                                                            "🎫 Tickets: " +
                                                            seats.size() + "\n\n" +

                                                            "💳 Payment: " +
                                                            paymentMethod + "\n\n" +

                                                            "💰 Total Paid: ₹" +
                                                            totalAmount + "\n\n" +

                                                            "━━━━━━━━━━━━━━━━━━━━━━\n" +
                                                            "       🍿 ENJOY THE MOVIE!\n" +
                                                            "━━━━━━━━━━━━━━━━━━━━━━";

                                            ticketAlert.setContentText(
                                                    ticketDetails
                                            );

                                            ticketAlert.showAndWait();


                                            // =========================
                                            // UPDATE MAIN SCREEN
                                            // =========================

                                            result.setText(
                                                    "✅ PAYMENT SUCCESSFUL!\n\n" +

                                                            "🎬 Movie: " +
                                                            movie.getTitle() + "\n" +

                                                            "📅 Date: " +
                                                            datePicker.getValue() + "\n" +

                                                            "🕐 Time: " +
                                                            timeBox.getValue() + "\n" +

                                                            "💺 Seats: " +
                                                            String.join(
                                                                    ", ",
                                                                    selectedSeats
                                                            ) + "\n" +

                                                            "🎫 Tickets: " +
                                                            seats.size() + "\n" +

                                                            "💳 Payment: " +
                                                            paymentMethod + "\n" +

                                                            "💰 Total Paid: ₹" +
                                                            totalAmount + "\n\n" +

                                                            "🎟 BOOKING CONFIRMED!"
                                            );

                                            result.setTextFill(
                                                    Color.LIGHTGREEN
                                            );


                                            // Clear selections
                                            selectedSeats.clear();

                                            updateSelection.run();


                                        } else {

                                            result.setText(
                                                    "❌ Payment completed, " +
                                                            "but booking could not be saved.\n" +
                                                            "Please try again."
                                            );

                                            result.setTextFill(
                                                    Color.RED
                                            );
                                        }
                                    }
                                });
                    });


                    // =========================
                    // CARD
                    // =========================

                    VBox card =
                            new VBox(
                                    12,

                                    movieLabel,
                                    movieBox,

                                    dateLabel,
                                    datePicker,

                                    timeLabel,
                                    timeBox,

                                    seatLabel,
                                    screenLabel,

                                    seatGrid,

                                    legend,

                                    selectedSeatsLabel,

                                    priceLabel,

                                    totalLabel,

                                    continueButton,

                                    historyButton,

                                    cancellationButton,

                                    result
                            );
                    card.setPadding(
                            new Insets(25)
                    );

                    card.setMaxWidth(
                            850
                    );

                    card.setStyle(
                            "-fx-background-color: rgba(255,255,255,0.08);" +
                                    "-fx-background-radius: 18;" +
                                    "-fx-border-radius: 18;" +
                                    "-fx-border-color: rgba(255,255,255,0.15);"
                    );


                    // =========================
                    // SCROLL
                    // =========================

                    ScrollPane scrollPane =
                            new ScrollPane(card);

                    scrollPane.setFitToWidth(true);

                    scrollPane.setStyle(
                            "-fx-background: transparent;" +
                                    "-fx-background-color: transparent;"
                    );


                    // =========================
                    // ROOT
                    // =========================

                    VBox root =
                            new VBox(
                                    10,
                                    header,
                                    scrollPane
                            );

                    root.setAlignment(
                            Pos.TOP_CENTER
                    );

                    root.setPadding(
                            new Insets(20)
                    );

                    root.setStyle(
                            "-fx-background-color: " +
                                    "linear-gradient(to bottom right, #141e30, #243b55);"
                    );


                    // =========================
                    // SCENE
                    // =========================

                    Scene scene =
                            new Scene(
                                    root,
                                    1100,
                                    900
                            );

                    stage.setTitle(
                            "Movie Ticket Booking System"
                    );

                    stage.setScene(scene);

                    stage.show();
                }


                // =========================
                // MAIN
                // =========================

        public static void main(String[] args) {

            launch(args);
        }
    }