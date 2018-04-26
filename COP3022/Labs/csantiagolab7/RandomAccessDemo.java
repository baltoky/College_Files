package csantiagolab7;

import java.util.*;
import java.io.*;

public class RandomAccessDemo{
	public static void main(String[] args){

		Grade g = new Grade((byte)10, 98.77);
		byte ID = 0;
		double dGrade = 0;
		boolean quit = false;
		Scanner scan = new Scanner(System.in);
		int response = 0;
		final int rcsize = 9;

		try{
			RandomAccessFile ioStream = new RandomAccessFile("text.txt", "rw");

			ioStream.seek(0);

			for(int i = 1; i <= 8; i++){
				g.setStudentID((byte)i);
				g.setGrade(i * 11.01);

				ioStream.writeByte((int)g.getStudentID());
				ioStream.writeDouble(g.getGrade());
			}

			do{

				System.out.println("Select a student from which to select: (1 - 8) otherwise use -1 to quit.");

				response = Integer.parseInt(scan.nextLine());


				if(response >= 1 && response <= 8){

					ioStream.seek((response - 1) * rcsize);

					ID = ioStream.readByte();
					dGrade = ioStream.readDouble();

		 			System.out.printf("Student ID: %d Student Grade: %f%n", (int)ID, dGrade);

				}else {
					quit = true;
				}

			}while(!quit);

			
		}catch(FileNotFoundException e){
			System.out.println("File was not found.");
		}catch(IOException o){
			System.out.println("Could not read from file");
		}

	}
}
