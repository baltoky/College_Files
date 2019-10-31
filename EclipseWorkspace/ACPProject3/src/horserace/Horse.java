package horserace;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Horse implements Runnable{
	
	public final static int SLEEP_TIME = 50;
	private int horseNumber;
	private int position;
	private FinishLine finishline;
	private int stride;
	private boolean finished;
	private HorseRenderer hr;
	
	public Horse() {
		horseNumber = 0;
		position = 0;
		finishline = null;
		stride = 0;
		finished = false;
		hr = null;
	}
	
	public Horse(int horseNumber, int position, int stride, FinishLine finishline, HorseRenderer h) {
		this.horseNumber = horseNumber;
		this.position = position;
		this.finishline = finishline;
		this.stride = stride;
		finished = false;
		hr = h;
	}
	
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
	
	public synchronized void setWinner() {
		if(finishline.getWinner() == -1)
			finishline.setWinner(horseNumber);
	}
	
	@Override
	public void run() {
		sprint();
	}

	public int getHorseNumber() {
		return horseNumber;
	}

	public void setHorseNumber(int horseNumber) {
		this.horseNumber = horseNumber;
	}
	
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public FinishLine getFinishline() {
		return finishline;
	}

	public void setFinishline(FinishLine finishline) {
		this.finishline = finishline;
	}

	public int getStride() {
		return stride;
	}

	public void setStride(int stride) {
		this.stride = stride;
	}
}
