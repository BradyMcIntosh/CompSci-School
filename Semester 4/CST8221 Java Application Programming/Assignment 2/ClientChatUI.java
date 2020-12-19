/*
 * File Name: ClientChatUI.java
 * Author: Brady McIntosh - 040706980
 * Course: CST8221 - JAP, Lab Section 302
 * Assignment: A2 Part 2
 * Date: 07 Dec 2019
 * Professor: Daniel Cormier
 * Purpose: Client UI builder and controller
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
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

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
 * Client UI builder/controller. Also connects to server and can send/read messages.
 * 
 * @author 	Brady McIntosh
 * @version 1.0
 * @since 	1.8
 */
public class ClientChatUI extends JFrame implements Accessible {
	
	private JTextField message;
	private JTextField hostField;
	private JComboBox<String> portList;
	private JButton sendButton;
	private JButton connectButton;
	private JTextArea display;
	private ObjectOutputStream outputStream;
	private Socket socket;
	private ConnectionWrapper connection;
	
	/** Swing serial version ID, for serializable {@value}*/
	private static final long serialVersionUID = 6248477390124812346L;
	
	public ClientChatUI(String title) {
		this.setTitle(title);
		this.runClient();
	}
	
	private class WindowController extends WindowAdapter {
		public void windowClosing(WindowEvent windowEvent){
			try {
				outputStream.writeObject(new String(
						ChatProtocolConstants.CHAT_TERMINATOR
						));
			} catch (IOException e) {
				// handle exception
			}
			finally {
				System.exit(0);
			}
         } 
	}
	
	private class Controller implements ActionListener {
		public void actionPerformed(ActionEvent action) {
			boolean connected = false;
			try {
				if(action.getActionCommand().equals("connect")) {
					// connect to server if "connect" button is pressed
					String host = hostField.getText();
					int port = new Integer(portList.getSelectedItem().toString());
					connected = connect(host, port);
					// if successful, rearrange gui to facilitate sending messages
					if(connected) {
						connectButton.setEnabled(false);
						connectButton.setBackground(Color.BLUE);
						sendButton.setEnabled(true);
						message.requestFocus();
						// establish client ui on its own thread
						Runnable run = new ChatRunnable<ClientChatUI>(ClientChatUI.this, connection);
						new Thread(run).start();
					}
					else { return; }
				}
				
				if(action.getActionCommand().equals("send")) {
					send();
				}
			}
			catch(Exception e) {
				display.append(e + ChatProtocolConstants.LINE_TERMINATOR);
				return;
			}
		}
	}
	
	public JTextArea getDisplay() {
		return display;
	}
	
