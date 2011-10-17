package network;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.net.Socket;

import util.SendObject;

import controller.ClientController;
//TODO: need library

/**
 * Server-thread that listens to the ServerSocket that the server provides.
 * When a phone sends a request to the server it takes care of it and redirects
 * to the correct model.
 * 
 */
public class ClientThread extends Thread implements Runnable{

	private Socket socket = null;
	private ClientController clientController = null;
	
	/**
	 * Creates a new ServerController from a Server and a ServerSocket.
	 * 
	 * @param server
	 * @param sSocket
	 */
	public ClientThread(ClientController sc, Socket s){
		socket = s;
		clientController = sc;
	}

	/**
	 * Executes the thread
	 */
	public void run(){
		
		while(true){
			try{
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				
				SendObject so = ((SendObject)(in.readObject()));
				clientController.redirect(so);
			}
			catch(IOException e){
				System.err.println("Error in server-thread (I/O): "+e.getMessage());
			}
			catch (ClassNotFoundException e) {
				System.err.println("Error in server-thread (ClassNotFound): "+e.getMessage());
			}
		}
	}
	
	public void send(SendObject so){
		
	}
}