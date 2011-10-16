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
	
	public Board getBoard();
	
	public void startGame();
	
	public Player receivePoints(String s);
	
	public void generateLetters(int i);
	
	public List<Player> endGame();
	
	public void changeTurn();
	
	public boolean pass();
	
	public void placeWord(WordObject word);
}
