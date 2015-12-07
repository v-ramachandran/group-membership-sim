package edu.utexas.cs.systems.membership.simulator.command;

import static edu.utexas.cs.systems.membership.simulator.command.ErrorMessageGenerator.createInvalidCommandMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.utexas.cs.systems.membership.simulator.process.ProcessPool;

public class StartCommand implements InteractiveCommand {

    private static final String START_GROUP = "ROUNDS";

    public static final String COMMAND_NAME = "START";
    public static final String HELP_MESSAGE = "START numberRounds - Start the current simulation and run it for a number of rounds. View the settings with SETTINGS.";
    public static final Pattern COMMAND_PATTERN = Pattern.compile("^START\\s+(?<ROUNDS>(\\d+))$");

    public ProcessPool processPool;

    public StartCommand(final ProcessPool processPool) {
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
        final Matcher match = COMMAND_PATTERN.matcher(commandLineEntry);
        if (match.matches()) {
            System.out.println(String.format("-- Starting simulation using %s...", processPool.getStrategy()));
            
            if (processPool.hasActiveProcesses()) {
                System.err.println("Unable to start processes that have already been started. Consider RESET first.");
            } else {
                try {
                    processPool.initialize();
                } catch (Exception exception) {
                    throw new InternalErrorException(exception);
                }
                final int numberTimesToRun = Integer.valueOf(match.group(START_GROUP));
                processPool.startAllProcesses(numberTimesToRun);
            }
        } else {
            System.err.println(createInvalidCommandMessage(COMMAND_NAME, 
                commandLineEntry));
            System.err.println("\n");
        }
    }
}
