package reflection_DB;

/**
Student Name: Cesar Santiago
File Name: Driver.java
Assignment number 1

The driver of the program. Performs all of the operations that the program is required to run.
*/
public class Driver {
	
	public static final String VEHICLEPATH = "vehicle.dat";
	public static final String CLASSPATH = "reflection_DB.Vehicle";
	public static final int NUM_OF_VEHICLES = 10;
	public static final String TABLENAME = "Vehicle";
	
	/**
	 * @param args
	 * Runs the program to requirements.
	 */
	public static void run(String[] args) {
		init(args);
		writeVehicles();
		VehicleManager vm = readVehicles();
		createTable();
		insertIntoTable(vm);
		execQueries();
		close();
	}
	
	/**
	 * @param args
	 * Initializes the database connection and the log.
	 */
	private static void init(String [] args) {

		Database.parseArguments(args);
		Database.setConnection();
		Log.startLog();
		
	}
	
	/**
	 * Creates and writes the vehicles to a file $VEHICLEPATH.
	 */
	private static void writeVehicles() {

		VehicleManager vm1 = new VehicleManager();
		
		vm1.addRandomVehicles(NUM_OF_VEHICLES);
		VehicleReader.vehicleToFile(VEHICLEPATH, vm1);
		
	}
	
	/**
	 * @return
	 * Reads the vehicles from $VEHICLEPATH and adds it to a VehicleManager
	 * Returns the new VehicleManager.
	 */
	private static VehicleManager readVehicles() {
		
		VehicleManager vm1 = new VehicleManager();
		VehicleReader.readVehicles(VEHICLEPATH, vm1);
		return vm1;
		
	}
	
	/**
	 * Allows the Database table to be created.
	 */
	private static void createTable() {
		
		Database.executeStatement("DROP TABLE " + TABLENAME);
		Database.createTableForClass(CLASSPATH, TABLENAME);
		
	}
	
	/**
	 * @param vm
	 * Inserts all of the vehicles from vm into the database table $TABLENAME.
	 */
	private static void insertIntoTable(VehicleManager vm) {

		String statement;
		for(Vehicle v : vm.getVehicles()) {
			statement = "INSERT INTO " + TABLENAME +" VALUES('"+  v.getMake() +"', '"
			+ v.getSize() +"', "+ v.getWeight() +", " + v.getEngineSize() + ", " + v.isImport() + ")";
			Database.executeStatement(statement);
		}
		
	}
	
	/**
	 * Executes the required queries.
	 */
	private static void execQueries() {
		
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
		
	}
	
	/**
	 * Closes the program by closing the database and the log.
	 */
	private static void close() {
		
		Database.closeConnection();
		Log.close();
		
	}
	
}
