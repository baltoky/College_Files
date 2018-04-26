package csantiagoproj3;

import java.util.*;

public class Bowler extends Player{

	public static final int BASE_BWL_AVG = 200;

	private String teamName;
	private ArrayList<BowlerScore> scores;
	
	/**
	 * Default Constructor.
	 */
	public Bowler(){
		super();
		setTeamName(null);
 		this.scores = new ArrayList<BowlerScore>();
	}

	/**
	 * Constructor.
	 *
	 * @param name		A string defining the name of the bowler.
	 * @param teamName	A string defining the name of the team the bowler belongs to.
	 */
	public Bowler(String name, String teamName){
		super(name);
		setTeamName(teamName);
		this.scores = new ArrayList<BowlerScore>();
	}

	public void setTeamName(String teamName){this.teamName = teamName;}
	public void setScoreAt(int index, BowlerScore score){this.scores.set(index, score);}

	public String getTeamName(){return this.teamName;}
	public BowlerScore getScoreAt(int index){return scores.get(index);}

	public double calculateHandicap(){
		double handicap = 0.0;
		if(scores.size() <= 5){
			for(BowlerScore b: scores)
				handicap += b.getScore().getScore();
			handicap /= scores.size();
		}
		else{
			for(int i = scores.size(); i < scores.size() - 5; i--){
				handicap += scores.get(i).getScore().getScore();
			}
			handicap /= 5;
		}

		handicap = BASE_BWL_AVG - handicap;
		handicap *= 0.8;

		return handicap;//TODO: Delete me!
	}

}
