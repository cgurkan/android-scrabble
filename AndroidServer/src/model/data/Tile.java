package model.data;

/**
 * Class that holds a Tile, i.e. a character at a specific place at the board.
 * This could mean that the letter c at (0,0) that the top letter of the board is 'c'.
 * 
 * @author Magnus
 */
public class Tile {

	private int x, y;
	private char c;
	
	public Tile(char c, int x, int y){
		this.c = c;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public char getChar() {
		return c;
	}
}
