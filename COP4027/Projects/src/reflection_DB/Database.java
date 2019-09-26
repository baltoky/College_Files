package reflection_DB;

import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Properties;

/**
Student Name: Cesar Santiago
File Name: Database.java
Assignment number 1

The class that handles the database aspect of the program.
*/
public class Database {
	private static String url;
	private static String username;
	private static String password;
	private static Connection connection;
	private static Statement statement;
	private static ResultSet result;
	
	/**
	 * @param fileName
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * Initializes the database.
	 */
	public static void init(String fileName)
	         throws IOException, ClassNotFoundException
	{  
		Properties props = new Properties();
	    FileInputStream in = new FileInputStream(fileName);
	    props.load(in);

	    String driver = props.getProperty("jdbc.driver");
	    url = props.getProperty("jdbc.url");
	    username = props.getProperty("jdbc.username");
	    if (username == null) username = "";
	    password = props.getProperty("jdbc.password");
	    if (password == null) password = "";
	    if (driver != null)
	    	Class.forName(driver);
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url, username, password);
	}

	/**
	 * @param args
	 * Parses the arguments given by the user and sends it to the init function.
	 */
	public static void parseArguments(String [] args) {
		if (args.length == 0){   
			System.out.println(
					"Usage: java -classpath driver_class_path" 
							+ File.pathSeparator 
							+ ". TestDB database.properties");
			return;
	      }
		else {
			try {
				Database.init(args[0]);
			}
			catch(Exception e) {
				Log.log("ERROR: Failed to achieve derby connection.");
			}
		}
	}
	
	/**
	 * @return statement
	 * Sets a connection to the database and returns it as a statement.
	 */
	public static Statement setConnection() {
		try {
			connection = Database.getConnection();
			statement = connection.createStatement(); 
		}
		catch(Exception e) {}
		return statement;
	}
	
	/**
	 * @param stat
	 * Executes a statement given as a String variable.
	 */
	public static void executeStatement(String stat) {
		try {
			statement.execute(stat);
		}
		catch(Exception e) {
			Log.log("ERROR: Failed to execute statement." + stat);
		}
		Log.log(stat);
	}
	
	/**
	 * @param query
	 * @return result
	 * Executes a query and stores the result into the result instance variable.
	 */
	public static ResultSet executeQuery(String query) {
		try {
			result = statement.executeQuery(query);
		}
		catch(Exception e){

			Log.log("ERROR: Failed to complete query." + query);
		}
		Log.log(query);
		return result;
	}
	
	/**
	 * @return resultString.
	 * Logs and returns the result of the last query that was retrieved.
	 */
	public static String getResult() {
		String resultString = "";
		int cols ;
		try {
			ResultSetMetaData rsm = result.getMetaData();
			cols = rsm.getColumnCount();
		
			while(result.next())
			{
				for(int i = 1; i <= cols; i++)
					resultString += result.getString(i) + " \t";
				resultString += "\n";
			}
		}
		catch (Exception e) {
			Log.log("ERROR: Could not get result from the query.");
		}
		Log.log(resultString);
		return resultString;
	}
	
	/**
	 * @param className
	 * @param tableName
	 * Creates the table on the database for a class given in the String className.
	 */
	public static void createTableForClass(String className, String tableName) {
		ArrayList<String> typesToTable = new ArrayList<String>();
		String tableCreate = "CREATE TABLE " + tableName + " (";
		try {
			Class c = Class.forName(className);
			Field f[] = c.getDeclaredFields();
			for(Field field : f) {
				typesToTable.add(field.getName());
				if(field.getType().toString().equalsIgnoreCase("class java.lang.String")) {
					typesToTable.add("CHAR(20)");
				}else if(field.getType().toString().equalsIgnoreCase("double")) {
					typesToTable.add("DOUBLE");
				}else if(field.getType().toString().equalsIgnoreCase("boolean")) {
					typesToTable.add("BOOLEAN");
				}
			}
			for(int i = 1; i < typesToTable.size(); i++) {
				tableCreate += typesToTable.get(i) + " ";
				i++;
				tableCreate += typesToTable.get(i);
				if(typesToTable.size() != i + 1)
					tableCreate += ", ";
			}
			tableCreate += ")";
			Log.log(tableCreate);
			Database.executeStatement(tableCreate);
		}
		catch(ClassNotFoundException e) {
			
		}
	}
	
	/**
	 * Closes the connection to the database.
	 */
	public static void closeConnection() {
		try {
			connection.close();
		}
		catch(Exception e) {
			Log.log("ERROR: Failed to close connection to the derby databse.");
		}
	}
	
}
