package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public final class DbConstants{
		public static final String serverName = "localhost";
		public static final String database = "android-scrabble";
		public static final String username = "root"; 
		public static final String password = "";
	}
	
	enum Status{
		CONNECTED,
		NOT_CONNECTED;
	}
	
	private Connection connection = null;
	private Status status = null;
	
	public static final int QUERY_OK = 1;
	public static final int QUERY_NOT_OK = 0;
	
	public Database(){
		connect();
	}
	
	public void connect(){ 
		try { 
			// Load the JDBC driver 
			String driverName = "org.gjt.mm.mysql.Driver"; // MySQL MM JDBC driver 
			Class.forName(driverName); 
	
			// Create a connection to the database
			String serverName = DbConstants.serverName;
			String database = DbConstants.database; 
			String url = "jdbc:mysql://" + serverName + "/" + database; // a JDBC url 
			String username = DbConstants.username; 
			String password = DbConstants.password; 
			connection = DriverManager.getConnection(url, username, password);
			status = Status.CONNECTED;
		}
		catch(ClassNotFoundException e){
			// Could not find the database driver 
			System.out.println("Could not find the database driver: "+e.getMessage());
			status = Status.NOT_CONNECTED;
		}
		catch(SQLException e){ 
			// Could not connect to the database
			System.out.println("Could not connect to the database: "+e.getMessage());
			status = Status.NOT_CONNECTED;
		}
	}
	
	public Status getStatus(){
		return status;
	}
	
	public ResultSet execQuery(String query) throws SQLException{
		
		if(status != Status.CONNECTED){
			System.out.println("NOT CONNECTED");
			return null;
		}
		
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		return rs;
	}
	
	public int execUpdate(String query) throws SQLException{
		
		if(status != Status.CONNECTED){
			System.out.println("NOT CONNECTED");
			return -1;
		}
		
		Statement stmt = connection.createStatement();
		int val = stmt.executeUpdate(query);
		
		return val;
	}
}