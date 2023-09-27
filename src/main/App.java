package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListenerProxy;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ireasoning.util.MibParseException;



public class App extends Thread{

	private JFrame frame;
	private String[] s = {"192.168.10.1","192.168.20.1","192.168.30.1" };
	private JComboBox routers;
	private BGP bgp1;
	private BGP bgp2;
	public JPanel center, glavni, router1, router2;
	private JLabel broj1,id1,stanje1,verzija1,ip1,as1,in1,out1,keep1,est1;
	private JLabel broj2,id2,stanje2,verzija2,ip2,as2,in2,out2,keep2,est2;
	public Router router;
	private JLabel izabrani = new JLabel("Izabran ruter:");
	public static int i = 0,j = 0;
	private Timer nit1 = new Timer(this);
	//private Timer nit2 = new Timer();
	
	
	
	
	
	public App() {
		initialize();
	}

	public void initialize() {
		frame =  new JFrame("RM Projekat");
		frame.setBounds(300, 300, 600, 300);
		//frame.getContentPane().setBackground(new Color(0,230,0));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLayout(new BorderLayout());
		
		routers = new JComboBox();
		for(String i : s) {
			routers.addItem(i);
		}
		try {
			router = new Router("192.168.10.1");
		} catch (MibParseException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		routers.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					router.close();
					try {
						
						router = new Router((String) e.getItem());
					} catch (MibParseException | IOException e2) {
						e2.printStackTrace();
					}
					try {
						router.initialize();
					} catch (MibParseException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					routerInfo();
					i=0;
					j=0;
					//frame.revalidate();
					
				}
			}
		});
		glavni = new JPanel();
		glavni.add(izabrani);
		glavni.add(routers);
		glavni.setSize(100, 50);
		glavni.setBackground(Color.LIGHT_GRAY);
		frame.add(glavni, BorderLayout.NORTH);
		
		populate();
		routerInfo();
		start();
		nit1.start();
		//nit2.start();
		//frame.pack();
	}

	public void populate() {
		router1 = new JPanel();
		
		broj1 = new JLabel();
		id1 = new JLabel();
		stanje1 = new JLabel();
		verzija1 = new JLabel();
		ip1 = new JLabel();
		as1 = new JLabel();
		in1 = new JLabel();
		out1 = new JLabel();
		keep1 = new JLabel();
		est1 = new JLabel();
		broj2 = new JLabel();
		id2 = new JLabel();
		stanje2 = new JLabel();
		verzija2 = new JLabel();
		ip2 = new JLabel();
		as2 = new JLabel();
		in2 = new JLabel();
		out2 = new JLabel();
		keep2 = new JLabel();
		est2 = new JLabel();
		
		
		
		
		router1.setLayout(new GridLayout(10, 1));
		router1.setBounds(50, 50, 350,450);
		router1.setBackground(Color.cyan);
		router1.add(broj1);
		router1.add(id1);
		router1.add(stanje1);
		router1.add(verzija1);
		router1.add(ip1);
		router1.add(as1);
		router1.add(in1);
		router1.add(out1);
		router1.add(keep1);
		router1.add(est1);
		
		router2 = new JPanel();
		router2.setLayout(new GridLayout(10, 1));
		router2.setBounds(150, 50, 350, 450 );
		router2.setBackground(Color.cyan);
		router2.add(broj2);
		router2.add(id2);
		router2.add(stanje2);
		router2.add(verzija2);
		router2.add(ip2);
		router2.add(as2);
		router2.add(in2);
		router2.add(out2);
		router2.add(keep2);
		router2.add(est2);
		center = new JPanel();
		//center.setLayout(new GridLayout(1,2));
		center.add(router1);
		center.add(router2);

		
		
		frame.add(center, BorderLayout.CENTER);
	}
	
	public String stanje1() {
		return bgp1.getBgpPeerState().toString();
	}
	
	public String stanje2() {
		return bgp2.getBgpPeerState().toString();
	}
	
	public void routerInfo() {
		
		
		
		
		bgp1 = router.bgpPeers.get(0);
		bgp2 = router.bgpPeers.get(1);
		
		
		
		broj1.setText("ROUTER " + bgp1.getBgpPeerRemoteAs().toString());
		
		id1.setText("ID suseda: " + bgp1.getBgpPeerIdentifier().toString());

		stanje1.setText("Stanje sesije: " + bgp1.getBgpPeerState());
		
		verzija1.setText("BGP verzija:" + bgp1.getBgpPeerNegotiatedVersion().toString());
	
		ip1.setText("IP suseda:" + bgp1.getBgpPeerRemoteAddr().toString());
		
		as1.setText("AS suseda: " + bgp1.getBgpPeerRemoteAs().toString());

		in1.setText("Updates IN: " + bgp1.getBgpPeerInUpdates());

		out1.setText("Updates OUT: " + bgp1.getBgpPeerOutUpdates().toString());
	
		keep1.setText("Keepalive: " + bgp1.getBgpPeerKeepAlive().toString());
	
		broj2.setText("ROUTER " + bgp2.getBgpPeerRemoteAs().toString());

		id2.setText("ID suseda: " + bgp2.getBgpPeerIdentifier().toString());
	
		stanje2.setText("Stanje sesije: " + bgp2.getBgpPeerState());
		
		verzija2.setText("BGP verzija:" + bgp2.getBgpPeerNegotiatedVersion().toString());

		ip2.setText("IP suseda:" + bgp2.getBgpPeerRemoteAddr().toString());
	
		as2.setText("AS suseda: " + bgp2.getBgpPeerRemoteAs().toString());
		
		in2.setText("Updates IN: " + bgp2.getBgpPeerInUpdates());
	
		out2.setText("Updates OUT: " + bgp2.getBgpPeerOutUpdates().toString());
	
		keep2.setText("Keepalive: " + bgp2.getBgpPeerKeepAlive().toString());
		
		est1.setText("Vreme od proslog update-a: "+ i +"s ago");
		est2.setText("Vreme od proslog update-a: "+ j +"s ago");
		
		//System.out.println(router.toString());
	}
	
	public void run() {
		try {
			while(!isInterrupted()) {
				router.close();
				try {
					router.initialize();
				} catch (MibParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				routerInfo();
				i=0;
				j=0;
				Thread.sleep(10000);
			}
		} catch (InterruptedException  e) {
			
		}
		
	}
	
}
