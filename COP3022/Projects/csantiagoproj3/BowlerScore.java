package csantiagoproj3;

public class BowlerScore{

	private String laneName;
	private Score score;

	public BowlerScore(){
		setLaneName(null);
		setScore(null, 0, null, 0.0, 0);
	}

	public BowlerScore(String laneName,
			String courseName, int score, String date,
			double courseRating, int courseSlope){
		setLaneName(laneName);
		setScore(courseName, score, date,
				courseRating, courseSlope);
	}

	public void setLaneName(String laneName){this.laneName = laneName;}
	public void setScore(String courseName, int score, String date,
			double courseRating, int courseSlope){
		this.score = new Score(courseName, score, date,
				courseRating, courseSlope);
	}

	public String getLaneName(){return this.laneName;}
	public Score getScore(){return this.score;}

	public String toString(){
		return "" + this.getLaneName() + "\n" + this.getScore().toString();
	}

}
