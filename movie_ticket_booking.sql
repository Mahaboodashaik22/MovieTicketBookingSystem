-- Movie Ticket Booking System
-- Clean database setup for team/lab use
-- MySQL 8.x

DROP DATABASE IF EXISTS movie_ticket_booking;
CREATE DATABASE movie_ticket_booking;
USE movie_ticket_booking;

CREATE TABLE users (
    user_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE movies (
    movie_id INT NOT NULL,
    title VARCHAR(150) NOT NULL,
    genre VARCHAR(50),
    language VARCHAR(50),
    duration INT,
    rating DOUBLE,
    PRIMARY KEY (movie_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE shows (
    show_id INT NOT NULL,
    movie_id INT,
    show_date DATE,
    show_time TIME,
    screen_name VARCHAR(50),
    ticket_price DOUBLE,
    PRIMARY KEY (show_id),
    KEY movie_id (movie_id),
    CONSTRAINT shows_ibfk_1 FOREIGN KEY (movie_id) REFERENCES movies (movie_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE seats (
    seat_number VARCHAR(10) NOT NULL,
    show_id INT,
    booked TINYINT(1) DEFAULT 0,
    PRIMARY KEY (seat_number),
    KEY show_id (show_id),
    CONSTRAINT seats_ibfk_1 FOREIGN KEY (show_id) REFERENCES shows (show_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE bookings (
    booking_id INT NOT NULL AUTO_INCREMENT,
    user_id INT,
    show_id INT,
    seat_number VARCHAR(10),
    total_amount DOUBLE,
    PRIMARY KEY (booking_id),
    KEY user_id (user_id),
    KEY show_id (show_id),
    CONSTRAINT bookings_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT bookings_ibfk_2 FOREIGN KEY (show_id) REFERENCES shows (show_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO users (user_id, name, email, password)
VALUES (1, 'Test User', 'test@gmail.com', 'test123');

INSERT INTO movies (movie_id, title, genre, language, duration, rating)
VALUES
(101, 'Test Movie', 'Action', 'English', 120, 4.5),
(102, 'Leo', 'Action', 'Tamil', 164, 4.3),
(103, 'Interstellar', 'Sci-Fi', 'English', 169, 4.8),
(104, 'The Avengers', 'Action', 'English', 143, 4.7),
(105, 'Inception', 'Sci-Fi', 'English', 148, 4.6),
(106, 'Jawan', 'Action', 'Hindi', 169, 4.2);

INSERT INTO shows (show_id, movie_id, show_date, show_time, screen_name, ticket_price)
VALUES
(501, 101, '2026-10-08', '18:00:00', 'Screen 1', 200),
(502, 102, '2026-10-08', '10:00:00', 'Screen 1', 200),
(503, 102, '2026-10-08', '18:00:00', 'Screen 2', 220),
(504, 103, '2026-10-08', '13:00:00', 'Screen 1', 200),
(505, 103, '2026-10-08', '21:00:00', 'Screen 2', 250),
(506, 104, '2026-10-08', '10:00:00', 'Screen 1', 180),
(507, 104, '2026-10-08', '18:00:00', 'Screen 2', 200),
(508, 105, '2026-10-08', '13:00:00', 'Screen 1', 220),
(509, 105, '2026-10-08', '21:00:00', 'Screen 2', 250),
(510, 106, '2026-10-08', '10:00:00', 'Screen 1', 180),
(511, 106, '2026-10-08', '18:00:00', 'Screen 2', 200);

-- No test bookings are included.
-- Seats are left empty because the current application checks booked seats through bookings.
