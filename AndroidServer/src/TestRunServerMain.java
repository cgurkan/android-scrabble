import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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
	
	public static void sendMessage(SendObject o){
		try {
			Socket sk = new Socket(ServerUtils.getIp(), 7896);;
			ObjectOutputStream out = new ObjectOutputStream(sk.getOutputStream());
			out.writeObject(o);
			
			ObjectInputStream is = new ObjectInputStream(sk.getInputStream());
			while(true){
				InputStreamReader input = new InputStreamReader(is/*, "UTF-8"*/);
		        final int CHARS_PER_PAGE = 5000; //counting spaces
		        final char[] buffer = new char[CHARS_PER_PAGE];
		        StringBuilder output = new StringBuilder(CHARS_PER_PAGE);
		        try {
		            for(int read = input.read(buffer, 0, buffer.length);
		                    read != -1;
		                    read = input.read(buffer, 0, buffer.length)) {
		                output.append(buffer, 0, read);
		            }
		        } catch (IOException ignore) { }

		        String text = output.toString();
		        System.out.println(text);
			}
			//sk.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
