package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import controller.LoginController;
import model.User;

public class LoginUI extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("🎬 Movie Ticket Booking");
        title.setFont(Font.font("Arial", 28));
        title.setTextFill(Color.WHITE);

        Label subtitle = new Label("Login to continue");
        subtitle.setTextFill(Color.LIGHTGRAY);

        Label emailLabel = new Label("Email");
        emailLabel.setTextFill(Color.WHITE);

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");

        Label passwordLabel = new Label("Password");
        passwordLabel.setTextFill(Color.WHITE);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        Button loginButton = new Button("🔐 Login");
        loginButton.setPrefHeight(40);
        loginButton.setMaxWidth(Double.MAX_VALUE);

        Label message = new Label();
        message.setWrapText(true);

        loginButton.setOnAction(e -> {

            String email = emailField.getText().trim();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {

                message.setText("⚠ Please enter email and password.");
                message.setTextFill(Color.ORANGE);

                return;
            }

            // Controller
            LoginController controller =
                    new LoginController();

            // Check credentials in MySQL
            User user =
                    controller.login(email, password);

            if (user != null) {

                message.setText(
                        "✅ Login successful!\nWelcome, "
                                + user.getName()
                );

                message.setTextFill(Color.LIGHTGREEN);

                // Open Movie Ticket Booking screen
                MovieTicketUI movieUI =
                        new MovieTicketUI();

                try {
                    movieUI.start(stage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {

                message.setText(
                        "❌ Invalid email or password."
                );

                message.setTextFill(Color.RED);
            }
        });

        VBox box = new VBox(
                12,
                title,
                subtitle,
                emailLabel,
                emailField,
                passwordLabel,
                passwordField,
                loginButton,
                message
        );

        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(35));
        box.setMaxWidth(400);

        VBox root = new VBox(box);

        root.setAlignment(Pos.CENTER);

        root.setStyle(
                "-fx-background-color: " +
                        "linear-gradient(to bottom right, #141e30, #243b55);"
        );

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Movie Ticket Booking System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
