package reflection_DB;

import java.io.*;
import java.sql.*;

public class Main {
	
	public static void main(String[] args) {
	 	   
			//  stat.execute("DROP TABLE Test2"); 
	         
			/*
	        stat.execute("CREATE TABLE Test2 (Name CHAR(20),Age INTEGER, Active BOOLEAN)");
	        stat.execute("INSERT INTO Test2 VALUES ('Romeo',27, true)");
	        stat.execute("INSERT INTO Test2 VALUES ('Juliet',25, true)");
	        stat.execute("INSERT INTO Test2 VALUES ('Tom',64, true)");
	        stat.execute("INSERT INTO Test2 VALUES ('Dick',55, false)");
	        stat.execute("INSERT INTO Test2 VALUES ('Harry',33, true)");
	        ResultSet result = stat.executeQuery("SELECT * FROM Test2");
			System.out.println("after inserts");
	         */
				/*  
				ResultSetMetaData rsm = result.getMetaData();
				int cols = rsm.getColumnCount();
				  while(result.next())
				  {
				    for(int i = 1; i <= cols; i++)
	               System.out.print(result.getString(i)+" ");
	             System.out.println("");      
				  }
				try {  
			     stat.execute("DROP TABLE Test2"); 
	         }
				catch (Exception e)
				{ System.out.println("drop failed"); }    
			}
	      finally
	      {
	         conn.close();
				System.out.println("dropped Table Test2, closed connection and ending program");  
	      }
	      */
		Database.parseArguments(args);
		Database.setConnection();
		Database.executeStatement("DROP TABLE Test2");
		Database.executeStatement("CREATE TABLE Test2 (Name CHAR(20),Age INTEGER, Active BOOLEAN)");
		Database.executeStatement("INSERT INTO Test2 VALUES ('Romeo',27, true)");
		Database.executeStatement("INSERT INTO Test2 VALUES ('Juliet',25, true)");
		Database.executeStatement("INSERT INTO Test2 VALUES ('Tom',64, true)");
		Database.executeStatement("INSERT INTO Test2 VALUES ('Dick',55, false)");
		Database.executeStatement("INSERT INTO Test2 VALUES ('Harry',33, true)");
		Database.executeQuery("SELECT * FROM Test2");
		String res = Database.getResult();
		System.out.println(res);
		Database.executeStatement("DROP TABLE Test2");
		Database.closeConnection();
		
	}
	
}
