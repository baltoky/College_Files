package tictactoe;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import horserace.Driver;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Student Name: Cesar Santiago
 * File Name: ClientApp.java
 * Assignment Number: 4
 * 
 * ClientApp controls the view and control mechanisms of the program.
 */

public class ClientApp extends Application{
	
    public static final int BOARD_SIZE = 3;
    public static final int SCREEN_HEIGHT = 300;

	private Client client;
	
	/**
	 * Initializes a client app with a new Client thread
	 */
	public ClientApp() {
		client = new Client();
	}
	
	/**
	 * Runs the application
	 */
	public void runClientApp() {
		Application.launch();
	}
	
	/**
	 * Attempts to connect the client to the server.
	 */
	public void startClientConnection() {
		try {
			client.startConnection();
		} catch (Exception e) {
			System.out.println("Could not connect to server " + client.getHost());
		}
	}
	
	/**
	 * Asks the client to join the server through N3TP
	 * @param name
	 */
	public void setConnectionAndJoin(String name) {
		System.out.println(name);
		String greeting = client.sendAndRecieve("join " + name);
		System.out.println("Server: " + greeting);
		String[] num = greeting.split("[\\s.]");
		try {
			client.setPlayerNum(Integer.parseInt(num[5]));
		}catch(Exception e) {
			System.out.println("Could not recieve a player number from server.");
		}
		
	}
	
	/**
	 * The display and control of the application.
	 * It will initialize an app with a text field on which to input the name
	 * and a submit button to submit the name to the server.
	 * It will then show a Grid of button which will allow the user to
	 * make a move.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("TicTacToe");
		BorderPane root = new BorderPane();
		
		TextField nameSpot = new TextField();
		nameSpot.setPromptText("Enter your name.");
		nameSpot.setMaxWidth(150);
		Button submitButton = new Button("Submit");
		VBox topBox = new VBox();
		topBox.setAlignment(Pos.CENTER);
		
		VBox boardBox = new VBox();
		boardBox.setAlignment(Pos.CENTER);
		GridPane play = new GridPane();
		play.setPrefSize(500, 400);
		ArrayList<PlayField> board = new ArrayList<PlayField>();
		PlayField temp;
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				temp = new PlayField(i, j);
				temp.setPrefHeight(SCREEN_HEIGHT / BOARD_SIZE);
				temp.setPrefWidth(SCREEN_HEIGHT / BOARD_SIZE);
				play.add( temp, i, j);
				board.add(temp);
			}
		}
		boardBox.getChildren().add(play);
		
		startClientConnection();
		
		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				root.getChildren().remove(topBox);
				setConnectionAndJoin(nameSpot.getText());
				root.setCenter(boardBox);
			}
			
		});
		
		for(PlayField b : board) {
			b.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					String action = "choose " + client.getPlayerNum() + " " + b.toString();
					System.out.println(action);
					client.sendAndRecieve(action);
				}
				
			});
		}
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				client.sendAndRecieve("quit");
			}
			
		});
		
		topBox.getChildren().addAll(nameSpot, submitButton);
		root.setCenter(topBox);
		
		Scene s = new Scene(root, 300, 300);
		
		stage.setScene(s);
		stage.show();
	}
	
}
