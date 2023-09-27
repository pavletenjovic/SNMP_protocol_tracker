package main;

import java.io.IOException;

import com.ireasoning.util.MibParseException;

public class Timer extends Thread{

	private App app;
//	private String p1,p2,p3,p4;
//	
	public Timer(App a) {
		this.app = a;
	}
	public void run() {
		try {
			while(!isInterrupted()) {
				//p1 = app.stanje1();
				//p2 = app.stanje2();
				//app.router.close();
//				
//				try {
//					app.router.initialize();
//				} catch (MibParseException | IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				App.i+=1;
				App.j+=1;
				app.routerInfo();
//				p3 = app.stanje1();
//				p4 = app.stanje2();
//				if(p1!=p3 || p2!=p4) {
//					App.i = 0;
//					App.j = 0;
				//}
				Thread.sleep(1000);
			}
		} catch (InterruptedException  e) {
			
		}
		
	}
}
