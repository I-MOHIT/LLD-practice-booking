package main.commands;

import main.OutputPrinter;
import main.models.Command;
import main.models.Hall;
import main.models.Show;
import main.services.*;

import java.util.UUID;

public class CreateShowCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "create_show";

    public CreateShowCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
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
        String name = command.getParams().getFirst();
        UUID hallId = UUID.fromString(command.getParams().get(1));
        Show show = this.showService.createShow(name, hallId);
        outputPrinter.createShow(show);
    }
}
