package main;


import com.ireasoning.protocol.snmp.SnmpDataType;
import com.ireasoning.protocol.snmp.SnmpInt;



public class BGP {

	
	
	private SnmpDataType bgpPeerIdentifier; //0
	public SnmpDataType bgpPeerState; //1
	private SnmpDataType bgpPeerNegotiatedVersion; //3
	private SnmpDataType bgpPeerRemoteAddr; //6
	private SnmpDataType bgpPeerRemoteAs; //8
	private SnmpDataType bgpPeerInUpdates; //9
	private SnmpDataType bgpPeerOutUpdates; //10
	private SnmpDataType bgpPeerKeepAlive; //18
	private String bgpPeerState1;
	
	
	
	public BGP(SnmpDataType bgpPeerIdentifier, SnmpDataType bgpPeerState, SnmpDataType bgpPeerNegotiatedVersion,
			SnmpDataType bgpPeerRemoteAddr, SnmpDataType bgpPeerRemoteAs, SnmpDataType bgpPeerInUpdates,
			SnmpDataType bgpPeerOutUpdates, SnmpDataType bgpPeerKeepAlive) {
		super();
		this.bgpPeerIdentifier = bgpPeerIdentifier;
		this.bgpPeerState = bgpPeerState;
		this.bgpPeerNegotiatedVersion = bgpPeerNegotiatedVersion;
		this.bgpPeerRemoteAddr = bgpPeerRemoteAddr;
		this.bgpPeerRemoteAs = bgpPeerRemoteAs;
		this.bgpPeerInUpdates = bgpPeerInUpdates;
		this.bgpPeerOutUpdates = bgpPeerOutUpdates;
		this.bgpPeerKeepAlive = bgpPeerKeepAlive;
		switch (Integer.parseInt(bgpPeerState.toString())) {
			case 1:
				bgpPeerState1 ="Idle";
				break;
			case 2: 
				bgpPeerState1 = "Connect";
				break;
			case 3: 
				bgpPeerState1 = "Active";
				break;
			case 4: 
				bgpPeerState1 = "OpenSent";
				break;
			case 5: 
				bgpPeerState1 = "OpenConfirm";
				break;
			case 6: 
				bgpPeerState1 = "Established";
				break;
		}
	}
	public SnmpDataType getBgpPeerIdentifier() {
		return bgpPeerIdentifier;
	}
	
	public String getBgpPeerState() {
		return bgpPeerState1;
	}
	
	public SnmpDataType getBgpPeerNegotiatedVersion() {
		return bgpPeerNegotiatedVersion;
	}
	
	public SnmpDataType getBgpPeerRemoteAddr() {
		return bgpPeerRemoteAddr;
	}
	
	public SnmpDataType getBgpPeerRemoteAs() {
		return bgpPeerRemoteAs;
	}
	
	public SnmpDataType getBgpPeerInUpdates() {
		return bgpPeerInUpdates;
	}
	
	public SnmpDataType getBgpPeerOutUpdates() {
		return bgpPeerOutUpdates;
	}
	
	public SnmpDataType getBgpPeerKeepAlive() {
		return bgpPeerKeepAlive;
	}
	
}
