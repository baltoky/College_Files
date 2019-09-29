package reflection_DB;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class VehicleManager {
	private ArrayList<Vehicle> vehicles;
	
	public VehicleManager() {
		this.vehicles = new ArrayList<Vehicle>();
	}
	
	public void addVehicle(Vehicle v) {
		this.vehicles.add(v);
	}
	
	public void addRandomVehicles(int number) {
		Vehicle v;
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

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void printVehicles() {
		for(Vehicle v: this.vehicles) {
			System.out.println(v.toString());
		}
	}

}
