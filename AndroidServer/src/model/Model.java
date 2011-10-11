package model;

import java.util.HashMap;

//TODO: should be the all mighty model that create gameModels for every game. This is the only class that the requestHandler communicate with. (on modelside) Needs to manage a list of all games seperated with GameIDs.
//TODO: should return a response object to the requestHandler!
public class Model {

	private UserManager ul = null;
	private HashMap<Integer, GameModel> modelList;
	
	public Model(){
		ul = new UserManager();
		modelList = new HashMap<Integer, GameModel>();
	}
	
	public GameModel getModel(int gameID){
		return modelList.get(gameID);
	}
	
	public int nextID(){
		return modelList.size()+1;
	}

	public void login(String s) {
		ul.login(s);
	}

	public void logout(String s2) {
		ul.logout(s2);
	}

	public void signUp(String s3) {
		ul.signUp(s3);
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