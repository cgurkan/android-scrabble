package model.data;

import util.Player;

public class GameDataObject {
	
	private Player p1 = null;
	private Player p2 = null;
	private String turn = null;
	private Board board = null;
	private int gameID = -1;
	
	public GameDataObject(Player p1, Player p2, String s, Board b, int i){
		this.p1 = p1;
		this.p2 = p2;
		turn = s;
		board = b;
		gameID = i;
	}
	
	public Player getP1() {
		return p1;
	}

	public Player getP2() {
		return p2;
	}

	public String getTurn() {
		return turn;
	}

	public Board getBoard() {
		return board;
	}

	public int getGameID() {
		return gameID;
	}
}
