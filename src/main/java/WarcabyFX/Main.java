package WarcabyFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application  {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Uklad.fxml"));

		GridPane gridPane = loader.load();
		Scene scene = new Scene(gridPane);

		primaryStage.setTitle("Warcaby");
                primaryStage.setResizable(false);               //blokuje mo¿liwoœæ zmieniania wielkoœcci sceny 
		primaryStage.setScene(scene);
		primaryStage.show();	
	}

}
