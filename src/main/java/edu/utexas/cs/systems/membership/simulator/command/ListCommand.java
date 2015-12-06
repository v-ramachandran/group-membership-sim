package edu.utexas.cs.systems.membership.simulator.command;

import static edu.utexas.cs.systems.membership.simulator.command.ErrorMessageGenerator.createInvalidCommandMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListCommand implements InteractiveCommand {

    public static final String COMMAND_NAME = "LIST";
    public static final String HELP_MESSAGE = "LIST";
    public static final Pattern COMMAND_PATTERN = Pattern.compile("^LIST$");

    public Iterable<InteractiveCommand> commands;

    public ListCommand(final Iterable<InteractiveCommand> commands) {
        this.commands = commands;
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
        final Matcher listMatch = COMMAND_PATTERN.matcher(commandLineEntry);
        if (listMatch.matches()) {
            System.out.println("Printing available commands ...");
            for (final InteractiveCommand command : commands) {
                System.out.print(" - ");
                System.out.println(command.getHelpMessage());
            }
        } else {
            System.err.println(createInvalidCommandMessage(COMMAND_NAME, 
                commandLineEntry));
        }
    }
}
