package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ErrorHandler;

import model.data.Board;

public class GameLogic extends Logic{

	private Database db = null;
	
	public GameLogic(){
		super();
		db = getDatabase();	//	Get the inherited database.
	}
	
	public final class GameConstats{
		public static final int boardWidth = 16;
		public static final int boardHeight = 16;
	}
	
	public void startGame(String host, String opp){
		String insGame = "INSERT INTO game VALUES(NULL, '"+host+"', '"+opp+"')";
		
		try{
			db.execUpdate(insGame);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public Board createBoard(String host, String opp){
		
		Board board = new Board();
		
		return board;
	}
	
	public List<Character> generateLetters(int i){
		
		List<Character> letters = new ArrayList<Character>();
		String query = "SELECT * FROM english ORDER BY RAND() LIMIT 'i'";
		
		try {
			ResultSet set = db.execQuery(query);
			
			while(set.next()){
				letters.add(set.getString("letter").charAt(0));
			}
			
		} catch (SQLException e) {
			ErrorHandler.report("The following SQL-error(s) occured while trying to log in GameLogic#generateLetters(): "+ e.getMessage());
		}
		
		return letters;
	}
}