	public void closeChat() {
		try {
			// attempt to close connection to server
			connection.closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		enableConnectButton();
	}
	
	public JPanel createClientUI() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		LineBorder redBorder = new LineBorder(Color.RED, 10);
		LineBorder blkBorder = new LineBorder(Color.BLACK, 10);
		LineBorder bluBorder = new LineBorder(Color.BLUE, 10);
		
		TitledBorder conBorder = new TitledBorder(redBorder, "CONNECTION", 
				TitledBorder.LEADING, TitledBorder.TOP);
		TitledBorder msgBorder = new TitledBorder(blkBorder, "MESSAGE", 
				TitledBorder.LEADING, TitledBorder.TOP);
		TitledBorder chaBorder = new TitledBorder(bluBorder, "CHAT DISPLAY", 
				TitledBorder.CENTER, TitledBorder.TOP);
		
		Controller control = new Controller();
		
		/* CONNECTION PANEL */
		
		JPanel connectPanel = new JPanel();
		connectPanel.setBorder(conBorder);
		connectPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		connectPanel.setPreferredSize(new Dimension(572, 110));
		
		// label displaying "Host"
		JLabel hostLabel = new JLabel("Host:");
		hostLabel.setPreferredSize(new Dimension(35,30));
		
		
		// field for entering host name
		hostField = new JTextField();
		hostField.setMargin(new Insets(0, 5, 0, 0));
		hostField.setPreferredSize(new Dimension(494, 20));
		hostField.requestFocus();
		hostField.setText("localhost");
		
		// set mnemonic for hostLabel and hostField
		hostLabel.setDisplayedMnemonic(KeyEvent.VK_H);
		hostLabel.setLabelFor(hostField);
		
		// label displaying "Port"
		JLabel portLabel = new JLabel("Port:");
		portLabel.setPreferredSize(new Dimension(35,30));
		
		// combo box containing port numbers
		portList = new JComboBox<String>( new String[] {"", "8089", "65000", "65535"} );
		portList.setPreferredSize(new Dimension(100, 20));
		portList.setEditable(true);
		
		// set mnemonic for hostLabel and hostField
		portLabel.setDisplayedMnemonic(KeyEvent.VK_P);
		portLabel.setLabelFor(portList);
		
		// button displaying "Connect"
		connectButton = new JButton("Connect");
		connectButton.setBackground(Color.RED);
		connectButton.setPreferredSize(new Dimension(100, 20));
		connectButton.setMnemonic(KeyEvent.VK_C);
		connectButton.addActionListener(control);
		connectButton.setActionCommand("connect");
		
		// attach to connection panel
		connectPanel.add(hostLabel);
		connectPanel.add(hostField);
		connectPanel.add(portLabel);
		connectPanel.add(portList);
		connectPanel.add(connectButton);
		
		/* MESSAGE PANEL */
		
		JPanel messagePanel = new JPanel();
		messagePanel.setBorder(msgBorder);
		messagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		messagePanel.setPreferredSize(new Dimension(572, 60));
		
		// text field displaying "Type message"
		message = new JTextField("Type message");
		message.setPreferredSize(new Dimension(454, 20));
		
		sendButton = new JButton("Send");
		sendButton.setEnabled(false);
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
		chatPanel.setPreferredSize(new Dimension(572, 248));
		
		display = new JTextArea(30, 45);
		display.setEditable(false);
		
		JScrollPane chatScroll = new JScrollPane (display, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				   JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		chatScroll.setPreferredSize(new Dimension(536, 210));
		
		// attach to chat panel
		chatPanel.add(chatScroll);
		
		/* ADDING ALL TO MAIN PANEL */
		
		panel.add(connectPanel);
		panel.add(messagePanel);
		panel.add(chatPanel);
		//TODO create stuff
		
		return panel;
	}
	
	private boolean connect(String host, int port) {
		
		try {
			// instantiate, connect and set values for socket
			Socket socket = new Socket(InetAddress.getByName(host), port);
			if(socket.getSoLinger() != -1) {
				socket.setSoLinger(true, 5);
			}
			if(!socket.getTcpNoDelay()) {
				socket.setTcpNoDelay(true);
			}
			display.append(socket + ChatProtocolConstants.LINE_TERMINATOR);
			
			// establish connection to server
			connection = new ConnectionWrapper(socket);
			connection.createStreams();
			outputStream = connection.getOutputStream();
			return true;
		} catch (IOException e) {
			display.append(e + ChatProtocolConstants.LINE_TERMINATOR);
			return false;
		}
	}
	
	private void send() {
		// extract message from gui field
		String sendMessage = message.getText();
		
		// show sent message on local chat display
		display.append(sendMessage + ChatProtocolConstants.LINE_TERMINATOR);
		try {
			// send message to server
			outputStream.writeObject(new String(
					ChatProtocolConstants.DISPLACEMENT +
					sendMessage + 
					ChatProtocolConstants.LINE_TERMINATOR
					));
		} catch (IOException e) {
			enableConnectButton();
			display.append(e + ChatProtocolConstants.LINE_TERMINATOR);
		}
	}
	
	private void runClient() {
		this.setContentPane(createClientUI());
		this.addWindowListener(new WindowController());
	}
	
	private void enableConnectButton() {
		connectButton.setEnabled(true);
		connectButton.setBackground(Color.RED);
		sendButton.setEnabled(false);
		hostField.requestFocus();
	}
}
