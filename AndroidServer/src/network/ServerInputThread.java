package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import controller.ServerController;

import util.ErrorHandler;
import util.SendableAction;

import model.GameLogic;
import model.UserLogic;

/**
 * Server-thread that listens to the ServerSocket that the server provides.
 * When a phone sends a request to the server it takes care of it and redirects
 * to the correct model.
 * 
 * @author Magnus
 */
public class ServerInputThread extends Thread implements Runnable{

	private ServerSocket serverSocket = null;
	private GameLogic gameLogic = null;	//	The logics of the game
	private UserLogic userLogic = null;	//	The logics of the user
	
	/**
	 * Creates a new ServerController from a Server and a ServerSocket.
	 * 
	 * @param server
	 * @param sSocket
	 */
	public ServerInputThread(ServerController server, ServerSocket sSocket){
		
		this.serverSocket = sSocket;
		
		gameLogic = new GameLogic();
		userLogic = new UserLogic();
		
		gameLogic.addObserver(server);
		userLogic.addObserver(server);
	}

	/**
	 * Executes the thread
	 */
	public void run(){		
		
		while(true){
			try{
				Socket socket = serverSocket.accept();
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				
				SendableAction command = ((SendableAction)(in.readUnshared()));
				Object data = in.readUnshared();
				
				switch(command){
					case LOGIN:
						String s = (String)data;
						userLogic.login(s);
						break;
					case LOGOUT:
						String s2 = (String)data;
						userLogic.logout(s2);
						break;
					case SEARCH_PLAYER:
						break;
					case SIGN_UP:
						String s3 = (String)data;
						userLogic.signUp(s3);
						break;
					default:
						break;
				}
			}
			catch(IOException e){
				ErrorHandler.report("Error in server-thread (I/O): "+e.getMessage());
			}
			catch (ClassNotFoundException e) {
				ErrorHandler.report("Error in server-thread (ClassNotFound): "+e.getMessage());
			}
		}
	}
}