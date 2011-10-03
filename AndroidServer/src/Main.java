import java.io.IOException;
import java.net.ServerSocket;

import network.ServerInputThread;

import controller.ServerController;


public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket s = null;
		try {
			s = new ServerSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServerController sc = new ServerController(s);
		ServerInputThread st = new ServerInputThread(sc, s);
		st.start();
	}
}