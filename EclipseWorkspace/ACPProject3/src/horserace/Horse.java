package horserace;

public class Horse extends Thread{
	
	int horseNumber;
	int position;
	FinishLine finishline;
	int stride;
	boolean finished;
	
	public Horse() {
		horseNumber = 0;
		position = 0;
		finishline = null;
		stride = 0;
		finished = false;
	}
	
	public Horse(int horseNumber, int position, int stride, FinishLine finishline) {
		this.horseNumber = horseNumber;
		this.position = position;
		this.finishline = finishline;
		this.stride = stride;
		finished = false;
	}
	
	public synchronized void sprint() {
		while(position < finishline.getFinishline()) {
			position = position + stride;
		}
		finished = true;
		if(finishline.getWinner() == -1)
			finishline.setWinner(horseNumber);
		System.out.println("#" + horseNumber + " finished.");
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
