package application.indicator;


import application.entities.Entity;
import javafx.scene.canvas.GraphicsContext;


public abstract class Indicator extends Entity{
	
	public abstract void render(GraphicsContext gc);
	public abstract void update();

	//life - grafika
	//score - szöveg
	//level - szöveg
	
//	public void draw(GraphicsContext gc, int number, Color color) {
//		gc.setFill(color);
//		
//		int elementXPos = this.posX;
//		
//		for (int i = 0; i < number; i++) {
//			gc.fillRoundRect(elementXPos, posY, 20, 30, 5, 5);
//			elementXPos += 20 + 5;
//		}
//	}
}
