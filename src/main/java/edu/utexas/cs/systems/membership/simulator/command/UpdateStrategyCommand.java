package edu.utexas.cs.systems.membership.simulator.command;

import static edu.utexas.cs.systems.membership.simulator.command.ErrorMessageGenerator.createInvalidCommandMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.utexas.cs.systems.membership.simulator.member.GroupMemberStrategyLabel;
import edu.utexas.cs.systems.membership.simulator.process.ProcessPool;

public class UpdateStrategyCommand implements InteractiveCommand {

    private static final String NAME_GROUP = "NAME";
    
    public static final String COMMAND_NAME = "UPDATE_STRATEGY";
    public static final String HELP_MESSAGE = "UPDATE_STRATEGY "
        + "PERIODIC_BROADCAST | ATTENDANCE_LIST | NEIGHBOR_SURVEILLANCE - "
        + "Change the strategy leveraged for membership maintenance.";
    public static final Pattern COMMAND_PATTERN = Pattern.compile(
        "^UPDATE_STRATEGY\\s+(?<NAME>(?:PERIODIC_BROADCAST)|(?:ATTENDANCE_LIST)|(?:NEIGHBOR_SURVEILLANCE))$");

    public ProcessPool processPool;

    public UpdateStrategyCommand(final ProcessPool processPool) {
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
            
            if (processPool.hasActiveProcesses()) {
                System.err.println(String.format("-- Unable to update strategy to %s when simulation active.", match.group(NAME_GROUP)));
            } else {
                final GroupMemberStrategyLabel strategyLabel = 
                    GroupMemberStrategyLabel.valueOf(match.group(NAME_GROUP));
                System.out.println(String.format("-- Updated strategy to %s.", match.group(NAME_GROUP)));
                processPool.setStrategy(strategyLabel);
            }
        } else {
            System.err.println(createInvalidCommandMessage(COMMAND_NAME, 
                commandLineEntry));
        }
    }
}
