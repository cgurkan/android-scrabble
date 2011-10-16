package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.UnknownHostException;

import model.data.UserData;

import android.content.Context;
import android.scrabble.R;
import android.util.Log;

import util.ResponseObject;
import util.SendObject;
import util.SendableAction;

public class GameSettingsModel implements IModel{
	
	private Context context;
	private Socket socket = null;
	private ObjectInputStream is = null;
	private ObjectOutputStream out = null;
	
	public GameSettingsModel(Context c){
		context = c;

		initSocket();
	}
	
	/*
	 * Initiates the socket and it's streams
	 */
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
	
	/*
	 * Sends a request to the server with the specified object
	 */
	public ResponseObject getData(SendObject object, boolean getAnswer){
		ResponseObject retrieved = null;
		try{
			out.writeUnshared(object);
			out.flush();
			if(socket.getSoTimeout() == 0){
				socket.setSoTimeout(4000);	//	max time to wait for response, 4 secs
			}
			
			if(getAnswer){
				retrieved = getServerAnswer();
			}
		}
		catch(IOException io){
			io.printStackTrace();
		}
		
		return retrieved;
	}
	
	/*
	 * Sends a request to the server to get the list of people online
	 */
	public ResponseObject getPeopleOnline(){
		SendObject object = new SendObject(SendableAction.PLAYERS_ONLINE, null);
		return getData(object, true);
	}
	
	/*
	 * Sends a request to the server to get all available dictionaries
	 */
	public ResponseObject getDictionaries(){
		SendObject object = new SendObject(SendableAction.GET_DICTIONARIES, null);
		return getData(object, true);
	}
	
	public void sendInviteRequest(String opponent){
		SendObject object = new SendObject(SendableAction.INVITE_GAME, new String[]{UserData.getInstance().getUsername(), opponent});
		getData(object, false);
	}

	/*
	 * Retrieves the answer from the server
	 */
	private ResponseObject getServerAnswer() {
		ResponseObject data = null;
		try {
			do{	//	wait until the data is read. must take less than socket.getSoTimeout() secs.
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
	
	@Override
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