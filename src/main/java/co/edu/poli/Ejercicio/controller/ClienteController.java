package co.edu.poli.Ejercicio.controller;

import java.sql.SQLException;
import co.edu.poli.Ejercicio.model.*;
import co.edu.poli.Ejercicio.services.ClienteDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ClienteController {

    // CAMPOS DE LA VISTA
    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private ListView<String> listComponentes;
    @FXML private Button btnCrearComposite;
    @FXML private Button btnAgregarEmpleado;
    @FXML private Button btnAgregarDepartamento;

    // MODELO COMPOSITE
    private Departamento departamentoRaiz;

    // ===========================
    // MANEJO DE CLIENTES (LOGIN, CRUD)
    // ===========================

    @FXML
    private void handleLogin() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.validarCredenciales(id, nombre)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Inicio de sesión correcto.");
                abrirPantallaPrincipal();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Credenciales incorrectas.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al validar credenciales: " + e.getMessage());
        }
    }

    @FXML
    private void handleInsertarCliente() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        if (id.isEmpty() || nombre.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }
        Cliente nuevoCliente = new Cliente(id, nombre);
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.insertarCliente(nuevoCliente)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente insertado correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo insertar el cliente.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    @FXML
    private void handleEditarCliente() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        if (id.isEmpty() || nombre.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }
        Cliente cliente = new Cliente(id, nombre);
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.actualizarCliente(cliente)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente actualizado correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el cliente.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al actualizar el cliente: " + e.getMessage());
        }
    }

    @FXML
    private void handleBorrarCliente() {
        String id = txtId.getText();
        if (id.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Debe ingresar un ID para borrar.");
            return;
        }
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.eliminarCliente(id)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente eliminado correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el cliente.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al eliminar el cliente: " + e.getMessage());
        }
    }

    // ===========================
    // COMPOSITE
    // ===========================

    @FXML
    public void crearComposite() {
        departamentoRaiz = new Departamento("Departamento Principal");
        listComponentes.getItems().clear();
        listComponentes.getItems().add("Composite creado: Departamento Principal");
        mostrarAlerta(Alert.AlertType.INFORMATION, "Composite creado", "Se ha creado el departamento raíz.");
    }

    @FXML
    public void agregarEmpleado() {
        if (departamentoRaiz == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Primero debes crear el Composite.");
            return;
        }
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Empleado");
        dialog.setHeaderText("Ingrese el nombre del empleado:");
        dialog.setContentText("Nombre:");
        dialog.showAndWait().ifPresent(nombre -> {
            if (!nombre.trim().isEmpty()) {
                Empleado empleado = new EmpleadoHoja(nombre, nombre);
                departamentoRaiz.agregar(empleado);
                listComponentes.getItems().add("Empleado agregado: " + nombre);
                mostrarAlerta(Alert.AlertType.CONFIRMATION, "Empleado agregado", "Empleado '" + nombre + "' agregado correctamente.");
            }
        });
    }

    @FXML
    public void agregarDepartamento() {
        if (departamentoRaiz == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Primero debes crear el Composite.");
            return;
        }
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Departamento");
        dialog.setHeaderText("Ingrese el nombre del subdepartamento:");
        dialog.setContentText("Nombre:");
        dialog.showAndWait().ifPresent(nombre -> {
            if (!nombre.trim().isEmpty()) {
                Departamento sub = new Departamento(nombre);
                departamentoRaiz.agregar(sub);
                listComponentes.getItems().add("Departamento agregado: " + nombre);
                mostrarAlerta(Alert.AlertType.CONFIRMATION, "Departamento agregado", "Subdepartamento '" + nombre + "' agregado correctamente.");
            }
        });
    }

    // ===========================
    // UTILS
    // ===========================

    private void abrirPantallaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/Ejercicio/view/Productos.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Productos");
            stage.show();
            Stage ventanaActual = (Stage) txtId.getScene().getWindow();
            ventanaActual.close();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la pantalla de productos: " + e.getMessage());
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
