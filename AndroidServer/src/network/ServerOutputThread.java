package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import util.ErrorHandler;
import util.SendObject;

public class ServerOutputThread {

	public ServerOutputThread(){
		
	}
	
	public synchronized void send(Socket s, Object object){
		ObjectOutputStream stream = null;
		try{
			stream = ((ObjectOutputStream)(s.getOutputStream()));
			stream.writeObject(object);
		}
		catch(IOException e){
			ErrorHandler.report("ServerOutputThread#send error: "+e.getMessage());
		}
	}
}
