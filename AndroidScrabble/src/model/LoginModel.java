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

public class LoginModel{
	
	private Context context = null;
	private Socket socket = null;
	private ObjectInputStream is = null;
	private ObjectOutputStream out = null;
	
	public static final int LOGIN_SIGN_UP = -1;
	public static final int LOGIN_NOT_OK = 0;
	public static final int LOGIN_OK = 1;
	
	public LoginModel(Context c){
		context = c;

		initSocket();
	}
	
	public void initSocket(){
		try {
			socket = new Socket(context.getString(R.string.serverip), 7896);
			
			is = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public int sendLoginRequest(String username){
		ResponseObject retrieved = null;
		try{
			SendObject object = new SendObject(SendableAction.LOGIN, username);
			
			out.writeUnshared(object);
			out.flush();
			
			if(socket.getSoTimeout() == 0){
				socket.setSoTimeout(10000);	//	wait max 10 seconds to get response
			}
			
			retrieved = getServerAnswer();
		}
		catch(IOException io){
			Log.e("error", io.getMessage());
		}
		Log.d("BAJS", "SOCKETJÄVLEN ÄR STÄNGD GODDAMNIT:"+socket.isClosed());
		int returnData = evaluate(retrieved, username);
		return returnData;
	}
	
	private ResponseObject getServerAnswer() {
		ResponseObject data = null;
		try {
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
	
	public void dispose(){
		try {
			socket.close();
			Log.d("dödats?", socket.isClosed()+" "+socket);
		}
		catch(IOException e){
			Log.e("Socket close", e.getMessage());
		}
		socket = null;
	}
}