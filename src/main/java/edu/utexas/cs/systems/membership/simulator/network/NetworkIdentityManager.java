package edu.utexas.cs.systems.membership.simulator.network;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import edu.utexas.cs.systems.membership.simulator.member.MemberIdentification;
import edu.utexas.cs.systems.membership.simulator.network.controller.Config;

public class NetworkIdentityManager {
    
    private PortValueGenerator portValueGenerator;
    private Integer numberProcesses;
    
    public NetworkIdentityManager(final PortValueGenerator portValueGenerator,
        final int numberProcesses) {
        
        this.portValueGenerator = portValueGenerator;
        this.numberProcesses = numberProcesses;
    }
    
    public Config generateNetworkConfig(final MemberIdentification memberIdentification) 
        throws Exception {
        
        final Integer procNum = memberIdentification.getId();
        final Properties properties = new Properties();
        properties.setProperty("NumProcesses", numberProcesses.toString());
        properties.setProperty("procNum", procNum.toString());
        
        for (int i = 0; i < numberProcesses; i++) {
            final Integer portValue = portValueGenerator.generatePort(i);
            final String hostLabel = String.format("host%s", i);
            final String portLabel = String.format("port%s", i);
            properties.setProperty(hostLabel, "localhost");
            properties.setProperty(portLabel, portValue.toString());
        }
        return new Config(properties);
    }
    
    public Set<MemberIdentification> generateIdentities() {
        final Set<MemberIdentification> memberIds = new HashSet<MemberIdentification>();
        for (int i = 0; i < numberProcesses; i++) {
            final int portValue = portValueGenerator.generatePort(i);
            final MemberIdentification memberIdentification = 
                MemberIdentification.builder()
                    .setId(i)
                    .setHostname("localhost")
                    .setPort(portValue)
                    .build();
            memberIds.add(memberIdentification);
        }
        
        return memberIds;
    }
    
    public MemberIdentification generateIdentity(final int id) {
        final int portValue = portValueGenerator.generatePort(id);
        return MemberIdentification.builder()
                .setId(id)
                .setHostname("localhost")
                .setPort(portValue)
                .build();
    }
}
