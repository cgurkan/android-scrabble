package controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Observable;
import java.util.Observer;

import network.ServerInputThread;

import util.ServerUtils;


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
public class ServerController implements Observer{

	private ServerInputThread controller = null;	//	The Thread that waits for input
	private ServerSocket serverSocket;	//	The socket that the phones connect to
	private int port;	//	server port
	
	/**
	 * Creates new server from port p.
	 * 
	 * @param p The server port.
	 */
	public ServerController(int p){
		port = p;
		try{
			serverSocket = new ServerSocket(port);
			controller = new ServerInputThread(this, serverSocket);
		}
		catch(IOException e){
			System.err.println("ERROR: SERVER CAN'T BE STARTED: "+e.getMessage());
		}
	}
	
	/**
	 * Starts the server.
	 */
	public void launch(){
		controller.start();
		
		System.out.println("Testserver online!");
		System.out.println("Server is running on online ip: "+getIp());
		System.out.println("Server is running on local ip: "+getLocalIp());
		System.out.println("Server port is: "+getPort());
	}
	
	/**
	 * Server port.
	 * 
	 * @return
	 */
	public int getPort(){
		return port;
	}
	
	/**
	 * Online ip.
	 * 
	 * @return
	 */
	public String getIp(){
		return ServerUtils.getIp();
	}
	
	/**
	 * Local ip
	 * 
	 * @return
	 */
	public String getLocalIp(){
		return ServerUtils.getLocalIp();
	}

	/**
	 * Send back data to phone here.
	 */
	public void update(Observable obs, Object obj) {
		System.out.println("Server retrieved "+obj.toString()+" from "+obs.toString());
	}
}