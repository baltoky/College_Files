package tictactoe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/**
 * Student Name: Cesar Santiago
 * File Name: Server.java
 * Assignment Number: 4
 * 
 * This class holds all of the actions the Server has to perform to correctly communicate with clients
 * As well as the control of the board.
 */

public class Server extends Thread{
    public static final int PORT_NUMBER = 8081;
    public static final int BOARD_SIZE = 3;
    public static final char P_ONE_MARKER = 'X';
    public static final char P_TWO_MARKER = 'O';

    private Socket player1;
    private Socket player2;
    private String player1Name;
    private String player2Name;
	private char board[][];
    private int turn;
    private boolean gameOver;
    private int x;
    private int y;
    InputStream inP1;
    InputStream inP2;
    OutputStream outputP1;
    OutputStream outputP2;


	public static void main(String[] args) {
        ServerSocket server = null;
        ArrayList<Server> serverList = new ArrayList<Server>();
        Server aServer = null;
        try {
            server = new ServerSocket(PORT_NUMBER);
            while (true) {
            	System.out.println("Starting a new Server. Awaiting players...");
            	aServer = new Server(server.accept());
            	aServer.start();
            	aServer.setPlayer2(server.accept());
                serverList.add(aServer);
                
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
        } finally {
            try {
                if (server != null)
                    server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
	
	/**
	 * Initializes the server with one player
	 * @param player1
	 */
	private Server(Socket player1) {
    	this(player1, null);
	}
    
    /**
     * Initializes the server with two players
     * @param player1
     * @param player2
     */
    private Server(Socket player1, Socket player2) {
    	setPlayer1(player1);
    	setPlayer1Name("");
    	setPlayer2(player2);
    	setPlayer2Name("");
        startBoard();
        this.inP1 = null;
        this.inP2 = null;
        this.outputP1 = null;
        this.outputP2 = null;
        this.turn = 1;
        this.gameOver = false;
    }
    
    /**
     * Initializes the board
     */
    public void startBoard() {
    	char board[][] = new char[BOARD_SIZE][BOARD_SIZE];
    	for(char b[]: board)
    		for(char c: b)
    			c = ' ';
    	setBoard(board);
    }
    
    /**
     * Toggles the turn variable
     */
    public void toggleTurn() {
    	if(turn == 1)
    		turn = 2;
    	else turn = 1;
    }
    
    /**
     * Makes a check on whether a tile on the board is clear
     * @param x
     * @param y
     * @return false if tile is not clear, true if it is clear
     */
    public boolean makeCheck(int x, int y) {
    	if(getBoard()[x][y] == ' ')
    		return true;
    	return false;
    }
    
    /**
     * Changes the board to the player whose turn it is
     * @param x
     * @param y
     */
    public void changeBoard(int x, int y) {
    	char[][] b = new char[BOARD_SIZE][BOARD_SIZE];
    	if(getTurn() == 1)
    		b[x][y] = P_ONE_MARKER;
    	else
    		b[x][y] = P_TWO_MARKER;
    	toggleTurn();
    	setBoard(b);
    }
    
    /**
     * Checks the win condition for the game
     * @param x
     * @param y
     * @return false if the game has not been won, true if the game has been won
     */
    public boolean checkWinConditions(int x, int y) {
    	char[][] b = getBoard();
    	char marker = P_ONE_MARKER;
    	
    	if(getTurn() == 1)
    		marker = P_ONE_MARKER;
    	else
    		marker = P_TWO_MARKER;
    	
		for(int i = 0; i < BOARD_SIZE; i++) {
			if(b[x][i] == marker)
				break;
			if(i == BOARD_SIZE - 1)
				return true;
		}
		
		for(int i = 0; i < BOARD_SIZE; i++) {
			if(b[i][y] == marker)
				break;
			if(i == BOARD_SIZE - 1)
				return true;
		}
		
		if(x == y) {
			for(int i = 0; i < BOARD_SIZE; i++) {
				if(b[i][i] == marker)
					break;
				if(i == BOARD_SIZE - 1)
					return true;
			}
		}
		
		if(x + y == BOARD_SIZE) {
			for(int i = 0; i < BOARD_SIZE; i++) {
				if(b[i][(BOARD_SIZE - 1) - i] != marker)
					break;
				if(i == BOARD_SIZE - 1)
					return true;
			}
		}
		
		return false;
    }
    
    /**
     * Parses the answer from the client and gives back an appropriate response
     * @param playerResponse
     * @return A string with the message to the player
     */
    public String parsePlayerResponse(String playerResponse){
    	String[] pr = playerResponse.split(" ");
    	String response = "";
    	System.out.println("Player response: \"" + playerResponse + "\"");
    	if(pr[0].equals("join")) {
    		response = "Hello " + pr[1] + " you are player " + turn + ".";
    		if(turn == 1)
    			this.setPlayer1Name(pr[1]);
    		else
    			this.setPlayer2Name(pr[1]);
    		toggleTurn();
    	}
    	else if(pr[0].equals("choose")) {
    		int _x = 0, _y = 0;
    		_x = Integer.parseInt(pr[2]);
    		_y = Integer.parseInt(pr[3]);
    		if(this.getPlayer1Name() == null || this.getPlayer2Name() == null) {
    			response = "We do not have two players yet.";
    		}else if(makeCheck(_x, _y)) {
    			changeBoard(_x, _y);
    			if(checkWinConditions(_x, _y))
    				response = "Player " + turn + " wins!";
    			return "Player " + turn + " has chosen " + pr[2] + " " + pr[3] + ".";
    		}else if(!makeCheck(_x, _y)) {
    			response = "Position " + pr[2] + " " + pr[3] + " is taken, try again.";
    		}
    	}else if(pr[0].equals("quit")) {
    		setGameOver(true);
    	}
		System.out.println(response);
		return response + "\n";
    }

    /**
     * Run the server thread
     * it will get both players, and allow to input the names and other inputs.
     * Then parse those inputs and send back a response. If an update to the board needs to be done
     * it will be done.
     */
    public void run() {
        String player1Message = "";
        String player2Message = "";
        try {
        	
        	BufferedReader br1 = null;
        	BufferedReader br2 = null;

			
        	while(!gameOver) {

    			if(player1 != null) {
    				inP1 = player1.getInputStream();
    				outputP1 = player1.getOutputStream();
            		br1 = new BufferedReader(new InputStreamReader(inP1));
    			}
    			else if(player2 != null) {
    				inP2 = player2.getInputStream();
        			outputP2 = player2.getOutputStream();
            		br2 = new BufferedReader(new InputStreamReader(inP2));
    			}

        		System.out.println("Turn : " + turn);
        		
        		if(turn == 1 || player2 == null)
        			player1Message = br1.readLine();
        		else if(turn == 2)
        			player2Message = br2.readLine();
        		
        		String responsep1 = parsePlayerResponse(player1Message);
        		String responsep2 = parsePlayerResponse(player2Message);
        		
        		try {
        			
        			outputP1.write(responsep1.getBytes());
        			System.out.println("Player " + this.getPlayer1Name());
        			outputP2.write(responsep2.getBytes());
        			System.out.println("Player " + this.getPlayer2Name());
        			
        		}catch(Exception e) {
        			System.out.println("Player 2 is not yet connected.");
        		}
        		
        	}
        }catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			inP1.close();
    			player1.close();
    			player2.close();
    			if(outputP1 != null)
    				outputP1.close();
    			if(outputP2 != null)
    				outputP2.close();
    		}catch(Exception e) {
    		}
    	}
    }

    
    public Socket getPlayer1() {
		return player1;
	}

	public void setPlayer1(Socket player1) {
		this.player1 = player1;
	}

	public Socket getPlayer2() {
		return player2;
	}

	public void setPlayer2(Socket player2) {
		this.player2 = player2;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}
    
    public char[][] getBoard() {
    	return this.board;
    }

    public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
    public String getPlayer1Name() {
		return player1Name;
	}

	public void setPlayer1Name(String player1Name) {
		this.player1Name = player1Name;
	}

	public String getPlayer2Name() {
		return player2Name;
	}

	public void setPlayer2Name(String player2Name) {
		this.player2Name = player2Name;
	}

}
