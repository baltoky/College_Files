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
    private char board[][];
    private int turn;
    private boolean gameOver;
    private int x;
    private int y;


	public static void main(String[] args) {
        System.out.println("SocketServer Example");
        ServerSocket server = null;
        ArrayList<Server> serverList = new ArrayList<Server>();
        try {
            server = new ServerSocket(PORT_NUMBER);
            while (true) {
                /**
                 * create a new {@link SocketServer} object for each connection
                 * this will allow multiple client connections
                 */
                serverList.add(new Server(server.accept(), server.accept()));
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
    
    private Server(Socket player1, Socket player2) {
    	setPlayer1(player1);
    	setPlayer2(player2);
        startBoard();
        this.turn = new Random().nextInt(1)+1;
        this.gameOver = false;
        System.out.println("Game has started between " + player1.getInetAddress().getHostAddress() + " and " + player2.getInetAddress().getHostAddress());
        start();
    }
    
    public void startBoard() {
    	char board[][] = new char[BOARD_SIZE][BOARD_SIZE];
    	for(char b[]: board)
    		for(char c: b)
    			c = ' ';
    	setBoard(board);
    }
    
    public void toggleTurn() {
    	if(turn == 0)
    		turn = 1;
    	else turn = 0;
    }
    
    public boolean makeCheck(int x, int y) {
    	if(getBoard()[x][y] == ' ')
    		return true;
    	return false;
    }
    
    public void changeBoard(int x, int y) {
    	char[][] b = new char[BOARD_SIZE][BOARD_SIZE];
    	if(getTurn() == 0)
    		b[x][y] = P_ONE_MARKER;
    	else
    		b[x][y] = P_TWO_MARKER;
    	toggleTurn();
    	setBoard(b);
    }
    
    public boolean checkWinConditions(int x, int y) {
    	char[][] b = getBoard();
    	char marker = P_ONE_MARKER;
    	
    	if(getTurn() == 0)
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
    
    public String parsePlayerResponse(String playerResponse){
    	String[] pr = playerResponse.split(" ");
    	String response = "";
    	if(pr[0].equals("join"))
    		response = "Hello " + pr[1] + " you are player " + turn + ".";
    	else if(pr[0].equals("choose")) {
    		int _x = 0, _y = 0;
    		_x = Integer.parseInt(pr[2]);
    		_y = Integer.parseInt(pr[3]);
    		if(getPlayer1() == null || getPlayer2() == null) {
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
		return response + "\n";
    }

    public void run() {
        InputStream in = null;
        OutputStream outputP1 = null;
        OutputStream outputP2 = null;
        String playerMessage;
        
        try {
        	while(!gameOver) {
        		if(turn == 0)
        			in = player1.getInputStream();
        		else
        			in = player2.getInputStream();
        		
        		outputP1 = player1.getOutputStream();
        		outputP2 = player2.getOutputStream();
        		
        		BufferedReader br = new BufferedReader(new InputStreamReader(in));
        		playerMessage = br.readLine();
        		String response = parsePlayerResponse(playerMessage);
        		
        		outputP1.write(response.getBytes());
        		outputP2.write(response.getBytes());
        		
        	}
        }catch(IOException e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			in.close();
    			outputP1.close();
    			outputP2.close();
    			player1.close();
    			player2.close();
    		}catch(Exception e) {
    			e.printStackTrace();
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
    
    public String boardToString() {
    	String result = "";
    	for(char[] b: getBoard())
    		for(char c: b)
    			result += c;
    	
    	return result;
    }
}
