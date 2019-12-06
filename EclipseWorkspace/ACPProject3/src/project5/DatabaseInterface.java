package project5;

/**
 * Student Name: Cesar Santiago
 * File Name: DatabaseInterface.java
 * Assignment Number: 5
 * 
 * An interface the server uses to communicate with the database.
 */

public interface DatabaseInterface {
	
	/**
	 * Drops all of the tables if they exist in the database.
	 */
	public void dropDatabase();
	
	/**
	 * Executes a statement on the database.
	 * @param stat - statement to execute.
	 */
	public void executeStatement(String stat);
	
	/**
	 * Executes a query on the database.
	 * @param query - query to execute.
	 * @return result - the result of the query.
	 * Executes a query and stores the result into the result instance variable.
	 */
	public String executeQuery(String query);
	
}
