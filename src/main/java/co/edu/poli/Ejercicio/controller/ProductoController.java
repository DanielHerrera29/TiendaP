package co.edu.poli.Ejercicio.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import co.edu.poli.Ejercicio.model.CargaExpress;
import co.edu.poli.Ejercicio.model.CargaFragil;
import co.edu.poli.Ejercicio.model.CargaPesada;
import co.edu.poli.Ejercicio.model.Certificacion;
import co.edu.poli.Ejercicio.model.Envio;
import co.edu.poli.Ejercicio.model.Evaluacion;
import co.edu.poli.Ejercicio.model.Internacional;
import co.edu.poli.Ejercicio.model.Mercancia;
import co.edu.poli.Ejercicio.model.Nacional;
import co.edu.poli.Ejercicio.model.PoliticaEntrega;
import co.edu.poli.Ejercicio.model.Producto;
import co.edu.poli.Ejercicio.model.ProductoAlimento;
import co.edu.poli.Ejercicio.model.ProductoElectronico;
import co.edu.poli.Ejercicio.model.Proveedor;
import co.edu.poli.Ejercicio.services.DAO;
import co.edu.poli.Ejercicio.services.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductoController {

    @FXML
    private ComboBox<String> cmbTipoProducto; 
    
    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCaracteristica;

    @FXML
    private TableView<Producto> tblProductos; 

    @FXML
    private TableColumn<Producto, String> colId;

    @FXML
    private TableColumn<Producto, String> colDescripcion;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, String> colCaracteristica;
  
    private final DAO<Producto> productoDAO = new ProductoDAO();
    
    @FXML
    private Button btnBuilder;

    @FXML
    private void initialize() {
        cmbTipoProducto.getItems().addAll("Electrónico", "Alimento");
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCaracteristica.setCellValueFactory(cellData -> 
            javafx.beans.binding.Bindings.createStringBinding(() -> 
                cellData.getValue().obtenerCaracteristica()
            )
        );

        tblProductos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtDescripcion.setText(newValue.getDescripcion());
                txtPrecio.setText(String.valueOf(newValue.getPrecio()));
                cmbTipoProducto.setValue(newValue.getTipo());

                if (newValue instanceof ProductoElectronico) {
                    txtCaracteristica.setText(((ProductoElectronico) newValue).getVoltajeEntrada());
                } else if (newValue instanceof ProductoAlimento) {
                    txtCaracteristica.setText(String.valueOf(((ProductoAlimento) newValue).getAporteCalorico()));
                }
            }
        });

        cmbTipoProducto.setOnAction(event -> cargarProductos());
    }


    @FXML
    private void handleConsultar() {
        cargarProductos();
    }

    @FXML
    private void handleInsertar() {
        String id = "P" + System.currentTimeMillis();
        String descripcion = txtDescripcion.getText().trim();
        String precioTexto = txtPrecio.getText().trim();
        String tipo = cmbTipoProducto.getValue();
        String caracteristicaTexto = txtCaracteristica.getText().trim();

        if (descripcion.isEmpty() || precioTexto.isEmpty() || tipo == null || caracteristicaTexto.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio debe ser un número válido.");
            return;
        }

        Producto nuevoProducto = null;
        if ("Electrónico".equals(tipo)) {
            nuevoProducto = new ProductoElectronico(id, descripcion, precio, tipo, caracteristicaTexto);
        } else if ("Alimento".equals(tipo)) {
            try {
                int aporteCalorico = Integer.parseInt(caracteristicaTexto);
                nuevoProducto = new ProductoAlimento(id, descripcion, precio, tipo, aporteCalorico);
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "El aporte calórico debe ser un número válido.");
                return;
            }
        }

        if (nuevoProducto != null) {
            try {
                productoDAO.insertar(nuevoProducto);
                mostrarAlerta("Éxito", "Producto insertado correctamente.");
                cargarProductos();
            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo insertar el producto: " + e.getMessage());
            }
        }
    }
    @FXML
    private void handleActualizar() {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                String id = seleccionado.getId(); 
                String descripcion = txtDescripcion.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                String tipo = cmbTipoProducto.getValue();
                String caracteristicaTexto = txtCaracteristica.getText().trim();

                Producto productoActualizado = null;

                if ("Electrónico".equals(tipo)) { 
                    productoActualizado = new ProductoElectronico(id, descripcion, precio, tipo, caracteristicaTexto);
                } else if ("Alimento".equals(tipo)) {
                    int aporteCalorico = Integer.parseInt(caracteristicaTexto);
                    productoActualizado = new ProductoAlimento(id, descripcion, precio, tipo, aporteCalorico);
                }

                if (productoActualizado != null) {
                    productoDAO.actualizar(productoActualizado);
                    mostrarAlerta("Éxito", "Producto actualizado correctamente.");
                    cargarProductos();
                }
            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo actualizar el producto: " + e.getMessage());
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "Verifica que el precio y la característica sean números válidos.");
            }
        } else {
            mostrarAlerta("Error", "Seleccione un producto para actualizar.");
        }
    }

    @FXML
    private void handleEliminar() {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                productoDAO.eliminar(seleccionado.getId());
                mostrarAlerta("Éxito", "Producto eliminado correctamente.");
                cargarProductos();
            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo eliminar el producto: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Error", "Seleccione un producto para eliminar.");
        }
    }

    private void cargarProductos() {
        String tipoSeleccionado = cmbTipoProducto.getValue();
        if (tipoSeleccionado != null) {
            try {
                List<Producto> productos = productoDAO.obtenerPorTipo(tipoSeleccionado);
                ObservableList<Producto> listaProductos = FXCollections.observableArrayList(productos);
                tblProductos.setItems(listaProductos);
            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo cargar los productos: " + e.getMessage());
            }
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(titulo.equals("Éxito") ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void handleClonar() {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                Producto clon = seleccionado.clone(); // Se crea el clon con nuevo ID
                productoDAO.insertar(clon);
                mostrarAlerta("Clonación Exitosa", "El producto ha sido clonado correctamente con ID: " + clon.getId());
                cargarProductos(); // Recargar la tabla para reflejar el cambio
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo clonar el producto: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Error", "Seleccione un producto para clonar.");
        }
    }
        @FXML
private void handleBuilder() {
    Proveedor proveedor = new Proveedor.ProveedorBuilder()
            .setId("PROV123")
            .setNombre("Proveedor de Ejemplo")
            .setContacto("contacto@proveedor.com")
            .setCertificaciones(Arrays.asList(
                    new Certificacion("CERT456", "ISO 9001",
                            java.sql.Date.valueOf(LocalDate.of(2022, 1, 1)),
                            java.sql.Date.valueOf(LocalDate.of(2024, 1, 1)))
            ))
            .setEvaluaciones(Arrays.asList(
                    new Evaluacion("EVAL789",
                            java.sql.Date.valueOf(LocalDate.now()),
                            4.5, "Buen proveedor")
            ))
            .setPoliticaEntrega(new PoliticaEntrega(7, 10.0, true, Arrays.asList("Zona A", "Zona B")))
            .build();

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Proveedor Creado");
    alert.setHeaderText("Se ha creado un proveedor usando el patrón Builder");
    alert.setContentText("Nombre: " + proveedor.getNombre() +
            "\nContacto: " + proveedor.getContacto() +
            "\nCertificación: " + proveedor.getCertificaciones().get(0).getNombre() +
            "\nEvaluación: " + proveedor.getEvaluaciones().get(0).getComentarios() +
            "\nPolítica de Entrega: " + proveedor.getPoliticaEntrega().getZonasCobertura());

    alert.showAndWait();
}
 @FXML
    private void handleBridge() {
        StringBuilder output = new StringBuilder();

        // carga frágil
        Envio envioNacional = new Nacional();
        Mercancia cargaFragil = new CargaFragil(envioNacional, "Carga frágil", 10);
        envioNacional.setMercancia(cargaFragil);
        output.append(envioNacional.enviar()).append("\n");
        output.append(cargaFragil.procesar()).append("\n");

        // carga pesada
        Envio envioInternacional = new Internacional();
        Mercancia cargaPesada = new CargaPesada(envioInternacional, "Carga pesada", 100.0);
        envioInternacional.setMercancia(cargaPesada);
        output.append(envioInternacional.enviar()).append("\n");
        output.append(cargaPesada.procesar()).append("\n");

        // Carga express
        Envio envioCargaExpress = new CargaExpress();
        Mercancia cargaPesadaExpress = new CargaPesada(envioCargaExpress, "Carga express", 100.0);
        envioCargaExpress.setMercancia(cargaPesadaExpress);
        output.append(envioCargaExpress.enviar()).append("\n");
        output.append(cargaPesadaExpress.procesar()).append("\n");

        // Mostrar en ventana
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Envíos Bridge");
        alert.setHeaderText("Se procesaron los siguientes envíos:");
        alert.setContentText(output.toString());
        alert.showAndWait();
    }
   
}