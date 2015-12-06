package edu.utexas.cs.systems.membership.simulator.command;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InteractiveCommandExecutor {
    
    public Map<String, InteractiveCommand> delegatorMap;
    public Pattern commandSetPattern;
    
    public InteractiveCommandExecutor(final Pattern commandSetPattern, 
        final Map<String, InteractiveCommand> delegatorMap) {
        
        this.commandSetPattern = commandSetPattern;
        this.delegatorMap = delegatorMap;
    }
    
    /**
     * 
     * @param commandLineEntry
     */
    public void executeCommand(String commandLineEntry) {
        final Matcher commandMatch = commandSetPattern.matcher(commandLineEntry);
        if (commandMatch.find()) {
            final String commandName = commandMatch.group(0);
            delegatorMap.get(commandName).executeCommand(commandLineEntry);
        } else {
            final String errorMessage = String.format("Unable to find a match "
                + "for command entry: %s.", commandLineEntry);
            System.err.println(errorMessage);
        }
    }
    
    /**
     * Print all commands that this InteractiveCommandExecutor can 
     * execute.
     */
    public void printExecutableCommands() {
        for (String command : delegatorMap.keySet()) {
            System.out.println(command);
        }
    }
}
