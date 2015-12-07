package edu.utexas.cs.systems.membership.simulator.command;

import static edu.utexas.cs.systems.membership.simulator.command.ErrorMessageGenerator.createInvalidCommandMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.utexas.cs.systems.membership.simulator.process.ProcessPool;

public class ResetCommand implements InteractiveCommand {

    public static final String COMMAND_NAME = "RESET";
    public static final String HELP_MESSAGE = "RESET - Reset the current simulation.";
    public static final Pattern COMMAND_PATTERN = Pattern.compile("^RESET$");

    public ProcessPool processPool;

    public ResetCommand(final ProcessPool processPool) {
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
    public void executeCommand(final String commandLineEntry) throws RuntimeException {
        final Matcher listMatch = COMMAND_PATTERN.matcher(commandLineEntry);
        if (listMatch.matches()) {
            System.out.println("-- Resetting member processes. Use the START command to start up simulation.");
            try {
                processPool.reset();
            } catch (Exception exception) {
                throw new InternalErrorException(exception);
            }
        } else {
            System.err.println(createInvalidCommandMessage(COMMAND_NAME, 
                commandLineEntry));
        }
    }
}
