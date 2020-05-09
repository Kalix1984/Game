package application.indicatorsview;

import javafx.scene.canvas.GraphicsContext;

public interface Indicator{
	public final static String FONT_NAME = "res/Heroes Legend.ttf";
	
	void render(GraphicsContext gameSpace);
}
