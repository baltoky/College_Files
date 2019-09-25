package reflection_DB;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;

public class Main {
	
	public static final String VEHICLEPATH = "vehicle.dat";
	public static final String CLASSPATH = "reflection_DB.Vehicle";
	public static final int NUM_OF_VEHICLES = 10;
	public static final String TABLENAME = "Vehicle";
	
	public static void main(String[] args) {
		Database.parseArguments(args);
		Database.setConnection();
		
		VehicleManager vm1 = new VehicleManager();
		VehicleManager vm2 = new VehicleManager();
		
		vm1.addRandomVehicles(NUM_OF_VEHICLES);
		VehicleReader.vehicleToFile(VEHICLEPATH, vm1);
		
		VehicleReader.readVehicles(VEHICLEPATH, vm2);
		vm2.printVehicles();
		
		Database.executeStatement("DROP TABLE " + TABLENAME);
		Database.createTableForClass(CLASSPATH, TABLENAME);
		String statement;
		
		for(Vehicle v : vm2.getVehicles()) {
			statement = "INSERT INTO " + TABLENAME +" VALUES('"+  v.getMake() +"', '"
			+ v.getSize() +"', "+ v.getWeight() +", " + v.getEngineSize() + ", " + v.isImport() + ")";
			Database.executeStatement(statement);
		}
		
		String res = "";
		
		Database.executeQuery("SELECT * FROM " + TABLENAME);
		res = Database.getResult();
		System.out.println(res);

		Database.executeQuery("SELECT * FROM " + TABLENAME + " WHERE make = 'Chevy' OR make = 'Toyota'");
		res = Database.getResult();
		System.out.println(res);

		Database.executeQuery("SELECT * FROM " + TABLENAME + " WHERE weight >= 2500");
		res = Database.getResult();
		System.out.println(res);
		
		Database.executeStatement("DROP TABLE " + TABLENAME);
		
		Database.closeConnection();
		
	}
	
}
