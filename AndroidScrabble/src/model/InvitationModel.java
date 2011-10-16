package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import model.data.UserData;

import util.ResponseObject;
import util.SendObject;
import util.SendableAction;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public class InvitationModel extends Thread implements IModel{
	
	private Socket socket = null;
	private ObjectInputStream is = null;
	private ObjectOutputStream out = null;
	
	private Context context = null;
	
	public InvitationModel(Context context, Socket socket){
		this.context = context;
		this.socket = socket;

		initSocket(socket);
	}
	
	/*
	 * Initiates the socket and it's streams
	 */
	public void initSocket(Socket s){
		try {
			is = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Retrieves the answer from the server
	 */
	public void run() {
		Object o = null;
		while(true){
	        try{
	            while((o = is.readUnshared()) != null){
					if(o.getClass().equals(ResponseObject.class)){
						ResponseObject so = ((ResponseObject)(o));
						String[] req = ((String[])so.getObject());
						showInvitationDialog(req[0], req[1]);
					}
	            }
	        }
	        catch(ClassNotFoundException e){
	        	e.printStackTrace();
	        }
	        catch (IOException e){
	        	//	We arrive here when the socket is closed.
	            break;
	        }
	    }
	}
	
	public void showInvitationDialog(String message, final String opp){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface di, int arg1) {
				sendStartGameRequest(opp);
				di.dismiss();
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface di, int arg1) {
				di.dismiss();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	/*
	 * Sends a request to the server that "username" wants to login
	 */
	public void sendStartGameRequest(String username){
		try{
			SendObject object = new SendObject(SendableAction.START_GAME, username);
			
			out.writeUnshared(object);
			out.flush();
		}
		catch(IOException io){
			Log.e("error", io.getMessage());
		}
	}
	
	/*
	 * Tell the server that this is the main thread where the user always can be reached.
	 */
	public void identifyToServer() throws IOException {
		SendObject so = new SendObject(SendableAction.MAIN_THREAD, UserData.getInstance().getUsername());
		out.writeUnshared(so);
		out.flush();
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