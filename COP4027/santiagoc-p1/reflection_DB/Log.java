package reflection_DB;

import java.io.*;
import java.time.*;
import java.time.format.*;

/**
Student Name: Cesar Santiago
File Name: Log.java
Assignment number 1

A static logging file that opens a file and logs all activity to it.
*/
public class Log {
	private static File logfile;
	private static FileWriter fos;
	
	/**
	 * @param toOutput
	 * Takes in a String object to output to a file stream that is open.
	 */
	public static void log(String toOutput) {
		try {
			
			LocalDateTime dt = LocalDateTime.now();
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy/HH:mm:ss");
			String dateTime = dt.format(dtFormat);
			fos.append("Date/Time: " + dateTime + " :\n");
			fos.append(toOutput + "\n\n");
			
		}catch (IOException e) {
			System.out.println("Could not append to file.");
		}
	}
	
	/**
	 * Opens a file stream.
	 */
	public static void startLog() {
		try {

			LocalDateTime dt = LocalDateTime.now();
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy_HHmmss");
			String dateTime = dt.format(dtFormat);
			
			logfile = new File("database_logs_" + dateTime + ".log");
			fos = new FileWriter(logfile);
		}catch(IOException e) {
			System.out.println("Could not start logging file.");
		}
	}
	
	/**
	 * Closes a file stream.
	 */
	public static void close(){
		try {
			fos.close();
		}catch(IOException e) {
			System.out.println("Cannot close file. The file may be close already.");
		}
	}
	
}
