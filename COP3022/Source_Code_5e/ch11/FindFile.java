import java.io.File;

public class FindFile
{
	public static String searchForFile(File dir, String target)
	{
		String result = "";

		// If dir is not a directory, return
		if (!dir.isDirectory())
			return "Path is not a directory.";

		// Check each item in the directory
		for (File folderItem : dir.listFiles())
		{
			// Recurse if it's a directory
			if (folderItem.isDirectory())
			{
				result = searchForFile(folderItem,target);
				// Return the result if it is not empty
				if (!result.equals(""))
					return result;
			}
			// If it's a file, check for a match
			else
			{
				if (folderItem.getName().equals(target))
					return folderItem.getAbsolutePath();
			}
		}
		// If we got here, nothing was found
		return "";
	}

	public static void main(String[] args)
	{
		// The root folder to search
		File rootFolder = new File("C:\\");
		String result = searchForFile(rootFolder, "FindFile.java");
		if (!result.equals(""))
			System.out.println(result);
		else
			System.out.println("File not found.");

	}
}