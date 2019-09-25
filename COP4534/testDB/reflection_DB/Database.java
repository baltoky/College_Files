package reflection_DB;

import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Properties;

public class Database {
	private static String url;
	private static String username;
	private static String password;
	private static Connection connection;
	private static Statement statement;
	private static ResultSet result;
	
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
	
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url, username, password);
	}

	public static void parseArguments(String [] args) {
		if (args.length == 0){   
			System.out.println(
					"Usage: java -classpath driver_class_path" 
							+ File.pathSeparator 
							+ ". TestDB database.properties");
			return;
	      }
		else {
			System.out.println("args[0] = " + args[0]);
			try {
				Database.init(args[0]);
			}
			catch(Exception e) {}
		}
	}
	
	public static Statement setConnection() {
		try {
			connection = Database.getConnection();
			statement = connection.createStatement(); 
		}
		catch(Exception e) {}
		return statement;
	}
	
	public static void executeStatement(String stat) {
		try {
			statement.execute(stat);
		}
		catch(Exception e) {
			System.out.println("Failed to complete statement");
		}
	}
	
	public static ResultSet executeQuery(String query) {
		try {
			result = statement.executeQuery(query);
		}
		catch(Exception e){
			System.out.println("Failed to complete query");
		}
		return result;
	}
	
	public static String getResult() {
		String resultString = "";
		int cols ;
		try {
			ResultSetMetaData rsm = result.getMetaData();
			cols = rsm.getColumnCount();
		
			while(result.next())
			{
				for(int i = 1; i <= cols; i++)
					resultString += result.getString(i) + " ";
				resultString += "\n";
			}
		}
		catch (SQLException e) {
			
		}
		return resultString;
	}
	
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
			System.out.println(typesToTable.toString());
			for(int i = 1; i < typesToTable.size(); i++) {
				tableCreate += typesToTable.get(i) + " ";
				i++;
				tableCreate += typesToTable.get(i);
				if(typesToTable.size() != i + 1)
					tableCreate += ", ";
			}
			tableCreate += ")";
			System.out.println(tableCreate);
			Database.executeStatement(tableCreate);
		}
		catch(ClassNotFoundException e) {
			
		}
	}
	
	/*
	public static ArrayList<String> ReflectionExample1(String className)
	{
		ArrayList<String> datatype = new ArrayList<String>();
		try {
			Class c = Class.forName(className);
			Field f[] = c.getDeclaredFields();
			System.out.println("fields:");
			for(Field field : f) {
				datatype.add(field.getType().toString());
			}                    
		}
		catch (Throwable e) {
			System.err.println(e);
		}  
		return datatype;
	}
	 * */
	
	public static int closeConnection() {
		try {
			connection.close();
			return 0;
		}
		catch(SQLException e) {
			
		}
		return 1;
	}
	
}
