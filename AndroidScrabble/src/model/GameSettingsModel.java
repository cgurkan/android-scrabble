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

public class GameSettingsModel{
	
	private Context context;
	private Socket socket = null;
	private ObjectInputStream is = null;
	private ObjectOutputStream out = null;
	
	public GameSettingsModel(Context c){
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
	
	public ResponseObject getData(SendObject object){
		ResponseObject retrieved = null;
		try{
			out.writeUnshared(object);
			out.flush();
			if(socket.getSoTimeout() == 0){
				socket.setSoTimeout(10000);	//	max time to wait for response, 10 secs
			}
			
			retrieved = getServerAnswer();
		}
		catch(IOException io){
			io.printStackTrace();
		}
		
		return retrieved;
	}
	
	public ResponseObject getPeopleOnline(){
		SendObject object = new SendObject(SendableAction.PLAYERS_ONLINE, null);
		return getData(object);
	}
	
	public ResponseObject getDictionaries(){
		SendObject object = new SendObject(SendableAction.GET_DICTIONARIES, null);
		return getData(object);
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