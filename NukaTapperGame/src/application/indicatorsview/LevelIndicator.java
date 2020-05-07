package application.indicatorsview;

import application.GameStats;
import application.entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LevelIndicator extends Entity implements Indicator{
	private GameStats gameStats;
	
	public LevelIndicator(double posX, double posY, GameStats gameStats) {
		this.gameStats = gameStats;
		setPosition(posX, posY);
		
	}

	@Override
	public void render(GraphicsContext gameSpace) {
		gameSpace.setFont(new Font("Verdana", 20));
		gameSpace.setFill(Color.BLACK);
		gameSpace.fillText("Szint: " + gameStats.getLevel(), getPositionX(), getPositionY());
		
	}
}
