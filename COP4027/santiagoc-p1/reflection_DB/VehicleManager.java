package reflection_DB;

import java.util.ArrayList;
import java.util.Random;

/**
Student Name: Cesar Santiago
File Name: VehicleManager.java
Assignment number 1

Obtains a list of Vehicle objects and conducts operations.
*/
public class VehicleManager {
	
	private ArrayList<Vehicle> vehicles;
	
	/**
	 * Default constructor of the VehicleManager class.
	 * Initializes the ArrayList vehicles instance variable
	 */
	public VehicleManager() {
		this.vehicles = new ArrayList<Vehicle>();
	}
	
	/**
	 * @param v
	 * Adds a vehicle to the ArrayList vehicles.
	 */
	public void addVehicle(Vehicle v) {
		this.vehicles.add(v);
	}
	
	/**
	 * @param number
	 * Obtains all of the values that make a Vehicle class from a random algorithm.
	 * Creates a Vehicle from those values.
	 * Adds the vehicle to the instance variable ArrayList vehicles.
	 * Repeats for the value given in the number parameter.
	 */
	public void addRandomVehicles(int number) {
		Random rand = new Random();
		Vehicle.Make randomMake;
		Vehicle.Size randomSize;
		double randomWeight = 0.0;
		double randomEngineSize;
		boolean randomIsImported;
		
		for(int i = 0; i < number; i++) {

			randomMake = Vehicle.Make.values()[rand.nextInt(Vehicle.Make.getCount())];
			randomSize = Vehicle.Size.values()[rand.nextInt(Vehicle.Size.getCount())];
			if(randomSize == Vehicle.Size.Compact) {
				randomWeight = (2000 - 1500) * rand.nextDouble() + 1500;
			}else if(randomSize == Vehicle.Size.Intermediate) {
				randomWeight = (2500 - 2000) * rand.nextDouble() + 2000;
			}else if(randomSize == Vehicle.Size.FullSize) {
				randomWeight = (4000 - 2500) * rand.nextDouble() + 2500;
			}
			randomEngineSize = (5000 - 2000) * rand.nextDouble() + 2000;
			randomIsImported = rand.nextBoolean();
			
			this.addVehicle(new Vehicle(randomMake.toString(), randomSize.toString(), randomWeight, randomEngineSize, randomIsImported));
			
		}
	}

	/**
	 * @return vehicles
	 */
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * @param vehicles
	 * Sets the vehicles instance variable to the parapeter's values.
	 */
	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	/**
	 * Prints all of the vehicles held in the 'vehicles' ArrayList to the console.
	 */
	public void printVehicles() {
		for(Vehicle v: this.vehicles) {
			System.out.println(v.toString());
		}
	}

}
