package main.commands;

import main.OutputPrinter;
import main.exceptions.NoShowOrTheatreByThisNameException;
import main.exceptions.UserNotFoundException;
import main.models.*;
import main.services.*;

import java.util.List;
import java.util.UUID;

public class GetShowAndTheatreByNameCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "get_show_and_theatre_by_name";

    public GetShowAndTheatreByNameCommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
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
        if (!this.theatreService.getNameTheatreMap().containsKey(name) && !this.showService.getNameShowMap().containsKey(name)) {
            throw new NoShowOrTheatreByThisNameException();
        }
        Theatre theatre = null;
        if (this.theatreService.getNameTheatreMap().containsKey(name)) {
            theatre = this.theatreService.getUuidTheatreMap().get(this.theatreService.getNameTheatreMap().get(name));
        }

        Show show = null;
        if (this.showService.getNameShowMap().containsKey(name)) {
            show = this.showService.getUuidShowMap().get(this.showService.getNameShowMap().get(name));
        }
        outputPrinter.getShowAndTheatreByName(name);
        System.out.println("Shows -");
        System.out.println(show.getName() + " " + show.getShowId() + " " + show.getHallId());
        System.out.println("Theatres -");
        System.out.println(theatre.getName() + " " + theatre.getTheatreId());
    }
}
