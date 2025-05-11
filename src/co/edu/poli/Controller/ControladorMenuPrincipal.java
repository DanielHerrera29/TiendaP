package co.edu.poli.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorMenuPrincipal {
    @FXML
    private Button botonPatron;

    @FXML
    private Button botonEstrategia;

    @FXML
    private Button botonResponsabilidad;

    @FXML
    private void initialize() {
        botonPatron.setOnAction(this::abrirVistaProductoView);
        botonEstrategia.setOnAction(this::abrirVistaEstrategia);
        botonResponsabilidad.setOnAction(this::abrirVistaResponsabilidad);
    }

    private void abrirVistaProductoView(ActionEvent event) {
        cargarVista("PruductoView.fxml");
    }

    private void abrirVistaEstrategia(ActionEvent event) {
        cargarVista("Pedido.fxml");
    }

    private void abrirVistaResponsabilidad(ActionEvent event) {
        cargarVista("Responsabilidad.fxml");
    }

    private void cargarVista(String nombreFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Actividad 11: " + nombreFXML);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}