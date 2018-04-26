package csantiagolab3;

import java.util.*;

public class ALaCarte extends Vacation{
	private String hotelName;
	private double roomCost;
	private String airline;
	private double airfare;
	private double meals;

	public ALaCarte(){
		super();
		this.hotelName = "";
		this.roomCost = 0.0;
		this.airline = "";
		this.airfare = 0.0;
		this.meals = 0.0;
	}

	public ALaCarte(String destination, double budget, String hotelName, double roomCost, String airline, double airfare, double meals){
		super(destination, budget);
		this.hotelName = hotelName;
		this.roomCost = roomCost;
		this.airline = airline;
		this.airfare = airfare;
		this.meals = meals;
	}


	void setHotelName(String hotelName){this.hotelName = hotelName;}
	void setRoomCost(double roomCost){this.roomCost = roomCost;}
	void setAirline(String airline){this.airline = airline;}
	void setAirfare(double airfare){this.airfare = airfare;}
	void setMeals(double meals){this.meals = meals;}

	String getHotelName(){return this.hotelName;}
	double getRoomCost(){return this.roomCost;}
	String getAirline(){return this.airline;}
	double getAirfare(){return this.airfare;}
	double getMeals(){return this.meals;}

	
	public double budgetBalance(){
		return this.getBudget() - this.getRoomCost() - this.getAirfare() - this.getMeals();
	}

	public String toString(){
		return super.toString() + "Hotel Name: " + this.getHotelName() + ", Room Cost: " + this.getRoomCost() + "\n\tAirline: " + 
			this.getAirline() + ", Airfare: " + this.getAirfare() + ", Meals: " + this.getMeals() + "\n\tTotal Buget Balance: " + 
			this.budgetBalance() + "\n\n";
	}

}
