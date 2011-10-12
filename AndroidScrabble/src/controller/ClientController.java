package controller;

import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import util.SendObject;

import network.ClientThread;
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

	private ClientThread thread = null;	//	The Thread that waits for output
	
	//TODO: maybe weird here?
	public ClientController(ClientController c, Socket s){
		thread = new ClientThread(c,s);
	}

	public synchronized void redirect(SendObject so){
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
			default:
				break;
		}
	}
	
	/**
	 * Send back data to phone here.
	 */
	public synchronized void update(Observable obs, Object obj) {
		System.out.println("Client retrieved "+obj.toString()+" from "+obs.toString());
	}
}