package horserace;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HorseRenderer {
	
	public final static String IMAGE_FILEPATH = "file:./horse.png";
	public final static int IMAGE_WIDTH = 100;
	public final static int IMAGE_HEIGHT = 100;
	private GraphicsContext track;
	private static Image i = new Image(IMAGE_FILEPATH);
	
	public HorseRenderer(Canvas canvas) {
		track = canvas.getGraphicsContext2D();
	}

	public void draw(int x) {
		refresh();
		track.drawImage(i, x, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		System.out.println("Drawing at " + x);
	}
	
	public void drawNormalized(int x) {
		double ratio = Driver.FINISH_LINE / (track.getCanvas().getWidth() - IMAGE_WIDTH);
		draw((int)(x/ratio));
	}
	
	public void refresh() {
		track.clearRect(0, 0, track.getCanvas().getWidth(), track.getCanvas().getHeight());
	}
	
	public GraphicsContext getTrack() {
		return track;
	}

	public void setTrack(GraphicsContext track) {
		this.track = track;
	}
	
}
