package co.edu.poli.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class VistaPrincipal {

    @FXML
    private Button botonComando;

    @FXML
    private Button botonEstrategia;

    @FXML
    private Button botonResponsabilidad;

    @FXML
    private void initialize() {
        botonComando.setOnAction(this::abrirVistaPatron);
        botonEstrategia.setOnAction(this::abrirVistaEstrategia);
        botonResponsabilidad.setOnAction(this::abrirVistaResponsabilidad);
    }

    private void abrirVistaPatron(ActionEvent event) {
        cargarVista("ProductoView.fxml");
    }

    private void abrirVistaEstrategia(ActionEvent event) {
        cargarVista("Pedido.fxml");
    }

    private void abrirVistaResponsabilidad(ActionEvent event) {
        cargarVista("responsabilidad.fxml");
    }

    private void cargarVista(String fxml) {
        try {
            URL fxmlLocation = getClass().getResource(fxml); // Por ejemplo: "/co/edu/poli/View/Pedido.fxml"
            if (fxmlLocation == null) {
                System.err.println("No se encontró el archivo FXML: " + fxml);
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

   