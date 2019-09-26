package reflection_DB;

import java.io.Serializable;

/**
Student Name: Cesar Santiago
File Name: Vehicle.java
Assignment number 1

Defines a Vehicle object, the data that it should handle, and it's output.
*/
public class Vehicle implements Serializable{
	private static final long serialVersionUID = 1727898691353381303L;
	private String make;
	private String size;
	private double weight;
	private double engineSize;
	private boolean isImport;

	/**
	 *	enumerated make class for the Vehicle instance.
	 *	Used for database purposes.
	 *	Includes a static count function to retrieve the count of enumerated objects.
	 */
	public enum Make{
		Chevy,
		Ford, 
		Toyota, 
		Nissan, 
		Hyundai;
		private final static int COUNT = 5;
		public static int getCount() {return COUNT;}
	}
	
	/**
	 *	enumerated size class for the Vehicle instance.
	 *	Used for database purposes.
	 *	Includes a static count function to retrieve the count of enumerated objects.
	 */
	public enum Size{
		Compact,
		Intermediate,
		FullSize;
		private final static int COUNT = 3;
		public static int getCount() {return COUNT;}
	}
	
	/**
	 * Default constructor for the Vehicle class
	 */
	public Vehicle() {
		
	}
	
	/**
	 * Constructor for the vehicle class
	 * @param make
	 * @param size
	 * @param weight
	 * @param engineSize
	 * @param isImport
	 * Sets the instance variables as their parameter values.
	 */
	public Vehicle(String make, String size, double weight, double engineSize, boolean isImport) {
		setMake(make);
		setSize(size);
		setWeight(weight);
		setEngineSize(engineSize);
		setImport(isImport);
	}
	
	/**
	 * @param make
	 * Sets the instance variable make as the parameter's value.
	 */
	public void setMake(String make) {
		if(!make.isEmpty())
			this.make = make;
		else
			System.out.println("Vehicle must have a make.");
	}
	
	/**
	 * @param size
	 * Sets the instance variable size as the parameter's value.
	 */
	public void setSize(String size) {
		if(!size.isEmpty())
			this.size = size;
		else
			System.out.println("Vehicle must have a size.");
	}
	
	/**
	 * @param weight
	 * Sets the instance variable weight as the parameter's value.
	 * assures that only the required weight is set.
	 */
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
	
	/**
	 * @param engineSize
	 * Sets the instance variable engineSize as the parameter's value.
	 */
	public void setEngineSize(double engineSize) {
		if(engineSize > 0)
			this.engineSize = engineSize;
		else
			System.out.println("Engine size must be a positive number.");
	}

	/**
	 * @param isImport
	 * Sets the instance variable isImport as the parameter's value.
	 */
	public void setImport(boolean isImport) {
		this.isImport = isImport;
	}

	/**
	 * @return make instance variable
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @return size instance variable
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @return weight instance variable
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @return engineSize instance variable
	 */
	public double getEngineSize() {
		return engineSize;
	}

	/**
	 * @return isImport instance variable
	 */
	public boolean isImport() {
		return isImport;
	}
	
	/**
	 * @return string for output
	 */
	@Override
	public String toString() {
		return "[" + this.make + ", " + this.size + ", " + this.weight + ", " + this.engineSize + ", " + this.isImport + "]";
	}
	
}
