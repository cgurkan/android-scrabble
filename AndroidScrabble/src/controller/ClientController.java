package controller;
import java.io.IOException;

import java.net.ServerSocket;
import java.util.Observable;
import java.util.Observer;

import network.ClientOutputThread;
//TODO: need library

/**
 * Class that represents a "Server".
 * The server holds a socket which the phones connect to.
 * 
 * The server starts a new Thread (ServerController) which listens to the sockets,
 * and take care of all info. The ServerController also makes sure that the server
 * listens to the models that it uses.
 * 
 * @author Magnus
 */
public class ClientController implements Observer{

	private ClientOutputThread out = null;	//	The Thread that waits for output
	
	public ClientController(ServerSocket s){
		out = new ClientOutputThread(s);
	}

	public void redirect(SendObject so){
		switch(so.getAction()){
			case LOGIN:
				//TODO: do message and login
				break;
			case LOGOUT:
				//TODO: do message and logout
				break;
			case SEARCH_PLAYER:
				//TODO: show search result
				break;
			case SIGN_UP:
				//TODO: do message and login(?) 
				break;
			case GAME_DATA:
				//TODO: do update board
				break;
			case MESSAGE:
				//TODO: print message
				break;
			default:
				break;
		}
	}
	
	/**
	 * Send back data to phone here.
	 */
	public void update(Observable obs, Object obj) {
		System.out.println("Client retrieved "+obj.toString()+" from "+obs.toString());
	}
}