/**
 * This code may be modified and used for non-commercial 
 * purposes as long as attribution is maintained.
 * 
 * @author: Isaac Levy
 */

package edu.utexas.cs.systems.membership.simulator.network.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This code has been modified from the version provided by Isaac Levy to:
 * 
 * 1. Be constructed from a {@link Properties} object.
 */
public class Config {
    
    private void initializeConfig(final Properties properties) throws IOException {
        numProcesses = loadInt(properties,"NumProcesses");
        addresses = new InetAddress[numProcesses];
        ports = new int[numProcesses];
        for (int i=0; i < numProcesses; i++) {
            ports[i] = loadInt(properties, "port" + i);
            addresses[i] = InetAddress.getByName(properties.getProperty("host" + i).trim());
        }
        
        final String procNumValue = System.getProperty("procNum");
        if (procNumValue != null) {
            procNum = Integer.parseInt(procNumValue);
        } else if (properties.getProperty("procNum") != null) {
            procNum = loadInt(properties,"procNum");
        } else {
            logger.info("procNum not loaded from file");
        }
    }
    /**
     * Loads config from a file.  Optionally puts in 'procNum' if in file.
     * See sample file for syntax
     * @param filename
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Config(String filename) throws FileNotFoundException, IOException {
        logger = Logger.getLogger("NetFramework");
        logger.setLevel(Level.INFO);
        
        Properties prop = new Properties();
        prop.load(new FileInputStream(filename));
        initializeConfig(prop);
    }
    
    public Config(final Properties properties) throws Exception {
        logger = Logger.getLogger("NetFramework");
        logger.setLevel(Level.SEVERE);
        
        initializeConfig(properties);
    }
    
    
    private int loadInt(Properties prop, String s) {
        return Integer.parseInt(prop.getProperty(s.trim()));
    }
    
    /**
     * Default constructor for those who want to populate config file manually
     */
    public Config() {
    }

    /**
     * Array of addresses of other hosts.  All hosts should have identical info here.
     */
    public InetAddress[] addresses;
    

    /**
     * Array of listening port of other hosts.  All hosts should have identical info here.
     */
    public int[] ports;
    
    /**
     * Total number of hosts
     */
    public int numProcesses;
    
    /**
     * This hosts number (should correspond to array above).  Each host should have a different number.
     */
    public int procNum;
    
    /**
     * Logger.  Mainly used for console printing, though be diverted to a file.
     * Verbosity can be restricted by raising level to WARN
     */
    public Logger logger;
    
    public Map<Integer, Boolean> retrievePeerMap() {
        
        final Map<Integer, Boolean> peerMap = new HashMap<Integer, Boolean>();
        for(int process=0; process < numProcesses; process++) {
            peerMap.put(process, true);
        }
        return peerMap;
    }
    
    public int getProcNum() {
        return procNum;
    }

    public int getNumProcesses() {
        return numProcesses;
    }
}
