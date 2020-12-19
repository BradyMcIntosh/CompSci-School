/*
 * File Name: Server.java
 * Author: Brady McIntosh - 040706980
 * Course: CST8221 - JAP, Lab Section 302
 * Assignment: A2 Part 2
 * Date: 07 Dec 2019
 * Professor: Daniel Cormier
 * Purpose: Server main class
 */


package chat;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JFrame;

/**
 * Main Server class - implements and invokes Runnable functionality.
 * 	Also establishes server socket for client connections.
 * 
 * @author 	Brady McIntosh
 * @version 1.0
 * @since 	1.8
 */
public class Server {

	public static void main(String[] args) {
		
		int port = 65535;
		int friend = 0;
		ServerSocket server = null;
		Socket socket = null;
		
		// use custom port, if specified in cmd args
		if(args.length > 0) {
			port = new Integer(args[0]);
		}
		else {
			System.out.println("Default port 65535 used.");
		}
		
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// attempt to accept client connections in endless loop
		while(true) {
			try {
				socket = server.accept();
				
				if(socket.getSoLinger() != -1) {
					socket.setSoLinger(true, 5);
				}
				if(!socket.getTcpNoDelay()) {
					socket.setTcpNoDelay(true);
				}
				
				System.out.println(socket);
				++friend;
				
				final String title = "Brady's Friend " + friend;
				launchClient(socket, title);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void launchClient(Socket socket, String title) {
		
		// call ServerChatUI constructor
		// set Frame properties
		// make frame visible / set location
		// make sure to create GUI inside event-dispatch thread
		
		Runnable server = new Runnable() {
			@Override
			public void run() {
				
				ServerChatUI runner = new ServerChatUI(socket);
				
				runner.setTitle(title);
				
				runner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				runner.setLocationRelativeTo(null);
				runner.setVisible(true);
			}
		};
		
		EventQueue.invokeLater(server);
	}
	
}
