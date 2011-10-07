package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ErrorHandler;
import util.WordObject;

import model.data.Board;

/**
 * A logic class that describes all general game states and modifiers.
 * 
 * @author Marika
 *
 */
public class GameLogic extends Logic{

	/*En generell spelklass med metoder som gäller ALLA spel*/
	
	private Database db = null;
	
	public GameLogic(){
		super();
		db = getDatabase();	//	Get the inherited database.
	}
	
	public boolean checkWord(WordObject o){
		String query = "SELECT * FROM words WHERE word = 'o.getWord()' LIMIT 1";
		boolean result = false;
		boolean notDone = true;
		
		while(notDone){
			try {
				ResultSet set = db.execQuery(query);
				if(set.next()){
					result = true;
				}
				notDone = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
