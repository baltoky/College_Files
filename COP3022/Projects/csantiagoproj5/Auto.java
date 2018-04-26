package csantiagoproj5;

import java.io.*;

public class Auto implements CarbonFootprint{

	private String make;
	private String model;
	private String year;
	private int mileage;
	private double milesPerGalon;

	public Auto(){
		this.make = null;
		this.model = null;
		this.year = null;
		this.mileage = 0;
		this.milesPerGalon = 0.0;
	}

	public Auto(String make, String model, String year,
			int mileage, double milesPerGalon){
		this.make = make;
		this.model = model;
		this.year = year;
		this.mileage = mileage;
		this.milesPerGalon = milesPerGalon;
	}

	public void setMake(String make){this.make = make;}
	public void setModel(String model){this.model = model;}
	public void setYear(String year){this.year = year;}
	public void setMileage(int mileage){this.mileage = mileage;}
	public void setMilesPerGalon(double milesPerGalon){this.milesPerGalon = milesPerGalon;}

	public String getMake(){return this.make;}
	public String getModel(){return this.model;}
	public String getYear(){return this.year;}
	public int getMileage(){return this.mileage;}
	public double getMilesPerGalon(){return this.milesPerGalon;}

	public double getCarbonFootprint(){
		double poundsEmitted = 19.4;
		double greenhouseEmissions = (double)100 / 95;
		double footprint = ((double)(mileage * 52) / milesPerGalon) * poundsEmitted * greenhouseEmissions;
		return footprint;
	}

	public long readFile(long offset){
		long filepointer = 0L;
		try{
			RandomAccessFile f = new RandomAccessFile("./csantiagoproj5/test.txt", "r");
			f.seek(offset);

			this.setMake(f.readLine());
			this.setModel(f.readLine());
			this.setYear(f.readLine());
			this.setMileage(Integer.parseInt(f.readLine()));
			this.setMilesPerGalon(Double.parseDouble(f.readLine()));

			filepointer = f.getFilePointer();
			f.close();
		}catch(IOException e){
			System.out.println("Could not find text file");
		}
		return filepointer;
	}

	public String toString(){
		return "My " + this.year + " " + this.make + " " + this.model + "'s carbon footprint is: " + getCarbonFootprint();
	}

}
