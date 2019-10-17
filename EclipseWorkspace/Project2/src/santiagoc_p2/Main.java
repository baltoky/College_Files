package santiagoc_p2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;

public class Main{
	final static String WORD_FILE_PATH = "words.txt";
	public static void main(String [] args) {
		File f = new File(WORD_FILE_PATH);
		HashSet<String> words = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String temp;
			while((temp = br.readLine()) != null) {
				words.add(temp.toLowerCase());
			}
			br.close();
		}
		catch(Exception e) {
			System.out.println("Could not find the file.");
		}

		Scanner scan = new Scanner(System.in);
		String test = scan.next();
		scan.close();
		
		System.out.println("The document has the word " + test + " " + words.contains(test.toLowerCase()));
		
	}
}
