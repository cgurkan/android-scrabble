package model;

import java.util.List;
import model.data.Board;

/**
 * An interface describing a game and it's states.
 * 
 * @author Marika
 *
 */
public interface IGame {
	
	public Board createBoard(String host, String opp);
	
	public void startGame(String host, String opp);
	
	public int receivePoints(String s);
	
	public List<Character> generateLetters(int i);
}
