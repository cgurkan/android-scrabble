package util;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private int nrletters = -1;
	private int points = 0;
	private String username = null;
	private List<Character> letters = new ArrayList<Character>();
	
	public List<Character> getLetters() {
		return letters;
	}

	public void setLetters(List<Character> letters) {
		this.letters = letters;
	}

	public Player(String name){
		username = name;
	}
	
	public int getNrLetters() {
		return nrletters;
	}

	public void setNrLetters(int lettersLeft) {
		this.nrletters = lettersLeft;
	}

	public String getUsername() {
		return username;
	}
	
	public int getPoints() {
		return points;
	}

	public void addPoints(int points) {
		this.points += points;
	}
}
