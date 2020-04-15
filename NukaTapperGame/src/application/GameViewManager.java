package application;

import application.entities.Guest;
import application.entities.Player;
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
	private GraphicsContext gc;

	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private boolean isUpKeyPressed;
	private boolean isDownKeyPressed;
	private boolean isSpaceKeyPressed;
	
	private Player player;
	private Guest guest1;

	public GameViewManager() {
		initGameStage();
		createKeyListeners();

	}

	private void initGameStage() {
		gamePane = new Group();
		canvas = new Canvas(WIDTH, HEIGHT);
		gc = canvas.getGraphicsContext2D();

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
//			long lastNanoTime = System.nanoTime();

			@Override
			public void handle(long currentNanoTime) {
				// calculate time since last update.
//				double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
//				lastNanoTime = currentNanoTime;
				
				
//				moveGameElements();
//				checkIfElementsCollide();
				
				//update
				movePlayer();
				player.update();
				
				//render
				gc.clearRect(0, 0, 800,600);
                player.renderWithRect(gc);

			}
		};
		timer.start();
	}

	protected void movePlayer() {
		player.setVelocity(0,0);
		
		if (isLeftKeyPressed) {
			player.addVelocity(-3,0);
			
		}else if (isRightKeyPressed) {
			player.addVelocity(+3,0);
			
		}else if (isUpKeyPressed) {
			player.addVelocity(0,-3);
			
		}else if (isDownKeyPressed) {
			player.addVelocity(0,+3);
		}

		
	}

	private void createGameElements() {
		player = new Player(40, 80, 80, 440);
		player.setFillColor(Color.BLUE);
		
		

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
				isUpKeyPressed = true;
				break;
			case DOWN:
				isDownKeyPressed = true;
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
			case UP:
				isUpKeyPressed = false;
				break;
			case DOWN:
				isDownKeyPressed = false;
				break;
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
