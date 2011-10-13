package controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import network.RequestHandler;
import model.Model;

import util.ErrorHandler;
import util.ListListener;
import util.ListenableList;
import util.NamedConnection;
import util.OnlineList;
import util.ResponseObject;
import util.SendObject;
import util.SendableAction;

public class WorkThread extends Thread implements ListListener{

	private ObjectOutputStream oos = null;
	private ListenableList<SendObject> tasks;
	private Model model = null;
	private Socket socket;
	
	public WorkThread(Socket socket) throws IOException{
		oos = new ObjectOutputStream(socket.getOutputStream());
		
		tasks = new ListenableList<SendObject>();
		tasks.addListListener(this);
		
		model = new Model();
		
		this.socket = socket;
		
		new RequestHandler(socket, tasks).start();
	}
	
	public void run(){
		while(true){
			try {
				while(tasks.size() == 0){
					synchronized(this){
						wait();
					}
				}
				
				work(tasks.get(0));
				tasks.remove(0);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public void work(SendObject object){
		
		ResponseObject data = null;
		int i = 0;
		
		switch(object.getAction()){
		case LOGIN:
			String s = (String)object.getObject();
			data = model.login(s);
			break;
		case LOGOUT:
			String s2 = (String)object.getObject();
			data = model.logout(s2);
			break;
		case PLAYERS_ONLINE:
			data = model.getPlayersOnline();
			break;
		case SEARCH_PLAYER:
			break;
		case SIGN_UP:
			String s3 = (String)object.getObject();
			data = model.signUp(s3);
			break;
		case PLACE_WORD:
			//model.placeWord(i);
			break;
		case START_GAME:
			//model.startGame();
			break;
		case QUIT_GAME:
			model.guitGame(i);
			break;
		case PASS:
			model.pass(i);
			break;
		case SWAP:
			//model.swap(i);
			break;
		default:
			break;
		}

		boolean login = (object.getAction().equals(SendableAction.LOGIN) && data.getObject().toString().startsWith("You are now logged in as "+object.getObject().toString()));
		boolean signup = (object.getAction().equals(SendableAction.SIGN_UP) && data.getObject().toString().startsWith("You are now signed up, welcome "+object.getObject().toString()));
		boolean logout = (object.getAction().equals(SendableAction.LOGOUT) && data.getObject().toString().startsWith("You are now logged out"));
		
		if(login || signup){
			OnlineList.getInstance().add(new NamedConnection(object.getObject().toString(), socket));
		}
		else if(logout){
			OnlineList.getInstance().remove(object.getObject().toString(), socket);
		}
		
		send(data);
	}
	
	public void send(Object object){
		try {
			oos.writeUnshared(object);
			oos.flush();
		}
		catch(IOException e){
			ErrorHandler.report("Error sending from WorkThread :"+e.getMessage());
		}
	}

	@Override
	public void listChanged() {
		synchronized(this){
			notifyAll();
		}
	}
}
