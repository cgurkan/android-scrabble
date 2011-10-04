package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

import util.ErrorHandler;
import util.SendObject;

public class ServerOutputThread {
	
	private ObjectOutputStream out;
	
	public ServerOutputThread(ServerSocket s){
		try {
			out = (ObjectOutputStream)(s.accept().getOutputStream());
		}
		catch(IOException e){
			ErrorHandler.report("Cannot open ObjectOutputStream from serversocket "+s+", errors: "+e.getMessage());
		}
	}
	
	public void send(SendObject so) throws IOException{
		out.writeObject(so);
	}

}
