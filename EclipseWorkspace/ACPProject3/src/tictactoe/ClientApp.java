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

public class ClientApp extends Application{
    public static final int BOARD_SIZE = 3;
    public static final int SCREEN_HEIGHT = 300;

	private Client client;
	
	public ClientApp() {
		client = new Client();
	}
	
	public void runClientApp() {
		Application.launch();
	}
	
	public void setConnectionAndJoin(String name) {
		System.out.println(name);
		try {
			client.startConnection();
		} catch (Exception e) {
			System.out.println("Could not connect to server " + client.getHost());
		}
		String greeting = client.sendAndRecieve("join " + name);
		System.out.println("Server: " + greeting);
		String[] num = greeting.split(" ");
		System.out.println(greeting);
		try {
			client.setPlayerNum(Integer.parseInt(num[1]));
		}catch(Exception e) {
			System.out.println("Could not recieve a player number from server.");
		}
		
	}
	
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
				temp.setPrefHeight(100);
				temp.setPrefWidth(100);
				play.add( temp, i, j);
				board.add(temp);
			}
		}
		boardBox.getChildren().add(play);
		
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
		
		/*
		primaryStage.setTitle("Horse Race");
		BorderPane root = new BorderPane();
		
		MenuBar mb = new MenuBar();
		Menu optionsMenu = new Menu("Options");
		MenuItem runItem = new MenuItem("Run");
		MenuItem resetItem = new MenuItem("Reset");
		MenuItem exitItem = new MenuItem("Exit");

		
		startHorses();
    	root.setCenter(grid);
		
		runItem.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	try {
            		Driver.runRace();
            	}catch(Exception e) {
            		System.out.println("Must reset the race");
            	}
            }
        });
		
		resetItem.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	startHorses();
            	root.setCenter(grid);
            }
        });
		
		exitItem.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
		
		optionsMenu.getItems().addAll(runItem, resetItem, exitItem);
		mb.getMenus().addAll(optionsMenu);
		
        root.setTop(mb);
        
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
 
        primaryStage.setScene(scene);
        primaryStage.show();
		 * */
	}
	
}
