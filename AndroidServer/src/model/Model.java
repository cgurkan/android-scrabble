package model;

import java.util.HashMap;

import util.ResponseObject;

//TODO: should be the all mighty model that create gameModels for every game. This is the only class that the requestHandler communicate with. (on modelside) Needs to manage a list of all games seperated with GameIDs.
//TODO: should return a response object to the requestHandler!
public class Model {

	private UserManager usermanager = null;
	private HashMap<Integer, GameModel> modelList;
	
	public Model(){
		usermanager = new UserManager();
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
		// TODO Auto-generated method stub
	}

	public void guitGame(int gameID) {
		// TODO Auto-generated method stub
	}

	public void placeWord(int gameID) {
		// TODO Auto-generated method stub
	}

	public void swap(int gameID) {
		// TODO Auto-generated method stub
	}

	public void startGame(String name1, String name2) {
		modelList.put(nextID(), new GameModel(name1, name2));
	}
}