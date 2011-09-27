import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import util.ServerUtils;

public class Server extends Thread implements Runnable{

	private ServerSocket serverSocket;
	private int port;
	
	public Server(int p){
		port = p;
		try {
			serverSocket = new ServerSocket(port);
		}
		catch(IOException e){}
	}
	
	public void run(){
		System.out.println("Testserver online!");
		System.out.println("Server is running on online ip: "+getIp());
		System.out.println("Server is running on local ip: "+getLocalIp());
		System.out.println("Server port is: "+getPort());
		
		
		while(true){
			try{
				Socket socket = serverSocket.accept();
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				Object o = in.readUnshared();
				System.out.println(o.toString());
			}
			catch(IOException e){
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	

	public int getPort(){
		return port;
	}
	
	public String getIp(){
		return ServerUtils.getIp();
	}
	
	public String getLocalIp(){
		return ServerUtils.getLocalIp();
	}
}