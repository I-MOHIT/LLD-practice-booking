package main.commands;

import main.OutputPrinter;
import main.exceptions.HallNotFoundException;
import main.exceptions.TheatreNotFoundException;
import main.models.Command;
import main.models.Hall;
import main.models.Theatre;
import main.services.*;

import java.util.UUID;

public class AssignHallToTheatreCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "assign_hall_to_theatre";

    public AssignHallToTheatreCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
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
        UUID hallId = UUID.fromString(command.getParams().getFirst());
        if (!this.theatreService.getUuidHallMap().containsKey(hallId)) {
            throw new HallNotFoundException();
        }
        UUID theatreId = UUID.fromString(command.getParams().get(1));
        if (!this.theatreService.getUuidTheatreMap().containsKey(theatreId)) {
            throw new TheatreNotFoundException();
        }
        this.theatreService.assignHallToTheatre(hallId, theatreId);
        Hall hall = this.theatreService.getUuidHallMap().get(hallId);
        Theatre theatre = this.theatreService.getUuidTheatreMap().get(theatreId);
        outputPrinter.assignHallToTheatre(hall, theatre);
    }
}
