package main.commands;

import main.OutputPrinter;
import main.exceptions.BookingNotFoundException;
import main.models.Command;
import main.services.*;

import java.util.UUID;

public class PaymentFailedCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "payment_failed";

    public PaymentFailedCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
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
        UUID bookingId = UUID.fromString(command.getParams().getFirst());
        if (!this.bookingService.getUuidBookingMap().containsKey(bookingId)) {
            throw new BookingNotFoundException();
        }
        boolean retriesLeft = this.paymentService.paymentFailed(bookingId);
        outputPrinter.paymentFailed(bookingId, retriesLeft);
    }
}
