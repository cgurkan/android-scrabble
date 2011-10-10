package model;

public class Player {
	
	private int letters = -1;
	private int points = 0;
	private String username = null;
	
	public Player(String name){
		username = name;
	}
	
	public int getLetters() {
		return letters;
	}

	public void setLetters(int lettersLeft) {
		this.letters = lettersLeft;
	}

	public String getUsername() {
		return username;
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
