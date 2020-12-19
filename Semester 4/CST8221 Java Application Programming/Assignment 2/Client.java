/*
 * File Name: Client.java
 * Author: Brady McIntosh - 040706980
 * Course: CST8221 - JAP, Lab Section 302
 * Assignment: A2 Part 2
 * Date: 07 Dec 2019
 * Professor: Daniel Cormier
 * Purpose: Client main class
 */

package chat;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * Main Client class - implements and invokes Runnable functionality.
 * 
 * @author 	Brady McIntosh
 * @version 1.0
 * @since 	1.8
 */
public class Client {
	
	/**
	 * Main method. Creates a client instance in a Runnable object,
	 * 	and invokes on the EventQueue.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// call ClientChatUI constructor
		// set Frame properties
		// make frame visible / set location
		// make sure to create GUI inside event-dispatch thread
		
		Runnable client = new Runnable() {
			@Override
			public void run() {
				
				ClientChatUI runner = new ClientChatUI("Brady's ClientChatUI");
				
				runner.setSize(588,500);
				runner.setResizable(false);
				runner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				runner.setLocationRelativeTo(null);
				runner.setVisible(true);
			}
		};
		
		EventQueue.invokeLater(client);
	}
}
