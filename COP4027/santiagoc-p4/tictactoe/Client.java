package tictactoe;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Student Name: Cesar Santiago
 * File Name: Client.java
 * Assignment Number: 4
 * 
 * This class performs the operations needed for the Client to send and recieve information from a server.
 */

public class Client{

	public static final String DEFAULT_HOST = "127.0.0.1";
	public static final int DEFAULT_PORT = 8081;
	
	private String host;
	private int port;
	private Socket connection;
	private BufferedReader in;
	private PrintWriter out;
	private boolean running;
	private String username;
	private int playerNum;
    
    /**
     * Initializes the client with the default settings
     */
    public Client() {
    	this(DEFAULT_HOST, DEFAULT_PORT);
    }


	/**
	 * Initializes the client with a host and a port
	 * @param host
	 * @param port
	 */
	public Client(String host, int port) {
		setHost(host);
		setPort(port);
		setConnection(null);
		running = true;
		playerNum = 0;
    }
	


	/**
	 * Attempts to connect to a server
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void startConnection() throws UnknownHostException, IOException {
		setConnection(new Socket(getHost(), getPort()));
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
	 * Sends a message to a server and expects a response
	 * @param command
	 * @return
	 */
	public String sendAndRecieve(String command){
		String res = "";
		try {
			out.println(command);
			res = in.readLine();
			System.out.println("Response from server: " + res);
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
    public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public boolean isRunning() {
		return running;
	}


	public void setRunning(boolean running) {
		this.running = running;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getPlayerNum() {
		return playerNum;
	}
	
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
}
