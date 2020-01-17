package project5;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Student Name: Cesar Santiago
 * File Name: ClientApp.java
 * Assignment Number: 5
 * 
 * Client application which serves as an interface to the program with the user.
 */

public class ClientApp extends Application{
	
    public static final int SCREEN_HEIGHT = 300;

	private Client client;
	
	/**
	 * Starts the application on a process thread.
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	/**
	 * Initializes a client app with a new Client thread
	 */
	public ClientApp() {
		client = new Client();
		startClientConnection();
	}
	
	/**
	 * Attempts to connect the client to the server.
	 */
	public void startClientConnection() {
		try {
			client.startConnection();
		} catch (Exception e) {
			System.out.println("Could not connect to server " + Client.DEFAULT_HOST);
		}
	}
	
	/**
	 * The display and control of the application.
	 * It will give the user several boxes that prompt for an input. Once filled
	 * The user can submit the response and receive a window of information from the server.
	 */
	@Override
	public void start(Stage stage){
		
		String title = new String("Musical Instrument Lookup");
		
		stage.setTitle(title);
		BorderPane root = new BorderPane();
		root.setPrefSize(SCREEN_HEIGHT, SCREEN_HEIGHT);
		
		VBox gui = new VBox();
		gui.setPadding(new Insets(10));
		gui.setSpacing(10);
		gui.setAlignment(Pos.CENTER);
		
		HBox tbox = new HBox();
		Text titleText = new Text(title);
		titleText.setFont(Font.font(20));
		titleText.setUnderline(true);
		tbox.setAlignment(Pos.CENTER);
		tbox.setPadding(new Insets(15));
		tbox.getChildren().addAll(titleText);
		
		HBox selection1 = new HBox();
		selection1.setAlignment(Pos.CENTER);
		Text typeText = new Text("Instrument Type: ");
		ChoiceBox<String> typeChoice = new ChoiceBox<String>(FXCollections.observableArrayList("all", "guitar", "bass", "keyboard"));
		typeChoice.setValue("all");
		selection1.getChildren().addAll(typeText, typeChoice);
		
		HBox selection2 = new HBox();
		selection2.setAlignment(Pos.CENTER);
		Text brandText = new Text("Instrument Brand: ");
		ChoiceBox<String> brandChoice = new ChoiceBox<String>(FXCollections.observableArrayList("all", "yamaha", "gibson", "fender", "roland", "alesis"));
		brandChoice.setValue("all");
		selection2.getChildren().addAll(brandText, brandChoice);
		
		HBox selection3 = new HBox();
		selection3.setAlignment(Pos.CENTER);
		Text costText = new Text("Maximum Cost: ");
		TextField costField = new TextField();
		costField.setText("0");
		selection3.getChildren().addAll(costText, costField);
		
		HBox selection4 = new HBox();
		selection4.setAlignment(Pos.CENTER);
		Text locationText = new Text("Warehouse Location: ");
		ChoiceBox<String> locationChoice = new ChoiceBox<String>(FXCollections.observableArrayList("all", "PNS", "CLT", "DFW"));
		locationChoice.setValue("all");
		selection4.getChildren().addAll(locationText, locationChoice);
		
		Button submit = new Button("Submit Request");
		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				String res, req;
				Alert a = new Alert(AlertType.INFORMATION);
				res = new String();
				req = new String();
				req += typeChoice.getSelectionModel().getSelectedItem() + "," 
						+ brandChoice.getSelectionModel().getSelectedItem() + "," 
						+ costField.getText() + "," 
						+ locationChoice.getSelectionModel().getSelectedItem();
				System.out.println("Sending request to server: " + req);
				res += client.sendAndReceive(req);
				res = res.trim().replaceAll("^ +| +$|( )+", "$1");
				a.setTitle("Database Request");
				a.setHeaderText("Musical Instruments Requested");
				a.setContentText(res);
				a.showAndWait();
			}
			
		});
		
		gui.getChildren().addAll(tbox, selection1, selection2, selection3, selection4, submit);
		
		root.setCenter(gui);
		
		Scene s = new Scene(root, SCREEN_HEIGHT, SCREEN_HEIGHT);
		
		stage.setScene(s);
		stage.show();
	}
}
