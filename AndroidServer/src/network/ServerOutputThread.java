package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerOutputThread {

	public ServerOutputThread(){
		
	}
	
	public synchronized void send(Socket s, Object object) throws IOException{
		ObjectOutputStream stream = null;
		stream = ((ObjectOutputStream)(s.getOutputStream()));
		stream.writeObject(object);
	}
}
