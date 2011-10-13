package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.util.Observable;

import android.content.Context;

import util.ResponseObject;
import util.SendObject;
import util.SendableAction;

public class POnlineModel extends Observable{
	
	private Context context;
	
	public POnlineModel(Context c){
		context = c;
	}
	
	public void sendOnlineRequest(){
		ResponseObject retrieved = null;
		try{
			SendObject object = new SendObject(SendableAction.PLAYERS_ONLINE, null);
			Socket s = new Socket(context.getString(android.scrabble.R.string.serverip), 7896);
			
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.writeUnshared(object);
			
			s.setSoTimeout(10000);	//	max time to wait for response, 10 secs
			
			retrieved = getServerAnswer(s);
			s.close();
		}
		catch(IOException io){
			io.printStackTrace();
		}
		
		setChanged();
		super.notifyObservers(retrieved);
	}

	private ResponseObject getServerAnswer(Socket s) {
		ResponseObject data = null;
		try {
			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			
			do{
				data = (ResponseObject)is.readUnshared();
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