package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

import util.ErrorHandler;
import util.SendObject;

public class ServerOutputThread {
	
	private ObjectOutputStream out;
	
	public ServerOutputThread(ServerSocket s){
		
	}
	
	public void send(SendObject so) throws IOException{
		out.writeObject(so);
	}

}
