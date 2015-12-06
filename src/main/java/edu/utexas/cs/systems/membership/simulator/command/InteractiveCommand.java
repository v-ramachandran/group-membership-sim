package edu.utexas.cs.systems.membership.simulator.command;

public interface InteractiveCommand {
    
    /**
     * @return The {@link String}ified name of this {@link InteractiveCommand}.
     */
    public String getCommandName();
    
    /**
     * @return The help message {@link String} for this {@link Interactive
     * Command}.
     */
    public String getHelpMessage();
    
    /**
     * Execute a specific command as described by the commandLineEntry input.
     */
    public void executeCommand(final String commandLineEntry);
}
