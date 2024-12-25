package main.commands;

import main.OutputPrinter;
import main.exceptions.UserNotFoundException;
import main.models.Booking;
import main.models.Command;
import main.models.Hall;
import main.models.User;
import main.services.*;

import java.util.List;
import java.util.UUID;

public class GetBookingsByUserCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "get_bookings_by_user";

    public GetBookingsByUserCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
        super(outputPrinter, bookingService, paymentService, showService, theatreService, userService);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommandName().equals(COMMAND_NAME) || command.getParams().size() != 1) {
            return false;
        }
        return true;
    }

    @Override
    public void executeCommand(Command command) {
        UUID userId = UUID.fromString(command.getParams().getFirst());
        if (!this.bookingService.getUserBookingMap().containsKey(userId)) {
            throw new UserNotFoundException();
        }
        User user = this.userService.getUuidUserMap().get(userId);
        List<UUID> bookingIdList = this.bookingService.getUserBookingMap().get(userId);
        outputPrinter.getBookingsByUser(user);
        for (UUID bookingId : bookingIdList) {
            Booking booking = this.bookingService.getUuidBookingMap().get(bookingId);
            System.out.println(booking.getBookingId() + " " + booking.getBookingStatus() + " " + booking.getBookingTime() + " " + booking.getShowId());
        }
    }
}
