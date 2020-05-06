package application;

import java.util.ArrayList;
import java.util.List;

import application.alternator.Alternator;
import application.entities.Bar;
import application.entities.Door;
import application.entities.Mob;
import application.entities.Mug;
import application.entities.OnBar;
import application.entities.Player;
import application.gamepanel.GamePanel;
import application.gamepanel.MessageGamePanel;
import application.gamestate.GameState;
import application.gamestate.GameStateManager;
import application.guest.Guest;
import application.guest.GuestState;
import application.guest.GuestsStateManager;
import application.indicator.Indicator;
import application.indicator.LifeIndicator;
import application.indicator.TextIndicator;
import application.input.Keyboard;
import application.menu.Panel;
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
	
	private RandomGenerator random;

	private GameStateManager gameStateManager;
	private GuestsStateManager guestStateManager;

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
		
		
		gameStateManager = new GameStateManager(mugs, guests);
		guestStateManager = new GuestsStateManager(mugs, guests);
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

//		RUNNING,
//		LOOSELIFE
//		WIN,
//		GAMEOVER,

		gameLoop = new AnimationTimer() {

			@Override
			public void handle(long currentNanoTime) {
				double deltaTime = (currentNanoTime - lastNanoTime) / 1_000_000_000.0;
				lastNanoTime = currentNanoTime;

				// clear
				clearScreen();

				switch (gameStateManager.getGameState()) {
				case START_LEVEL:
					// update

					scoreIndicator.update("Pont: " + gameStateManager.getGameStats().getScore());
					levelIndicator.update("Szint: " + gameStateManager.getGameStats().getLevel());
					lifeIndicator.update(gameStateManager.getGameStats().getLife());

					// render

					for (Bar bar : bars) {
						bar.renderWithRect(gameSpace, Color.BROWN);
					}

					for (Mug mug : mugs) {
						mug.renderWithImage(gameSpace);
					}

					for (Door door : doors) {
						door.renderWithRect(gameSpace, Color.BLACK);
					}

					player.renderWithRect(gameSpace, Color.BLUE);

					scoreIndicator.render(gameSpace);
					levelIndicator.render(gameSpace);
					lifeIndicator.render(gameSpace);

					gamePanel.render(gameSpace, "Kezdéshez nyomj ENTER-t");

					if (gamePanel.isExitKeyPressed()) {
						gameStateManager.changeGameState(GameState.RUNNING);
					}

					break;
				case RUNNING:
					
					guestStateManager.remove();
					guestStateManager.check();
					
					// update
					player.update(deltaTime);

					for (Guest guest : guests) {
						guest.update(deltaTime);
						
					}

					for (Mug mug : mugs) {
						mug.update(deltaTime);
					}

					scoreIndicator.update("Pont: " + gameStateManager.getGameStats().getScore());
					levelIndicator.update("Szint: " + gameStateManager.getGameStats().getLevel());
					lifeIndicator.update(gameStateManager.getGameStats().getLife());

					// render
					for (Guest guest : guests) {
						guest.renderWithRect(gameSpace, Color.GREEN);
					}

					for (Bar bar : bars) {
						bar.renderWithRect(gameSpace, Color.BROWN);
					}

					for (Mug mug : mugs) {
						mug.renderWithImage(gameSpace);
					}

//					for (Door door : doors) {
//						door.renderWithRect(gameSpace, Color.BLACK);
//					}

					player.renderWithRect(gameSpace, Color.BLUE);

					scoreIndicator.render(gameSpace);
					levelIndicator.render(gameSpace);
					lifeIndicator.render(gameSpace);

					gameStateManager.changeGameState(gameStateManager.check());

					break;
				case LOSE_LIFE:
					scoreIndicator.update("Pont: " + gameStateManager.getGameStats().getScore());
					levelIndicator.update("Szint: " + gameStateManager.getGameStats().getLevel());
					lifeIndicator.update(gameStateManager.getGameStats().getLife());

					// render

					for (Bar bar : bars) {
						bar.renderWithRect(gameSpace, Color.BROWN);
					}

					for (Mug mug : mugs) {
						mug.renderWithImage(gameSpace);
					}

//					for (Door door : doors) {
//						door.renderWithRect(gameSpace, Color.BLACK);
//					}

					player.renderWithRect(gameSpace, Color.BLUE);

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
					scoreIndicator.update("Pont: " + gameStateManager.getGameStats().getScore());
					levelIndicator.update("Szint: " + gameStateManager.getGameStats().getLevel());
					lifeIndicator.update(gameStateManager.getGameStats().getLife());

					// render

					for (Bar bar : bars) {
						bar.renderWithRect(gameSpace, Color.BROWN);
					}

					for (Mug mug : mugs) {
						mug.renderWithImage(gameSpace);
					}

//					for (Door door : doors) {
//						door.renderWithRect(gameSpace, Color.BLACK);
//					}

					player.renderWithRect(gameSpace, Color.BLUE);

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

					break;
				}
			}
		};
		gameLoop.start();

	}

	private void clearScreen() {
		gameSpace.clearRect(0, 0, 800, 600);
//		gameSpace.setFill(Color.ANTIQUEWHITE);
//		gameSpace.fillRect(0, 0, 800, 600);
	}

	private void createGameElements() {

		scoreIndicator = new TextIndicator(20, 50);
		levelIndicator = new TextIndicator(680, 50);
		lifeIndicator = new LifeIndicator(10, 60, 4);

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

		guests.add(new Guest(OnBar.BAR4, bars, 30, random));
		guests.add(new Guest(OnBar.BAR3, bars, 30, random));
		guests.add(new Guest(OnBar.BAR2, bars, 30, random));
		guests.add(new Guest(OnBar.BAR1, bars, 30, random));

		gamePanel = new MessageGamePanel(200, 100, 400, 400, keyListener);

	}

	private void backToMainMenu() {
		//elméletileg működik de nem szép
		Button btn = new Button("vissza");
		gamePane.getChildren().add(btn);

		btn.setOnAction(e -> {
			menuStage.show();
			gameStage.hide();
		});

	}
}
