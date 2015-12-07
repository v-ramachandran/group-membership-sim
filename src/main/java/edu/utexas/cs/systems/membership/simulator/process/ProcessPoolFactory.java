package edu.utexas.cs.systems.membership.simulator.process;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import edu.utexas.cs.systems.membership.simulator.logs.LogSet;
import edu.utexas.cs.systems.membership.simulator.member.GroupMemberState;
import edu.utexas.cs.systems.membership.simulator.member.MemberIdentification;
import edu.utexas.cs.systems.membership.simulator.network.AbortingNetworkCommunicator;
import edu.utexas.cs.systems.membership.simulator.network.NetworkIdentityManager;
import edu.utexas.cs.systems.membership.simulator.network.PortValueGenerator;
import edu.utexas.cs.systems.membership.simulator.network.SimpleNetworkCommunicator;
import edu.utexas.cs.systems.membership.simulator.network.controller.Config;
import edu.utexas.cs.systems.membership.simulator.network.controller.NetController;

public class ProcessPoolFactory {

    private Gson gson;
    private NetworkIdentityManager networkIdentityManager;

    
    public ProcessPoolFactory(final PortValueGenerator portValueGenerator,
        final Gson gson, final int numberMembers) {

        this.gson = gson;
        this.networkIdentityManager = new NetworkIdentityManager(portValueGenerator, numberMembers);
    }

    public Map<Integer, CrashingMemberProcess> createMemberProcesses(GroupMemberState groupMemberState,
        final LogSet logSet) throws Exception {

        final Map<Integer, CrashingMemberProcess> memberPool = 
            new HashMap<Integer, CrashingMemberProcess>();
        final Set<MemberIdentification> memberIds = 
            networkIdentityManager.generateIdentities();
        for (final MemberIdentification memberIdentification : memberIds) {
            
            final Config config = networkIdentityManager.generateNetworkConfig(memberIdentification);
            final NetController netController = new NetController(config);
            final AbortingNetworkCommunicator networkCommunicator = 
                new SimpleNetworkCommunicator(netController, memberIdentification, memberIds, gson);
            final CrashingMemberProcess memberProcess = 
                new SimpleCrashingMemberProcess(networkCommunicator, groupMemberState, memberIds, logSet);
            memberPool.put(memberIdentification.getId(), memberProcess);
        }
        
        return memberPool;
    }
}
