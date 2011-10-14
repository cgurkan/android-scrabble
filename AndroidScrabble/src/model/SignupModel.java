package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;

import android.content.Context;

import util.ResponseObject;
import util.SendObject;
import util.SendableAction;

public class SignupModel{
	
	private Context context;
	
	public static final int SIGNUP_NOT_OK = 0;
	public static final int SIGNUP_OK = 1;
	
	public SignupModel(Context c){
		context = c;
	}
	
	public int sendLoginRequest(String username){
		ResponseObject retrieved = null;
		try{
			SendObject object = new SendObject(SendableAction.SIGN_UP, username);
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
		
		int res = evaluate(retrieved, username);
		return res;
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

	private int evaluate(ResponseObject obj, String username) {
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