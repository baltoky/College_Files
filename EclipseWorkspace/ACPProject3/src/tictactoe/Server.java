package tictactoe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

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
    InputStream in;
    OutputStream outputP1;
    OutputStream outputP2;


	public static void main(String[] args) {
        System.out.println("SocketServer Example");
        ServerSocket server = null;
        ArrayList<Server> serverList = new ArrayList<Server>();
        Server aServer = null;
        try {
            server = new ServerSocket(PORT_NUMBER);
            while (true) {
                /**
                 * create a new {@link SocketServer} object for each connection
                 * this will allow multiple client connections
                 */
            	System.out.println("Starting a new Server. Awaiting players...");
            	aServer = new Server(server.accept(), server.accept());
            	aServer.start();
            	//aServer.setPlayer2(server.accept());
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
	
	private Server(Socket player1) {
    	this(player1, null);
	}
    
    private Server(Socket player1, Socket player2) {
    	setPlayer1(player1);
    	setPlayer1Name(null);
    	setPlayer2(player2);
    	setPlayer2Name(null);
        startBoard();
        this.in = null;
        this.outputP1 = null;
        this.outputP2 = null;
        this.turn = new Random().nextInt(2)+1;
        this.gameOver = false;
    }
    
    public void startBoard() {
    	char board[][] = new char[BOARD_SIZE][BOARD_SIZE];
    	for(char b[]: board)
    		for(char c: b)
    			c = ' ';
    	setBoard(board);
    }
    
    public void toggleTurn() {
    	if(turn == 1)
    		turn = 2;
    	else turn = 1;
    }
    
    public boolean makeCheck(int x, int y) {
    	if(getBoard()[x][y] == ' ')
    		return true;
    	return false;
    }
    
    public void changeBoard(int x, int y) {
    	char[][] b = new char[BOARD_SIZE][BOARD_SIZE];
    	if(getTurn() == 1)
    		b[x][y] = P_ONE_MARKER;
    	else
    		b[x][y] = P_TWO_MARKER;
    	toggleTurn();
    	setBoard(b);
    }
    
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
    
    // System.out.println("Game has started between " + this.getPlayer1Name() + " and " + this.getPlayer2Name() + "\n");
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
    
    public void waitForConnection() {
    	while(player1 == null || player2 == null) {
    		
    	}
    }

    public void run() {
        String playerMessage;
        
        try {
        	//waitForConnection();
        	while(!gameOver) {
        		
        		if(turn == 1 || player2 == null)
        			in = player1.getInputStream();
        		else if(turn == 2 && player2 != null)
        			in = player2.getInputStream();
        		
        		System.out.println("Turn : " + turn);
        		
        		if(getPlayer1() != null)
        			outputP1 = player1.getOutputStream();
        		if(getPlayer2() != null)
        			outputP2 = player2.getOutputStream();
        		
        		BufferedReader br = new BufferedReader(new InputStreamReader(in));
        		playerMessage = br.readLine();
        		String response = parsePlayerResponse(playerMessage);
        		
        		try {
        			outputP1.write(response.getBytes());
        			System.out.println("Player " + this.getPlayer1Name());
        			outputP2.write(response.getBytes());
        			System.out.println("Player " + this.getPlayer2Name());
        		}catch(Exception e) {
        			System.out.println("Player 2 is not yet connected.");
        		}
        		
        	}
        }catch(IOException e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			in.close();
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
    
    public String boardToString() {
    	String result = "";
    	for(char[] b: getBoard())
    		for(char c: b)
    			result += c;
    	
    	return result;
    }
}
