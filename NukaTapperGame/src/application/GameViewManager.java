package application;

import application.entities.Bar;
import application.entities.Guest;
import application.entities.OSD;
import application.entities.Player;
import application.entities.PlayerPosY;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameViewManager {
	AnimationTimer timer;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	private Stage gameStage;
	private Stage menuStage;
	private Group gamePane;
	private Scene gameScene;
	private Canvas canvas;
	private GraphicsContext gameSpace;

	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private boolean isPlayerOnAnotherBar;
	private boolean isSpaceKeyPressed;

	private Player player;
	private Guest guest1;

	private Bar bar1;
	private Bar bar2;
	private Bar bar3;
	private Bar bar4;

	private OSD score;
	private OSD level;
	private OSD life;

	public GameViewManager() {
		initGameStage();
		createKeyListeners();

	}

	private void initGameStage() {
		gamePane = new Group();
		canvas = new Canvas(WIDTH, HEIGHT);
		gameSpace = canvas.getGraphicsContext2D();

		gameStage = new Stage();

		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		gameStage.setScene(gameScene);
		gameStage.setTitle("A játék fut");
		gameStage.setResizable(false);
	}

	public void newGame(Stage menuStage) {
		this.menuStage = menuStage;
		menuStage.hide();

//		backToMainMenu();

//		createBackground();

		createGameElements();

		createGameLoop();

		gamePane.getChildren().add(canvas);

		gameStage.show();
	}

	private void createGameLoop() {

		timer = new AnimationTimer() {

			@Override
			public void handle(long currentNanoTime) {

				// update
				movePlayer();
				player.update();

				// render
				gameSpace.clearRect(0, 0, 800, 600);

				bar1.renderWithRect(gameSpace, Color.BROWN);
				bar2.renderWithRect(gameSpace, Color.BROWN);
				bar3.renderWithRect(gameSpace, Color.BROWN);
				bar4.renderWithRect(gameSpace, Color.BROWN);
				player.renderWithRect(gameSpace, Color.BLUE);

				// OSD
				score = new OSD();
				score.setPos(80, 50);
				score.draw(gameSpace, Integer.toString(player.getScore()), Color.BLACK);

				level = new OSD();
				level.setPos(760, 50);
				level.draw(gameSpace, player.getLevel().toString(), Color.BLACK);

				life = new OSD();
				life.setPos(10, 70);
				life.draw(gameSpace, player.getLife(), Color.RED);

//                System.out.println(player.getPlayerOnBar());
			}
		};
		timer.start();
	}

	protected void movePlayer() {
		player.setVelocity(0, 0);

		if (isLeftKeyPressed && player.getPositionX() > 50) {
			player.addVelocity(-3, 0);

		} else if (isRightKeyPressed) {
			player.addVelocity(+3, 0);

		} else if (isPlayerOnAnotherBar) {
			player.setPositionY(getBarYPosWherePlayerIs());
			player.setPositionX(getBarXPosWherePlayerIs());
			isPlayerOnAnotherBar = false;
		}
	}

	private int getBarYPosWherePlayerIs() {

		switch (player.getPlayerOnBar()) {
		case BAR1:
			return (bar1.getPositionY() - bar1.getHeight());
		case BAR2:
			return (bar2.getPositionY() - bar2.getHeight());
		case BAR3:
			return (bar3.getPositionY() - bar3.getHeight());
		case BAR4:
			return (bar4.getPositionY() - bar4.getHeight());
		}

		return 0;
	}
	
	private int getBarXPosWherePlayerIs() {
		
		int playerStartPointInX = player.getWidth() + 10;
		
		switch (player.getPlayerOnBar()) {
		case BAR1:
			return (bar1.getPositionX() - playerStartPointInX);
		case BAR2:
			return (bar2.getPositionX() - playerStartPointInX);
		case BAR3:
			return (bar3.getPositionX() - playerStartPointInX);
		case BAR4:
			return (bar4.getPositionX() - playerStartPointInX);
		}
		
		return 0;
	}

	private void createGameElements() {

		bar1 = new Bar(450, 40, 175, 200);
		bar2 = new Bar(500, 40, 150, 300);
		bar3 = new Bar(550, 40, 125, 400);
		bar4 = new Bar(600, 40, 100, 500);

		player = new Player(40, 80, 80, 460);

		player.addScore(7100);
		player.looseLife();

		System.out.println("pontok: " + player.getScore());
		System.out.println("Élet: " + player.getLife());
		System.out.println("Élet: " + player.getLevel());

	}

	private void createKeyListeners() {
		gameScene.setOnKeyPressed(e -> {

			switch (e.getCode()) {
			case LEFT:
				isLeftKeyPressed = true;
				break;
			case RIGHT:
				isRightKeyPressed = true;
				break;
			case UP:
				isPlayerOnAnotherBar = player.changePlayerPosUp();
				break;
			case DOWN:
				isPlayerOnAnotherBar = player.changePlayerPosDown();
				break;
			case SPACE:
				isSpaceKeyPressed = true;
				break;
			default:
				break;
			}

		});

		gameScene.setOnKeyReleased(e -> {

			switch (e.getCode()) {
			case LEFT:
				isLeftKeyPressed = false;
				break;
			case RIGHT:
				isRightKeyPressed = false;
				break;
//			case UP:
//				isUpKeyPressed = false;
//				break;
//			case DOWN:
//				isDownKeyPressed = false;
//				break;
			case SPACE:
				isSpaceKeyPressed = false;
				break;
			default:
				break;
			}
		});
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
