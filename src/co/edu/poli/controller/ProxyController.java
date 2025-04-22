package co.edu.poli.controller;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import co.edu.poli.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class ProxyController {

    @FXML
    private TextField nombreUsuarioTextField;
    
    @FXML
    private TextField usuarioIdTextField;
    
    @FXML
    private ComboBox<String> nivelAccesoComboBox;
    
    @FXML
    private Label detallesLabel;
    
    @FXML
    private Label precioLabel;
    
    @FXML
    private Label especificacionesLabel;

    private ProductoProxy productoProxy;
    private Usuario usuario;

    @FXML
    private void initialize() {
        inicializarProducto();
    }
    @FXML
    private void inicializarProducto() {
        Especificacion especificacion1 = new Especificacion("Pantalla", "5.5 pulgadas");
        Especificacion especificacion2 = new Especificacion("Batería", "3000 mAh");
        ProductoReal productoReal = new ProductoReal(1, "Smartphone X", 599.99, "Un smartphone con gran rendimiento", List.of(especificacion1, especificacion2));

        AutenticacionService autenticacionService = new AutenticacionService();

        // Inicializar ProductoProxy sin el usuario
        productoProxy = new ProductoProxy(productoReal, autenticacionService);

        ObservableList<String> nivelesAcceso = FXCollections.observableArrayList("1", "2");
        nivelAccesoComboBox.setItems(nivelesAcceso);
    }
    @FXML
    public void abrirClienteFacade() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/view/cliente_view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Cliente Facade");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void crearUsuario() {
        String nombre = nombreUsuarioTextField.getText();
        String idString = usuarioIdTextField.getText();
        String nivelString = nivelAccesoComboBox.getValue();

        if (nombre.isEmpty() || idString.isEmpty() || nivelString == null) {
            detallesLabel.setText("Por favor complete todos los campos.");
            return;
        }

        try {
            int usuarioId = Integer.parseInt(idString);
            int nivelAcceso = Integer.parseInt(nivelString);

            usuario = new Usuario(usuarioId, nombre, nivelAcceso);
            productoProxy.setUsuario(usuario);  // Asignar el usuario a productoProxy

            detallesLabel.setText("Usuario creado: " + nombre + " con nivel de acceso " + nivelAcceso);
        } catch (NumberFormatException e) {
            detallesLabel.setText("El ID de usuario debe ser un número.");
        }
    }

    @FXML
    private void mostrarDetalles() {
        if (usuario == null) {
            detallesLabel.setText("Primero cree un usuario.");
            return;
        }

        if (productoProxy == null) {
            detallesLabel.setText("Producto no inicializado correctamente.");
            return;
        }

        try {
            int usuarioId = usuario.getId();

            String detalles = productoProxy.obtenerDetalles(usuarioId);
            double precio = productoProxy.obtenerPrecio(usuarioId);
            String especificaciones = productoProxy.obtenerEspecificaciones(usuarioId);

            if (!detalles.equals("Acceso denegado a los detalles del producto.") &&
                !especificaciones.equals("Acceso denegado a las especificaciones.")) {
                detallesLabel.setText(detalles);
                precioLabel.setText("Precio: " + precio);
                especificacionesLabel.setText(especificaciones);
            } else {
                detallesLabel.setText("Acceso denegado.");
                precioLabel.setText("");
                especificacionesLabel.setText("");
            }
        } catch (NumberFormatException e) {
            detallesLabel.setText("ID de usuario inválido.");
        }
    }

}
