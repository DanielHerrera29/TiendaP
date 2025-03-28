package co.edu.poli.Ejercicio.controller;

import java.sql.SQLException;

import co.edu.poli.Ejercicio.model.Cliente;
import co.edu.poli.Ejercicio.services.ClienteDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClienteController {

	 @FXML
	    private TextField txtId;

	    @FXML
	    private TextField txtNombre; 


	    @FXML
	    private void handleLogin() {
	        String id = txtId.getText();
	        String nombre = txtNombre.getText();

	        try {
	            ClienteDAO clienteDAO = new ClienteDAO(); 
	            if (clienteDAO.validarCredenciales(id, nombre)) {
	                mostrarAlerta("Éxito", "Inicio de sesión correcto.");
	                abrirPantallaPrincipal();
	            } else {
	                mostrarAlerta("Error", "Credenciales incorrectas.");
	            }
	        } catch (SQLException e) {
	            mostrarAlerta("Error", "Error al validar credenciales: " + e.getMessage());
	        }
	    }

	    @FXML
	    private void handleInsertarCliente() {
	        String id = txtId.getText();
	        String nombre = txtNombre.getText();

	        if (id.isEmpty() || nombre.isEmpty()) {
	            mostrarAlerta("Error", "Todos los campos son obligatorios.");
	            return;
	        }

	        Cliente nuevoCliente = new Cliente(id, nombre);
	        try {
	            ClienteDAO clienteDAO = new ClienteDAO();
	            if (clienteDAO.insertarCliente(nuevoCliente)) {
	                mostrarAlerta("Éxito", "Cliente insertado correctamente.");
	            } else {
	                mostrarAlerta("Error", "No se pudo insertar el cliente.");
	            }
	        } catch (SQLException e) {
	            mostrarAlerta("Error", "Error al conectar con la base de datos: " + e.getMessage());
	        }
	    }
	    @FXML
	    private void handleEditarCliente() {
	        String id = txtId.getText();
	        String nombre = txtNombre.getText();

	        if (id.isEmpty() || nombre.isEmpty()) {
	            mostrarAlerta("Error", "Todos los campos son obligatorios.");
	            return;
	        }

	        Cliente cliente = new Cliente(id, nombre);
	        try {
	            ClienteDAO clienteDAO = new ClienteDAO();
	            if (clienteDAO.actualizarCliente(cliente)) {
	                mostrarAlerta("Éxito", "Cliente actualizado correctamente.");
	            } else {
	                mostrarAlerta("Error", "No se pudo actualizar el cliente.");
	            }
	        } catch (SQLException e) {
	            mostrarAlerta("Error", "Error al actualizar el cliente: " + e.getMessage());
	        }
	    }

	    @FXML
	    private void handleBorrarCliente() {
	        String id = txtId.getText();

	        if (id.isEmpty()) {
	            mostrarAlerta("Error", "Debe ingresar un ID para borrar.");
	            return;
	        }

	        try {
	            ClienteDAO clienteDAO = new ClienteDAO();
	            if (clienteDAO.eliminarCliente(id)) {
	                mostrarAlerta("Éxito", "Cliente eliminado correctamente.");
	            } else {
	                mostrarAlerta("Error", "No se pudo eliminar el cliente.");
	            }
	        } catch (SQLException e) {
	            mostrarAlerta("Error", "Error al eliminar el cliente: " + e.getMessage());
	        }
	    }

	    private void abrirPantallaPrincipal() {
	        try {
	        	c
	        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/Ejercicio/view/Productos.fxml"));
	            Parent root = loader.load();
	            Stage stage = new Stage();
	            stage.setScene(new Scene(root));
	            stage.setTitle("Productos");
	            stage.show();
	            Stage ventanaActual = (Stage) txtId.getScene().getWindow();
	            ventanaActual.close();
	        } catch (Exception e) {
	            mostrarAlerta("Error", "No se pudo abrir la pantalla de productos: " + e.getMessage());
	        }
	    }

	    private void mostrarAlerta(String titulo, String mensaje) {
	        Alert alert = new Alert(titulo.equals("Éxito") ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
	        alert.setTitle(titulo);
	        alert.setHeaderText(null);
	        alert.setContentText(mensaje);
	        alert.showAndWait();
	    }
	}