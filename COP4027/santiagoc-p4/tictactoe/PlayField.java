package tictactoe;

import javafx.scene.control.Button;

/**
 * Student Name: Cesar Santiago
 * File Name: PlayField.java
 * Assignment Number: 4
 * 
 * PlayField is a class that holds information that allows us to
 * set an x and a y coordinate to our buttons.
 */

public class PlayField extends Button{
	private int x;
	private int y;
	
	/**
	 * Initializes a field on a board to 0, 0
	 */
	public PlayField() {
		this(0, 0);
	}
	
	/**
	 * Initializes a field on a board to x, y
	 * @param x
	 * @param y
	 */
	public PlayField(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Outputs a formatted string of x and y
	 */
	public String toString() {
		return "" + x + " " + y;
	}
	
}
