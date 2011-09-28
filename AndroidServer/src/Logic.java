import java.util.Observable;

public abstract class Logic extends Observable {

	private Database db;
	
	protected Logic(){
		db = new Database();
	}

	public Database getDatabase(){
		return db;
	}
}
