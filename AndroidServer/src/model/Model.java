package model;

//TODO: should be the all mighty model that create gameModels for every game. This is the only class that the requestHandler communicate with. (on modelside) Needs to manage a list of all games seperated with GameIDs.
//TODO: should return a response object to the requestHandler!
public class Model {
	
	private UserLogic ul = null;
	
	public Model(){
		ul = new UserLogic();
	}

	public void login(String s) {
		// TODO Auto-generated method stub
	}

	public void logout(String s2) {
		// TODO Auto-generated method stub
	}

	public void signUp(String s3) {
		// TODO Auto-generated method stub
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

	public void startGame() {
		// TODO Auto-generated method stub
	}
}
