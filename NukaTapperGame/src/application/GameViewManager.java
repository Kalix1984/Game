package application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import application.entities.Bar;
import application.entities.Door;
import application.entities.guest.Guest;
import application.entities.mug.Mug;
import application.entities.player.Player;
import application.finitestatemachine.GameState;
import application.finitestatemachine.GameStateManager;
import application.gamepanel.GamePanel;
import application.gamepanel.MessageGamePanel;
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

	private GameStateManager gameStateManager;
	private GameStats gameStats;

	public List<Bar> bars = new ArrayList<>();

	private List<List<Guest>> allGuests = new LinkedList<>();
	private List<Mug> mugs = new ArrayList<>();
	private List<Door> doors = new ArrayList<>();

	private Keyboard keyListener;
	private Player player;

	private GamePanel gamePanel;

	private Indicator scoreIndicator;
	private Indicator levelIndicator;
	private Indicator lifeIndicator;

	public GameViewManager() {
		initGameStage();
		keyListener = new Keyboard(gameScene, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN, KeyCode.SPACE,
				KeyCode.ENTER);
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

	public void changeToGameStage(Stage menuStage) {
		this.menuStage = menuStage;
		menuStage.hide();

		createGameElements();
		createGameLoop();
		gamePane.getChildren().add(canvas);
		gameStage.show();
	}

	private void changeToMenuStage() {
		new Main();
	}

	private void createPlayerAtRespawnPoint() {
		player = new Player(40, 460, keyListener, bars, mugs);
		player.setWidth(40);
		player.setHeight(80);
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
					gameStats.levelUP();
					gameStateManager.removeAllRemainingMugs();

					createPlayerAtRespawnPoint();

					gameStateManager.initGuests();

					gameStateManager.changeGameState(GameState.START_LEVEL);
					break;

				case START_LEVEL:
					// render
					renderGameObjects();

					gamePanel.render(gameSpace,
							"A(z) " + gameStats.getLevel() + ". szint következik.\nKezdéshez nyomj ENTER-t");

					if (gamePanel.isExitKeyPressed()) {
						gameStateManager.changeGameState(GameState.RUNNING);
					}

					break;

				case RUNNING:
					// update
					updateGameObjects(deltaTime);
					// render
					renderGameObjects();

					// check cases
					gameStateManager.checkCatchesByGuestAndPlayer(player);

					// remove "dead" things
					gameStateManager.removeGuests();
					gameStateManager.removeMugs();

					// change state
					gameStateManager.changeGameState(gameStateManager.checkGameState());

					break;

				case LOSE_LIFE:
					// render
					renderGameObjects();

					gamePanel.render(gameSpace, "Életet vesztettél, nyomj ENTER-t");

					if (gamePanel.isExitKeyPressed()) {
						createPlayerAtRespawnPoint();

						gameStats.looseLife();
						gameStateManager.restartLevel();
						gameStateManager.changeGameState(GameState.RUNNING);
					}
					break;

				case GAME_OVER:
					// render
					renderGameObjects();

					gamePanel.render(gameSpace, "Vége a játéknak, nyomj ENTER-t");

					if (gamePanel.isExitKeyPressed()) {
						System.out.println("GAME OVER");
						changeToMenuStage();
					}
					break;

				default:
					break;
				}
			}

			private void updateGameObjects(double deltaTime) {
				player.update(deltaTime);

				for (List<Guest> list : allGuests) {
					for (Guest guest : list) {
						guest.update(deltaTime);
					}
				}

				for (Mug mug : mugs) {
					mug.update(deltaTime);
				}
			}

			private void renderGameObjects() {
				for (List<Guest> list : allGuests) {
					for (Guest guest : list) {
						guest.render(gameSpace);
					}
				}

				for (Bar bar : bars) {
					bar.render(gameSpace);
				}

				for (Mug mug : mugs) {
					mug.render(gameSpace);
				}

//				for (Door door : doors) {
//					door.render(gameSpace);
//				}

				player.render(gameSpace);

				scoreIndicator.render(gameSpace);
				levelIndicator.render(gameSpace);
				lifeIndicator.render(gameSpace);
			}
		};
		gameLoop.start();

	}

	private void clearGameSpace() {
		gameSpace.clearRect(0, 0, 800, 600);
	}

	private void createGameElements() {

		gameStats = new GameStats();

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

		gameStateManager = new GameStateManager(mugs, allGuests, bars, gameStats);
		gamePanel = new MessageGamePanel(200, 100, 400, 400, keyListener);

	}

}
