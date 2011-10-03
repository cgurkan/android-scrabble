import java.io.IOException;
import java.net.ServerSocket;

import util.ServerUtils;

import network.ServerInputThread;

import controller.ServerController;


public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket s = null;
		try {
			s = new ServerSocket(256);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServerController sc = new ServerController(s);
		ServerInputThread st = new ServerInputThread(sc, s);
		st.start();
		
		System.out.println("Servern springer! Ip "+ServerUtils.getIp()+" och port "+s.getLocalPort());
	}
}