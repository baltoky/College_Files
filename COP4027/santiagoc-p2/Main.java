package santiagoc_p2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application{
	final static String WORD_FILE_PATH = "words.txt";
	final static String DELIMS = " \n\t,.";
	public static void main(String [] args) {
		Application.launch();
	}
	
	public static String fileRead(File f) {
		String content = "Could not find file from filepath";
		try {
			content = Files.readString(Paths.get(f.getPath()));
		}
		catch(IOException e) {
			System.out.println(content + " " + f.getPath());
		}
		return content;
	}
	
	public static HashSet<String> openDictionary(){
		File f = new File(WORD_FILE_PATH);
		HashSet<String> words = new HashSet<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String temp;
			while((temp = br.readLine()) != null) {
				words.add(temp.toLowerCase());
			}
			br.close();
		}
		catch(Exception e) {
			System.out.println("Could not find the file.");
		}
		return words;
	}
	
	public static ArrayList<String> oneLetterMissing(String s, HashSet<String> dictionary) {
		StringBuilder buffer = new StringBuilder(s);
		String toCompare = buffer.toString();
		ArrayList<String> possibleOutcomes = new ArrayList<String>();
		for(int i = 0; i <= s.length(); i++) {
			toCompare = buffer.toString();
			for(char j = 'a'; j <= 'z'; j++) {
				buffer.insert(i, j);
				toCompare = buffer.toString();
				buffer.deleteCharAt(i);
				if(dictionary.contains(toCompare)) {
					possibleOutcomes.add(toCompare);
				}
			}
		}
		return possibleOutcomes;
	}
	
	public static ArrayList<String> oneLetterAdded(String s, HashSet<String> dictionary) {
		StringBuilder buffer = new StringBuilder(s);
		String toCompare = buffer.toString();
		ArrayList<String> possibleOutcomes = new ArrayList<String>();
		for(int i = 0; i < s.length(); i++) {
			buffer.deleteCharAt(i);
			toCompare = buffer.toString();
			if(dictionary.contains(toCompare)) {
				possibleOutcomes.add(toCompare);
			}
			buffer = new StringBuilder(s);
		}
		return possibleOutcomes;
	}
	
	public static ArrayList<String> twoLettersReversed(String s, HashSet<String> dictionary) {
		StringBuilder buffer = new StringBuilder(s);
		String toCompare = buffer.toString();
		char memory = ' ';
		ArrayList<String> possibleOutcomes = new ArrayList<String>();
		for(int i = 0; i < s.length() - 1; i++) {
			toCompare = buffer.toString();
			memory = buffer.charAt(i);
			buffer.deleteCharAt(i);
			buffer.insert(i + 1, memory);
			toCompare = buffer.toString();
			if(dictionary.contains(toCompare)) {
				possibleOutcomes.add(toCompare);
			}
			buffer = new StringBuilder(s);
		}
		return possibleOutcomes;
	}
	
	public static ArrayList<String> oneLetterMisspelled(String s, HashSet<String> dictionary) {
		StringBuilder buffer = new StringBuilder(s);
		String toCompare = buffer.toString();
		ArrayList<String> possibleOutcomes = new ArrayList<String>();
		for(int i = 0; i <= s.length(); i++) {
			toCompare = buffer.toString();
			for(char j = 'a'; j <= 'z'; j++) {
				buffer.replace(i, i + 1, Character.toString(j));
				toCompare = buffer.toString();
				if(dictionary.contains(toCompare)) {
					possibleOutcomes.add(toCompare);
				}
				buffer = new StringBuilder(s);
			}
		}
		return possibleOutcomes;
	}

	@Override
	public void start(Stage stage) throws Exception {
		// Create MenuBar
        MenuBar menuBar = new MenuBar();
        TextArea userTextField = new TextArea();
        userTextField.setEditable(false);

		HashSet<String> words = openDictionary();
 
        // Create menus
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        
        MenuItem newItem = new MenuItem("New");
        
        newItem.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent event) {
        	   userTextField.setEditable(true);
           }
       });

        MenuItem openFileItem = new MenuItem("Open File");
        
        
          openFileItem.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	FileChooser fC = new FileChooser();
            	fC.setTitle("Open File");
            	String content = " ";
            	try {
            		File file = fC.showOpenDialog(stage);
            		content = fileRead(file);
            	} catch (Exception e) {
            		System.out.println("Could not open file.");
            	}
                userTextField.appendText(content);
            }
        });       
        
        MenuItem exitItem = new MenuItem("Exit");
 
        // Set Accelerator for Exit MenuItem.
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
 
        // When user click on the Exit item
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        Alert a = new Alert(AlertType.INFORMATION);
        
        MenuItem checkItem = new MenuItem("Check Spelling");
 
        // When user click on the Exit item
        checkItem.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	String toTest = userTextField.getText();
            	StringTokenizer st = new StringTokenizer(toTest, DELIMS);
        		ArrayList<String> check = new ArrayList<String>();
        		while(st.hasMoreTokens()) {
        			check.add(st.nextToken().toLowerCase());
        		}
        		String message = "";
        		HashSet<String> possibles = new HashSet<String>();
        		for(String s: check) {
        			possibles = new HashSet<String>();
        			if(!words.contains(s)) {
        				message += s + "\n Recomended: \n";
        				possibles.addAll(oneLetterMissing(s, words));
        				possibles.addAll(oneLetterAdded(s, words));
        				possibles.addAll(twoLettersReversed(s, words));
        				possibles.addAll(oneLetterMisspelled(s, words));
        			}
        			for(String ans: possibles) {
        				message += ans + "\n";
        			}
        		}
				a.setContentText(message);
				a.show();
            }
        });
 
        // Add menuItems to the Menus
        fileMenu.getItems().addAll(newItem, openFileItem, exitItem);
        editMenu.getItems().addAll(checkItem);
        
        menuBar.getMenus().addAll(fileMenu, editMenu);
        
        
        BorderPane root = new BorderPane();
        
        root.setTop(menuBar);
        root.setCenter(userTextField);
        Scene scene = new Scene(root, 350, 200);
 
        stage.setTitle("JavaFX Menu Coffey Edits");
        stage.setScene(scene);
        stage.show();
        
	}
}
