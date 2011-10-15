package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import util.SendObject;

/**
 * 
 * @author 
 */
public class RequestHandler extends Thread implements Runnable{
	
	private ObjectInputStream ois;
	private List<SendObject> tasks;
	
	public RequestHandler(Socket s, List<SendObject> tasks){
		this.tasks = tasks;
		try{
			ois = new ObjectInputStream(s.getInputStream());
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void run(){
		Object o = null;
		while(true){
	        try{
	            while((o = ois.readUnshared()) != null){
					if(o.getClass().equals(SendObject.class)){
						SendObject so = ((SendObject)(o));
						tasks.add(so);
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
		tasks.add(null);	//	send bad data to kill the listener thread
	}
}