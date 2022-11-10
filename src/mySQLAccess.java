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
	try {
		Statement stmt = connect.createStatement();
		ResultSet result = stmt.executeQuery("call uca.hash('"+password+"', '"+user+"');");
		result.next();
		if (result.getInt(1) == 0) {
			return true;
		}
		else return false;	}
	catch (Exception e) {
		System.out.println(e);
		System.out.println("use uca; select 'Password' from logins where Username = '" + user + "'");
		return false;
	}
}
}
