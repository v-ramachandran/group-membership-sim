package edu.utexas.cs.systems.membership.simulator.command;

import static edu.utexas.cs.systems.membership.simulator.command.ErrorMessageGenerator.createInvalidCommandMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.utexas.cs.systems.membership.simulator.process.ProcessPool;

public class PrintLogCommand implements InteractiveCommand {

    public static final String COMMAND_NAME = "PRINT_LOG";
    public static final String HELP_MESSAGE = "PRINT_LOG - Print all of the important events that was logged in the last run.";
    public static final Pattern COMMAND_PATTERN = Pattern.compile("^PRINT_LOG$");

    public ProcessPool processPool;

    public PrintLogCommand(final ProcessPool processPool) {
        this.processPool = processPool;
    }

    /**
     * @see InteractiveCommand#getCommandName()
     */
    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
    
    /**
     * @see InteractiveCommand#getHelpMessage()
     */
    @Override
    public String getHelpMessage() {
        return HELP_MESSAGE;
    }

    @Override
    public void executeCommand(final String commandLineEntry) {
        final Matcher match = COMMAND_PATTERN.matcher(commandLineEntry);
        if (match.matches()) {
            System.out.println("Printing stored log ...");
            processPool.getLogSet().prettyPrintToConsole();
        } else {
            System.err.println(createInvalidCommandMessage(COMMAND_NAME, 
                commandLineEntry));
        }
    }
}
