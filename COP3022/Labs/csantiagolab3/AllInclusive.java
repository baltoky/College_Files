package csantiagolab3;
import java.util.*;

public class AllInclusive extends Vacation{

	private String brand;
	private int rating;
	private double price;

	public AllInclusive(){
		super();
		this.brand = "";
		this.rating = 0;
		this.price = 0.0;
	}
	public AllInclusive(String destination, double budget, String brand, int rating, double price){
		super(destination, budget);
		this.brand = brand;
		this.rating = rating;
		this.price = price;
	}

	void setBrand(String brand){this.brand = brand;}
	void setRating(int rating){this.rating = rating;}
	void setPrice(double price){this.price = price;}

	String getBrand(){return this.brand;}
	int getRating(){return this.rating;}
	double getPrice(){return this.price;}

	public double budgetBalance(){
		return this.getBudget() - this.getPrice();
	}

	public String toString(){
		return super.toString() + "Brand: " + this.getBrand() + ", Rating: " + this.getRating() + ", Price: " + 
			this.getPrice() + ", Total Buget Balance: " + this.budgetBalance() + "\n\n";
	}

}
