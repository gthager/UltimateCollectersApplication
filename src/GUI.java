import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI {
	public static Object[] LoginPage(JFrame window, Boolean error) {
		//creates variables
		Object[] loginCreds = new Object[2];
		JPanel inputPanel = new JPanel();
		//Adds image to login page
		ImageIcon loginImage = new ImageIcon("UCALoginAccountImage.png");
		JLabel loginImageLabel = new JLabel(loginImage);

		//creates text input prompts
		JLabel UsernameInputPrompt = new JLabel("Username");
		JLabel PasswordInputPrompt = new JLabel("Password");
		JLabel ErrorPrompt = new JLabel("Invalid Email or Password");
		ErrorPrompt.setForeground(Color.red);
		//adds text input
		JTextField UsernameInput = new JTextField(20);
		UsernameInput.setBounds(10,10, 0, 0);
		JPasswordField PasswordInput = new JPasswordField(20);
		//creates a login button
		JButton LoginButton = new JButton("Login");
		
		
		//adds all of the components to the JFrame
		if (error == true) {
			inputPanel.add(ErrorPrompt);
		}
		inputPanel.add(loginImageLabel);
		inputPanel.add(UsernameInputPrompt);
		inputPanel.add(UsernameInput);
		inputPanel.add(PasswordInputPrompt);
		inputPanel.add(PasswordInput);
		inputPanel.add(LoginButton);
		inputPanel.setLayout(new GridLayout(7,1,0,10));
		window.add(inputPanel);
		
		
		//returns login credentials
		window.setSize(1920,1080);
		window.setVisible(true);
		//waits for user input
		while (true) {
			LoginButton.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent e) {
					loginCreds[0]= UsernameInput.getText();
					loginCreds[1] = PasswordInput.getPassword();
					
					
				}
				
			});
			//once user input is detected it breaks from the loop
			if (loginCreds[0]!=null && loginCreds[1]!=null) {
				break;
			}
		}
			//returns the input credentials
			return loginCreds;

	}
	
	public static void homeScreen(JFrame window) {
		
	}
}
