package edu.utexas.cs.systems.membership.simulator.command;

public class ErrorMessageGenerator {
    
    public static String createInvalidCommandMessage(final String commandName, 
        final String commandEntry) {
        return String.format("-- Your supplied %s command [%s] contains "
            + "insufficient or invalid arguments. --", commandName, commandEntry);
    }
    
    public static String createHelpNotice(final String commandName, final String usage) {
        return String.format("The %s command accepts messages in the format: %s",
            commandName, usage);
    }
}
