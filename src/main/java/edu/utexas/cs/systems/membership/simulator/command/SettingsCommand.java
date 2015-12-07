package edu.utexas.cs.systems.membership.simulator.command;

import static edu.utexas.cs.systems.membership.simulator.command.ErrorMessageGenerator.createInvalidCommandMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.utexas.cs.systems.membership.simulator.process.ProcessPool;

public class SettingsCommand implements InteractiveCommand {

    public static final String COMMAND_NAME = "SETTINGS";
    public static final String HELP_MESSAGE = "SETTINGS - Display the settings under which the simulation will be run.";
    public static final Pattern COMMAND_PATTERN = Pattern.compile("^SETTINGS$");

    public ProcessPool processPool;

    public SettingsCommand(final ProcessPool processPool) {
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
            System.out.println("-- Displaying simulation settings.");
            System.out.println(String.format("----- STRATEGY: %s", processPool.getStrategy()));
            System.out.println(String.format("----- PERIOD LENGTH: %s ms", 
                    processPool.getMembershipConfiguration().getPeriodLengthMillis()));
            System.out.println(String.format("----- BROADCAST LATENCY: %s ms", 
                    processPool.getMembershipConfiguration().getBroadcastLatencyMillis()));
            System.out.println(String.format("----- SCHEDULING DELAY: %s ms", 
                processPool.getMembershipConfiguration().getSchedulingDelayMillis()));
        } else {
            System.err.println(createInvalidCommandMessage(COMMAND_NAME, 
                commandLineEntry));
        }
    }
}
