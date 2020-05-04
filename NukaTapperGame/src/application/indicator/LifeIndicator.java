package application.indicator;

import java.util.ArrayList;
import java.util.List;

import application.entities.Entity;
import application.entities.LifeSymbol;
import javafx.scene.canvas.GraphicsContext;

public class LifeIndicator extends Entity implements Indicator {

	private List<LifeSymbol> lifeSymbols = new ArrayList<>();

	public LifeIndicator(double posX, double posY, int lifes) {
		setPosition(posX, posY);

		for (int i = 0; i < lifes; i++) {
			lifeSymbols.add(new LifeSymbol(posX + i * 35, posY));
		}
	}

	@Override
	public void render(GraphicsContext gameSpace) {
		for (LifeSymbol symbol : lifeSymbols) {
			symbol.renderWithImage(gameSpace);
		}
	}

	private void remove() {
		lifeSymbols.remove(lifeSymbols.size() - 1);
	}

	@Override
	public void update(int newValue) {
		if (newValue < lifeSymbols.size()) {
			remove();
		}
	}

	@Override
	public void update(String newValue) {
	}

}
