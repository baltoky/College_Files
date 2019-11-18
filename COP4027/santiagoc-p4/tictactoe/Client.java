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
	
    public static void main(String args[]) {
        String host = "127.0.0.1";
        int port = 8081;
        new Client(host, port);
    }
    
    public Client() {
    	this(DEFAULT_HOST, DEFAULT_PORT);
    }


	public Client(String host, int port) {
		setHost(host);
		setPort(port);
		setConnection(null);
		running = true;
		try {
			startConnection();
			runClient();
		} catch (IOException e) {
			running = false;
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (IOException e) {
				System.exit(1);
			}
		}
    }
	
	public void startConnection() throws UnknownHostException, IOException {
		setConnection(new Socket(getHost(), getPort()));
		setOut(new PrintWriter(getConnection().getOutputStream(), true));
		setIn(new BufferedReader(new InputStreamReader(getConnection().getInputStream())));
	}
	
	public void closeConnection() throws IOException {
		getOut().close();
		getIn().close();
		getConnection().close();
	}
	
	public void sendMove(int move) {
		getOut().println(move);
	}
	
	public String getBoard() throws IOException {
		return in.readLine();
	}
	
	public String getUserInput() {

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String ui = "";
		try {
			ui = stdIn.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ui;
	}
	
	public void runClient() {
		
		while(running) {
			String userInput = getUserInput();
			if(userInput.equals("q")) {
				running = false;
			}
			try {
				String serverResponse = in.readLine();
			} catch (IOException e) {
				System.out.println("No response from server.");
			}
		}
		/*
        try {
            String serverHostname = new String("127.0.0.1");

            System.out.println("Connecting to host " + serverHostname + " on port " + port + ".");
            System.out.println("Enter message, q to quit");
            Socket echoSocket = null;
            PrintWriter out = null;
            BufferedReader in = null;

            try {
                echoSocket = new Socket(serverHostname, 8081);
                out = new PrintWriter(echoSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + serverHostname);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.exit(1);
            }

            // {@link UnknownHost} object used to read from console
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("client: ");
                String userInput = stdIn.readLine();
                // Exit on 'q' char sent 
                if ("q".equals(userInput)) {
                    break;
                }
                out.println(userInput);
                System.out.println("server: " + in.readLine());
            }

            // Closing all the resources
            out.close();
            in.close();
            stdIn.close();
            echoSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
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
}
