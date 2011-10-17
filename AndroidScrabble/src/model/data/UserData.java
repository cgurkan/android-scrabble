package model.data;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;

import model.InvitationModel;

public class UserData {

	public static UserData userData = null;
	public InvitationModel invModel = null;
	
	private Socket socket = null;
	private String username = "";
	
	private UserData(){
	}
	
	public static UserData getInstance(){
		if(userData == null){
			userData = new UserData();
		}
		return userData;
	}
	
	/*
	 * Initiate the socket so the server knows that the user is online
	 */
	public void init(Context c, String serverIp){
		//	create socket
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
		
		//	create model
		invModel = new InvitationModel(c, socket);
		
		//	tell the server that this is the main listener-thread
		try{
			invModel.identifyToServer();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		invModel.start();	// start waiting for game requests
	}

	/*
	 * Close the socket so the server knows that the user is offline
	 */
	public void killSocket() {
		if(socket != null){
			if(!socket.isClosed()){
				try {
					socket.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
				socket = null;
				//invModel.dispose();
				invModel = null;
			}
		}
	}

	public void setUsername(String uName) {
		username = uName;
	}

	public String getUsername() {
		return username;
	}

	public Socket getSocket() {
		return socket;
	}
}