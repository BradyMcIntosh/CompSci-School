/*
 * File Name: ServerChatUI.java
 * Author: Brady McIntosh - 040706980
 * Course: CST8221 - JAP, Lab Section 302
 * Assignment: A2 Part 2
 * Date: 07 Dec 2019
 * Professor: Daniel Cormier
 * Purpose: Server UI builder and controller
 */

package chat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Server UI builder/controller. Also connects to clients and can send/read messages.
 * 
 * @author 	Brady McIntosh
 * @version 1.0
 * @since 	1.8
 */
public class ServerChatUI extends JFrame implements Accessible {
	
	private JTextField message;
	private JButton sendButton;
	private JTextArea display;
	private ObjectOutputStream outputStream;
	private Socket socket;
	private ConnectionWrapper connection;

	public ServerChatUI(Socket socket) {
		this.socket = socket;
		setFrame(createUI());
		runClient();
	}
	
	private class WindowController extends WindowAdapter {
		public void windowClosing(WindowEvent windowEvent){
			
			// when closing server window, 
			// attempt to close any connected client windows
            System.out.println("ServerUI Window closing!");
			try {
				outputStream.writeObject(new String(
						ChatProtocolConstants.DISPLACEMENT +
						ChatProtocolConstants.CHAT_TERMINATOR + 
						ChatProtocolConstants.LINE_TERMINATOR
						));
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				dispose();
			}
			
			System.out.println("Closing chat!");
			
			// close local chat connection
			try {
				connection.closeConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				dispose();
			}
			
			dispose();
			System.out.println("ServerUI Closed!");
			//System.exit(0);
         } 
		
		public void windowClosed() {
			System.out.println("ServerUI Closed");
		}
	}
	
	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent action) {
			if(action.getActionCommand().equals("send")) {
				send();
			}
		}
	}
	
	public JTextArea getDisplay() {
		return display;
	}
	
	public void closeChat() {
		try {
			connection.closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dispose();
	}
	
	public JPanel createUI() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		LineBorder blkBorder = new LineBorder(Color.BLACK, 10);
		LineBorder bluBorder = new LineBorder(Color.BLUE, 10);
		
		TitledBorder msgBorder = new TitledBorder(blkBorder, "MESSAGE", 
				TitledBorder.LEADING, TitledBorder.TOP);
		TitledBorder chaBorder = new TitledBorder(bluBorder, "CHAT DISPLAY", 
				TitledBorder.CENTER, TitledBorder.TOP);
		
		Controller control = new Controller();
		
		/* MESSAGE PANEL */
		
		JPanel messagePanel = new JPanel();
		messagePanel.setBorder(msgBorder);
		messagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		messagePanel.setPreferredSize(new Dimension(572, 60));
		
		// text field displaying "Type message"
		message = new JTextField("Type message");
		message.setPreferredSize(new Dimension(454, 20));
		
		sendButton = new JButton("Send");
		sendButton.setPreferredSize(new Dimension(75, 20));
		sendButton.setMnemonic(KeyEvent.VK_S);
		sendButton.addActionListener(control);
		sendButton.setActionCommand("send");
		
		// attach to message panel
		messagePanel.add(message);
		messagePanel.add(sendButton);
		
		/* CHAT PANEL */
		
		JPanel chatPanel = new JPanel();
		chatPanel.setBorder(chaBorder);
		chatPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		chatPanel.setPreferredSize(new Dimension(572, 364));
		
		
		display = new JTextArea(30, 45);
		display.setEditable(false);
		
		JScrollPane chatScroll = new JScrollPane (display, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				   JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		chatScroll.setPreferredSize(new Dimension(536, 324));
		
		// attach to chat panel
		chatPanel.add(chatScroll);
		display.requestFocus();
		
		/* ADDING ALL TO MAIN PANEL */
		
		panel.add(messagePanel);
		panel.add(chatPanel);
		//TODO create stuff
		
		return panel;
	}
	
	public final void setFrame(JPanel panel) {
		this.setContentPane(panel);
		this.setSize(588,500);
		this.setResizable(false);
		this.addWindowListener(new WindowController());
	}
	
	private void runClient() {
		// establish local connection and open gui
		connection = new ConnectionWrapper(socket);
		try {
			connection.createStreams();
			outputStream = connection.getOutputStream();
			Runnable run = new ChatRunnable<ServerChatUI>(this,connection);
			new Thread(run).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void send() {
		String sendMessage = message.getText();
		display.append(sendMessage + ChatProtocolConstants.LINE_TERMINATOR);
		try {
			outputStream.writeObject(new String(
					ChatProtocolConstants.DISPLACEMENT +
					sendMessage + 
					ChatProtocolConstants.LINE_TERMINATOR
					));
		} catch (IOException e) {
			display.append(e + ChatProtocolConstants.LINE_TERMINATOR);
		}
	}
}
