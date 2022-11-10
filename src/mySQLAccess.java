import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class mySQLAccess {
	 private Connection connect = null;
	 private Statement statement = null;
	 private PreparedStatement preparedStatement = null;
	 private ResultSet resultSet = null;
public void mySQLAccess() throws SQLException {
	System.out.println("Connecting to database...");
	connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=CST", "login", "HanSolo69");
	System.out.println("Connection is valid: " + connect.isValid(2));
}
//credential testing method
@SuppressWarnings("unlikely-arg-type")
public boolean testCredentials(String user, String password) throws SQLException {
	//try catch to avoid breaking my code
	try {
		//creates a statement to call the hashing function which compares the hash of the password\
		//to the hash of the password stored on the database
		Statement stmt = connect.createStatement();
		ResultSet result = stmt.executeQuery("call uca.hash('"+password+"', '"+user+"');");
		//if the two hashs match that it returns true
		result.next();
		if (result.getInt(1) == 0) {
			return true;
		}
		//if they dont match or if there is any sort of exception it returns false, likely an exception would
		//be caused by invalid input anyway
		else return false;	}
	catch (Exception e) {
		System.out.println(e);
		System.out.println("use uca; select 'Password' from logins where Username = '" + user + "'");
		return false;
	}
}
}
