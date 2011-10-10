package util;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class UserData {

	public static Socket socket;
	public static String username = "";
	
	public static void init(String serverIp){
		try {
			socket = new Socket(serverIp, 7896);
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
