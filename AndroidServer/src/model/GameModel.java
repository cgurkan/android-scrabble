package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.data.Board;

import util.ErrorHandler;
import util.Player;
import util.WordObject;
import util.WordObject.Direction;

/**
 * A model class that describes all states and game specific details for one game.
 */
public class GameModel extends Logic implements IGame{
	
	private Database db = null;
	private int lettersLeft = 300;
	private int pass = 0;
	private Board board = null;
	private Player p1 = null;
	private Player p2 = null;
	
	
	public GameModel(String name1, String name2){
		super();
		db = getDatabase();
		board = new Board();
		p1 = new Player(name1);
		p2 = new Player(name2);
	}
	
	public static final class GameConstats{
		public static final int boardWidth = 16;
		public static final int boardHeight = 16;
	}
	
	public Board getBoard(){
		return board;
	}
	
	@Override
	public void startGame() {
		if(db.startGame(p1.getUsername(), p2.getUsername())){
			p1.setTurn(true);
		}
	}

	@Override
	public void generateLetters(int i) {
		List<Character> letters = new ArrayList<Character>();
		ResultSet set = null;
		
		if(lettersLeft >= i){
			set = db.generateLetters(i);
			if(p1.isTurn()){
				p1.setNrLetters(7);
			}
			else {
				p2.setNrLetters(7);
			}
		}
		else if(lettersLeft != 0){
			set = db.generateLetters(lettersLeft);
			if(p1.isTurn()){
				p1.setNrLetters(p1.getNrLetters()-i+lettersLeft);
			}
			else {
				p2.setNrLetters(p2.getNrLetters()-i+lettersLeft);
			}
		}
		else{
			if(p1.getNrLetters() == 0 || p2.getNrLetters() == 0){
				endGame();
			}
			else{
				if(p1.isTurn()){
					p1.setNrLetters(p1.getNrLetters()-i);
				}
				else {
					p2.setNrLetters(p2.getNrLetters()-i);
				}
			}
		}
		
		try {
			while(set.next()){
				letters.add(set.getString("letter").charAt(0));
				if(p1.isTurn()){
					p1.setLetters(letters);
				}
				else {
					p2.setLetters(letters);
				}
			}
		} catch (SQLException e) {
			ErrorHandler.report("The following SQL-error(s) occured while trying to log in GameLogic#generateLetters(): "+ e.getMessage());
		}
	}

	@Override
	public Player receivePoints(String s) {
		pass = 0;
		
		if(p1.isTurn()){
			p1.addPoints(db.countPoints(s));
			return p1;
		}
		else{
			p2.addPoints(db.countPoints(s));
			return p2;
		}
	}
	
	public void changeTurn(){
		if(p1.isTurn()){
			p1.setTurn(false);
			p2.setTurn(true);
		}
		else{
			p1.setTurn(true);
			p2.setTurn(false);
		}
	}
	
	public boolean pass(){
		pass++;
		if(pass == 4){
			endGame();
			return false;
		}
		else{
			changeTurn();
			return true;
		}
	}
	
	public List<Player> endGame(){
		List<Player> p = new ArrayList<Player>();
		
		p.add(p1);
		p.add(p2);
		
		return p;
	}

	public void placeWord(WordObject word) {
		String w = word.getWord();
		int size = w.length();
		int iterator = 0;
		int x = word.getX();
		int y = word.getY();
		
		if(word.getDirection() == Direction.VERTICAL){
			while(iterator <= size){
				board.addLetter(w.charAt(iterator), x, y);
				y++;
				iterator++;
			}
		}
		else if(word.getDirection() == Direction.HORIZONTAL){
			while(iterator <= size){
				board.addLetter(w.charAt(iterator), x, y);
				x++;
				iterator++;
			}
		}
		receivePoints(w);
		generateLetters(size);
		changeTurn();
	}
	
	public Player getPlayer1(){
		return p1;
	}
	
	public Player getPlayer2(){
		return p2;
	}
}