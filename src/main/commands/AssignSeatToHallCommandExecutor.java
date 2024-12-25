package main.commands;

import main.OutputPrinter;
import main.exceptions.HallNotFoundException;
import main.exceptions.SeatNotFoundException;
import main.exceptions.TheatreNotFoundException;
import main.models.Command;
import main.models.Hall;
import main.models.Seat;
import main.models.Theatre;
import main.services.*;

import java.util.UUID;

public class AssignSeatToHallCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "assign_seat_to_hall";

    public AssignSeatToHallCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
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
        UUID hallId = UUID.fromString(command.getParams().get(1));
        if (!this.theatreService.getUuidHallMap().containsKey(hallId)) {
            throw new HallNotFoundException();
        }
        this.theatreService.assignSeatToHall(seatId, hallId);
        Seat seat = this.theatreService.getUuidSeatMap().get(seatId);
        Hall hall = this.theatreService.getUuidHallMap().get(hallId);
        outputPrinter.assignSeatToHall(seat, hall);
    }
}
