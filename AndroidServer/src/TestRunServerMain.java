import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import util.SendObject;
import util.SendableAction;
import util.ServerUtils;

public class TestRunServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		sendMessage(new SendObject(SendableAction.SIGN_UP, "Micro"));
	}
	
	public static void sendMessage(SendObject o){
		try {
			Socket sk = new Socket(ServerUtils.getIp(), 7896);
			ObjectOutputStream out = new ObjectOutputStream(sk.getOutputStream());
			out.writeObject(o);
			
			out.flush();
			
			ObjectInputStream is = new ObjectInputStream(sk.getInputStream());
			while(true){
				try {
					Object to = is.readObject();
					if(to != null){
						System.out.println(to.toString());
						break;
					}
				}
				catch(ClassNotFoundException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			sk.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
