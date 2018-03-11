package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SerialComm extends Application {
	
	String title = "SerialComm @dfso";

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());

			
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.setTitle(title);
			primaryStage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}


	@Override
	public void stop() throws Exception {
		System.exit(0);
	}


	public static void main(String[] args) {
		launch(args);

	}

}
