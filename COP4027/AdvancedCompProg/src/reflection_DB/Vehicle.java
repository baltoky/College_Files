package reflection_DB;

import java.io.Serializable;

public class Vehicle implements Serializable{
	private String make;
	private String size;
	private double weight;
	private double engineSize;
	private boolean isImport;
	
	public Vehicle() {
		
	}
	
	public void getMake(String make) {
		if(!make.isEmpty())
			this.make = make;
		else
			System.out.println("Vehicle must have a make.");
	}
	
	public void getSize(String size) {
		if(!size.isEmpty())
			this.size = size;
		else
			System.out.println("Vehicle must have a size.");
	}
	
	public void getWeight(double weight) {
		if(weight > 0) {
			if(this.size.equals("compact") && this.weight >= 1500 && this.weight < 2000)
				this.weight = weight;
			else if(this.size.equals("intermidiate") && this.weight >= 2000 && this.weight < 2500)
				this.weight = weight;
			else if(this.size.equals("fullSize") && this.weight >= 2500 && this.weight < 4000)
				this.weight = weight;
			else
				System.out.println("Weight does not line up with it's size.");
		}
		else 
			System.out.println("Weight must be a positive number.");
	}
	
	public void getEngineSize(double engineSize) {
		if(engineSize > 0)
			this.engineSize = engineSize;
		else
			System.out.println("Engine size must be a positive number.");
	}
	
	
	
}
