package co.edu.poli.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import co.edu.poli.Model.*;

public class ProductoController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;

    private InvokerComando controlador = new InvokerComando();

    @FXML
    public void crearProducto() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();

            Producto producto = new Producto(id, nombre, 0);
            Command comando = new CrearProductoCommand(producto);
            controlador.setComando(comando);
            mostrarAlerta("Resultado", controlador.ejecutarComando());

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID debe ser un número.");
        }
    }

    @FXML
    public void actualizarProducto() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();

            Producto producto = new Producto(id, "Temporal", 0);
            Command comando = new ActualizarProductoCommand(producto, nombre);
            controlador.setComando(comando);
            mostrarAlerta("Resultado", controlador.ejecutarComando());

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID debe ser un número.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
