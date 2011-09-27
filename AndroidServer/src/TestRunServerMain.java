

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import util.ServerUtils;

public class TestRunServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = new Server(1337);
		//String s = "ESTABLISH";
		
		//sendMessage(server, s);
		
		String s = "SELECT * FROM test";
		sendMessage(server, s);
		
		//s = "INSERT INTO test VALUES(NULL, 'woop woop')";
		sendMessage(server, s);
	}

	
	public static Socket socketFromServer(Server s){
		Socket sk = null;
		try {
			sk = new Socket(s.getIp(), s.getPort());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sk;
	}
	
	public static void sendMessage(Server s, String m){
		try {	
			Socket sk = socketFromServer(s);
			ObjectOutputStream out = new ObjectOutputStream(sk.getOutputStream());
			out.writeUnshared(m);
			sk.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
