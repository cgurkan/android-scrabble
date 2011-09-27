public class DatabaseNotConnectedException extends Exception {

	private static final long serialVersionUID = 2554292539638134869L;

	public DatabaseNotConnectedException(){
	}

	public DatabaseNotConnectedException(String message) {
		super(message);
	}

	public DatabaseNotConnectedException(Throwable t) {
		super(t);
	}

	public DatabaseNotConnectedException(String message, Throwable t) {
		super(message, t);
	}

}
