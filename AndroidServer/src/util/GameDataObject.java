package util;

import model.data.Board;

public class GameDataObject {
	
	//TODO: should be an object that returns all data that you need to know after a move
	
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
}
