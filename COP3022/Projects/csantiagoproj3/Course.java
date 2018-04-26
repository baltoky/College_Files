package csantiagoproj3;
import java.lang.*;

public class Course{
	private String courseName;
	private double courseRating;
	private int courseSlope;

	public Course(String courseName, double courseRating, int courseSlope){
		setCourseName(courseName);
		setCourseRating(courseRating);
		setCourseScore(courseSlope);
	}

	public Course(){
		setCourseName("");
		setCourseRating(0.0);
		setCourseScore(0);
	}

	public void setCourseName(String courseName){this.courseName = courseName;}
	public void setCourseRating(double courseRating){
		try{
			if(courseRating >= 60 && courseRating <= 80)
				this.courseRating = courseRating;
			else
				throw new Exception("Course Rating is out of bounds.");
		}catch(Exception e){
			System.out.printf("%s%n", e.getMessage());
		}
	}
	public void setCourseScore(int courseSlope){
		try{
			if(courseSlope >= 55 && courseSlope <= 155)
				this.courseSlope = courseSlope;
			else
				throw new Exception("Course Slope is out of bounds.");
		}catch(Exception e){
			System.out.printf("%s%n", e.getMessage());
		}
	}

	public String getCourseName(){return this.courseName;}
	public double getCourseRating(){return this.courseRating;}
	public int getCourseSlope(){return this.courseSlope;}

	public String toString(){
		return "Course Name: " + getCourseName() + " , Course Rating: " + getCourseRating() 
			+ " , Course Slope: " + getCourseSlope() + "\n";
	}

}
