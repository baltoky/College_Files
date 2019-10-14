package reflection_DB;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class VehicleReader {
	
	public static void vehicleToFile(String filepath, VehicleManager manager) {
		try {
			FileOutputStream fos = new FileOutputStream(filepath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			for(Vehicle v : manager.getVehicles()) {
				oos.writeObject(v);
			}
			
			oos.close();
			fos.close();
		}
		catch (IOException e) {
			System.out.println("Could not write to file " + filepath + ".");
		}
	}
	
	public static void readVehicles(String filepath, VehicleManager manager) {
		try {
			FileInputStream fis = new FileInputStream(filepath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			while(true) {
				try {
					manager.addVehicle((Vehicle) ois.readObject());
				}
				catch(EOFException e) {
					break;
				}
			}
			
			ois.close();
			fis.close();
		}
		catch (IOException e) {
			System.out.println("Could not read from file" + filepath + ".");
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find class to cast to.");
		}
	}
}
