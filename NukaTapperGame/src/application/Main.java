package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		ViewManager manager = new ViewManager();
		primaryStage = manager.getMainStage();
		manager.initSplashScreen();
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
