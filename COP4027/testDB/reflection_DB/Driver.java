package reflection_DB;

public class Driver {
	
	public static final String VEHICLEPATH = "vehicle.dat";
	public static final String CLASSPATH = "reflection_DB.Vehicle";
	public static final int NUM_OF_VEHICLES = 10;
	public static final String TABLENAME = "Vehicle";
	
	public static void run(String[] args) {
		init(args);
		writeVehicles();
		VehicleManager vm = readVehicles();
		createTable();
		insertIntoTable(vm);
		execQueries();
		close();
	}
	
	private static void init(String [] args) {

		Database.parseArguments(args);
		Database.setConnection();
		Log.startLog();
		
	}
	
	private static void writeVehicles() {

		VehicleManager vm1 = new VehicleManager();
		
		vm1.addRandomVehicles(NUM_OF_VEHICLES);
		VehicleReader.vehicleToFile(VEHICLEPATH, vm1);
		
	}
	
	private static VehicleManager readVehicles() {
		
		VehicleManager vm1 = new VehicleManager();
		VehicleReader.readVehicles(VEHICLEPATH, vm1);
		vm1.printVehicles();
		return vm1;
		
	}
	
	private static void createTable() {
		
		Database.executeStatement("DROP TABLE " + TABLENAME);
		Database.createTableForClass(CLASSPATH, TABLENAME);
		
	}
	
	private static void insertIntoTable(VehicleManager vm) {

		String statement;
		for(Vehicle v : vm.getVehicles()) {
			statement = "INSERT INTO " + TABLENAME +" VALUES('"+  v.getMake() +"', '"
			+ v.getSize() +"', "+ v.getWeight() +", " + v.getEngineSize() + ", " + v.isImport() + ")";
			Database.executeStatement(statement);
		}
		
	}
	
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
	
	private static void close() {
		
		Database.closeConnection();
		Log.close();
		
	}
	
}
