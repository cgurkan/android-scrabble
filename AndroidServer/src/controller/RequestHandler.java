package controller;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import model.GameLogic;
import model.UserLogic;
import network.ServerOutputThread;

import util.SendObject;


/**
 * Class that represents a "Server".
 * The server holds a objectcket which the phones connect to.
 * 
 * The server starts a new Thread (ServerController) which listens to the objectckets,
 * and take care of all info. The ServerController alobject makes sure that the server
 * listens to the models that it uses.
 * 
 * @author Magnus
 */
public class RequestHandler extends Thread implements Runnable, Observer{

	private ServerOutputThread output = null;	//	The Thread that waits for output
	private Socket socket;
	private SendObject object;
	
	private UserLogic ul = null;
	private GameLogic gl = null;
	
	public RequestHandler(SendObject obj, Socket s){
		ul = new UserLogic();
		ul.addObserver(this);
		gl = new GameLogic();
		gl.addObserver(this);
		
		output = new ServerOutputThread();
		object = obj;
		socket = s;
	}


	@Override
	public void run() {
		switch(object.getAction()){
		case LOGIN:
			String s = (String)object.getObject();
			ul.login(s);
			break;
		case LOGOUT:
			String s2 = (String)object.getObject();
			ul.logout(s2);
			break;
		case SEARCH_PLAYER:
			break;
		case SIGN_UP:
			String s3 = (String)object.getObject();
			ul.signUp(s3);
			break;
		/*case GAME_DATA:
			//TODO: do gamelogic
			break;*/
		default:
			break;
		}
	}
	
	/**
	 * Send back data to phone here.
	 */
	public synchronized void update(Observable obs, Object obj) {
		//System.out.println("Server retrieved "+obj.toString()+" from "+obs.toString());
		/*SendObject object = (SendObject)obj;
		try {
			out.send(object);
		}
		catch(IOException e){
			ErrorHandler.report("Server controller cant send object: "+e.getMessage());
		}*/
		output.send(socket, obj);
	}
}