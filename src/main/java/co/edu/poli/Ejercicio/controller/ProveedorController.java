package co.edu.poli.Ejercicio.controller;
import java.time.LocalDate;
import java.util.Arrays;

import co.edu.poli.Ejercicio.model.Certificacion;
import co.edu.poli.Ejercicio.model.Evaluacion;
import co.edu.poli.Ejercicio.model.Proveedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ProveedorController {

    @FXML
    private Button btnBuilder;

    @FXML
    void handleBuilderAction(ActionEvent event) {
        Proveedor proveedor = new Proveedor.ProveedorBuilder()
                .setId("PROV123")
                .setNombre("Proveedor Ejemplo")
                .setContacto("contacto@proveedor.com")
                .addCertificacion(
                        new Certificacion("CERT456", "ISO 9001", LocalDate.of(2022, 1, 1), LocalDate.of(2024, 1, 1)))
                .addEvaluacion(new Evaluacion("EVAL789", LocalDate.now(), 4.5, "Buen proveedor"))
                .setPoliticaEntrega(new Proveedor.PoliticaEntrega(7, 10.0, true, Arrays.asList("Zona A", "Zona B")))
                .build();

        // Mostrar información del proveedor
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Proveedor Creado");
        alert.setHeaderText(null);
        alert.setContentText(proveedor.getNombre() + " creado con éxito.");
        alert.showAndWait();
    }
}
