package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import controller.ServerController;

import util.ErrorHandler;
import util.SendObject;
import util.SendableAction;

import model.GameLogic;
import model.UserLogic;

/**
 * Server-thread that listens to the ServerSocket that the server provides.
 * When a phone sends a request to the server it takes care of it and redirects
 * to the correct model.
 * 
 * @author random dude
 */
public class ServerInputThread extends Thread implements Runnable{

	private ServerSocket serverSocket = null;
	private ServerController serverController = null;
	
	/**
	 * Creates a new ServerController from a Server and a ServerSocket.
	 * 
	 * @param server
	 * @param sSocket
	 */
	public ServerInputThread(ServerController sc, ServerSocket s){
		this.serverSocket = s;
		serverController = sc;
	}

	/**
	 * Executes the thread
	 */
	public void run(){		
		
		while(true){
			try{
				Socket socket = serverSocket.accept();
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				
				SendObject so = ((SendObject)(in.readObject()));
				serverController.redirect(so);
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