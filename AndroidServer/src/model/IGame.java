package model;

import java.sql.ResultSet;
import java.util.List;

import util.Player;
import util.WordObject;
import model.data.Board;

/**
 * An interface describing a game and it's states.
 * 
 * @author Marika
 *
 */
public interface IGame {
	
	public Board getBoard();
	
	public boolean startGame();
	
	public Player receivePoints(String s);
	
	public boolean generateLetters(int i);
	
	public void addLettersToPlayer(ResultSet set, List<Character> letters);
	
	public void changeTurn();
	
	public boolean pass();
	
	public boolean placeWord(WordObject word);
	
	public Player getPlayer1();
	
	public Player getPlayer2();
	
	public void setLettersLeft(int i);
	
	public int getLettersLeft();
	
	public int getPass();
	
	public void setPass(int i);
}
