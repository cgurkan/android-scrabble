package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.util.Observable;

import android.content.Context;

import util.ResponseObject;
import util.SendableAction;

public class SignupModel extends Observable{
	
	private Context context;
	
	public static final int SIGNUP_NOT_OK = 0;
	public static final int SIGNUP_OK = 1;
	
	public SignupModel(Context c){
		context = c;
	}
	
	public void sendLoginRequest(String username){
		ResponseObject retrieved = null;
		try{
			ResponseObject object = new ResponseObject(SendableAction.SIGN_UP, username);
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
		
		Integer res = evaluate(retrieved, username);
		setChanged();
		super.notifyObservers(res);
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

	private Integer evaluate(ResponseObject obj, String username) {
		String[] possibleOutcome = {
				"Sorry, the username "+username+" is already taken. Please choose another one.",
				"You are now signed up, welcome "+username
		};

		for(int i = 0; i<possibleOutcome.length; i++){
			if(obj.getObject().toString().equalsIgnoreCase(possibleOutcome[i])){
				return i;
			}
		}
		
		return (SIGNUP_OK+1);	//error
	}
}