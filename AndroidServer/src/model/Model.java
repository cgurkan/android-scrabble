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
	
	private int nextID(){
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

	public ResponseObject pass(int gameID) {
		GameModel gm = modelList.get(gameID);
		gm.pass();
		return null;
	}

	public ResponseObject guitGame(int gameID) {
		GameModel gm = modelList.get(gameID);
		gm.endGame();
		return null;
	}

	public ResponseObject placeWord(int gameID, WordObject wo) {
		GameModel gm = modelList.get(gameID);
		if(gameLogic.checkWord(wo)){
			gm.placeWord(wo);
		}
		else{
			//TODO: what to return???
		}
		return null;
	}

	public ResponseObject swap(int gameID, int i) {
		GameModel gm = modelList.get(gameID);
		gm.generateLetters(i);
		return null;
	}

	public ResponseObject startGame(String name1, String name2) {
		int ID = nextID();
		modelList.put(ID, new GameModel(name1, name2, ID));
		return null;
	}

	public ResponseObject getPlayersOnline() {
		return usermanager.getUsersOnline();
	}
}