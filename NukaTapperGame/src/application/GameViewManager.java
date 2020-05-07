package application;

import java.util.ArrayList;
import java.util.List;

import application.entities.Bar;
import application.entities.Door;
import application.entities.OnBar;
import application.entities.guest.Guest;
import application.entities.mug.Mug;
import application.entities.player.Player;
import application.gamepanel.GamePanel;
import application.gamepanel.MessageGamePanel;
import application.gamestatemachine.GameState;
import application.gamestatemachine.GameStateManager;
import application.indicatorsview.Indicator;
import application.indicatorsview.LevelIndicator;
import application.indicatorsview.LifeIndicator;
import application.indicatorsview.ScoreIndicator;
import application.input.Keyboard;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
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

	private RandomGenerator random;

	private GameStats gameStats;

	private GameStateManager gameStateManager;

	public List<Bar> bars = new ArrayList<>();

	private Keyboard keyListener;
	private Player player;

	public List<Guest> guests = new ArrayList<>();

	// takaró elemek csak
	private List<Door> doors = new ArrayList<>();

	private GamePanel gamePanel;

	private Indicator scoreIndicator;
	private Indicator levelIndicator;
	private Indicator lifeIndicator;

	public List<Mug> mugs = new ArrayList<>();

	public GameViewManager() {
		initGameStage();
		keyListener = new Keyboard(gameScene, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN, KeyCode.SPACE,
				KeyCode.ENTER);

		random = new RandomGenerator();
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

				// clear
				clearGameSpace();

				switch (gameStateManager.getGameState()) {
				case INIT_LEVEL:
					
					
					// render

					for (Bar bar : bars) {
						bar.render(gameSpace);
					}

					for (Mug mug : mugs) {
						mug.render(gameSpace);
					}

					for (Door door : doors) {
						door.render(gameSpace);
					}

					player.render(gameSpace);

					scoreIndicator.render(gameSpace);
					levelIndicator.render(gameSpace);
					lifeIndicator.render(gameSpace);

					gamePanel.render(gameSpace, "Kezdéshez nyomj ENTER-t");

					if (gamePanel.isExitKeyPressed()) {
						gameStateManager.changeGameState(GameState.RUNNING);
					}

					break;
					
				case RUNNING:

					// update
					player.update(deltaTime);

					for (Guest guest : guests) {
						guest.update(deltaTime);

					}

					for (Mug mug : mugs) {
						mug.update(deltaTime);

						System.out.println(mug.getPositionX());
						System.out.println(mug.getState());
						System.out.println(mug.getVelocityX());

					}

					// render
					for (Guest guest : guests) {
						guest.render(gameSpace);
					}

					for (Bar bar : bars) {
						bar.render(gameSpace);
					}

					for (Mug mug : mugs) {
						mug.render(gameSpace);
					}

//					for (Door door : doors) {
//						door.render(gameSpace);
//					}

					player.render(gameSpace);

					scoreIndicator.render(gameSpace);
					levelIndicator.render(gameSpace);
					lifeIndicator.render(gameSpace);

					// check cases
					gameStateManager.changeGameState(gameStateManager.check());

//					guestStateManager.removeEmptyMugs();
//					guestStateManager.removeServedGuestsAndGiveScore();
//					guestStateManager.checkGuestsAreServed();

					break;
					
				case LOSE_LIFE:
					// render
					for (Bar bar : bars) {
						bar.render(gameSpace);
					}

					for (Mug mug : mugs) {
						mug.render(gameSpace);
					}

					for (Door door : doors) {
						door.render(gameSpace);
					}

					player.render(gameSpace);

					scoreIndicator.render(gameSpace);
					levelIndicator.render(gameSpace);
					lifeIndicator.render(gameSpace);

					gamePanel.render(gameSpace, "Életet vesztettél, nyomj ENTER-t");

					if (gamePanel.isExitKeyPressed()) {
						gameStateManager.getGameStats().looseLife();
						gameStateManager.restartLevel();
						gameStateManager.changeGameState(GameState.RUNNING);
					}
					break;
					
				case GAME_OVER:
					// render
					for (Bar bar : bars) {
						bar.render(gameSpace);
					}

					for (Mug mug : mugs) {
						mug.render(gameSpace);
					}

					for (Door door : doors) {
						door.render(gameSpace);
					}

					player.render(gameSpace);

					scoreIndicator.render(gameSpace);
					levelIndicator.render(gameSpace);
					lifeIndicator.render(gameSpace);

					gamePanel.render(gameSpace, "Vége a játéknak, nyomj ENTER-t");

					if (gamePanel.isExitKeyPressed()) {
						System.out.println("GAME OVER");
						backToMainMenu();
					}
					break;
					
				case WIN:
					gamePanel.render(gameSpace, "LEVEL UP, nyomj ENTER-t");

					if (gamePanel.isExitKeyPressed()) {
						gameStats.levelUP();
						gameStateManager.changeGameState(GameState.INIT_LEVEL);
					}
					break;
				}
			}
		};
		gameLoop.start();

	}

	private void clearGameSpace() {
		gameSpace.clearRect(0, 0, 800, 600);
	}

	private void createGameElements() {

		gameStats = new GameStats();

		gameStateManager = new GameStateManager(mugs, guests, gameStats);

		scoreIndicator = new ScoreIndicator(20, 50, gameStats);
		levelIndicator = new LevelIndicator(680, 50, gameStats);
		lifeIndicator = new LifeIndicator(10, 60, gameStats);

		bars.add(new Bar(450, 40, 175, 200));
		bars.add(new Bar(500, 40, 150, 300));
		bars.add(new Bar(550, 40, 125, 400));
		bars.add(new Bar(600, 40, 100, 500));

		for (Bar bar : bars) {
			doors.add(new Door(bar));
		}

		player = new Player(40, 460, keyListener, bars, mugs);
		player.setWidth(40);
		player.setHeight(80);

		guests.add(new Guest(OnBar.BAR4, bars, mugs, 30));
		guests.add(new Guest(OnBar.BAR3, bars, mugs, 30));
		guests.add(new Guest(OnBar.BAR2, bars, mugs, 30));
		guests.add(new Guest(OnBar.BAR1, bars, mugs, 30));

		gamePanel = new MessageGamePanel(200, 100, 400, 400, keyListener);

	}

	private void backToMainMenu() {
		// elméletileg működik de nem szép
		Button btn = new Button("vissza");
		gamePane.getChildren().add(btn);

		btn.setOnAction(e -> {
			menuStage.show();
			gameStage.hide();
		});

	}
}
