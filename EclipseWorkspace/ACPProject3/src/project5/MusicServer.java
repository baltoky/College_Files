package project5;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Student Name: Cesar Santiago
 * File Name: MusicServer.java
 * Assignment Number: 5
 * 
 * Server class that allows a connection to be had and serves as a link between the server and the client.
 */

public class MusicServer extends Thread implements NetworkLink{
	
	private static ReentrantLock lock = new ReentrantLock();
	
	private DatabaseInterface db;
	
	private Socket user;
	private boolean connected;
    InputStream input;
    OutputStream output;

	/**
	 * Constructor for the Music Server class which gets a user connection,
	 *  sets the connection variable to true,
	 *  and initializes the input and output to communicate with the client.
	 * @param user - A socket which allows a connection between this server and the client.
	 */
	public MusicServer(Socket user) {
		setUser(user);
		setConnected(true);
		try {
			setInput(user.getInputStream());
			setOutput(user.getOutputStream());
		} catch (IOException e) {
			System.out.println("Communication could not be established with " + user.getInetAddress());
		}
		db = new Database();
	}

	/**
     * Run the server thread
     * it will get both players, and allow to input the names and other inputs.
     * Then parse those inputs and send back a response. If an update to the board needs to be done
     * it will be done.
     */
	@Override
    public void run() {
		BufferedReader comm = new BufferedReader(new InputStreamReader(input));
		String userMsg = "";
		while(isConnected()) {
			try {
				userMsg = comm.readLine();
				if(userMsg.contains("quit"))
					setConnected(false);
			} catch (IOException e) {
				System.out.println("There was no communication with the client. IP: " + user.getInetAddress());
				setConnected(false);
			}
			String requestToDB = getDatabaseResponse(userMsg);
			System.out.println("Response: \n" + requestToDB);
			requestToDB += EOR_STR + "\n";
			try {
				output.write(requestToDB.getBytes());
			} catch (IOException e) {
				System.out.println("There was no communication with the client. IP: " + user.getInetAddress());
				setConnected(false);
			}
		}
		try {
			exitServer();
		} catch (IOException e) {
			System.out.println("Connection with the user ended early.");
		}
	}
	
	/**
	 * Retrieves the message from the user and splits the parameters given into different strings.
	 * These strings are then conditionally added to the query string which will prompt the database.
	 * @param userMsg - the complete user message.
	 * @return dbRes - A string of added parameters for the query.
	 */
	private String parseUserMsg(String userMsg) {
		String[] splitMsg = userMsg.split("[,]");
		String dbRes = "";
		if(!splitMsg[0].isEmpty()) {
			if(!splitMsg[0].equals("all")) {
				dbRes += " AND i.instName = '" + splitMsg[0] + "'";
			}
		}
		if(!splitMsg[1].isEmpty()) {
			if(!splitMsg[1].equals("all")) {
				dbRes += " AND i.descrip = '" + splitMsg[1] + "'";
			}
		}
		if(!splitMsg[2].isEmpty()) {
			if(!splitMsg[2].equals("0")) {
				dbRes += " AND i.cost <= " + splitMsg[2];
			}
		}
		if(!splitMsg[3].isEmpty()) {
			if(!splitMsg[3].equals("all")) {
				dbRes += " AND l.locName = '" + splitMsg[3] + "'";
			}
		}
		return dbRes;
	}
	
	/**
	 * Sets up an initial query and asks the user for a message to pass on to modify the query,
	 * 	To satisfy the user's request.
	 * @param userMsg - The complete user message.
	 * @return databaseResponse - All of the information asked from the database.
	 */
	private String getDatabaseResponse(String userMsg) {
		String query = "SELECT i.instName, i.descrip, i.cost, v.quantity, l.address FROM Instruments i, Inventory v, Locations l WHERE v.iNumber = i.instNumber AND v.lNumber = l.locNumber" + parseUserMsg(userMsg);
		lock.lock();
		String databaseResponse = db.executeQuery(query);
		lock.unlock();
		return databaseResponse;
	}

	/**
	 * Exits the server safely.
	 * @return returns 1 when it has exited correctly.
	 * @throws IOException
	 */
	private int exitServer() throws IOException {
		db.dropDatabase();
		user.close();
		return 1;
	}

	public Socket getUser() {
		return user;
	}

	public void setUser(Socket user) {
		this.user = user;
	}
	
	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public OutputStream getOutput() {
		return output;
	}

	public void setOutput(OutputStream output) {
		this.output = output;
	}
}
