import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class mySQLAccess {
	 private Connection connect = null;
	 private int user;
	 
	 
public void mySQLAccess() throws SQLException {
	System.out.println("Connecting to database...");
	connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=CST", "login", "HanSolo69");
	System.out.println("Connection is valid: " + connect.isValid(2));
}


//credential testing method
@SuppressWarnings("unlikely-arg-type")
public void close() {
	try {
		connect.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public boolean testCredentials(String user, String password) throws SQLException {
	//try catch to avoid breaking my code
	try {
		//creates a statement to call the hashing function which compares the hash of the password\
		//to the hash of the password stored on the database
		Statement stmt = connect.createStatement();
		ResultSet result = stmt.executeQuery("call uca.hash('"+password+"', '"+user+"');");
		//if the two hashs match that it returns true
		result.next();
		//System.out.println(result.getInt(1));
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


public void setUser(String user) throws SQLException {
	Statement stmt = connect.createStatement();
	ResultSet result = stmt.executeQuery("select userID from uca.logins where Username = '" + user + "';");
	result.next();
	this.user = result.getInt(1);
	stmt.close();
	result.close();
}

public String[] getSets() throws SQLException {
	Statement stmt = connect.createStatement();
	ResultSet result = stmt.executeQuery("select count(set_num) from uca.sets");
	result.next();
	String[] sets = new String[result.getInt(1)];
	result = stmt.executeQuery("select name from uca.sets");
	int index = 0;
	while (result.next()) {
		sets[index] = result.getString(1);
		index++;
	}
	stmt.close();
	result.close();
	return sets;
}

public String[] getSetsSearch(String keyWord) throws SQLException {
	Statement stmt = connect.createStatement();
	ResultSet result = stmt.executeQuery("select count(set_num) from uca.sets where instr(name, '"+keyWord+"') > 0;");
	result.next();
	String[] sets = new String[result.getInt(1)];
	result = stmt.executeQuery("select name from uca.sets where instr(name, '"+keyWord+"') > 0;");
	int index = 0;
	while (result.next()) {
		sets[index] = result.getString(1);
		index++;
	}
	stmt.close();
	result.close();
	return sets;
}

public void addToCollection(String set) throws SQLException {
	Statement stmt = connect.createStatement();
	stmt.executeUpdate("insert into uca.collection values ('"+user+"', (select set_num from uca.sets where name = '"+set+"'));");
	stmt.close();
	
}

public String[] getCollection() throws SQLException {
	Statement stmt = connect.createStatement();
	ResultSet result = stmt.executeQuery("select count(distinct(collection.set_num)) from uca.sets inner join uca.collection on collection.set_num = sets.set_num where collection.userID = "+user+";");
	result.next();
	String[] results = new String[result.getInt(1)];
	result = stmt.executeQuery("select distinct(name) from uca.sets inner join uca.collection on collection.set_num = sets.set_num where collection.userID = "+user+";");
	int index = 0;
	while (result.next()) {
		results[index] = result.getString(1);
		index++;
	}
	result.close();
	stmt.close();
	return results;
}

public void removeFromCollection(String set) throws SQLException {
	Statement stmt = connect.createStatement();
	stmt.executeUpdate("delete from uca.collection where set_num = (select set_num from uca.sets where name = '" + set + "');");
	stmt.close();
}
}
