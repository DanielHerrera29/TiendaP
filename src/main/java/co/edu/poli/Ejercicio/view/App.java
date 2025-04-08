package co.edu.poli.Ejercicio.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/Ejercicio/view/Login.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Ejercicio");
        stage.show();
        
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/co/edu/poli/Ejercicio/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
