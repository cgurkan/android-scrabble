package model;

import java.util.HashMap;
import java.util.List;

import util.ResponseObject;
import util.WordObject;


public class Model {

	private UserManager usermanager = null;
	private GameLogic gameLogic = null;
	private HashMap<Integer, GameModel> modelList;
	
	public Model(){
		usermanager = new UserManager();
		gameLogic = new GameLogic();
		modelList = new HashMap<Integer, GameModel>();
	}
	
	public GameModel getModel(int gameID){
		return modelList.get(gameID);
	}
	
	public int nextID(){
		return modelList.size()+1;
	}

	public ResponseObject login(String s) {
		return usermanager.login(s);
	}

	public ResponseObject logout(String s2) {
		return usermanager.logout(s2);
	}

	public ResponseObject signUp(String s3) {
		return usermanager.signUp(s3);
	}

	public void pass(int gameID) {
		GameModel gm = modelList.get(gameID);
		gm.pass();
	}

	public void guitGame(int gameID) {
		GameModel gm = modelList.get(gameID);
		gm.endGame();
	}

	public void placeWord(int gameID, WordObject wo) {
		GameModel gm = modelList.get(gameID);
		if(gameLogic.checkWord(wo)){
			gm.placeWord(wo);
		}
		else{
			//TODO: what to return???
		}
	}

	public List<Character> swap(int gameID, int i) {
		GameModel gm = modelList.get(gameID);
		return gm.generateLetters(i);
	}

	public void startGame(String name1, String name2) {
		int ID = nextID();
		modelList.put(ID, new GameModel(name1, name2, ID));
	}
}