package model;

import java.util.HashMap;

import util.GameDataObject;
import util.ResponseObject;
import util.SendableAction;
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
		GameDataObject obj = new GameDataObject(null, null, gm.getTurn(), null, gameID);
		return new ResponseObject(SendableAction.PASS, obj);
	}

	public ResponseObject quitGame(int gameID) {
		GameModel gm = modelList.get(gameID);
		gm.endGame();
		GameDataObject obj = new GameDataObject(gm.getPlayer1(), gm.getPlayer2(), gm.getTurn(), gm.getBoard(), gameID);
		return new ResponseObject(SendableAction.QUIT_GAME, obj);
	}

	public ResponseObject placeWord(int gameID, WordObject wo) {
		GameModel gm = modelList.get(gameID);
		if(gameLogic.checkWord(wo)){
			gm.placeWord(wo);
			GameDataObject obj = new GameDataObject(gm.getPlayer1(), gm.getPlayer2(), gm.getTurn(), gm.getBoard(), gameID);
			return new ResponseObject(SendableAction.PLACE_WORD, obj);
		}
		else{
			GameDataObject obj = null;
			return new ResponseObject(SendableAction.PLACE_WORD, obj);
		}
	}

	public ResponseObject swap(int gameID, int i) {
		GameModel gm = modelList.get(gameID);
		gm.generateLetters(i);
		GameDataObject obj = new GameDataObject(gm.getPlayer1(), gm.getPlayer2(), gm.getTurn(), gm.getBoard(), gameID);
		return new ResponseObject(SendableAction.SWAP, obj);
	}

	public ResponseObject startGame(String name1, String name2) {
		int ID = nextID();
		modelList.put(ID, new GameModel(name1, name2));
		GameModel gm = modelList.get(ID);
		GameDataObject obj = new GameDataObject(gm.getPlayer1(), gm.getPlayer2(), gm.getTurn(), gm.getBoard(), ID);
		return new ResponseObject(SendableAction.START_GAME, obj);
	}

	public ResponseObject getPlayersOnline() {
		return usermanager.getUsersOnline();
	}
	
	public ResponseObject getDictionaries(){
		return new ResponseObject(SendableAction.GET_DICTIONARIES, new String[]{"English"});
	}
}