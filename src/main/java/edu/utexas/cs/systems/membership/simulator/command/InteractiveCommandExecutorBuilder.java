package edu.utexas.cs.systems.membership.simulator.command;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class InteractiveCommandExecutorBuilder {
    
    private StringBuilder commandsExpressionBuilder;
    private Map<String, InteractiveCommand> commandEntryMap;
    
    public InteractiveCommandExecutorBuilder() {
        commandsExpressionBuilder = new StringBuilder();
        commandEntryMap = new HashMap<String, InteractiveCommand>();
    }
    
    private void addListCommand() {
        final ListCommand listCommand = new ListCommand(commandEntryMap.values());
        addCommand(listCommand);
    }
    
    /**
     * 
     * @param interactiveCommand
     * @return
     */
    public InteractiveCommandExecutorBuilder addCommand(
        final InteractiveCommand interactiveCommand) {
        
        final String commandName = interactiveCommand.getCommandName();
        commandEntryMap.put(commandName, interactiveCommand);
        if (commandsExpressionBuilder.length() == 0) {
            commandsExpressionBuilder.append("^(");
        } else {
            commandsExpressionBuilder.append("|");
        }
        commandsExpressionBuilder.append(commandName);
        return this;
    }
    
    /**
     * 
     * @return
     */
    public InteractiveCommandExecutor build() {

        addListCommand();
        commandsExpressionBuilder.append(")\\b");
        
        final String commandsExpression = commandsExpressionBuilder.toString();
        final Pattern commandsPattern = Pattern.compile(commandsExpression);
        return new InteractiveCommandExecutor(commandsPattern, commandEntryMap);
    }
}
