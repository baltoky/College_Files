package tictactoe;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.stage.Stage;

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
    
    public Client() {
    	this(DEFAULT_HOST, DEFAULT_PORT);
    }


	public Client(String host, int port) {
		setHost(host);
		setPort(port);
		setConnection(null);
		running = true;
		playerNum = 0;
    }
	


	public void startConnection() throws UnknownHostException, IOException {
		setConnection(new Socket(getHost(), getPort()));
		System.out.println("Got connection");
		setOut(new PrintWriter(getConnection().getOutputStream(), true));
		System.out.println("Got an output");
		setIn(new BufferedReader(new InputStreamReader(getConnection().getInputStream())));
		System.out.println("Got an input");
	}
	
	public void closeConnection() throws IOException {
		getOut().close();
		getIn().close();
		getConnection().close();
	}
	
	public String sendAndRecieve(String command){
		String res = "";
		String temp = "";
		try {
			out.println(command);
			
			while((temp = in.readLine()).isEmpty()) {
				res += temp;
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
