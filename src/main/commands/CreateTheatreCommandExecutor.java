package main.commands;

import main.OutputPrinter;
import main.models.Command;
import main.models.Theatre;
import main.services.*;

public class CreateTheatreCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "create_theatre";

    public CreateTheatreCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
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
        Theatre theatre = this.theatreService.createTheatre(name);
        outputPrinter.createTheatre(theatre);
    }
}
