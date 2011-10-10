package controller;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import network.ServerOutput;

import util.ErrorHandler;
import util.SendObject;


/**
 * Class that represents a "Server".
 * The server holds a objectsocket which the phones connect to.
 * 
 * The server starts a new Thread (ServerController) which listens to the objectsockets,
 * and take care of all info. The ServerController alobject makes sure that the server
 * listens to the models that it uses.
 * 
 * @author 
 */
public class RequestHandler extends Thread implements Runnable{

	private ServerOutput output = null;	//	The Thread that waits for output
	private Socket socket;
	private SendObject object;
	
	private Model m = null;
	
	public RequestHandler(Socket s, SendObject obj){
		m = new Model();
		
		output = new ServerOutput();
		object = obj;
		socket = s;
	}


	@Override
	public void run() {
		int i = 0;
		switch(object.getAction()){
		case LOGIN:
			String s = (String)object.getObject();
			m.login(s);
			break;
		case LOGOUT:
			String s2 = (String)object.getObject();
			m.logout(s2);
			break;
		case SEARCH_PLAYER:
			break;
		case SIGN_UP:
			String s3 = (String)object.getObject();
			m.signUp(s3);
			break;
		case PLACE_WORD:
			m.placeWord(i);
			break;
		case START_GAME:
			m.startGame();
			break;
		case QUIT_GAME:
			m.guitGame(i);
			break;
		case PASS:
			m.pass(i);
			break;
		case SWAP:
			m.swap(i);
			break;
		default:
			break;
		}
		
		/*All enums have to return a value that will be saved and at the end of run send the return to serverOutput*/
	}
	
	/**
	 * Send back data to phone here.
	 */
	public synchronized void update(Observable obs, Object obj) {
		//System.out.println("Server retrieved "+obj.toString()+" from "+obs.toString());
		//SendObject object = (SendObject)obj;
		try {
			output.send(socket, obj);
		}
		catch(IOException e){
			ErrorHandler.report("Server controller cant send object: "+e.getMessage());
		}
	}
}