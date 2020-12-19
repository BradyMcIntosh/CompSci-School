/*
 * File Name: Accessible.java
 * Author: Brady McIntosh - 040706980
 * Course: CST8221 - JAP, Lab Section 302
 * Assignment: A2 Part 2
 * Date: 07 Dec 2019
 * Professor: Daniel Cormier
 * Purpose: Accessible interface for connectable UIs
 */

package chat;

import javax.swing.JTextArea;

/**
 * Allows access to display fields, and close functionality,
 * 	of UI server/client classes.
 * 
 * @author 	Brady McIntosh
 * @version 1.0
 * @since 	1.8
 */
public interface Accessible {
	abstract JTextArea getDisplay();
	abstract void closeChat();
}
