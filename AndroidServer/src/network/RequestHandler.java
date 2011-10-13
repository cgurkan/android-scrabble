package network;
import java.io.EOFException;
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
	private Socket socket;
	
	public RequestHandler(Socket s, List<SendObject> t2){
		socket = s;
		tasks = t2;
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
				if(socket.isConnected()){
					Object o = ois.readUnshared();
					System.out.println(o.toString());
					if(o.getClass().equals(SendObject.class)){
						SendObject so = ((SendObject)(o));
						tasks.add(so);
					}
				}
			}
			catch(EOFException e){
				/*
				 * This will happen every time it waits for input.
				 * That is not an error, this is the way java tells
				 * the system that the stream is empty.
				 */
				try{
					sleep(500);
				}
				catch(InterruptedException e1){
					e1.printStackTrace();
				}
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