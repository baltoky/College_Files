package project5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Student Name: Cesar Santiago
 * File Name: Server.java
 * Assignment Number: 5
 * 
 * Server load balancer class that connects a client with a server thread.
 */

public class Server implements NetworkLink{
	
	/**
	 * Starts multiple threads of servers when clients connect through a socket connection.
	 * @param args
	 */
	public static void main(String[] args) {
        ServerSocket server = null;
        MusicServer service;
        try {
            server = new ServerSocket(PORT_NUMBER);
            while (true) {
            	System.out.println("Starting a new Server. Awaiting new users...");
            	Socket s = server.accept();
            	service = new MusicServer(s);
            	service.start();
            	System.out.println("User accepted. IP: " + s.getInetAddress());
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
        } finally {
            try {
                if (server != null)
                    server.close();
            } catch (IOException ex) {
                System.out.println("Could not close the server. It does not exist.");
            }
        }
    }
	
}
