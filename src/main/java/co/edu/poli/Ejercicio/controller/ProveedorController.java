package co.edu.poli.Ejercicio.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import co.edu.poli.Ejercicio.model.Proveedor;
import co.edu.poli.Ejercicio.model.Certificacion;
import co.edu.poli.Ejercicio.model.Evaluacion;
import co.edu.poli.Ejercicio.model.PoliticaEntrega;

public class ProveedorController {

    @FXML
    public void mostrarProveedor() {
        Proveedor proveedor = new Proveedor.Builder("Proveedor A", "Calle Principal 123")
                .certificacion(new Certificacion("ISO 9001", "2023-10-26"))
                .evaluacion(new Evaluacion(95, "Excelente proveedor"))
                .politicaEntrega(new PoliticaEntrega("3 días hábiles", 10.0))
                .build();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información del Proveedor");
        alert.setHeaderText(null);
        alert.setContentText(proveedor.toString());
        alert.showAndWait();
    }
}