package application.indicatorsview;

import java.util.ArrayList;
import java.util.List;

import application.GameStats;
import application.entities.Entity;
import javafx.scene.canvas.GraphicsContext;

public class LifeIndicator extends Indicator {

	private GameStats gameStats;

	public LifeIndicator(double posX, double posY, GameStats gameStats) {
		super(posX, posY, gameStats);
		this.gameStats = gameStats;

	}

	@Override
	public void render(GraphicsContext gameSpace) {
		for (int i = 0; i < gameStats.getLife(); i++) {
			new LifeSymbol(getPositionX() + i * 32, getPositionY()).render(gameSpace);
		}
	}
}
