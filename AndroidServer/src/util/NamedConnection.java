package util;

import java.net.Socket;

public class NamedConnection {

	private String name;
	private Socket socket;
	
	public NamedConnection(String name, Socket socket){
		this.name = name;
		this.socket = socket;
	}

	public String getName() {
		return name;
	}

	public Socket getSocket() {
		return socket;
	}
	
	public void setName(String n){
		name = n;
	}
}
