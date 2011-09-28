import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server-thread that listens to the ServerSocket that the server provides.
 * When a phone sends a request to the server it takes care of it and redirects
 * to the correct model.
 * 
 * @author Magnus
 */
public class ServerController extends Thread implements Runnable{

	private ServerSocket serverSocket = null;
	private GameLogic gameLogic = null;	//	The logics of the game
	private UserLogic userLogic = null;	//	The logics of the user
	
	/**
	 * Creates a new ServerController from a Server and a ServerSocket.
	 * 
	 * @param server
	 * @param sSocket
	 */
	public ServerController(Server server, ServerSocket sSocket){
		
		this.serverSocket = sSocket;
		
		gameLogic = new GameLogic();
		userLogic = new UserLogic();
		
		gameLogic.addObserver(server);
		userLogic.addObserver(server);
	}

	/**
	 * Executes the thread
	 */
	public void run(){		
		
		while(true){
			try{
				Socket socket = serverSocket.accept();
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				
				SendableAction command = ((SendableAction)(in.readUnshared()));
				Object data = in.readUnshared();
				
				switch(command){
					case LOGIN:
						String s = (String)data;
						userLogic.login(s);
						break;
					case LOGOUT:
						break;
					case SIGN_UP:
						break;
					default:
						break;
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}