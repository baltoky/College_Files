package csantiagolab3;
import java.util.*;

public abstract class Vacation{
	private String destination;
	private double budget;

	public Vacation(){
		this.destination = "";
		this.budget = 0.0;
	}

	public Vacation(String destination, double budget){
		this.destination = destination;
		this.budget = budget;
	}

	public void setDestination(String destination){this.destination = destination;}
	public void setBudget(double budget){this.budget = budget;}

	public String getDestination(){return this.destination;}
	public double getBudget(){return this.budget;}

	public abstract double budgetBalance();

	public String toString(){return "Destination: " + this.getDestination() + ", Budget: " + 
		this.getBudget() + "\n";}

}
