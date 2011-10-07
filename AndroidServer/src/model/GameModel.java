package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.data.Board;

import util.ErrorHandler;

/**
 * A model class that describes all states and game specific details for one game.
 * 
 * @author Marika
 *
 */
public class GameModel extends Logic implements IGame{
	
	private Database db = null;
	private int lettersLeft = 300;
	
	/*allt ett spel behöver samt vilket state det är i* (all data som rör det specifika spelet)*/
	/*En specifikt spelklass med metoder som gäller ETT spel*/
	
	public GameModel(){
		db = getDatabase();	//	Get the inherited database.
	}
	
	public final class GameConstats{
		public static final int boardWidth = 16;
		public static final int boardHeight = 16;
	}

	@Override
	public Board createBoard(String host, String opp) {
		Board board = new Board();
		return board;
	}
	
	@Override
	public void startGame(String host, String opp) {
		String insGame = "INSERT INTO game VALUES(NULL, '"+host+"', '"+opp+"')";
		
		try{
			db.execUpdate(insGame);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Character> generateLetters(int i) {
		List<Character> letters = new ArrayList<Character>();
		String query = null;
		
		if(lettersLeft > i){
			query = "SELECT char FROM english ORDER BY RAND() LIMIT 'i'";
		}
		else if(lettersLeft != 0){
			query = "SELECT char FROM english ORDER BY RAND() LIMIT 'lettersLeft'";
		}
		else{
			//TODO: when lettersLeft = 0, how do we check the winner? Can the players play with own letters?
		}
		
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

	@Override
	public int receivePoints(String s) {
		int letters = s.length();
		int points = 0;
		
		while(letters >= 0){
			String query = "SELECT points FROM english WHERE char = '" + s.charAt(letters) + "' LIMIT 1";
			
			try {
				ResultSet set = db.execQuery(query);
				points += Integer.parseInt(set.getString("points"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			letters--;
		}
		
		return points;
	}	
}
