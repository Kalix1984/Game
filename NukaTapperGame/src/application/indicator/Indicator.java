package application.indicator;

import javafx.scene.canvas.GraphicsContext;

public interface Indicator{
	
	public void render(GraphicsContext gameSpace);
	public void update(int newValue);
	public void update(String newValue);

}
