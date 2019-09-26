package reflection_DB;

import java.io.*;
import java.time.*;
import java.time.format.*;

public class Log {
	private static File logfile;
	private static FileWriter fos;
	
	public static void log(String s) {
		try {
			
			LocalDateTime dt = LocalDateTime.now();
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy/HH:mm:ss");
			String dateTime = dt.format(dtFormat);
			fos.append("Date/Time: " + dateTime + " :\n");
			fos.append(s + "\n\n");
			
		}catch (IOException e) {
			System.out.println("Could not append to file.");
		}
	}
	
	public static void startLog() {
		try {
			logfile = new File("database_logs_" + LocalDate.now() + ".log");
			fos = new FileWriter(logfile);
		}catch(IOException e) {
			System.out.println("Could not start logging file.");
		}
	}
	
	public static void close(){
		try {
			fos.close();
		}catch(IOException e) {
			System.out.println("Cannot close file. The file may be close already.");
		}
	}
	
}
