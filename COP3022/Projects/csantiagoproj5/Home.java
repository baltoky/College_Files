package csantiagoproj5;

import java.io.*;

public class Home implements CarbonFootprint{

	private String type;
	private FuelType fuel;
	private int monthlyCost;
	private double costPerUnit;

	public Home(){
		this.type = null;
		this.fuel = FuelType.NONE;
		this.monthlyCost = 0;
		this.costPerUnit = 0.0;
	}
	public Home(String type, FuelType fuel, int monthlyCost, double costPerUnit){
		this.type = type;
		this.fuel = fuel;
		this.monthlyCost = monthlyCost;
		this.costPerUnit = costPerUnit;
	}

	public double getCarbonFootprint(){
		double emissionFactor;
		double monthInYear = 12.0;
		switch(this.fuel){
			case ELECTRICITY:
				emissionFactor = 1.37;
				break;
			case GAS:
				emissionFactor = 120.61;
				break;
			case OIL:
				emissionFactor = 22.37;
				break;
			default:
				emissionFactor = 0.0;
		}
		double footprint = ((double)monthlyCost / costPerUnit) * emissionFactor * monthInYear;
		return footprint;
	}

	public long readFile(long offset){
		long filepointer = 0L;
		try{
			RandomAccessFile f = new RandomAccessFile("./csantiagoproj5/test.txt", "r");
			f.seek(offset);

			this.setType(f.readLine());
			switch(f.readLine()){
				case "E":
					this.setFuel(FuelType.ELECTRICITY);
					break;
				case "G":
					this.setFuel(FuelType.GAS);
					break;
				case "O":
					this.setFuel(FuelType.OIL);
					break;
				default:
					this.setFuel(FuelType.NONE);
			}
			this.setMontlyCost(Integer.parseInt(f.readLine()));
			this.setCostPerUnit(Double.parseDouble(f.readLine()));

			filepointer = f.getFilePointer();
			f.close();
		}catch(IOException e){
			System.out.println("Could not find text file");
		}
		return filepointer;
	}

	public void setType(String type){this.type = type;}
	public void setFuel(FuelType fuel){this.fuel = fuel;}
	public void setMontlyCost(int monthlyCost){this.monthlyCost = monthlyCost;}
	public void setCostPerUnit(double costPerUnit){this.costPerUnit = costPerUnit;}

	public String getType(){return this.type;}
	public FuelType getFuel(){return this.fuel;}
	public int getMontlyCost(){return this.monthlyCost;}
	public double getCostPerUnit(){return this.costPerUnit;}

	public String toString(){
		return "My " + this.type + "s' footprint is: " + this.getCarbonFootprint();
	}

}
