import controller.Server;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Server s = new Server(1337);
		s.launch();
	}
}