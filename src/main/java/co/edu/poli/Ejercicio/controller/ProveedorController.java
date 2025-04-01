package co.edu.poli.Ejercicio.controller;
/*import javafx.fxml.FXML;
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
}*/
//package co.edu.poli.Ejercicio.controller;

import co.edu.poli.Ejercicio.model.Certificacion;
import co.edu.poli.Ejercicio.model.Evaluacion;
import co.edu.poli.Ejercicio.model.PoliticaEntrega;
import co.edu.poli.Ejercicio.model.Proveedor;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ProveedorController {

    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtDireccion;
    
    @FXML
    private TextField txtCertificacion;
    
    @FXML
    private TextField txtEvaluacion;
    
    @FXML
    private TextField txtPoliticaEntrega;

    @FXML
    private Button btnGuardar;

    @FXML
    private void handleGuardar() {
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String certificacion = txtCertificacion.getText().trim();
        String evaluacion = txtEvaluacion.getText().trim();
        String politicaEntrega = txtPoliticaEntrega.getText().trim();

        if (nombre.isEmpty() || direccion.isEmpty() || certificacion.isEmpty() || evaluacion.isEmpty() || politicaEntrega.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        Proveedor proveedor = new Proveedor.Builder(nombre, direccion)
            .certificacion(new Certificacion(certificacion, "Entidad Emisora"))
            .evaluacion(new Evaluacion(5.0, evaluacion))
            .politicaEntrega(new PoliticaEntrega("2 días", 10.0))
            .build();

        // Aquí puedes hacer algo con el proveedor, como guardarlo en una base de datos

        mostrarAlerta("Éxito", "Proveedor guardado correctamente.");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(titulo.equals("Éxito") ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
