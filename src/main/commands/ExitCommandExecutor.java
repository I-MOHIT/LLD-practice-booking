package main.commands;

import main.OutputPrinter;
import main.models.Command;
import main.services.*;

public class ExitCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "exit";

    public ExitCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
        super(outputPrinter, bookingService, paymentService, showService, theatreService, userService);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommandName().equals(COMMAND_NAME) || !command.getParams().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void executeCommand(Command command) {
        outputPrinter.exit();
    }
}
