package project5;

/**
 * Student Name: Cesar Santiago
 * File Name: NetworkLink.java
 * Assignment Number: 5
 * 
 * An interface between the client and the server.
 */

public interface NetworkLink {
	
	public static final int PORT_NUMBER = 8080; // The port that both the client and the server use to communicate.
	public static final String DEFAULT_HOST = "127.0.0.1"; // The default host for the application.
	public static final String EOR_STR = "[r]"; // A String that allows the client and server to stop receiving messages when this is received.	
}
