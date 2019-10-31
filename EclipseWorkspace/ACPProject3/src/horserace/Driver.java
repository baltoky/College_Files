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

public class Driver extends Application{
	
	public final static int SCENE_WIDTH = 1280;
	public final static int SCENE_HEIGHT = 720;
	public final static int NUM_HORSES = 5;
	public final static int START_LINE = 0;
	public final static int STRIDE = 50;
	public final static int FINISH_LINE = 5000;
	private static ArrayList<Thread> horses = new ArrayList<Thread>();
	private static ArrayList<HorseRenderer> hr = new ArrayList<HorseRenderer>();
	private static FinishLine fl = new FinishLine(FINISH_LINE);
	private static GridPane grid = new GridPane();
	
	public static void runRace() {
		sendAlert();
		for(Thread h: horses) {
			h.setDaemon(true);
			h.start();
		}
	}
	
	public static void sendAlert() {
		class AlertChecker implements Runnable{
			Alert a = new Alert(AlertType.INFORMATION);
			public void run() {
				
				while(!fl.hasWinner()) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		        
				a.setContentText("Horse #" + fl.getWinner() + " won the race!");
				a.show();
			}
		}
		Runnable r = new AlertChecker();
		Thread t = new Thread(r);
		t.setDaemon(true);
		t.start();
	}
	
	public static void setRace() {
		for(int i = 0; i < NUM_HORSES; i++) {
			hr.get(i).draw(0);
		}
	}
	
	public static void renderRace() {
		Application.launch();
	}
	
	public static void startHorses() {
		Canvas c;
		horses = new ArrayList<Thread>();
		hr = new ArrayList<HorseRenderer>();
		grid = new GridPane();
		for(int i = 0; i < NUM_HORSES; i++) {
			c = new Canvas(SCENE_WIDTH, SCENE_HEIGHT / NUM_HORSES);
			hr.add(new HorseRenderer(c));
		}
		for(int i = 0; i < NUM_HORSES; i++) {
			horses.add(new Thread(new Horse(i, START_LINE, STRIDE, fl, hr.get(i))));
		}
		for(int i = 0; i < NUM_HORSES; i++) {
			grid.addRow(i, hr.get(i).getTrack().getCanvas());
		}
		setRace();
	}

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
