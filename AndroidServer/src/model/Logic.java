package model;
import java.util.Observable;

/**
 * Class that is extended by all logic-classes on the server.
 * The Logic-class handles stuff like the database and makes sure
 * that every logic-class is Observable.
 * 
 * @author Magnus
 */
public abstract class Logic extends Observable{

	private Database db;	//	the database that all logic-classes work against
	
	protected Logic(){
		db = new Database();	//	Open db.
	}

	public Database getDatabase(){
		return db;
	}
}