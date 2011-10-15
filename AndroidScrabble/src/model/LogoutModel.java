package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;

import util.ResponseObject;
import util.SendObject;
import util.SendableAction;
import android.content.Context;

public class LogoutModel{

	private Context context = null;
	
	public LogoutModel(Context c){
		context = c;
	}
	
	public String sendLogoutRequest(String username){
		ResponseObject retrieved = null;
		try{
			SendObject object = new SendObject(SendableAction.LOGOUT, username);
			Socket s = new Socket(context.getString(android.scrabble.R.string.serverip), 7896);
			
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.writeUnshared(object);
			out.flush();
			s.setSoTimeout(10000);	//	wait max 10 seconds to get response
			
			retrieved = getServerAnswer(s);
			s.close();
		}
		catch(IOException io){
			io.printStackTrace();
		}
		
		return retrieved.getObject().toString();
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