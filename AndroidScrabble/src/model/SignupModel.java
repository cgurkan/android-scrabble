package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.util.Observable;

import util.SendObject;
import util.SendableAction;

public class SignupModel extends Observable{
	
	public SignupModel(){
		
	}
	
	public void sendLoginRequest(String username){
		SendObject retrieved = null;
		try{
			SendObject object = new SendObject(SendableAction.SIGN_UP, username);
			Socket s = new Socket("46.239.108.240", 7896);
			
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.writeUnshared(object);
			
			s.setSoTimeout(10000);
			
			retrieved = getServerAnswer(s);
			s.close();
		}
		catch(IOException io){
			io.printStackTrace();
		}
		
		setChanged();
		super.notifyObservers(retrieved);
	}

	private SendObject getServerAnswer(Socket s) {
		SendObject data = null;
		try {
			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			
			do{
				data = (SendObject)is.readUnshared();
			}while(data == null);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(StreamCorruptedException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return data;
	}
}