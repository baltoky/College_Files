package project5;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * Student Name: Cesar Santiago
 * File Name: Database.java
 * Assignment Number: 5
 * 
 * Database class that handles SQL calls to tables that are initialized.
 */


public class Database implements DatabaseInterface{
	public static final String DATABASE_FILE = "database.properties";
	
	private static String url;
	private static String username;
	private static String password;
	private static Connection connection;
	private static Statement statement;
	private static ResultSet result;
	
	/**
	 * Constructor for the Database, initializes and creates the database and tables.
	 */
	public Database() {
		try {
			init(DATABASE_FILE);
			dropDatabase();
			createDatabase();
		} catch (Exception e) {
			System.out.println("Failed to achieve derby connection.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Drops all of the tables if they exist in the database.
	 */
	public void dropDatabase() {
		try {
			executeStatement("DROP TABLE Instruments");
			executeStatement("DROP TABLE Locations");
			executeStatement("DROP TABLE Inventory");
		} catch (Exception e) {
			System.out.println("Attempted to drop tables. The tables could not be found.");
		}
	}
	
	/**
	 * Creates all of the tables needed for the program.
	 * @throws Exception
	 */
	private void createDatabase() throws Exception {
		createInstruments();
		createLocations();
		createInventory();
	}
	
	/**
	 * Executes a statement on the database.
	 * @param stat - statement to execute.
	 */
	public void executeStatement(String stat) {
		try {
			statement.execute(stat);
		}
		catch(Exception e) {
			System.out.println("ERROR: Failed to execute statement." + stat);
		}
		System.out.println(stat);
	}
	
	/**
	 * Executes a query on the database.
	 * @param query - query to execute.
	 * @return result - the result of the query.
	 * Executes a query and stores the result into the result instance variable.
	 */
	public String executeQuery(String query) {
		try {
			result = statement.executeQuery(query);
		}
		catch(Exception e){

			System.out.println("ERROR: Failed to complete query." + query);
		}
		System.out.println(query);
		return getResult();
	}
	
	/**
	 * Returns the result of a query from the result set.
	 * @return resultString.
	 */
	private String getResult() {
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
		catch (Exception e) {
			System.out.println("ERROR: Could not get result from the query.");
		}
		return resultString;
	}

	/**
	 * Gets the connection to the database from the driver manager.
	 * @return a connection object.
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	/**
	 * Sets the connection and creates a statement.
	 */
	public void setConnection() {
		try {
			connection = this.getConnection();
			statement = connection.createStatement();
		}catch(Exception e) {
			System.out.println("Could not create statement.");
		}
	}
	
	/**
	 * Initializes the database given a properties file.
	 * @param fileName - the name of the properties file.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void init(String fileName)
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
	    
	    setConnection();
	    
	    System.out.println("Initiated Database safely.");
	}
	
	/**
	 * Creates an inserts a table of instruments in the database.
	 * @throws Exception
	 */
	public void createInstruments() throws Exception{
		executeStatement("CREATE TABLE Instruments (instName CHAR(12),instNumber INTEGER,cost DOUBLE,descrip CHAR(20))");
		executeStatement("INSERT INTO Instruments VALUES ('guitar',1,100.0,'yamaha')");
		executeStatement("INSERT INTO Instruments VALUES ('guitar',2,500.0,'gibson')");
		executeStatement("INSERT INTO Instruments VALUES ('bass',3,250.0,'fender')");
		executeStatement("INSERT INTO Instruments VALUES ('keyboard',4,600.0,'roland')");
		executeStatement("INSERT INTO Instruments VALUES ('keyboard',5,500.0,'alesis')");
	}

	/**
	 * Returns a string from a query of all instruments.
	 * @return
	 * @throws SQLException
	 */
	public String getAllInstruments() throws SQLException {
		return executeQuery("SELECT * FROM Instruments");		
	}
	

	/**
	 * Creates an inserts a table of locations in the database.
	 * @throws Exception
	 */
	public void createLocations() throws Exception{
		executeStatement("CREATE TABLE Locations (locName CHAR(12),locNumber INTEGER,address CHAR(50))");
		executeStatement("INSERT INTO Locations VALUES ('PNS',1,'Pensacola Florida')");
		executeStatement("INSERT INTO Locations VALUES ('CLT',2,'Charlotte North Carolina')");
		executeStatement("INSERT INTO Locations VALUES ('DFW',3,'Dallas Fort Worth Texas')");
	}
	
	/**
	 * Returns a string from a query of all locations.
	 * @return
	 * @throws SQLException
	 */
	public String getAllLocations() throws SQLException {
		return executeQuery("SELECT * FROM Locations");
	}

	/**
	 * Creates an inserts a table of inventory in the database.
	 * @throws Exception
	 */
	public void createInventory() throws Exception
	{
		executeStatement("CREATE TABLE Inventory (iNumber INTEGER,lNumber INTEGER,quantity INTEGER)");
		executeStatement("INSERT INTO Inventory VALUES (1,1,15)");
		executeStatement("INSERT INTO Inventory VALUES (1,2,27)");
		executeStatement("INSERT INTO Inventory VALUES (1,3,20)");
		executeStatement("INSERT INTO Inventory VALUES (2,1,10)");
		executeStatement("INSERT INTO Inventory VALUES (2,2,10)");
		executeStatement("INSERT INTO Inventory VALUES (2,3,35)");
		executeStatement("INSERT INTO Inventory VALUES (3,1,45)");
		executeStatement("INSERT INTO Inventory VALUES (3,2,10)");
		executeStatement("INSERT INTO Inventory VALUES (3,3,17)");
		executeStatement("INSERT INTO Inventory VALUES (4,1,28)");
		executeStatement("INSERT INTO Inventory VALUES (4,2,10)");
		executeStatement("INSERT INTO Inventory VALUES (4,3,16)");        
	}
	
	/**
	 * Returns a string from a query of all inventory.
	 * @return
	 * @throws SQLException
	 */
	public String getAllInventory() throws SQLException {
		return executeQuery("SELECT * FROM Inventory");		
	}
	  
}
