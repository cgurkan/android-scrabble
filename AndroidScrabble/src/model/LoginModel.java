package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.util.Observable;

import android.content.Context;

import util.SendObject;
import util.SendableAction;

public class LoginModel extends Observable{
	
	private Context context = null;
	
	public static final int LOGIN_SIGN_UP = -1;
	public static final int LOGIN_NOT_OK = 0;
	public static final int LOGIN_OK = 1;
	
	public LoginModel(Context c){
		context = c;
	}
	
	public void sendLoginRequest(String username){
		SendObject retrieved = null;
		try{
			SendObject object = new SendObject(SendableAction.LOGIN, username);
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
		
		Integer returnData = evaluate(retrieved, username);
		setChanged();
		super.notifyObservers(returnData);
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
	
	private Integer evaluate(SendObject obj, String username) {

		String[] possibleOutcome = {
				"The requested username "+username+" does not exist. Please sign up!",
				username+" is already logged in on another device",
				"You are now logged in as "+username
		};
		
		for(int i = 0; i<possibleOutcome.length; i++){
			if(obj.getObject().toString().equalsIgnoreCase(possibleOutcome[i])){
				return (i-1);
			}
		}
		
		return (LOGIN_OK+1);	//error
	}
}
