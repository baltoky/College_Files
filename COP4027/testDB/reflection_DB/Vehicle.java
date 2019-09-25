package reflection_DB;

import java.io.Serializable;

public class Vehicle implements Serializable{
	private static final long serialVersionUID = 1;
	private String make;
	private String size;
	private double weight;
	private double engineSize;
	private boolean isImport;
	
	public enum Make{
		Chevy,
		Ford, 
		Toyota, 
		Nissan, 
		Hyundai;
		private final static int COUNT = 5;
		public static int getCount() {return COUNT;}
	}
	
	public enum Size{
		Compact,
		Intermediate,
		FullSize;
		private final static int COUNT = 3;
		public static int getCount() {return COUNT;}
	}
	
	public Vehicle() {
		
	}
	
	public Vehicle(String make, String size, double weight, double engineSize, boolean isImport) {
		setMake(make);
		setSize(size);
		setWeight(weight);
		setEngineSize(engineSize);
		setImport(isImport);
	}
	
	public void setMake(String make) {
		if(!make.isEmpty())
			this.make = make;
		else
			System.out.println("Vehicle must have a make.");
	}
	
	public void setSize(String size) {
		if(!size.isEmpty())
			this.size = size;
		else
			System.out.println("Vehicle must have a size.");
	}
	
	public void setWeight(double weight) {
		if(weight > 0) {
			if(this.size.equals(Size.Compact.toString()) && (weight >= 1500.0 && weight < 2000.0))
				this.weight = weight;
			else if(this.size.equals(Size.Intermediate.toString()) && (weight >= 2000.0 && weight < 2500.0))
				this.weight = weight;
			else if(this.size.equals(Size.FullSize.toString()) && (weight >= 2500.0 && weight < 4000.0))
				this.weight = weight;
			else
				System.out.println("Weight does not line up with it's size.");
		}
		else 
			System.out.println("Weight must be a positive number.");
	}
	
	public void setEngineSize(double engineSize) {
		if(engineSize > 0)
			this.engineSize = engineSize;
		else
			System.out.println("Engine size must be a positive number.");
	}

	public void setImport(boolean isImport) {
		this.isImport = isImport;
	}

	public String getMake() {
		return make;
	}

	public String getSize() {
		return size;
	}

	public double getWeight() {
		return weight;
	}

	public double getEngineSize() {
		return engineSize;
	}

	public boolean isImport() {
		return isImport;
	}
	
	public String toString() {
		return "[" + this.make + ", " + this.size + ", " + this.weight + ", " + this.engineSize + ", " + this.isImport + "]";
	}
	
}
