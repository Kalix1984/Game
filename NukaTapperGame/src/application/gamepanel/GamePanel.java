package application.gamepanel;

import javafx.scene.canvas.GraphicsContext;

public interface GamePanel {
	public void render(GraphicsContext gameSpace, String text);
	public boolean isExitKeyPressed();
}
