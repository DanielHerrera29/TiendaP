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

	@FXML private TextField txtId;
	@FXML private TextField txtNombre;
	@FXML private ListView<String> listComponentes; 
	@FXML private Button btnCrearComposite;
	@FXML private Button btnAgregarEmpleado;
	@FXML private Button btnAgregarDepartamento;
	@FXML private TextArea txtEstructura;

    private Departamento departamentoRaiz;

    @FXML
    public void initialize() {
        departamentoRaiz = null;
    }

    // =============================
    // FUNCIONES CLIENTE (CRUD)
    // =============================

 
    @FXML
    private void handleInsertarCliente() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();

        if (id.isEmpty() || nombre.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }

        Cliente cliente = new Cliente(id, nombre);
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.insertarCliente(cliente)) {
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

    // =============================
    // FUNCIONES COMPOSITE
    // =============================

    @FXML
    public void crearComposite() {
        if (departamentoRaiz == null) {
            departamentoRaiz = new Departamento("Departamento Principal");

            Empleado emp1 = new EmpleadoHoja("E001", "Juan Pérez");
            Empleado emp2 = new EmpleadoHoja("E002", "Ana García");
            Departamento subDepartamento = new Departamento("Subdepartamento A");
            Empleado emp3 = new EmpleadoHoja("E003", "Carlos Martínez");

            departamentoRaiz.agregar(emp1);
            departamentoRaiz.agregar(emp2);
            subDepartamento.agregar(emp3);
            departamentoRaiz.agregar(subDepartamento);

            listComponentes.getItems().add("Composite creado: Departamento Principal");
            listComponentes.getItems().add("Empleado: Juan Pérez");
            listComponentes.getItems().add("Empleado: Ana García");
            listComponentes.getItems().add("Subdepartamento: Subdepartamento A");
            listComponentes.getItems().add("Empleado: Carlos Martínez (en Subdepartamento A)");

            mostrarAlerta(Alert.AlertType.INFORMATION, "Composite creado", "Se ha creado el departamento raíz y se han agregado empleados y subdepartamentos.");
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "El departamento raíz ya fue creado.");
        }
    }

    @FXML
    public void agregarEmpleado() {
        if (departamentoRaiz == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Debe crear primero el departamento raíz.");
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
                listComponentes.getItems().add("Empleado: " + nombre);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Empleado agregado", "Empleado '" + nombre + "' agregado.");
            }
        });
    }

    @FXML
    public void agregarDepartamento() {
        if (departamentoRaiz == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Debe crear primero el departamento raíz.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Departamento");
        dialog.setHeaderText("Ingrese el nombre del subdepartamento:");
        dialog.setContentText("Nombre:");

        dialog.showAndWait().ifPresent(nombre -> {
            if (!nombre.trim().isEmpty()) {
                TextInputDialog padreDialog = new TextInputDialog();
                padreDialog.setTitle("Departamento Padre");
                padreDialog.setHeaderText("¿En qué departamento desea agregarlo?");
                padreDialog.setContentText("Nombre del departamento padre:");

                padreDialog.showAndWait().ifPresent(nombrePadre -> {
                    Departamento padre = buscarDepartamento(departamentoRaiz, nombrePadre);
                    if (padre != null) {
                        Departamento nuevo = new Departamento(nombre);
                        padre.agregar(nuevo);
                        listComponentes.getItems().add("Subdepartamento: " + nombre + " (padre: " + nombrePadre + ")");
                    } else {
                        mostrarAlerta(Alert.AlertType.WARNING, "No encontrado", "No se encontró el departamento padre.");
                    }
                });
            }
        });
    }

    @FXML
    public void mostrarEstructura() {
        if (departamentoRaiz == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Debe crear un Composite primero.");
            return;
        }

        StringBuilder estructura = new StringBuilder();
        construirEstructura(departamentoRaiz, estructura, "");
        txtEstructura.setText(estructura.toString());
    }

    private void construirEstructura(Componente componente, StringBuilder estructura, String prefijo) {
        estructura.append(prefijo).append("📂 ").append(componente.getNombre()).append("\n");

        if (componente instanceof Departamento) {
            for (Componente sub : ((Departamento) componente).getComponente()) {
                if (sub instanceof Departamento) {
                    construirEstructura(sub, estructura, prefijo + "  ");
                } else {
                    estructura.append(prefijo).append("  👤 ").append(sub.getNombre()).append("\n");
                }
            }
        }
    }

    private Departamento buscarDepartamento(Departamento actual, String nombre) {
        if (actual.getNombre().equalsIgnoreCase(nombre)) return actual;

        for (Componente comp : actual.getComponente()) {
            if (comp instanceof Departamento) {
                Departamento encontrado = buscarDepartamento((Departamento) comp, nombre);
                if (encontrado != null) return encontrado;
            }
        }
        return null;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

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
}
