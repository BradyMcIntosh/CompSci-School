/*
 * File Name: ChatRunnable.java
 * Author: Brady McIntosh - 040706980
 * Course: CST8221 - JAP, Lab Section 302
 * Assignment: A2 Part 2
 * Date: 07 Dec 2019
 * Professor: Daniel Cormier
 * Purpose: Chat input handler class.
 */

package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Handles chat input by reading input stream objects into local chat window.
 * 
 * @author 	Brady McIntosh
 * @version 1.0
 * @since 	1.8
 */
public class ChatRunnable <T extends JFrame & Accessible> implements Runnable {

	final T ui;
	final Socket socket;
	final ObjectInputStream inputStream;
	final ObjectOutputStream outputStream;
	final JTextArea display;
	
	ChatRunnable(T ui, ConnectionWrapper connection) {
		this.socket = connection.getSocket();
		this.inputStream = connection.getInputStream();
		this.outputStream = connection.getOutputStream();
		this.ui = ui;
		this.display = this.ui.getDisplay();
	}
	
	@Override
	public void run() {
		
		String strin;
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMMM d, HH:mm a");
		
		// endless loop to read objects from the input stream
		while(true) {
			if(null != socket && !socket.isClosed()) {
				try {
					strin = (String) inputStream.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					break;
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
				
				// print out date, time, and message in both cases
				// in case message matches chat terminator string,
				// break loop
				if(strin.trim().equals(ChatProtocolConstants.CHAT_TERMINATOR)) {
					final String terminate = ChatProtocolConstants.DISPLACEMENT +
							dateFormat.format(LocalDateTime.now()) +
							ChatProtocolConstants.LINE_TERMINATOR +
							strin;
					display.append(terminate);
					break;
				}
				// in case message does NOT match chat terminator string,
				// print as normal and continue loop
				else {
					final String append = ChatProtocolConstants.DISPLACEMENT +
							dateFormat.format(LocalDateTime.now()) +
							ChatProtocolConstants.LINE_TERMINATOR +
							strin;
					display.append(append);
				}
			}
		}
		
		// once loop ends, try to write chat terminator to output
		if(null != socket && !socket.isClosed()) {
			try {
				outputStream.writeObject(new String(
						ChatProtocolConstants.DISPLACEMENT +
						ChatProtocolConstants.CHAT_TERMINATOR +
						ChatProtocolConstants.LINE_TERMINATOR
						));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// close chat connection
		ui.closeChat();
	}

}
