package util;

public class WordObject {

	String word = null;
	int x = -1;
	int y = -1;
	Direction direction = null;
	
	public WordObject(String word, int x, int y, Direction direction) {
		super();
		this.word = word;
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	
	public String getWord() {
		return word;
	}


	public void setWord(String word) {
		this.word = word;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public Direction getDirection() {
		return direction;
	}


	public void setDirection(Direction direction) {
		this.direction = direction;
	}


	public enum Direction{
		VERTICAL,
		HORIZONTAL
	}
}
