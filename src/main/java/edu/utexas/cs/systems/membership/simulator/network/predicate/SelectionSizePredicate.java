package edu.utexas.cs.systems.membership.simulator.network.predicate;

import com.google.common.collect.Table;

import edu.utexas.cs.systems.membership.simulator.network.message.MessageType;

public class SelectionSizePredicate implements 
    Predicate<Table<Integer, MessageType, Integer>> {
    
    private Integer expectedSelectionSize;
    private Integer expectedTarget;
    private MessageType messageType;
    
    public SelectionSizePredicate(final Integer expectedSelectionSize, 
        final Integer expectedTarget, final MessageType messageType) {
        
        this.expectedSelectionSize = expectedSelectionSize;
        this.expectedTarget = expectedTarget;
        this.messageType = messageType;
    }
    
    @Override
    public boolean isSatisfied(final Table<Integer, MessageType, Integer> table) {
        if (table.contains(expectedTarget, messageType)) {
            return (table.get(expectedTarget, messageType) > expectedSelectionSize); 
        } else {
            return false;
        }
    }
}