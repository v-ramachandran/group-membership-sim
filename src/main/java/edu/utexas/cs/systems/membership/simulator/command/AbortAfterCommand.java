package edu.utexas.cs.systems.membership.simulator.command;

import static edu.utexas.cs.systems.membership.simulator.command.ErrorMessageGenerator.createInvalidCommandMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Table;

import edu.utexas.cs.systems.membership.simulator.member.GroupMemberStrategyLabel;
import edu.utexas.cs.systems.membership.simulator.network.message.MessageType;
import edu.utexas.cs.systems.membership.simulator.network.predicate.Predicate;
import edu.utexas.cs.systems.membership.simulator.network.predicate.SelectionSizePredicate;
import edu.utexas.cs.systems.membership.simulator.process.CrashingMemberProcess;
import edu.utexas.cs.systems.membership.simulator.process.ProcessPool;

public class AbortAfterCommand implements InteractiveCommand {

    private static final String MESSAGE_GROUP = "MESSAGE";
    private static final String COUNT_GROUP = "COUNT";
    private static final String ID_GROUP = "ID";
    
    public static final String COMMAND_NAME = "ABORT_AFTER_SEND";
    public static final String HELP_MESSAGE = "ABORT_AFTER_SEND messageCount"
        + " LIST | PRESENT targetId"
        + " - Crash the member specified by the targetId after it has sent messageCount number of [LIST|PRESENT].";
    public static final Pattern COMMAND_PATTERN = Pattern.compile(
        "^ABORT_AFTER_SEND\\s+(?<COUNT>(\\d+))\\s+(?<MESSAGE>(?:LIST)|(?:PRESENT))\\s+(?<ID>(\\d+))$");

    public ProcessPool processPool;

    public AbortAfterCommand(final ProcessPool processPool) {
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
                final MessageType messageType = MessageType.valueOf(match.group(MESSAGE_GROUP));
                final Integer memberId = Integer.valueOf(match.group(ID_GROUP));
                final Integer count = Integer.valueOf(match.group(COUNT_GROUP));
                
                final String countString = String.format(
                    "- Added simulation to abort:\n -- After sending [%s] messages", count);
                System.out.println(countString);
                final String memberIdString = String.format(
                    "--- Where messages are from [%s]", memberId);
                System.out.println(memberIdString);
                final String messageTypeString = String.format(
                    "--- Where messages are type [%s]", messageType);
                System.out.println(messageTypeString);
                
                final CrashingMemberProcess memberProcess = 
                    processPool.retrieveMemberProcess(memberId);
                final Predicate<Table<Integer, MessageType, Integer>> abortPredicate =
                    new SelectionSizePredicate(count, memberId, messageType);
                memberProcess.addSendMessageAbortCondition(abortPredicate);
            }
        } else {
            System.err.println(createInvalidCommandMessage(COMMAND_NAME, 
                commandLineEntry));
        }
    }
}
