package horserace;

/**
 * Student Name: Cesar Santiago
 * File Name: FinishLine.java
 * Assignment Number: 3
 * 
 * A class that sets the end point to the race as well as stores which horse is the winner.
 */

public class FinishLine {
	private int finishline;
	private int winner;

	/**
	 * @param finishline
	 * Custom constructor, sets the finish line
	 */
	public FinishLine(int finishline) {
		this.finishline = finishline;
		winner = -1;
	}
	
	/**
	 * @return
	 * Getter that returns the winner's number
	 */
	public int getWinner() {
		return winner;
	}
	
	/**
	 * @return
	 * Returns true if the race has had a winner
	 */
	public boolean hasWinner() {
		return (getWinner() != -1);
	}

	/**
	 * @param winner
	 * Setter that sets a winning number
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}

	/**
	 * @return
	 * Returns the length of the race
	 */
	public int getFinishline() {
		return finishline;
	}

	/**
	 * @param finishline
	 * Sets the length of the race.
	 */
	public void setFinishline(int finishline) {
		this.finishline = finishline;
	}
	
}
