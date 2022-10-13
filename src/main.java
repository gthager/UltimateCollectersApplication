 import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		JFrame window = new JFrame("Ultimate Collector's Aplication");
		Object[] loginCreds = new Object[2];
		loginCreds = GUI.LoginPage(window);
		//test that credentials were recieved
		System.out.println(loginCreds[0]);
		char[] password = (char[]) loginCreds[1];
		System.out.println(String.valueOf(password));
		//test credentials
		
		
		//open home screen
		
		
	}

}
