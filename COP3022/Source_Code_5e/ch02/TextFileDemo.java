import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TextFileDemo
{
	public static void main(String[] args)
	{
		Scanner fileIn = null;  // Initializes fileIn to an empty object
		try
		{
			// Attempt to open the file
			fileIn = new Scanner(
				new FileInputStream("player.txt"));
		}
		catch (FileNotFoundException e)
		{
			// If the file could not be found, this code is executed
			// and then the program exits
			System.out.println("File not found.");
			System.exit(0);
		}

		// If the program gets here then
		// the file was opened successfully
		int highscore;
		String name;

		System.out.println("Text left to read? " + fileIn.hasNextLine());
		highscore = fileIn.nextInt();
		fileIn.nextLine();	// Read newline left from nextInt()
		name = fileIn.nextLine();

		System.out.println("Name: " + name);
		System.out.println("High score: " + highscore);
		System.out.println("Text left to read? " + fileIn.hasNextLine());

		fileIn.close();
	}
}