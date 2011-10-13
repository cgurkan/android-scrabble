package model;

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
	
	public Board createBoard(String host, String opp);
	
	public void startGame();
	
	public Player receivePoints(String s);
	
	public List<Character> generateLetters(int i);
	
	public int endGame();
	
	public void changeTurn();
	
	public void pass();
	
	public void placeWord(WordObject word);
}
