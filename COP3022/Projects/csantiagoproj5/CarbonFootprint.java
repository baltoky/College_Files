package csantiagoproj5;

public interface CarbonFootprint{

	public static enum FuelType{
		NONE, ELECTRICITY, GAS, OIL
	}

	public static enum FoodType{
		MEAT, CEREALS, DAIRY, FRUITS, EATING_OUT, OTHER
	}

	public double getCarbonFootprint();
	public long readFile(long offset);

}
