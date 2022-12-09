 import java.sql.SQLException;

import javax.swing.JFrame;

public class main {
	public static mySQLAccess conn = new mySQLAccess();
	public static void main(String[] args) throws SQLException {
		//connect to the database
		conn.mySQLAccess();
		//allows the user to login from the login GUI
        LoginWindow.main();
       // Discover.main(args);
		
		
	}

}
