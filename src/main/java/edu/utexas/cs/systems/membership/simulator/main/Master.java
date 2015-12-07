package edu.utexas.cs.systems.membership.simulator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.utexas.cs.systems.membership.simulator.command.AbortAfterCommand;
import edu.utexas.cs.systems.membership.simulator.command.InteractiveCommandExecutor;
import edu.utexas.cs.systems.membership.simulator.command.InteractiveCommandExecutorBuilder;
import edu.utexas.cs.systems.membership.simulator.command.InternalErrorException;
import edu.utexas.cs.systems.membership.simulator.command.ListCommand;
import edu.utexas.cs.systems.membership.simulator.command.PrintLogCommand;
import edu.utexas.cs.systems.membership.simulator.command.ResetCommand;
import edu.utexas.cs.systems.membership.simulator.command.SettingsCommand;
import edu.utexas.cs.systems.membership.simulator.command.StartCommand;
import edu.utexas.cs.systems.membership.simulator.command.UpdateDelaysCommand;
import edu.utexas.cs.systems.membership.simulator.command.UpdateStrategyCommand;
import edu.utexas.cs.systems.membership.simulator.logs.LogSet;
import edu.utexas.cs.systems.membership.simulator.network.AutoValueTypeAdapterFactory;
import edu.utexas.cs.systems.membership.simulator.network.PortValueGenerator;
import edu.utexas.cs.systems.membership.simulator.network.SimplePortValueGenerator;
import edu.utexas.cs.systems.membership.simulator.process.ProcessPool;
import edu.utexas.cs.systems.membership.simulator.process.ProcessPoolFactory;

public class Master {

    
    /**
     * TODO Leverage Guice or Spring to better create objects rather than creating them
     * manually as below.
     */

    public static Gson installSerializationModule() {
        return new GsonBuilder()
            .registerTypeAdapterFactory(new AutoValueTypeAdapterFactory())
            .create();
    }
    
    public static ProcessPool installProcessModule() {
        final Gson gson = installSerializationModule();
        final PortValueGenerator portValueGenerator = new SimplePortValueGenerator(7000);
        final ProcessPoolFactory processPoolFactory = new ProcessPoolFactory(portValueGenerator, gson, 3);

        return new ProcessPool(processPoolFactory);
    }
    

    public static InteractiveCommandExecutor installCommandModule() throws Exception {
        final ProcessPool processPool = installProcessModule();
        processPool.initialize();

        final ResetCommand resetCommand = new ResetCommand(processPool);
        final StartCommand startCommand = new StartCommand(processPool);
        final SettingsCommand settingsCommand = new SettingsCommand(processPool);
        final UpdateStrategyCommand updateCommand = new UpdateStrategyCommand(processPool);
        final UpdateDelaysCommand updateDelaysCommand = new UpdateDelaysCommand(processPool);
        final AbortAfterCommand abortAfterCommand = new AbortAfterCommand(processPool);
        final PrintLogCommand printLogCommand = new PrintLogCommand(processPool);
        
        return new InteractiveCommandExecutorBuilder()
            .addCommand(settingsCommand)
            .addCommand(startCommand)
            .addCommand(resetCommand)
            .addCommand(updateCommand)
            .addCommand(updateDelaysCommand)
            .addCommand(abortAfterCommand)
            .addCommand(printLogCommand)
            .build();
    }

    /**
     * Start the simulation application.
     * 
     * @param args The command line arguments necessary for the application.
     */
    public static void main(final String[] args) throws Exception{

        final InteractiveCommandExecutor interactiveCommandExecutor = 
            installCommandModule();
        System.err.println("-- Command Listener started --\n");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)))
        {
            while (true) {
               final String input = br.readLine();
               if (input != null) {
                   interactiveCommandExecutor.executeCommand(input);
               }
               Thread.sleep(1500);
            }
        } catch(IOException ioException) {
            System.err.println("User input listener shutting down due to error.");
        } catch (InterruptedException e) {
            System.err.println("User input listener shutting down due to error.");
        } 
    }
}
