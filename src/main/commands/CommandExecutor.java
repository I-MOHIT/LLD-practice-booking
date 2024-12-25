package main.commands;

import main.OutputPrinter;
import main.models.Command;
import main.services.*;

public abstract class CommandExecutor {
    OutputPrinter outputPrinter;
    BookingService bookingService;
    PaymentService paymentService;
    ShowService showService;
    TheatreService theatreService;
    UserService userService;

    public CommandExecutor(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
        this.outputPrinter = outputPrinter;
        this.bookingService = bookingService;
        this.paymentService = paymentService;
        this.showService = showService;
        this.theatreService = theatreService;
        this.userService = userService;
    }

    public abstract boolean validateCommand(Command command);
    public abstract void executeCommand(Command command);
}
