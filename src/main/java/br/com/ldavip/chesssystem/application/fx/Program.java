package br.com.ldavip.chesssystem.application.fx;

import java.io.IOException;

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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("./gui/View.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			scene.getStylesheets().add("/br/com/ldavip/chesssystem/application/fx/gui/stylesheet.css");
			ViewController controller = (ViewController) loader.getController();
			stage.setScene(scene);
			stage.setResizable(false);
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
