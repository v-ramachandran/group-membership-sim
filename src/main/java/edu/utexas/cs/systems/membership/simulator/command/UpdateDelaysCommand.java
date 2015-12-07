package edu.utexas.cs.systems.membership.simulator.command;

import static edu.utexas.cs.systems.membership.simulator.command.ErrorMessageGenerator.createInvalidCommandMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.utexas.cs.systems.membership.simulator.member.GroupMemberStrategyLabel;
import edu.utexas.cs.systems.membership.simulator.member.MembershipConfiguration;
import edu.utexas.cs.systems.membership.simulator.process.ProcessPool;

public class UpdateDelaysCommand implements InteractiveCommand {

    private static final String PERIOD_GROUP = "PERIOD";
    private static final String BROADCAST_GROUP = "BROADCAST";
    private static final String SCHEDULING_GROUP = "SCHEDULING";
    
    public static final String COMMAND_NAME = "UPDATE_TIME";
    public static final String HELP_MESSAGE = "UPDATE_TIME "
        + " period broadcastDelay schedulingDelay - "
        + "Change the time parameters, in millis, leveraged for membership maintenance.";
    public static final Pattern COMMAND_PATTERN = Pattern.compile(
        "^UPDATE_TIME\\s+(?<PERIOD>(\\d+))\\s+(?<BROADCAST>(\\d+))\\s+(?<SCHEDULING>(\\d+))$");

    public ProcessPool processPool;

    public UpdateDelaysCommand(final ProcessPool processPool) {
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
                System.err.println(String.format("-- Unable to update when simulation is active."));
            } else {
                final Long period = Long.valueOf(match.group(PERIOD_GROUP));
                final Long broadcast = Long.valueOf(match.group(BROADCAST_GROUP));
                final Long scheduling = Long.valueOf(match.group(SCHEDULING_GROUP));
                final MembershipConfiguration membershipConfiguration = MembershipConfiguration
                        .builder(processPool.getMembershipConfiguration())
                        .setBroadcastLatencyMillis(broadcast)
                        .setPeriodLengthMillis(period)
                        .setSchedulingDelayMillis(scheduling)
                        .build();
                processPool.setMembershipConfiguration(membershipConfiguration);
                System.out.println(String.format("-- Updated timings. View the updated variants with SETTINGS."));
            }
        } else {
            System.err.println(createInvalidCommandMessage(COMMAND_NAME, 
                commandLineEntry));
        }
    }
}
