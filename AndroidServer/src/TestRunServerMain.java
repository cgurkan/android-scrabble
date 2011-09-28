import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import util.SendableAction;
import util.ServerUtils;

public class TestRunServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		sendMessage(SendableAction.LOGIN, "µ");
	}

	
	public static Socket socketFromServer(){
		Socket sk = null;
		try {
			sk = new Socket(ServerUtils.getIp(), 1337);
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return sk;
	}
	
	public static void sendMessage(SendableAction cmd, String data){
		try {
			Socket sk = socketFromServer();
			ObjectOutputStream out = new ObjectOutputStream(sk.getOutputStream());
			out.writeUnshared(cmd);
			out.writeUnshared(data);
			sk.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
