import java.io.IOException;
import java.net.ServerSocket;

import util.ServerUtils;

import network.ServerThread;

import controller.ServerController;


public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket s = null;
		try {
			s = new ServerSocket(7896);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServerController sc = new ServerController(s);
		ServerThread st = new ServerThread(sc, s);
		st.start();
		
		System.out.println("Servern springer! Ip "+ServerUtils.getIp()+" och port "+s.getLocalPort());
	}
}