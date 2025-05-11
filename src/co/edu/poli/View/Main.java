package co.edu.poli.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/View/VistaPrincipal.fxml"));
        	
        	
            Parent root = loader.load();
            // Aumentar tamaño de ventana
            Scene scene = new Scene(root, 300, 400); // Ancho x Alto
            primaryStage.setTitle("Actividad 11");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
   
    }
    public static void main(String[] args) {
        launch(args);
    }
}
