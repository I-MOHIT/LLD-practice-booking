package main.commands;

import main.OutputPrinter;
import main.models.Command;
import main.models.Hall;
import main.models.Seat;
import main.services.*;

public class CreateSeatCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "create_seat";

    public CreateSeatCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
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
        int numSeats = Integer.parseInt(command.getParams().getFirst());
        for (int i = 0; i < numSeats; i++) {
            Seat seat = this.theatreService.createSeat();
            outputPrinter.createSeat(seat);
        }
    }
}
