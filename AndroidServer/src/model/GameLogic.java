package model;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ErrorHandler;

import model.data.Board;
import model.data.Tile;

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
	
	public Board createBoard(BigInteger gameID){
		
		Board board = new Board();
		String query = "SELECT * FROM character WHERE game_ID = 'game_ID'";
		
		try{
			ResultSet set = db.execQuery(query);
			
			while(set.next()){
				char c = set.getString("character").charAt(0);
				int x = Integer.parseInt(set.getString("x"));
				int y = Integer.parseInt(set.getString("y"));
				Tile t = new Tile(c, x, y);
				board.add(t);
			}
		}
		catch(SQLException sql){
			ErrorHandler.report("The following SQL-error(s) occured while trying to log in GameLogic#createBoard(): "+sql.getMessage());
		}
		
		return board;
	}
}
