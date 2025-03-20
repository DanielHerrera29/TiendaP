package co.edu.poli.Ejercicio.controller;

import java.util.Date;
import co.edu.poli.Ejercicio.model.Certificacion;
import co.edu.poli.Ejercicio.model.Evaluacion;
import co.edu.poli.Ejercicio.model.Proveedor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProveedorController {
    @FXML private TextField nombreField;
    @FXML private TextField certificacionField;
    @FXML private TextField evaluacionField;
    @FXML private TextField tiempoEntregaField;
    @FXML private TextField costoEnvioField;
    @FXML private Label resultadoLabel;

    @FXML
    private void crearProveedor() {
        String nombre = nombreField.getText();
        String certificacionNombre = certificacionField.getText();
        int evaluacionPuntaje = Integer.parseInt(evaluacionField.getText());
        int tiempoEntrega = Integer.parseInt(tiempoEntregaField.getText());
        double costoEnvio = Double.parseDouble(costoEnvioField.getText());
    
        Certificacion certificacion = new Certificacion(certificacionNombre, new Date(), "Entidad Desconocida");
        Evaluacion evaluacion = new Evaluacion(evaluacionPuntaje, "Evaluación automática", new Date());
    
        Proveedor proveedor = new Proveedor.Builder()
            .conIdentificacion("P001", nombre) // Se requiere ID
            .agregarCertificacion(certificacion)
            .agregarEvaluacion(evaluacion)
            .conPoliticaEntrega(tiempoEntrega, costoEnvio)
            .build();
    
        resultadoLabel.setText("Proveedor creado: " + proveedor.getNombre() + ", Entrega: " + proveedor.getPoliticaEntrega());
    }
    
}
