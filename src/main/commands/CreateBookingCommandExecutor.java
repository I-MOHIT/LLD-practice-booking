package main.commands;

import main.OutputPrinter;
import main.exceptions.ShowNotFoundException;
import main.exceptions.UserNotFoundException;
import main.models.Booking;
import main.models.Command;
import main.models.Hall;
import main.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateBookingCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "create_booking";

    public CreateBookingCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
        super(outputPrinter, bookingService, paymentService, showService, theatreService, userService);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommandName().equals(COMMAND_NAME) || command.getParams().size() < 3) {
            return false;
        }
        return true;
    }

    @Override
    public void executeCommand(Command command) {
        UUID showId = UUID.fromString(command.getParams().getFirst());
        if (!this.showService.getUuidShowMap().containsKey(showId)) {
            throw new ShowNotFoundException();
        }
        UUID userId = UUID.fromString(command.getParams().get(1));
        if (!this.userService.getUuidUserMap().containsKey(userId)) {
            throw new UserNotFoundException();
        }
        List<UUID> showseatIdList = new ArrayList<>();
        for (int i = 2; i < command.getParams().size(); i++) {
            showseatIdList.add(UUID.fromString(command.getParams().get(i)));
        }
        Booking booking = this.bookingService.createBooking(showId, userId, showseatIdList);
        outputPrinter.createBooking(booking);
    }
}
