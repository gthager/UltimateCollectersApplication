 import java.sql.SQLException;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) throws SQLException {
		mySQLAccess dbConnection = new mySQLAccess();
		dbConnection.mySQLAccess();
        LoginWindow.main(dbConnection);

		//test credentials
		//boolean isValid = dbConnection.testCredentials("gthager", "Password");
		//System.out.println(isValid);
		
		//open home screen
		
		
	}

}
