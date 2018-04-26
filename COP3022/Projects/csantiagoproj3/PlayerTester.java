package csantiagoproj3;

import java.io.*;
import java.util.*;

public class PlayerTester{
	public static void main(String[] args){
		FileInputStream file = null;
		ArrayList<Player> players = new ArrayList<Player>();

		try{
			 file = new FileInputStream("data.txt");
		}catch(FileNotFoundException e){
			System.out.println("No file found. Make sure the file is called data.txt");
		}

		Scanner scan = new Scanner(file);
		while(scan.hasNext()){

		}
	}
}
