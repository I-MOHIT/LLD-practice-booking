package main.commands;

import main.OutputPrinter;
import main.models.Command;
import main.models.Hall;
import main.models.Theatre;
import main.services.*;

public class CreateHallCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "create_hall";

    public CreateHallCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
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
        String name = command.getParams().getFirst();
        Hall hall = this.theatreService.createHall(name);
        outputPrinter.createHall(hall);
    }
}
