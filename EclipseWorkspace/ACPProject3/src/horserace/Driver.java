package horserace;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Driver extends Application{
	
	public final static int NUM_HORSIES = 5;
	public final static int START_LINE = 0;
	public final static int FINISH_LINE = 1000000;
	public final static int SCENE_WIDTH = 1280;
	public final static int SCENE_HEIGHT = 720;
	private static ArrayList<Horse> horsies = new ArrayList<Horse>();
	
	public static void runRace() {

		FinishLine fl = new FinishLine(FINISH_LINE);
		for(int i = 0; i < NUM_HORSIES; i++) {
			horsies.add(new Horse(i, START_LINE, 1, fl));
		}
		for(Horse h: horsies) { 
			h.start();
		}
		for(Horse h: horsies) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("The winner is: #" + fl.getWinner());
	}
	
	public static void resetRace() {
		horsies = new ArrayList<Horse>();
	}
	
	public static void renderRace() {
		Application.launch();
	}

	@Override
	public void start(Stage primaryStage){
		
		primaryStage.setTitle("Horse Race");
		
		MenuBar mb = new MenuBar();
		Menu optionsMenu = new Menu("Options");
		MenuItem runItem = new MenuItem("Run");
		MenuItem resetItem = new MenuItem("Reset");
		MenuItem exitItem = new MenuItem("Exit");
		
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
                Driver.resetRace();
            }
        });
		
		exitItem.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
		
		optionsMenu.getItems().addAll(runItem, resetItem, exitItem);
		mb.getMenus().addAll(optionsMenu);BorderPane root = new BorderPane();
        
        root.setTop(mb);
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
 
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
}
