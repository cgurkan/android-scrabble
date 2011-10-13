package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.data.Board;

import util.ErrorHandler;
import util.WordObject;

/**
 * A model class that describes all states and game specific details for one game.
 * 
 * @author Marika
 *
 */
public class GameModel extends Logic implements IGame{
	
	private Database db = null;
	private int lettersLeft = 300;
	private int pass = 0;
	private int gameID = 0;
	private String turn = null;
	private Player p1 = null;
	private Player p2 = null;
	
	
	public GameModel(String name1, String name2, int gameID){
		super();
		db = getDatabase();	//	Get the inherited database.
		p1 = new Player(name1);
		p2 = new Player(name2);
		this.gameID = gameID;
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
	public void startGame() {
		//TODO: give the turn to one of the players
		changeTurn();
		
		String insGame = "INSERT INTO game VALUES(NULL, '"+p1.getUsername()+"', '"+p2.getUsername()+"')";
		
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
			if(turn == p1.getUsername()){
				p1.setLetters(7);
			}
			else if(turn == p2.getUsername()){
				p2.setLetters(7);
			}
		}
		else if(lettersLeft != 0){
			query = "SELECT char FROM english ORDER BY RAND() LIMIT 'lettersLeft'";
			if(turn == p1.getUsername()){
				p1.setLetters(p1.getLetters()-i+lettersLeft);
			}
			else if(turn == p2.getUsername()){
				p2.setLetters(p2.getLetters()-i+lettersLeft);
			}
		}
		else{
			if(p1.getLetters() == 0 || p2.getLetters() == 0){
				endGame();
				return null;
			}
			else if(i == 0){
				pass++;
			}
			
			if(pass == 4){
				return null;
			}
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
		pass = 0;
		
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
	
	public void changeTurn(){
		if(turn == p1.getUsername()){
			turn = p2.getUsername();
		}
		else{
			turn = p1.getUsername();
		}
	}
	
	public void pass(){
		pass++;
		changeTurn();
	}
	
	public int endGame(){
		//TODO: implement this
		return 0;
	}

	public void placeWord(WordObject word) {
		//TODO: save characters to the board, generate letters, receive point and change turn
		
	}
}