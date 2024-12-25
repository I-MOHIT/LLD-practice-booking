package main;

import main.models.*;

import java.util.UUID;

public class OutputPrinter {
    public void hello() {
        System.out.println("Hello!");
    }

    public void exit() {
        System.out.println("Bye!");
    }

    public void createTheatre(Theatre theatre) {
        System.out.println("A theatre named " + theatre.getName() + " having theatre id " + theatre.getTheatreId() + " has been created");
    }

    public void createHall(Hall hall) {
        System.out.println("A hall named " + hall.getName() + " having hall id " + hall.getHallId() + " has been created");
    }

    public void createSeat(Seat seat) {
        System.out.println("A seat having seat id " + seat.getSeatId() + " has been created");
    }

    public void createUser(User user) {
        System.out.println("A user named " + user.getName() + " having user id " + user.getUserId() + " has been created");
    }

    public void assignHallToTheatre(Hall hall, Theatre theatre) {
        System.out.println("Hall " + hall.getName() + " has been assigned to theatre " + theatre.getName());
    }

    public void assignSeatToHall(Seat seat, Hall hall) {
        System.out.println("Seat " + seat.getSeatId() + " has been assigned to hall " + hall.getName());
    }

    public void createShow(Show show) {
        System.out.println("A show named " + show.getName() + " having show id " + show.getShowId() + " has been created being hosted in the hall " + show.getHallId());
    }

    public void assignSeatToShow(Showseat showseat) {
        System.out.println("Seat " + showseat.getSeatId() + " has been assigned to show " + showseat.getShowId() + " and created the showseat " + showseat.getShowseatId());
    }

    public void createBooking(Booking booking) {
        System.out.println("Created a booking having booking id " + booking.getBookingId() + ". Please confirm the booking within 100 seconds to avoid cancellation");
    }

    public void confirmBooking(UUID bookingId) {
        System.out.println("The booking having booking id " + bookingId + " has been confirmed");
    }

    public void cancelBooking(UUID bookingId) {
        System.out.println("The booking having booking id " + bookingId + " has been cancelled");
    }

    public void paymentFailed(UUID bookingId, boolean retriesLeft) {
        System.out.println("The payment for booking having booking id " + bookingId + " has failed. " + (retriesLeft ? "Retrying again" : "Retries exhausted, cancelled booking"));
    }

    public void getBookingsByUser(User user) {
        System.out.println("The bookings created by the user " + user.getName() + " are -");
    }

    public void getBookingsByShow(Show show) {
        System.out.println("The bookings created for the show " + show.getName() + " are -");
    }

    public void getShowAndTheatreByName(String name) {
        System.out.println("The shows and theatres by this name " + name + " are -");
    }
}
