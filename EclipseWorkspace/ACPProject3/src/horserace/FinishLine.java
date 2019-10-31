package horserace;

public class FinishLine {
	int finishline;
	int winner;

	public FinishLine(int finishline) {
		this.finishline = finishline;
		winner = -1;
	}
	
	public int getWinner() {
		return winner;
	}
	
	public boolean hasWinner() {
		return (getWinner() != -1);
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public int getFinishline() {
		return finishline;
	}

	public void setFinishline(int finishline) {
		this.finishline = finishline;
	}
	
}
