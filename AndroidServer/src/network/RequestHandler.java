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
	
	public RequestHandler(Socket s, List<SendObject> t){
		tasks = t;
		try{
			ois = new ObjectInputStream(s.getInputStream());
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while(true){
			try{
				SendObject so = ((SendObject)(ois.readUnshared()));
				tasks.add(so);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}
	}
}