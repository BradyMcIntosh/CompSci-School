/*
 * File Name: ConnectionWrapper.java
 * Author: Brady McIntosh - 040706980
 * Course: CST8221 - JAP, Lab Section 302
 * Assignment: A2 Part 2
 * Date: 07 Dec 2019
 * Professor: Daniel Cormier
 * Purpose: Allows connections for object exchange
 */

package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Provides common handle for object stream connection
 * 
 * @author 	Brady McIntosh
 * @version 1.0
 * @since 	1.8
 */
public class ConnectionWrapper {

	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	private Socket socket;
	
	ConnectionWrapper(Socket socket) {
		this.socket = socket;
	}
	
	Socket getSocket() {
		return socket;
	}
	
	ObjectOutputStream getOutputStream() {
		return outputStream;
	}
	
	ObjectInputStream getInputStream() {
		return inputStream;
	}
	
	ObjectInputStream createObjectIStreams() throws IOException {
		return inputStream = new ObjectInputStream(socket.getInputStream());
	}
	
	ObjectOutputStream createObjectOStreams() throws IOException {
		return outputStream = new ObjectOutputStream(socket.getOutputStream());
	}
	
	void createStreams() throws IOException {
		createObjectOStreams();
		createObjectIStreams();
	}
	
	public void closeConnection() throws IOException {
		if(null != outputStream) {
			outputStream.close();
		}
		if(null != inputStream) {
			inputStream.close();
		}
		if(null != socket && !socket.isClosed()) {
			socket.close();
		}
	}
}
