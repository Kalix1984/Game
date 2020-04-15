package application.menu;


import application.entities.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Start {
	private final Group rootPane;
	private Canvas canvas;
	
	private AnimationTimer gameTimer;


    public Start(Stage mainStage) {
        this.rootPane = new Group();
        canvas = new Canvas(mainStage.getWidth(),mainStage.getHeight());
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.clearRect(0, 0,mainStage.getWidth(),mainStage.getHeight());
        
        Entity player = new Entity(40, 80, 80, 440);
        player.setFillColor(Color.BLUE);
        player.renderWithRect(gc);
        
        
        rootPane.getChildren().add(canvas);

        gameLoop();
        
//        createGameElements();
        
        
    }

    public Group getRootPane() {
        return rootPane ;
    }
    
    
    
    private void createGameElements() {
		
      	
    	
//		player = new Player(80, 440, 40, 80, Color.BLACK);
//		guest1 = new Guest(700, 440, 40, 80, Color.BLUE);
//
//		bar1 = new Bar(100, 120, 500, 40, Color.BROWN);
//		bar2 = new Bar(100, 240, 500, 40, Color.BROWN);
//		bar3 = new Bar(100, 360, 500, 40, Color.BROWN);
//		bar4 = new Bar(100, 480, 500, 40, Color.BROWN);

//		StackPane stack = new StackPane();
//		stack.setTranslateX(10);
//		stack.setTranslateY(10);
//		
//		Rectangle rect = new Rectangle();
//		rect.setWidth(50);
//		rect.setHeight(50);
//		rect.setFill(Color.AQUA);
//		
//		
//		VBox box1 = new VBox(5);
//		box1.setPadding(new Insets(5, 5, 5, 5));
//		box1.setTranslateX(0);
//		box1.setTranslateY(0);
//		
//		stack.getChildren().addAll(rect, box1);
//		Label label1 = new Label();
//		Label label2 = new Label();
//		label1.setText("Life:");
//		label2.setText("4");
//		box1.getChildren().addAll(label1, label2);
		
		
//		rootPane.getChildren().addAll(guest1, bar1, bar2, bar3, bar4, player, stack);

//		rootPane.getScene().setOnKeyPressed(e -> {
//
//			if (e.getCode() == KeyCode.UP) {
//
//				PlayerPos pos = player.getPlayerPos();
//				switch (pos) {
//				case BAR4:
//					player.setPlayerPos(PlayerPos.BAR3);
//					break;
//				case BAR3:
//					player.setPlayerPos(PlayerPos.BAR2);
//					break;
//				case BAR2:
//					player.setPlayerPos(PlayerPos.BAR1);
//					break;
//				case BAR1:
//					return;
//
//				}
//				player.setTranslateY(player.getTranslateY() - 120);
//				player.setTranslateX(0);
//
//			} else if (e.getCode() == KeyCode.DOWN) {
//
//				PlayerPos pos = player.getPlayerPos();
//				switch (pos) {
//				case BAR1:
//					player.setPlayerPos(PlayerPos.BAR2);
//					break;
//				case BAR2:
//					player.setPlayerPos(PlayerPos.BAR3);
//					break;
//				case BAR3:
//					player.setPlayerPos(PlayerPos.BAR4);
//					break;
//				case BAR4:
//					return;
//				}
//				player.setTranslateY(player.getTranslateY() + 120);
//				player.setTranslateX(0);
//
//			} else if (e.getCode() == KeyCode.LEFT) {
//				if (player.getTranslateX() > 0) {
//					player.setTranslateX(player.getTranslateX() - 40);
//				}
//			} else if (e.getCode() == KeyCode.RIGHT) {
//				if (player.getTranslateX() < 450) {
//					player.setTranslateX(player.getTranslateX() + 40);
//				}
//			}
//
//		});

		
		
		
	}

	private void gameLoop() {
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
//				update();
			}

		};
		timer.start();
	}

//	private void update() {
//		if (guest1.getTranslateX() > -600) {
//			guest1.setTranslateX(guest1.getTranslateX() - 1);
//			
//		}
	
	}


    

