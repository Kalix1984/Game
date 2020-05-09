package application.gamepanel;

import javafx.scene.canvas.GraphicsContext;

public interface GamePanel {
	public final static String FONT_NAME = "res/Heroes Legend.ttf";
	
	
	public void render(GraphicsContext gameSpace);
	public void setLabel(String label);
	public boolean isExitKeyPressed();
}
