 import java.sql.SQLException;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) throws SQLException {
		//connect to the database
		mySQLAccess dbConnection = new mySQLAccess();
		dbConnection.mySQLAccess();
		//allows the user to login from the login GUI
        LoginWindow.main(dbConnection);
		
		
	}

}
