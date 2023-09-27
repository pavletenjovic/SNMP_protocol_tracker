package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ireasoning.protocol.snmp.MibUtil;
import com.ireasoning.protocol.snmp.SnmpConst;
import com.ireasoning.protocol.snmp.SnmpDataType;
import com.ireasoning.protocol.snmp.SnmpSession;
import com.ireasoning.protocol.snmp.SnmpTableModel;
import com.ireasoning.protocol.snmp.SnmpTarget;
import com.ireasoning.util.MibParseException;



public class Router {

	private String ipAddress;
	private static final String community = "si2019";

	private SnmpTarget targetRouter;
	private SnmpSession session;
	private SnmpTableModel bgpPeerTable;
	List<BGP> bgpPeers = new ArrayList<>();
	
	public Router(String ip) throws MibParseException, IOException {
		this.ipAddress = ip;
		targetRouter = new SnmpTarget(ipAddress, 161, community, community, SnmpConst.SNMPV2);
		System.out.println("Kreiran je ruter " + ip);
		initialize();
	}
	
	public void initialize() throws MibParseException, IOException {
		
		session = new SnmpSession(targetRouter);
		MibUtil.loadMib("mibs/BGP4-MIB");
		bgpPeers.clear();
		bgpPeerTable = session.snmpGetTable("bgpPeerTable");
		
		for (int i = 0; i < bgpPeerTable.getRowCount(); i++) {

			SnmpDataType peerId = bgpPeerTable.get(i, 0).getValue();
			SnmpDataType peerState = bgpPeerTable.get(i, 1).getValue();
			SnmpDataType peerBGPv = bgpPeerTable.get(i, 3).getValue();
			SnmpDataType peerRemoteAddr = bgpPeerTable.get(i, 6).getValue();
			SnmpDataType peerRemoteAS = bgpPeerTable.get(i, 8).getValue();
			SnmpDataType peerInUpdates = bgpPeerTable.get(i, 9).getValue();
			SnmpDataType peerOutUpdates = bgpPeerTable.get(i, 10).getValue();
			SnmpDataType peerKeepAlive = bgpPeerTable.get(i, 18).getValue();
			BGP peer = new BGP(peerId, peerState, peerBGPv, peerRemoteAddr, peerRemoteAS, peerInUpdates,
					peerOutUpdates, peerKeepAlive);
			bgpPeers.add(peer);
		}
	}
	
	public void close() {
		session.close();
	}
	
	public SnmpTarget getTargetRouter() {
		return targetRouter;
	}

	public void setTargetRouter(SnmpTarget targetRouter) {
		this.targetRouter = targetRouter;
	}

	public List<BGP> getBgpPeers() {
		return bgpPeers;
	}
	
	public String toString() {
		String s = "";
		for(BGP p : bgpPeers) {
			s+= "ID: " + p.getBgpPeerIdentifier() + " " + "ST: " +  p.getBgpPeerState()
			+ " " + "V: " + p.getBgpPeerNegotiatedVersion() + " " + "IP: " + p.getBgpPeerRemoteAddr() + " "
			+ "UI: " + p.getBgpPeerInUpdates() + " " + "UO: " + p.getBgpPeerOutUpdates() + " " + "KP: "
			+ p.getBgpPeerKeepAlive() + " \n";
		}
		return s;
	}
}
