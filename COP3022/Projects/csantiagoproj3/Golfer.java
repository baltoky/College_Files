package csantiagoproj3;

import java.util.*;

public class Golfer extends Player{

	public final static double BASE_STK_DIF = 133;

	private Course homeCourse;
	private ArrayList<Score> scores;

	public Golfer(){
		super();
		this.homeCourse = new Course();
		this.scores = new ArrayList<Score>();
	}

	public Golfer(String name, Course homeCourse, Score score){
		super(name);
		this.homeCourse = homeCourse;
		this.scores = new ArrayList<Score>();
	}

	public Course getHomeCourse(){return homeCourse;}
	public void setHomeCourse(Course homeCourse){this.homeCourse = homeCourse;}

	private Score getScoreAt(int index){
		return this.scores.get(index);
	}

	public void addScore(Score score){
		this.scores.add(score);
	}
	public boolean deleteScore(int index){
		if(this.scores.get(index) != null){
			this.scores.remove(index);
			return true;
		}
		else return false;
	}

	public Score lowestScore(){
		int max = Integer.MIN_VALUE;
		int index = 0;
		for(int i = 0; i < this.scores.size(); i++){
			if(scores.get(i).getScore() > max){
				index = i;
				max = scores.get(i).getScore();
			}
		}
		return scores.get(index);
	}

	public String toString(){
		String golferString = "";

		golferString = super.toString() + "Home Course: " + homeCourse.toString();

		if(this.scores != null){
			golferString += "\nScore Date Course Course Rating Course Slope\n";
			for(Score s: scores){
				golferString += s.toString();
			}
		}

		return golferString;
	}

	public double calculateHandicap(){
		double handicap = 0.0;
		double differentials[] = new double[10];
		double temp = 0;
		int index;

		for(int i = scores.size() - 1, j = 0; i >= scores.size() - 10; i--, j++){
			differentials[j] = (scores.get(i).getScore() - scores.get(i).getCourse().getCourseRating()) *
				(BASE_STK_DIF / scores.get(i).getCourse().getCourseSlope());
		}

		
		for(int i = 10; i < 0; i--){
			for(int j = 0; j < i; j++){
				if(differentials[j] > differentials[i]){
					temp = differentials[i];
					differentials[i] = differentials[j];
					differentials[j] = temp;
				}
			}
		}

		for(int i = 0; i < 5; i++)
			handicap += differentials[i];

		handicap *= 0.96;

		return handicap;
	}

}
