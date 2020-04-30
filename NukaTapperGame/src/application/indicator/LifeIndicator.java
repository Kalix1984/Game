package application.indicator;

import java.util.ArrayList;
import java.util.List;

import application.entities.Life;
import javafx.scene.canvas.GraphicsContext;

public class LifeIndicator extends Indicator{
	
	private List<Life> icons = new ArrayList<>();
	
	public LifeIndicator(double posX, double posY) {
		setPosition(posX, posY);
		
	}
	

	@Override
	public void render(GraphicsContext gc) {
		
		renderWithImage(gc);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
