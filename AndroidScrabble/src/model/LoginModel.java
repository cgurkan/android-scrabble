package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
import android.scrabble.R;
import android.util.Log;

import util.ResponseObject;
import util.SendObject;
import util.SendableAction;

public class LoginModel implements IModel{
	
	private Context context = null;
	private Socket socket = null;
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;
	
	public static final int LOGIN_SIGN_UP = -1;
	public static final int LOGIN_NOT_OK = 0;
	public static final int LOGIN_OK = 1;
	
	public LoginModel(Context c){
		context = c;

		initSocket();
	}
	
	/*
	 * Initiates the socket and it's streams
	 */
	public void initSocket(){
		try {
			socket = new Socket(context.getString(R.string.serverip), 7896);
			
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		}
		catch(UnknownHostException e){
			Log.d("alpha", "UHE", e);
		}
		catch(IOException e){
			Log.d("alpha", "IOE", e);
		}
	}
	
	/*
	 * Sends a request to the server that "username" wants to login
	 */
	public int sendLoginRequest(String username){
		ResponseObject retrieved = null;
		try{
			SendObject object = new SendObject(SendableAction.LOGIN, username);
			
			out.writeUnshared(object);
			out.flush();
			
			if(socket.getSoTimeout() == 0){
				socket.setSoTimeout(4000);	//	wait max 4 seconds to get response
			}
			
			retrieved = getServerAnswer();
		}
		catch(IOException io){
			Log.e("error", io.getMessage());
		}
		
		int returnData = evaluate(retrieved, username);
		return returnData;
	}
	
	/*
	 * Retrieves the answer from the server
	 */
	private ResponseObject getServerAnswer() {
		ResponseObject data = null;
		try {
			do{	//	wait until the data is read. must take less than socket.getSoTimeout() secs.
				data = (ResponseObject)in.readUnshared();
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
	
	/*
	 * Evaluates the answer from the server and returns an int that is
	 * LoginModel#LOGIN_SIGN_UP, LoginModel#LOGIN_NOT_OK, LoginModel#LOGIN_OK or an error.
	 */
	private int evaluate(ResponseObject obj, String username) {

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
	
	@Override
	public void dispose(){
		try {
			socket.close();
		}
		catch(IOException e){
			Log.e("Socket close", e.getMessage());
		}
		socket = null;
	}
}