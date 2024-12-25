package main.commands;

import main.OutputPrinter;
import main.exceptions.HallNotFoundException;
import main.exceptions.SeatNotFoundException;
import main.exceptions.ShowNotFoundException;
import main.models.*;
import main.services.*;

import java.util.UUID;

public class AssignSeatToShowCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "assign_seat_to_show";

    public AssignSeatToShowCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
        super(outputPrinter, bookingService, paymentService, showService, theatreService, userService);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommandName().equals(COMMAND_NAME) || command.getParams().size() != 2) {
            return false;
        }
        return true;
    }

    @Override
    public void executeCommand(Command command) {
        UUID seatId = UUID.fromString(command.getParams().getFirst());
        if (!this.theatreService.getUuidSeatMap().containsKey(seatId)) {
            throw new SeatNotFoundException();
        }
        UUID showId = UUID.fromString(command.getParams().get(1));
        if (!this.showService.getUuidShowMap().containsKey(showId)) {
            throw new ShowNotFoundException();
        }
        Showseat showseat = this.showService.assignSeatToShow(seatId, showId);
        outputPrinter.assignSeatToShow(showseat);
    }
}
