import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Reader;
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
		sendMessage(new SendObject(SendableAction.LOGIN, "Micro"));
	}
	
	public static Socket socketFromServer(){
		Socket sk = null;
		try {
			sk = new Socket(ServerUtils.getIp(), 7896);
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return sk;
	}
	
	public static void sendMessage(SendObject o){
		try {
			Socket sk = socketFromServer();
			ObjectOutputStream out = new ObjectOutputStream(sk.getOutputStream());
			out.writeObject(o);
			sk.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
