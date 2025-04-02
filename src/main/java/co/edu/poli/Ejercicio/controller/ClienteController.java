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
import java.util.Optional;
import co.edu.poli.Ejercicio.model.Departamento;
import co.edu.poli.Ejercicio.model.Empleado;
import co.edu.poli.Ejercicio.model.EmpleadoHoja;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

public class ClienteController {

    private static final String Empleado = null;
	// CAMPOS DE LA VISTA
    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private ListView<String> listComponentes;
    @FXML private Button btnCrearComposite1;
    @FXML private Button btnAgregarEmpleado;
    @FXML private Button btnAgregarDepartamento;

    // MODELO COMPOSITE
    private Departamento departamentoRaiz1;

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
                mostrarAlerta1(Alert.AlertType.INFORMATION, "Éxito", "Inicio de sesión correcto.");
                abrirPantallaPrincipal();
            } else {
                mostrarAlerta1(Alert.AlertType.ERROR, "Error", "Credenciales incorrectas.");
            }
        } catch (SQLException e) {
            mostrarAlerta1(Alert.AlertType.ERROR, "Error", "Error al validar credenciales: " + e.getMessage());
        }
    }

    @FXML
    private void handleInsertarCliente() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        if (id.isEmpty() || nombre.isEmpty()) {
            mostrarAlerta1(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }
        Cliente nuevoCliente = new Cliente(id, nombre);
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.insertarCliente(nuevoCliente)) {
                mostrarAlerta1(Alert.AlertType.INFORMATION, "Éxito", "Cliente insertado correctamente.");
            } else {
                mostrarAlerta1(Alert.AlertType.ERROR, "Error", "No se pudo insertar el cliente.");
            }
        } catch (SQLException e) {
            mostrarAlerta1(Alert.AlertType.ERROR, "Error", "Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    @FXML
    private void handleEditarCliente() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        if (id.isEmpty() || nombre.isEmpty()) {
            mostrarAlerta1(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }
        Cliente cliente = new Cliente(id, nombre);
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.actualizarCliente(cliente)) {
                mostrarAlerta1(Alert.AlertType.INFORMATION, "Éxito", "Cliente actualizado correctamente.");
            } else {
                mostrarAlerta1(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el cliente.");
            }
        } catch (SQLException e) {
            mostrarAlerta1(Alert.AlertType.ERROR, "Error", "Error al actualizar el cliente: " + e.getMessage());
        }
    }

    @FXML
    private void handleBorrarCliente() {
        String id = txtId.getText();
        if (id.isEmpty()) {
            mostrarAlerta1(Alert.AlertType.ERROR, "Error", "Debe ingresar un ID para borrar.");
            return;
        }
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.eliminarCliente(id)) {
                mostrarAlerta1(Alert.AlertType.INFORMATION, "Éxito", "Cliente eliminado correctamente.");
            } else {
                mostrarAlerta1(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el cliente.");
            }
        } catch (SQLException e) {
            mostrarAlerta1(Alert.AlertType.ERROR, "Error", "Error al eliminar el cliente: " + e.getMessage());
        }
    }

    // ===========================
    // COMPOSITE
    // ===========================
    @FXML private Button btnCrearComposite11;
    private Departamento departamentoRaiz;

    @FXML
    public void initialize() {
        // Inicializamos el departamento raíz al inicio
        departamentoRaiz1 = new Departamento("Departamento Principal");
    }
    @FXML private Button btnCrearComposite;
    private Departamento departamentoActual;

    @FXML
    public void initialize1() {
        departamentoActual = null;  // Inicialmente no hay departamento creado
    }

    @FXML
    public void crearComposite() {
        if (departamentoRaiz == null) { // Evitar recrear si ya existe
            departamentoRaiz = new Departamento("Departamento Principal");
            mostrarAlerta1(null, "Composite creado", "Se ha creado el departamento raíz.");
            System.out.println("Departamento Principal creado.");
        } else {
            mostrarAlerta1(null, "Error", "El Composite ya fue creado.");
        }
    }
    @FXML
    public void agregarEmpleado() {
        if (departamentoRaiz == null) {
            mostrarAlerta1(null, "Error", "Debe crear primero el Composite.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Empleado");
        dialog.setHeaderText("Ingrese el nombre del empleado:");
        dialog.setContentText("Nombre:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nombre -> {
            if (!nombre.trim().isEmpty()) {
                Empleado empleado = new EmpleadoHoja(nombre, nombre);
                departamentoRaiz.agregar(empleado);
                mostrarAlerta1(null, "Empleado agregado", "Empleado '" + nombre + "' agregado correctamente.");
                System.out.println("Empleado agregado: " + nombre);

                // ✅ Ver la estructura después de agregar el empleado
                departamentoRaiz.mostrarEstructura("");
            }
        });
    }
    
    private Departamento buscarDepartamento(Departamento actual, String nombre) {
        // 1️⃣ Si el departamento actual coincide con el nombre buscado, lo retornamos
        if (actual.getNombre().equalsIgnoreCase(nombre)) {
            return actual;
        }

        // 2️⃣ Iterar sobre los componentes del departamento
        for (Componente componente : actual.getComponente()) { 
            if (componente instanceof Departamento) { // 🔹 Solo buscamos dentro de los subdepartamentos
                Departamento encontrado = buscarDepartamento((Departamento) componente, nombre);
                if (encontrado != null) {
                    return encontrado; // ✅ Si lo encuentra, lo devuelve
                }
            }
        }

        // 3️⃣ Si no lo encuentra, retorna null
        return null;
    }

    @FXML
    public void agregarDepartamento() {
        if (departamentoRaiz == null) {
            mostrarAlerta1(null, "Error", "Debe crear primero el Composite.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Departamento");
        dialog.setHeaderText("Ingrese el nombre del nuevo departamento:");
        dialog.setContentText("Nombre:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nombre -> {
            if (!nombre.trim().isEmpty()) {
                
                // 🔹 Preguntar en qué departamento se debe agregar
                TextInputDialog seleccionDialog = new TextInputDialog();
                seleccionDialog.setTitle("Seleccionar Departamento");
                seleccionDialog.setHeaderText("Ingrese el nombre del departamento padre:");
                seleccionDialog.setContentText("Nombre del departamento padre:");

                Optional<String> departamentoPadre = seleccionDialog.showAndWait();
                departamentoPadre.ifPresent(nombrePadre -> {
                    Departamento padre = buscarDepartamento(departamentoRaiz, nombrePadre);
                    
                    if (padre != null) {
                        Departamento subDepartamento = new Departamento(nombre);
                        padre.agregar(subDepartamento);
                        mostrarAlerta1(null, "Departamento agregado", "Se agregó el subdepartamento '" + nombre + "' dentro de '" + nombrePadre + "'.");
                    } else {
                        mostrarAlerta1(null, "Error", "No se encontró el departamento padre.");
                    }
                    
                    // ✅ Ver la estructura después de agregar el departamento
                    departamentoRaiz.mostrarEstructura("");
                });
            }
        });	
    }
    @FXML    
    public void mostrarEstructura() {
    	    if (departamentoActual == null) {
    	        mostrarAlerta1(Alert.AlertType.WARNING, "Error", "No hay un departamento seleccionado.");
    	        return;
    	    }

    	    // Construir la estructura del departamento
    	    StringBuilder estructura = new StringBuilder();
    	    construirEstructura(departamentoActual, estructura, "");

    	    // Mostrar la estructura en una alerta emergente
    	    mostrarAlerta1(Alert.AlertType.INFORMATION, "Estructura Organizacional", estructura.toString());
    	}

    

    // 🔹 Método auxiliar para recorrer la estructura del departamento
    
    private void construirEstructura(Componente componente, StringBuilder estructura, String prefijo) {
        estructura.append(prefijo).append("📂 ").append(componente.getNombre()).append("\n");

        if (componente instanceof Departamento) {
            Departamento depto = (Departamento) componente;
            for (Componente subComponente : depto.getComponente()) {
                if (subComponente instanceof Departamento) {
                    construirEstructura(subComponente, estructura, prefijo + "  ");
                } else {
                    estructura.append(prefijo).append("  👤 ").append(subComponente.getNombre()).append("\n");
                }
            }
        }
    }

    // Método recursivo para construir la estructura
    private void mostrarEstructuraRecursivo(Departamento departamento, String prefijo, StringBuilder estructura) {
        estructura.append(prefijo).append("- ").append(departamento.getNombre()).append("\n");
        for (Componente componente : departamento.getComponente()) {
            if (componente instanceof Departamento) {
                mostrarEstructuraRecursivo((Departamento) componente, prefijo + "  ", estructura);
            } else {
                estructura.append(prefijo).append("  - ").append(componente.getNombre()).append("\n");
            }
        }
    }



    private void mostrarAlerta1(AlertType warning, String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    private void abrirPantallaPrincipal() {
        System.out.println("Abriendo pantalla principal..."); // Verificar si se llama inesperadamente
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/Ejercicio/view/Productos.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Productos");
            stage.show();
            
            // Cerrar la ventana actual si es necesario
            Stage ventanaActual = (Stage) txtId.getScene().getWindow();
            ventanaActual.close();
        } catch (Exception e) {
            mostrarAlerta1(Alert.AlertType.ERROR, "Error", "No se pudo abrir la pantalla de productos: " + e.getMessage());
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    @FXML private ListView<String> listEstructura; // O usa un TextArea
    @FXML private TextArea txtEstructura;

}
