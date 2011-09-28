package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Class that manages all database storage.
 * The database can connect to a MySQL-database and execute queries.
 * 
 * @author Magnus
 *
 */
public class Database {

	/**
	 * Class that keeps track of all the constants
	 * used to manage the database.
	 * 
	 * @author Magnus
	 */
	public final class DbConstants{
		public static final String serverName = "localhost";
		public static final String database = "android-scrabble";
		public static final String username = "root"; 
		public static final String password = "";
	}
	
	/**
	 * enum that holds variables to see if
	 * the database is connected or not.
	 * 
	 * @author Magnus
	 */
	enum Status{
		CONNECTED,
		NOT_CONNECTED;
	}
	
	private Connection connection = null;	//	Database connection
	private Status status = null;
	
	/**
	 * static query variables, if query is executed ok or not
	 */
	public static final int QUERY_OK = 1;
	public static final int QUERY_NOT_OK = 0;
	
	/**
	 * Create new database and connect it to MySQL.
	 */
	public Database(){
		connect();
	}
	
	/**
	 * Connects to MySQL.
	 */
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
	
	/**
	 * The Status whether the database is connected or not
	 * @return
	 */
	public Status getStatus(){
		return status;
	}
	
	/**
	 * Executes a SELECT query.
	 * @param query the query to be executed
	 * @return the ResultSet that is returned from the query
	 * @throws SQLException
	 */
	public ResultSet execQuery(String query) throws SQLException{
		
		if(status != Status.CONNECTED){
			System.out.println("NOT CONNECTED");
			return null;
		}
		
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		return rs;
	}
	
	/**
	 * Executes an update query (UPDATE, DELETE, INSERT INTO...)
	 * 
	 * @param query the query to be executed
	 * @return int, QUERY_OK or QUERY_NOT_OK
	 * @throws SQLException
	 */
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