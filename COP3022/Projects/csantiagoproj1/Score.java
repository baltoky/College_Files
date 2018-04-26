package csantiagoproj1;

class Score{

	private String courseName;
	private int score;
	private String date;
	private double courseRating;
	private int courseSlope;

	public Score(){
		this.courseName = null;
		this.score = 0;
		this.date = null;
		this.courseRating = 0.0;
		this.courseSlope = 0;
	}

	public Score(String courseName, int score, String date, double courseRating, int courseSlope){
		this.courseName = courseName;
		this.score = score;
		this.date = date;
		this.courseRating = courseRating;
		this.courseSlope = courseSlope;
	}

	public String getCourseName(){return courseName;}
	public int getScore(){return score;}
	public String getDate(){return date;}
	public double getCourseRating(){return courseRating;}
	public int getCourseSlope(){return courseSlope;}

	public void setCourseName(String courseName){this.courseName = courseName;}
	public void setScore(int score){this.score = score;}
	public void setDate(int month, int day, int year){this.date = "" + month + "/" + day + "/" + year;}
	public void setCourseRating(double courseRating){this.courseRating = courseRating;}
	public void setCourseSlope(int courseSlope){this.courseSlope = courseSlope;}

	public String toString(){
		String scoreString = " ";
		scoreString = "" + getScore() + "\t" + getDate() + "\t\t"
			+ getCourseName() + "\t\t" + getCourseRating() + "\t\t" + getCourseSlope();
		return scoreString;
	}

}
