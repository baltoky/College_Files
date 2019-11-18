package horserace;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Student Name: Cesar Santiago
 * File Name: HorseRenderer.java
 * Assignment Number: 3
 * 
 * This class allows the view to render the horse and that horse run through the race.
 */

public class HorseRenderer {
	
	public final static String IMAGE_FILEPATH = "file:./horse.png";
	public final static int IMAGE_WIDTH = 100;
	public final static int IMAGE_HEIGHT = 100;
	private GraphicsContext track;
	private static Image i = new Image(IMAGE_FILEPATH);
	
	/**
	 * @param canvas
	 * 
	 * A custom constructor that from a given canvas sets the graphics context.
	 */
	public HorseRenderer(Canvas canvas) {
		track = canvas.getGraphicsContext2D();
	}

	/**
	 * @param x
	 * Draws an image to the position given.
	 */
	public void draw(int x) {
		refresh();
		track.drawImage(i, x, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		System.out.println("Drawing at " + x);
	}
	
	/**
	 * @param x
	 * Normalizes the value given into the function to fir into the canvas
	 */
	public void drawNormalized(int x) {
		double ratio = Driver.FINISH_LINE / (track.getCanvas().getWidth() - IMAGE_WIDTH);
		draw((int)(x/ratio));
	}
	
	/**
	 * Erases any previous drawing of the canvas.
	 */
	public void refresh() {
		track.clearRect(0, 0, track.getCanvas().getWidth(), track.getCanvas().getHeight());
	}
	
	/**
	 * @return
	 * Getter for track
	 */
	public GraphicsContext getTrack() {
		return track;
	}

	/**
	 * @param track
	 * Setter for track
	 */
	public void setTrack(GraphicsContext track) {
		this.track = track;
	}
	
}
