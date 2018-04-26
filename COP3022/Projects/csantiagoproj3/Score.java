package csantiagoproj3;

/**
 * A class representing the score a Player gets on a game.
 */
public class Score{

	private int score;
	private String date;
	private Course course;

	/**
	 * The default constructor of the Score Class.
	 */
	public Score(){
		this.score = 0;
		this.date = null;
		this.course = null;
	}

	/**
	 * Constructor of the Score Class.
	 *
	 * @param courseName	The name of the course the score was achieved in.
	 * @param score		The score value that the class represents.
	 * @param date 		The String representing the date the score was achieved.
	 * @param courseRating 	The difficulty rating of the course the score was achieved in.
	 * @param courseSlope	The slope of the course the scroe was achieved in.
	 */
	public Score(String courseName,
			int score,
			String date,
			double courseRating,
			int courseSlope){
		this.score = score;
		this.date = date;
		this.course = new Course(courseName,
				courseRating,
				courseSlope);
	}

	/**
	 * Mutator method to set the score value.
	 *
	 * @param score		The score value in integer form.
	 */
	public void setScore(int score){this.score = score;}

	/**
	 * Mutator method for the date String.
	 *
	 * @param month		The month portion of the date.
	 * @param day		The day portion of the date.
	 * @param year		The year portion of the date.
	 */
	public void setDate(int month, int day, int year){this.date = "" + month + "/" + day + "/" + year;}

	/**
	 * Mutator method for the course object.
	 *
	 * @param courseName	The name of the course.
	 * @param courseRating	The rating of the course.
	 * @param courseSlope	The slope of the course.
	 */
	public void setCourse(String courseName,
			double courseRating,
			int courseSlope){
		this.course = new Course(courseName, courseRating, courseSlope);
	}

	/**
	 * Accessor method of the score value in integer form.
	 *
	 * @return 		<code>score</code> returns the score value in integer form.
	 */
	public int getScore(){return this.score;}

	/**
	 * Accessor method of the date string value.
	 *
	 * @return 		<code>date</code> returns the date string stored in a score object.
	 */
	public String getDate(){return this.date;}

	/**
	 * Accessor method of the course the score was taken in.
	 *
	 * @return 		<code>course</code> returns the course object that lies in a score object.
	 */
	public Course getCourse(){return this.course;}

	/**
	 * An override of the toString method used to return a formatted string.
	 *
	 * @return 		<code>Stirng</code> returns formatted stirng.
	 */
	public String toString(){
		String scoreString = "Score: " + getScore() + " ,Date: " + getDate() + "\n"
			+ course.toString();
		return scoreString;
	}


}
