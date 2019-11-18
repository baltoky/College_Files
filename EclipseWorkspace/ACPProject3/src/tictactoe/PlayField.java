package tictactoe;

import javafx.scene.control.Button;

public class PlayField extends Button{
	private int x;
	private int y;
	
	public PlayField() {
		this(0, 0);
	}
	
	public PlayField(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "" + x + " " + y;
	}
	
}
