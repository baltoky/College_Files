package horserace;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Student Name: Cesar Santiago
 * File Name: Horse.java
 * Assignment Number: 3
 * 
 * A runnable class that simulates a horse coursing through a race.
 */

public class Horse implements Runnable{
	
	public final static int SLEEP_TIME = 50;
	private int horseNumber;
	private int position;
	private FinishLine finishline;
	private int stride;
	private boolean finished;
	private HorseRenderer hr;
	
	/**
	 * Default constructor
	 */
	public Horse() {
		horseNumber = 0;
		position = 0;
		finishline = null;
		stride = 0;
		finished = false;
		hr = null;
	}
	
	/**
	 * @param horseNumber
	 * @param position
	 * @param stride
	 * @param finishline
	 * @param h
	 * Custom constructor, it defines the number of the horse, the starting position,
	 * the stride that it takes per cycle, a standard finish line object, and its renderer.
	 */
	public Horse(int horseNumber, int position, int stride, FinishLine finishline, HorseRenderer h) {
		this.horseNumber = horseNumber;
		this.position = position;
		this.finishline = finishline;
		this.stride = stride;
		finished = false;
		hr = h;
	}
	
	/**
	 * Function that lets the horse run every cycle.
	 */
	public void sprint() {
		while(position < finishline.getFinishline() && finishline.hasWinner()) {
			position = position + stride;
			hr.drawNormalized(position);
			System.out.println("#" + horseNumber + " Got here " + position);
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		setFinished(true);
		System.out.println("#" + horseNumber + " finished.");
		setWinner();
	}
	
	/**
	 * Sets the winner of the race, locked to one thread at a time
	 */
	public synchronized void setWinner() {
		if(finishline.getWinner() == -1)
			finishline.setWinner(horseNumber);
	}
	
	/**
	 * Runs the object's thread on a concurrent thread
	 */
	@Override
	public void run() {
		sprint();
	}

	/**
	 * @return
	 * Returns the horse number
	 */
	public int getHorseNumber() {
		return horseNumber;
	}

	/**
	 * @param horseNumber
	 * Sets the horse number
	 */
	public void setHorseNumber(int horseNumber) {
		this.horseNumber = horseNumber;
	}
	
	/**
	 * @return
	 * Returns true if the horse has finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * @param finished
	 * Sets the horse to a finished state
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	/**
	 * @return
	 * Returns the position of the horse
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position
	 * Sets the position of the horse
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @return
	 * Returns the finishline context
	 */
	public FinishLine getFinishline() {
		return finishline;
	}

	/**
	 * @param finishline
	 * Sets a finish line context given the parameter
	 */
	public void setFinishline(FinishLine finishline) {
		this.finishline = finishline;
	}

	/**
	 * @return
	 * Returns the length of the stride the horse takes
	 */
	public int getStride() {
		return stride;
	}

	/**
	 * @param stride
	 * Sets the length of the stride the horse takes
	 */
	public void setStride(int stride) {
		this.stride = stride;
	}
}
