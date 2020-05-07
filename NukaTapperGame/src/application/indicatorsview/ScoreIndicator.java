package application.indicatorsview;

import application.GameStats;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreIndicator extends Indicator{
	private GameStats gameStats;
	

	public ScoreIndicator(double posX, double posY, GameStats gameStats) {
		super(posX, posY, gameStats);
		this.gameStats = gameStats;
	}

	@Override
	public void render(GraphicsContext gameSpace) {
		gameSpace.setFont(new Font("Verdana", 20));
		gameSpace.setFill(Color.BLACK);
		gameSpace.fillText("" + gameStats.getScore(), getPositionX(), getPositionY());
	}
}
