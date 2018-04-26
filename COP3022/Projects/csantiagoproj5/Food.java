package csantiagoproj5;

import java.io.*;

public class Food implements CarbonFootprint{
	private String name;
	private int dollars;
	private FoodType type;

	public Food(){
		this.name = null;
		this.dollars = 0;
		this.type = FoodType.OTHER;
	}

	public Food(String name, FoodType type, int dollars){
		this.name = name;
		this.dollars = dollars;
		this.type = type;
	}

	public void setName(String name){this.name = name;}
	public void setDollars(int dollars){this.dollars = dollars;}
	public void setType(FoodType type){this.type = type;}
	public String getName(){return this.name;}
	public int getDollars(){return this.dollars;}
	public FoodType getType(){return this.type;}

	public double getCarbonFootprint(){
		double emissionFactor;
		double monthInYear = 12.0;
		switch(this.type){
			case MEAT:
				emissionFactor = 1452;
				break;
			case CEREALS:
				emissionFactor = 741;
				break;
			case DAIRY:
				emissionFactor = 1911;
				break;
			case FRUITS:
				emissionFactor = 1176;
				break;
			case EATING_OUT:
				emissionFactor = 368;
				break;
			case OTHER:
				emissionFactor = 467;
				break;
			default:
				emissionFactor = 0.0;

		}
		double footprint = 0.0;
		footprint = (this.dollars * emissionFactor * monthInYear) * 0.0022;
		return footprint;
	}

	public long readFile(long offset){
		long filepointer = 0L;
		try{
			RandomAccessFile f = new RandomAccessFile("./csantiagoproj5/test.txt", "r");
			f.seek(offset);

			this.setName(f.readLine());
			switch(f.readLine()){
				case "M":
					this.setType(FoodType.MEAT);
					break;
				case "C":
					this.setType(FoodType.CEREALS);
					break;
				case "D":
					this.setType(FoodType.DAIRY);
					break;
				case "F":
					this.setType(FoodType.FRUITS);
					break;
				case "E":
					this.setType(FoodType.EATING_OUT);
					break;
				default:
					this.setType(FoodType.OTHER);
			}
			this.setDollars(Integer.parseInt(f.readLine()));

			filepointer = f.getFilePointer();
			f.close();
		}catch(IOException e){
			System.out.println("Could not find text file");
		}
		return filepointer;
	}

	public String toString(){
		return "An " + this.name + "'s carbon footprint is: " + this.getCarbonFootprint();
	}
}
