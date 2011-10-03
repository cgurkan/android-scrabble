import controller.ServerController;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerController s = new ServerController(1337);
		s.launch();
	}
}