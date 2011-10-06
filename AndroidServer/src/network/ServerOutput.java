package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerOutput {

	public ServerOutput(){
		
	}
	
	public synchronized void send(Socket s, Object object) throws IOException{
		ObjectOutputStream stream = new ObjectOutputStream(s.getOutputStream());
		stream.writeObject(object);
		stream.flush();
	}
}