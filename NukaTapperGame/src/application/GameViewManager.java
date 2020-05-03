package application;

import java.util.ArrayList;
import java.util.List;

import application.entities.Bar;
import application.entities.Door;
import application.entities.Guest;
import application.entities.Mob;
import application.entities.Mug;
import application.entities.Player;
import application.indicator.Indicator;
import application.indicator.LifeIndicator;
import application.indicator.TextIndicator;
import application.input.Keyboard;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameViewManager {
	AnimationTimer gameLoop;
	private long lastNanoTime;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	private Stage gameStage;
	private Stage menuStage;
	private Group gamePane;
	private Scene gameScene;
	private Canvas canvas;
	private GraphicsContext gameSpace;

	public List<Bar> bars = new ArrayList<>();

	private Keyboard keyListener;
	private Player player;

//	private List<Guest> guestsOnBar1 = new LinkedList<>();
//	private List<Guest> guestsOnBar2 = new LinkedList<>();
//	private List<Guest> guestsOnBar3 = new LinkedList<>();
//	private List<Guest> guestsOnBar4 = new LinkedList<>();

	// takaró elemek csak
	private List<Door> doors = new ArrayList<>();

	private GameStats stats;

	private Indicator scoreIndicator;
	private Indicator levelIndicator;
	private Indicator lifeIndicator;

	public List<Mug> mugs = new ArrayList<>();

	public GameViewManager() {
		initGameStage();
		keyListener = new Keyboard(gameScene, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN, KeyCode.SPACE);
	}

	private void initGameStage() {
		gamePane = new Group();
		canvas = new Canvas(WIDTH, HEIGHT);
		gameSpace = canvas.getGraphicsContext2D();

		gameStage = new Stage();

		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		gameStage.setScene(gameScene);
		gameStage.setTitle("Nuka Tapper Game");
		gameStage.setResizable(false);
	}

	public void newGame(Stage menuStage) {
		this.menuStage = menuStage;
		menuStage.hide();
		createGameElements();
		createGameLoop();
		gamePane.getChildren().add(canvas);
		gameStage.show();
	}

	private void createGameLoop() {
		lastNanoTime = System.nanoTime();

		gameLoop = new AnimationTimer() {

			@Override
			public void handle(long currentNanoTime) {
				double deltaTime = (currentNanoTime - lastNanoTime) / 1_000_000_000.0;
				lastNanoTime = currentNanoTime;

				clearScreen();

				player.tapBeer();
				player.update(deltaTime);

				for (Bar bar : bars) {
					bar.renderWithRect(gameSpace, Color.BROWN);
				}

				if (isBrokenMugInTheList()) {
					mugs.clear();

					if (stats.getLife() > 0) {
						stats.looseLife();

						if (stats.getLife() == 0) {
							System.out.println("GAME OVER");
							gameLoop.stop();
						}
					}
				}

				for (Mug mug : mugs) {
					mug.update(deltaTime);
					mug.renderWithImage(gameSpace);
				}

				for (Door door : doors) {
					door.renderWithRect(gameSpace, Color.BLACK);
				}

				player.renderWithRect(gameSpace, Color.BLUE);

				scoreIndicator.update("Pont: " + stats.getScore());
				scoreIndicator.render(gameSpace);

				levelIndicator.update("Szint: " + stats.getLevel());
				levelIndicator.render(gameSpace);

				lifeIndicator.update(stats.getLife());
				lifeIndicator.render(gameSpace);

			}

		};
		gameLoop.start();

	}

	private boolean isBrokenMugInTheList() {
		for (Mug mug : mugs) {
			if (mug.isMugBroken()) {
				return true;
			}
		}
		return false;
	}

	private void clearScreen() {
		gameSpace.clearRect(0, 0, 800, 600);
		gameSpace.setFill(Color.ANTIQUEWHITE);
		gameSpace.fillRect(0, 0, 800, 600);
	}

	private void createGameElements() {

		stats = new GameStats();

		scoreIndicator = new TextIndicator(100, 50);
		levelIndicator = new TextIndicator(700, 50);
		lifeIndicator = new LifeIndicator(10, 60, 4);

		bars.add(new Bar(450, 40, 175, 200));
		bars.add(new Bar(500, 40, 150, 300));
		bars.add(new Bar(550, 40, 125, 400));
		bars.add(new Bar(600, 40, 100, 500));

//		doors.add(new Door(bar1));
//		doors.add(new Door(bar2));
//		doors.add(new Door(bar3));
//		doors.add(new Door(bar4));

		player = new Player(40, 460, keyListener, bars, mugs);

		player.setWidth(40);
		player.setHeight(80);

//		guestsOnBar1.add(new Guest(bar1));
//		guestsOnBar2.add(new Guest(bar2));
//		guestsOnBar3.add(new Guest(bar3));
//		guestsOnBar4.add(new Guest(bar4));

	}

	private void backToMainMenu() {
		Button btn = new Button("vissza");
		gamePane.getChildren().add(btn);

		btn.setOnAction(e -> {
			menuStage.show();
			gameStage.hide();
		});

	}
}
