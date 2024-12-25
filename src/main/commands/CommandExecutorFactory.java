package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.models.Command;
import main.services.*;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {
    private OutputPrinter outputPrinter;
    private BookingService bookingService;
    private PaymentService paymentService;
    private ShowService showService;
    private TheatreService theatreService;
    private UserService userService;
    private Map<String, CommandExecutor> commandExecutorMap;

    public CommandExecutorFactory(OutputPrinter outputPrinter, BookingService bookingService, PaymentService paymentService, ShowService showService, TheatreService theatreService, UserService userService) {
        this.outputPrinter = outputPrinter;
        this.bookingService = bookingService;
        this.paymentService = paymentService;
        this.showService = showService;
        this.theatreService = theatreService;
        this.userService = userService;
        this.commandExecutorMap = new HashMap<>();
        this.commandExecutorMap.put(ExitCommandExecutor.COMMAND_NAME, new ExitCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(AssignHallToTheatreCommandExecutor.COMMAND_NAME, new AssignHallToTheatreCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(AssignSeatToHallCommandExecutor.COMMAND_NAME, new AssignSeatToHallCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(AssignSeatToShowCommandExecutor.COMMAND_NAME, new AssignSeatToShowCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(CancelBookingCommandExecutor.COMMAND_NAME, new CancelBookingCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(ConfirmBookingCommandExecutor.COMMAND_NAME, new ConfirmBookingCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(CreateBookingCommandExecutor.COMMAND_NAME, new CreateBookingCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(CreateHallCommandExecutor.COMMAND_NAME, new CreateHallCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(CreateSeatCommandExecutor.COMMAND_NAME, new CreateSeatCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(CreateShowCommandExecutor.COMMAND_NAME, new CreateShowCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(CreateTheatreCommandExecutor.COMMAND_NAME, new CreateTheatreCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(CreateUserCommandExecutor.COMMAND_NAME, new CreateUserCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(PaymentFailedCommandExecutor.COMMAND_NAME, new PaymentFailedCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(GetBookingsByUserCommandExecutor.COMMAND_NAME, new GetBookingsByUserCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(GetBookingsByShowCommandExecutor.COMMAND_NAME, new GetBookingsByShowCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
        this.commandExecutorMap.put(GetShowAndTheatreByNameCommandExecutor.COMMAND_NAME, new GetShowAndTheatreByNameCommandExecutor(outputPrinter, bookingService, paymentService, showService, theatreService, userService));
    }

    public CommandExecutor getCommandExecutor(Command command) {
        if (!this.commandExecutorMap.containsKey(command.getCommandName())) {
            throw new CommandNotFoundException();
        }

        return this.commandExecutorMap.get(command.getCommandName());
    }

    public OutputPrinter getOutputPrinter() {
        return outputPrinter;
    }

    public void setOutputPrinter(OutputPrinter outputPrinter) {
        this.outputPrinter = outputPrinter;
    }

    public BookingService getBookingService() {
        return bookingService;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public ShowService getShowService() {
        return showService;
    }

    public void setShowService(ShowService showService) {
        this.showService = showService;
    }

    public TheatreService getTheatreService() {
        return theatreService;
    }

    public void setTheatreService(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Map<String, CommandExecutor> getCommandExecutorMap() {
        return commandExecutorMap;
    }

    public void setCommandExecutorMap(Map<String, CommandExecutor> commandExecutorMap) {
        this.commandExecutorMap = commandExecutorMap;
    }
}
