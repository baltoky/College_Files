package reflection_DB;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
Student Name: Cesar Santiago
File Name: VehicleReader.java
Assignment number 1

Reads and writes to and from a serialized encrypted file.
*/
public class VehicleReader {
	
	/**
	 * @param filepath
	 * @param manager
	 * Opens an output stream with the filepath given.
	 * Writes into that file stream all of the Vehicle objects stored in manager.
	 * Closes the file stream.
	 */
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
	
	/**
	 * @param filepath
	 * @param manager
	 * Opens an input file stream with the given filepath.
	 * Retrieves the serialized objects from the file stream into a VehicleManager.
	 * Closes the file stream.
	 */
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
