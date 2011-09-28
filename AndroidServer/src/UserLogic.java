import java.sql.ResultSet;
import java.sql.SQLException;


public class UserLogic extends Logic{

	public UserLogic(){
		super();
		// DO STUFF
	}
	
	public void login(String username){
		Database db = getDatabase();
		
		String query = "SELECT * FROM player WHERE name = '"+username+"'";
		String result = "";
		
		try{
			ResultSet set = db.execQuery(query);
			set.last();
			if(set.getRow() > 0){
				result = "You are now logged in as "+username;
			}
			else{
				result = "The requested username "+username+" does not exist. Please sign up!";
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			result = "The following error(s) occured while trying to log in: "+e.getMessage();
		}
		
		super.setChanged();
		super.notifyObservers(result);
	}
}
