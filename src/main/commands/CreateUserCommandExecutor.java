package main.commands;

import main.OutputPrinter;
import main.models.Command;
import main.models.Hall;
import main.models.User;
import main.services.*;

public class CreateUserCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "create_user";

    public CreateUserCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
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
        User user = this.userService.createUser(name);
        outputPrinter.createUser(user);
    }
}
