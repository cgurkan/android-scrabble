package model;

public class GameLogic extends Logic{

	private Database db = null;
	
	public GameLogic(){
		super();
		db = getDatabase();	//	Get the inherited database.
	}
}
