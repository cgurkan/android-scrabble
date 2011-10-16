package util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class UserData {

	public static Socket socket = null;
	public static String username = "";
	
	/*
	 * Initiate the socket so the server knows that the user is online
	 */
	public static void init(String serverIp){
		if(socket == null){
			try {
				socket = new Socket(serverIp, 7896);
				identifyToServer();
			}
			catch(UnknownHostException e){
				e.printStackTrace();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	/*
	 * Tell the server that this is the main thread where the user always can be reached.
	 */
	private static void identifyToServer() throws IOException {
		SendObject so = new SendObject(SendableAction.MAIN_THREAD, username);
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeUnshared(so);
		oos.flush();
	}

	/*
	 * Close the socket so the server knows that the user is offline
	 */
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
