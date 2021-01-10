package br.com.ldavip.chesssystem.application.fx;

import java.io.IOException;
import java.net.URL;

import br.com.ldavip.chesssystem.application.fx.gui.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Program extends Application {

	@Override
	public void start(Stage stage) {
		try {
			URL location = getClass().getClassLoader().getResource("gui/View.fxml");
			FXMLLoader loader = new FXMLLoader(location);
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			scene.getStylesheets().add("gui/stylesheet.css");
			stage.setScene(scene);
			stage.setResizable(false);
			ViewController controller = loader.getController();
			controller.setupListeners(stage);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
