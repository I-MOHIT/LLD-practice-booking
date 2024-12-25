package main;

import main.commands.CommandExecutorFactory;
import main.services.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OutputPrinter outputPrinter = new OutputPrinter();
        TheatreService theatreService = new TheatreService();
        ShowService showService = new ShowService(theatreService);
        BookingService bookingService = new BookingService(showService);
        PaymentService paymentService = new PaymentService(bookingService);
        UserService userService = new UserService(bookingService);

        CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(outputPrinter, bookingService, paymentService, showService, theatreService, userService);

        new InteractiveMode(outputPrinter, commandExecutorFactory).process();
    }
}