package csantiagolab5;

import java.util.*;
import java.io.*;

public class File{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		FileInputStream file;
		String fileName = "";
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

		System.out.printf("Insert the title of the text file: ");
		fileName = scan.next();

		try{
			file = new FileInputStream(fileName);
			scan = new Scanner(file);
			while(scan.hasNext()){
				arr.add(scan.nextInt());
			}
		}
		catch(FileNotFoundException e){
			System.out.printf("File %s not found", fileName);
		}

		for(Integer i: arr){
			if(i > max)
				max = i;
			else if(i < min)
				min = i;
		}

		System.out.printf(" [%s]'s minimum value is: %d %n", fileName, min);
		System.out.printf(" [%s]'s maximum value is: %d %n", fileName, max);
		
	}
}
