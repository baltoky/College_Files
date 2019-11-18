package horserace;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Student Name: Cesar Santiago
 * File Name: Driver.java
 * Assignment Number: 3
 * 
 * This class is the driver of the program as well as the javaFX view.
 * It performs all of the steps needed for the program to run it's sequence.
 */

public class Driver extends Application{
	
	public final static int SCENE_WIDTH = 1280;
	public final static int SCENE_HEIGHT = 720;
	public final static int NUM_HORSES = 6;
	public final static int START_LINE = 0;
	public final static int STRIDE = 50;
	public final static int FINISH_LINE = 5000;
	private static ArrayList<Thread> horses = new ArrayList<Thread>();
	private static ArrayList<HorseRenderer> horseRenderers = new ArrayList<HorseRenderer>();
	private static FinishLine finish = new FinishLine(FINISH_LINE);
	private static GridPane grid = new GridPane();
	
	/**
	 * Starts multiple horse objects on separate background threads.
	 */
	public static void runRace() {
		sendAlert();
		for(Thread h: horses) {
			h.setDaemon(true);
			h.start();
		}
	}
	
	/**
	 * Starts a monitor thread that monitors when the race has finished and sends an alert.
	 */
	public static void sendAlert() {
		class AlertChecker implements Runnable{
			Alert a = new Alert(AlertType.INFORMATION);
			public void run() {
				
				while(!finish.hasWinner()) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		        
				a.setContentText("Horse #" + finish.getWinner() + " won the race!");
				a.show();
			}
		}
		Runnable r = new AlertChecker();
		Thread t = new Thread(r);
		t.setDaemon(true);
		t.start();
	}
	
	/**
	 * Draws all of the horses back at the starting line
	 */
	public static void setRace() {
		for(int i = 0; i < NUM_HORSES; i++) {
			horseRenderers.get(i).draw(0);
		}
	}
	
	/**
	 * Launches the JavaFX application
	 */
	public static void renderRace() {
		Application.launch();
	}
	
	/**
	 * Generates the horses, the renderers, the grid pattern and sets the race
	 */
	public static void startHorses() {
		Canvas c;
		horses = new ArrayList<Thread>();
		horseRenderers = new ArrayList<HorseRenderer>();
		grid = new GridPane();
		finish.setWinner(-1);
		for(int i = 0; i < NUM_HORSES; i++) {
			c = new Canvas(SCENE_WIDTH, SCENE_HEIGHT / NUM_HORSES);
			horseRenderers.add(new HorseRenderer(c));
		}
		for(int i = 0; i < NUM_HORSES; i++) {
			horses.add(new Thread(new Horse(i, START_LINE, STRIDE, finish, horseRenderers.get(i))));
		}
		for(int i = 0; i < NUM_HORSES; i++) {
			grid.addRow(i, horseRenderers.get(i).getTrack().getCanvas());
		}
		setRace();
	}

	/**
	 *	JavaFX application that has a scene witha  menu bar and a grid of horses running
	 */
	@Override
	public void start(Stage primaryStage){
		
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
		
	}
}
