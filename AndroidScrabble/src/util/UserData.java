package util;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class UserData {

	public static Socket socket = null;
	public static String username = "";
	
	public static void init(String serverIp){
		if(socket == null){
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

	public static void killSocket() {
		if(socket != null){
			if(!socket.isClosed()){
				try {
					socket.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
				socket = null;
			}
		}
	}
}
