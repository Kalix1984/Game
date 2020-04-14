
package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main3 extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		ViewManager2 manager = new ViewManager2();
		primaryStage = manager.getMainStage();
		manager.initWindow();
		manager.initMenu();
		
		primaryStage.setOnCloseRequest(e -> {
			System.out.println("kilépett(X-el)...menteni kell!");
		});
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
