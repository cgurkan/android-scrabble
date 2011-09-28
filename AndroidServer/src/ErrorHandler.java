import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorHandler {
	
	private final static Database db = new Database();

	public static void report(String error){
		String date = getDateNow();
		System.err.println(error+" @ "+date);
		String q = "INSERT INTO error VALUES(NULL, '"+error+"', '"+date+"')";
		
		try{
			db.execUpdate(q);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	private static String getDateNow(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}