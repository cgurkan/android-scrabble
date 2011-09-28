package model.data;

import java.util.LinkedList;
import java.util.List;

public class Board {
	
	private List<Tile> allTiles = null;
	
	/**
	 * Create empty board.
	 */
	public Board(){
		this(null);
	}
	
	/**
	 * Create board from existing array.
	 *  
	 * @param tiles the Tile-array to build from.
	 */
	public Board(Tile[] tiles){
		allTiles = new LinkedList<Tile>();
		
		if(tiles != null){
			for(Tile t1 : tiles){
				allTiles.add(t1);
			}
		}
	}
	
	/**
	 * Adds a new Tile to the board.
	 * 
	 * @param t the Tile to adds
	 */
	public void add(Tile t){
		allTiles.add(t);
	}

}
