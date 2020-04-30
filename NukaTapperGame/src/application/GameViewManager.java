package application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import application.entities.Bar;
import application.entities.Door;
import application.entities.Guest;
import application.entities.GuestStatus;
import application.entities.Life;
import application.entities.Mug;
import application.entities.Player;
import application.indicator.Indicator;
import application.indicator.TextIndicator;
import application.input.Keyboard;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
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
	
	private Keyboard keyListener;
	private Player player;
	
//	private List<Guest> guestsOnBar1 = new LinkedList<>();
//	private List<Guest> guestsOnBar2 = new LinkedList<>();
//	private List<Guest> guestsOnBar3 = new LinkedList<>();
//	private List<Guest> guestsOnBar4 = new LinkedList<>();

	private List<Bar> bars = new ArrayList<>();
	
	//takaró elemek csak
	private List<Door> doors = new ArrayList<>();

	private GameStats stats;
	
	private TextIndicator score;
	private TextIndicator level;
	private Indicator life;

	private List<Mug> mugs = new ArrayList<>();

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

				// update
				
//				movePlayer();
//				tapBeer();
				player.update(deltaTime);
				score.update();
				
				
				
//				updateGuests(guestsOnBar1, deltaTime);
//				updateGuests(guestsOnBar2, deltaTime);
//				updateGuests(guestsOnBar3, deltaTime);
//				updateGuests(guestsOnBar4, deltaTime);

				// render
				for (Bar bar : bars) {
					bar.renderWithRect(gameSpace, Color.BROWN);
				}
				
				
//				renderGuests(guestsOnBar1);
//				renderGuests(guestsOnBar2);
//				renderGuests(guestsOnBar3);
//				renderGuests(guestsOnBar4);
				
//				//Mug render & update
//				for (Mug mug : mugs) {
//					mug.renderWithRect(gameSpace, Color.AQUAMARINE);
//					
////					if (mug.isMugInTheEndOfBar()) {
////						mug.setVelocity(0, 0);
////					}
//					
//					if (!guestsOnBar1.isEmpty()) {
//						if (mug.intersects(guestsOnBar1.get(0))) {
//							
//							mug.setVelocity(0.5);
//							guestsOnBar1.get(0).setVelocity(0.5);
//							guestsOnBar1.get(0).setStatus(GuestStatus.LEAVE);
//						}
//					}
//					
//					if (!guestsOnBar2.isEmpty()) {
//						if (mug.intersects(guestsOnBar2.get(0))) {
//							
//							mug.setVelocity(0.5);
//							guestsOnBar2.get(0).setVelocity(0.5);
//							guestsOnBar2.get(0).setStatus(GuestStatus.LEAVE);
//						}
//					}
//					
//					if (!guestsOnBar3.isEmpty()) {
//						if (mug.intersects(guestsOnBar3.get(0))) {
//							
//							mug.setVelocity(0.5);
//							guestsOnBar3.get(0).setVelocity(0.5);
//							guestsOnBar3.get(0).setStatus(GuestStatus.LEAVE);
//						}
//					}
//					
//					if (!guestsOnBar4.isEmpty()) {
//						if (mug.intersects(guestsOnBar4.get(0))) {
//							
//							mug.setVelocity(0.5);
//							guestsOnBar4.get(0).setVelocity(0.5);
//							guestsOnBar4.get(0).setStatus(GuestStatus.LEAVE);
//						}
//					}
//					
//					mug.update(deltaTime);
//				}
//				
//				for (Guest guest : guestsOnBar1) {
////					System.out.println(guest.getDistanceFromBarFront());
//					if (guest.getDistanceFromBarFront() <= 0) {
////						System.out.println("a vendég nem lett kiszolgálva");
//					}
//				}
				
				for (Door door : doors) {
					door.renderWithRect(gameSpace, Color.BLACK);
				}
				
				player.renderWithRect(gameSpace, Color.BLUE);
				
				score.setText("" + stats.getScore());
				score.render(gameSpace);
				
				level.setText("Szint: " + stats.getLevel());
				level.render(gameSpace);
				
				
	
			}

		};
		gameLoop.start();
		
		
	}

	private void clearScreen() {
		gameSpace.clearRect(0, 0, 800, 600);
		gameSpace.setFill(Color.ANTIQUEWHITE);
		gameSpace.fillRect(0, 0, 800, 600);
	}

