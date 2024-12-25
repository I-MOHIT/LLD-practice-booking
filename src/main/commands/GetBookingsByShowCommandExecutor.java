package main.commands;

import main.OutputPrinter;
import main.exceptions.ShowNotFoundException;
import main.exceptions.UserNotFoundException;
import main.models.Booking;
import main.models.Command;
import main.models.Show;
import main.models.User;
import main.services.*;

import java.util.List;
import java.util.UUID;

public class GetBookingsByShowCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "get_bookings_by_show";

    public GetBookingsByShowCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
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
        UUID showId = UUID.fromString(command.getParams().getFirst());
        if (!this.bookingService.getShowBookingMap().containsKey(showId)) {
            throw new ShowNotFoundException();
        }
        Show show = this.showService.getUuidShowMap().get(showId);
        List<UUID> bookingIdList = this.bookingService.getShowBookingMap().get(showId);
        outputPrinter.getBookingsByShow(show);
        for (UUID bookingId : bookingIdList) {
            Booking booking = this.bookingService.getUuidBookingMap().get(bookingId);
            System.out.println(booking.getBookingId() + " " + booking.getBookingStatus() + " " + booking.getBookingTime() + " " + booking.getShowId());
        }
    }
}
