package csantiagoproj5;

import java.util.*;
import java.io.*;

public class CarbonFootprintTester{
	public static void main(String[] args){
		Home h = new Home("Home", CarbonFootprint.FuelType.GAS, 100, 19.9);

		System.out.println(h.getType() + " Emissions: " + h.getCarbonFootprint());

		long offset = 0;
		long fileLength;
		ArrayList<CarbonFootprint> cfList = new ArrayList<CarbonFootprint>();

		try{
			RandomAccessFile file = new RandomAccessFile("test.txt", "r");
			String cls = "";
			fileLength = file.length();
			int i = 0;

			while(offset <= fileLength){

				cls = file.readLine();
				offset++;
				switch(cls){
					case "H":
						cfList.add(new Home());
						break;
					case "A":
						cfList.add(new Auto());
						break;
					case "F":
						cfList.add(new Food());
						break;
				}
				offset = cfList.get(i).readFile(offset);

				i++;
			}

		}catch(IOException e){
			System.out.println("Could not find test file.");
		}

		for(CarbonFootprint c: cfList){
			System.out.println(c.toString());
		}

	}
}