//	private void tapBeer() {
//		if (isSpaceKeyReleased && isPlayerAtStartingPos()) {
//
//			Bar actualBar = null;
//
//			switch (player.getActualBar()) {
//			case BAR1:
//				actualBar = bar1;
//				break;
//			case BAR2:
//				actualBar = bar2;
//				break;
//			case BAR3:
//				actualBar = bar3;
//				break;
//			case BAR4:
//				actualBar = bar4;
//				break;
//
//			}
//
//			mugs.add(new Mug(actualBar, player));
//
//		}
//
//		isSpaceKeyReleased = false;
//	}

//	private boolean isPlayerAtStartingPos() {
//
//		return player.getPositionX() == getActualBarXPos();
//	}

//	private void movePlayer() {
//		player.setVelocity(0);
//
//		if (isLeftKeyPressed && player.getPositionX() > getActualBarXPos()) {
//			player.addVelocity(-120);
//
//		} else if (isRightKeyPressed && (player.getPositionX() + player.getWidth()) < getBarEndPosition()) {
//			player.addVelocity(+120);
//
//		} else if (isPlayerOnAnotherBar) {
//			player.setPositionY(getActualBarYPos());
//			player.setPositionX(getActualBarXPos());
//			isPlayerOnAnotherBar = false;
//		}
//	}

//	private double getActualBarYPos() {
//
//		switch (player.getActualBar()) {
//		case BAR1:
//			return (bar1.getPositionY() - bar1.getHeight());
//		case BAR2:
//			return (bar2.getPositionY() - bar2.getHeight());
//		case BAR3:
//			return (bar3.getPositionY() - bar3.getHeight());
//		case BAR4:
//			return (bar4.getPositionY() - bar4.getHeight());
//		}
//
//		return 0;
//	}

//	private double getActualBarXPos() {
//
//		double playerStartPointInX = player.getWidth() + 10;
//
//		switch (player.getActualBar()) {
//		case BAR1:
//			return (bar1.getPositionX() - playerStartPointInX);
//		case BAR2:
//			return (bar2.getPositionX() - playerStartPointInX);
//		case BAR3:
//			return (bar3.getPositionX() - playerStartPointInX);
//		case BAR4:
//			return (bar4.getPositionX() - playerStartPointInX);
//		}
//
//		return 0;
//	}

//	private int getBarEndPosition() {
//
//		switch (player.getActualBar()) {
//		case BAR1:
//			return bar1.getEndPointInX();
//		case BAR2:
//			return bar2.getEndPointInX();
//		case BAR3:
//			return bar3.getEndPointInX();
//		case BAR4:
//			return bar4.getEndPointInX();
//		}
//
//		return 0;
//	}

	private void createGameElements() {
		
		stats = new GameStats();
		score = new TextIndicator(100, 50);
		level = new TextIndicator(700, 50);
		

		bars.add(new Bar(450, 40, 175, 200));
		bars.add(new Bar(500, 40, 150, 300));
		bars.add(new Bar(550, 40, 125, 400));
		bars.add(new Bar(600, 40, 100, 500));

//		doors.add(new Door(bar1));
//		doors.add(new Door(bar2));
//		doors.add(new Door(bar3));
//		doors.add(new Door(bar4));

		player = new Player(50, 460, keyListener);
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

	private void updateGuests(List<Guest> guestsOnBar, double timer) {
		for (Guest guest : guestsOnBar) {
			guest.update(timer);
			
		}
	}

	private void renderGuests(List<Guest> guestsOnBar) {
		for (Guest guest : guestsOnBar) {
//			guest.setVelocity(-0.5, 0);
			guest.renderWithRect(gameSpace, Color.BLUEVIOLET);
			
		}
	}
}
