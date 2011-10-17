package controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import android.scrabble.LoginActivity;
import android.scrabble.LogoutActivity;
import android.util.Log;

import util.SendObject;

import network.ClientOutput;

/**
 * Class that represents a "Server".
 * The server holds a socket which the phones connect to.
 * 
 * The server starts a new Thread (ServerController) which listens to the sockets,
 * and take care of all info. The ServerController also makes sure that the server
 * listens to the models that it uses.
 * 
 */
public class ClientController implements Observer{

	private ClientOutput co = null;
	private Socket s = null;
	private int port = 7896;
	
	public ClientController(String serverip){
		try {
			s = new Socket(serverip, port);
		}
		catch(UnknownHostException e){
			Log.e("socket e", e.getMessage());
		}
		catch(IOException e){
			Log.e("socket e", e.getMessage());
		}
		co = new ClientOutput(s);
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
			case MESSAGE:
				break;
			case PLAYERS_ONLINE:
				break;
			case PLACE_WORD:
				break;
			case START_GAME:
				break;
			case QUIT_GAME:
				break;
			case PASS:
				break;
			case SWAP:
				break;
			case GET_DICTIONARIES:
				break;
			case OPPONENT_DATA:
				break;
			case MAIN_THREAD:
				break;
			case INVITE_GAME:
				break;
			default:
				break;
		}
	}
	
	public Socket getSocket(){
		return s;
	}
	
	/**
	 * Send back data to phone here.
	 */
	public synchronized void update(Observable obs, Object obj) {
		System.out.println("Client retrieved "+obj.toString()+" from "+obs.toString());
	}
}