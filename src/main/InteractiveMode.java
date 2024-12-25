package main;

import main.commands.CommandExecutor;
import main.commands.CommandExecutorFactory;
import main.commands.ExitCommandExecutor;
import main.exceptions.CommandNotFoundException;
import main.models.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveMode {
    private OutputPrinter outputPrinter;
    private CommandExecutorFactory commandExecutorFactory;

    public InteractiveMode(OutputPrinter outputPrinter, CommandExecutorFactory commandExecutorFactory) {
        this.outputPrinter = outputPrinter;
        this.commandExecutorFactory = commandExecutorFactory;
    }

    public void process() throws IOException {
        outputPrinter.hello();
        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String inputString  = bufferedReader.readLine();
            Command command = new Command(inputString);
            CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
            this.processCommand(commandExecutor, command);

            if (command.getCommandName().equals(ExitCommandExecutor.COMMAND_NAME)) {
                return;
            }
        }
    }

    private void processCommand(CommandExecutor commandExecutor, Command command) {
        if (!commandExecutor.validateCommand(command)) {
            throw new CommandNotFoundException();
        }

        commandExecutor.executeCommand(command);
    }
}
