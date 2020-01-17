package project5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Student Name: Cesar Santiago
 * File Name: Client.java
 * Assignment Number: 5
 * 
 * Client class that allows the application to communicate with a server.
 */

public class Client implements NetworkLink{

	private Socket connection;
	private BufferedReader in;
	private PrintWriter out;
	
	/**
	 * Attempts to connect to a server
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void startConnection() throws UnknownHostException, IOException {
		setConnection(new Socket(DEFAULT_HOST, PORT_NUMBER));
		setOut(new PrintWriter(getConnection().getOutputStream(), true));
		setIn(new BufferedReader(new InputStreamReader(getConnection().getInputStream())));
	}
	
	/**
	 * Closes the connection to a server
	 * @throws IOException
	 */
	public void closeConnection() throws IOException {
		getOut().close();
		getIn().close();
		getConnection().close();
	}
	
	/**
	 * Sends a request to the server and expects a response.
	 * @param command - request to send to the server.
	 * @return res - response received from the server.
	 */
	public String sendAndReceive(String command){
		String res = new String();
		boolean EOR = false; // End of Response. An EOR char should be given by the server.
		String temp = "";
		try {
			out.println(command);
			while(!EOR) {
				System.out.println("More to recieve");
				temp = in.readLine();
				System.out.println("Response from server: " + temp);
				if(temp.contains(EOR_STR)) {
					EOR = true;
				}
				else {
					res += temp + "\n";
				}
			}
			System.out.println(res);
			return res;
		} catch (Exception e) {
			System.out.println("Could not communicate with server.");
		}
		return res;
	}

	public Socket getConnection() {
		return connection;
	}

	public void setConnection(Socket connection) {
		this.connection = connection;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

}
