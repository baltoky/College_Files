package csantiagoproj1;


class Golfer{

	static public int nextIdNumber = 1000;
	public final static int NOTFOUND = -1;

	private String name;
	private String homeCourse;
	private int idNumber;
	private Score scores[];

	public Golfer(){
		this.name = null;
		this.homeCourse = null;
		this.idNumber = nextIdNumber++;
		this.scores = null;
	}

	public Golfer(String name, String homeCourse, Score score){
		this.name = name;
		this.homeCourse = homeCourse;
		this.idNumber = nextIdNumber++;
		this.addScore(score);
	}

	public String getName(){return name;}
	public String getHomeCourse(){return homeCourse;}
	public int getIdNumber(){return idNumber;}
	public void setName(String name){this.name = name;}
	public void setHomeCourse(String homeCourse){this.homeCourse = homeCourse;}
	public void setIdNumber(int idNumber){this.idNumber = nextIdNumber++;}

	private int findScore(String date){
		for(int i = 0; i < this.scores.length; i++){
			if(scores[i].getDate().compareTo(date) == 0)
				return i;
		}
		return NOTFOUND;
	}

	public void addScore(Score score){
		if(this.scores == null){
			this.scores = new Score[1];
		}
		else{
			Score[] tempScores = new Score[this.scores.length + 1];
			for(int i = 0; i < tempScores.length - 1; i++)
				tempScores[i] = this.scores[i];
			this.scores = tempScores;
		}
		this.scores[this.scores.length - 1] = score;
	}
	public boolean deleteScore(String scoreDate){
		int find = findScore(scoreDate);
		Score temp = new Score();
		if(find != NOTFOUND){
			temp = this.scores[scores.length - 1];
			this.scores[find] = temp;
			this.scores = new Score[scores.length - 1];
		}
		else
			return false;

		return true;

	}
	public Score getScore(String date){
		int find = findScore(date);
		if(find == NOTFOUND)
			return null;
		else
			return this.scores[findScore(date)];
	}
	public Score lowestScore(){
		Score min = new Score();
		min.setScore(200);
		if(this.scores == null)
			return null;

		for(int i = 0; i < this.scores.length; i++){
			if(min.getScore() > this.scores[i].getScore())
				min = this.scores[i];
		}

		return min;
	}

	public String toString(){
		String golferString;

		golferString = "" + getName() + "\tID Number: "  + getIdNumber() + "\tHome Course: " + getHomeCourse();

		if(this.scores != null){
			golferString += "\n\nScore\tDate\t\tCourse\t\t\tCourse Rating\tCourse Slope\n";
			for(int i = 0; i < this.scores.length; i++)
				golferString += this.scores[i].toString() + "\n";
		}

		return golferString;
	}
}
