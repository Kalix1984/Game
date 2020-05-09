package application.indicatorsview;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		try {
			gameSpace.setFont(Font.loadFont(new FileInputStream(new File(FONT_NAME)), 25));
		} catch (FileNotFoundException e) {
			gameSpace.setFont(Font.font("Verdana", 25));
		}
		
		gameSpace.setFill(Color.WHITE);
		gameSpace.setStroke(Color.BLACK);
		gameSpace.setLineWidth(2);
		gameSpace.fillText("level " + gameStats.getLevel(), getPositionX(), getPositionY());
		gameSpace.strokeText("level " + gameStats.getLevel(), getPositionX(), getPositionY());
		
		
	
		
	}
}
