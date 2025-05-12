package co.edu.poli.Controller;

import co.edu.poli.Model.Cliente;
import co.edu.poli.Model.handlers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ClienteController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtDireccion;
    @FXML
    private ComboBox<String> cmbCategoria;
    @FXML
    private Label lblNombreError;
    @FXML
    private Label lblCorreoError;
    @FXML
    private Label lblDireccionError;
    @FXML
    private Label lblCategoriaError;

    @FXML
    private TextField txtMontoCompraMensual;
    @FXML
    private TextField txtBeneficios;

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colCorreo;
    @FXML
    private TableColumn<Cliente, String> colDireccion;
    @FXML
    private TableColumn<Cliente, String> colCategoria;
    @FXML
    private TableColumn<Cliente, String> colBeneficios;
    @FXML
    private TableColumn<Cliente, Double> colMontoCompraMensual;
    @FXML
    private Button btnGuardarEdicion;

    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    private Cliente clienteAEditar;
    private ClienteController controller;
    private ClienteHandler handlerChain;
    private Set<String> correosRegistrados = new HashSet<>();
    private boolean clienteValidoParaRegistro = true;
    private String mensajeErrorCorreo = "";
    private String mensajeErrorDireccion = "";

    @FXML
    public void initialize() {
        controller = this;

        ObservableList<String> categorias = FXCollections.observableArrayList("Básico", "Plata", "Oro", "Premium");
        cmbCategoria.setItems(categorias);
        cmbCategoria.setValue("Básico");

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colBeneficios.setCellValueFactory(new PropertyValueFactory<>("beneficios"));
        colMontoCompraMensual.setCellValueFactory(new PropertyValueFactory<>("montocompraMensual"));

        TableColumn<Cliente, Void> colAcciones = new TableColumn<>("Acciones");
        colAcciones.setMinWidth(100);
        colAcciones.setCellFactory(new Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>>() {
            @Override
            public TableCell<Cliente, Void> call(final TableColumn<Cliente, Void> param) {
                return new TableCell<Cliente, Void>() {
                    private final HBox buttons = new HBox(10);
                    private final Button editButton = new Button("Editar");
                    private final Button deleteButton = new Button("Eliminar");

                    {
                        editButton.setOnAction(event -> {
                            Cliente cliente = getTableView().getItems().get(getIndex());
                            controller.editarCliente(cliente);
                        });
                        deleteButton.setOnAction(event -> {
                            Cliente cliente = getTableView().getItems().get(getIndex());
                            controller.eliminarCliente(cliente);
                        });
                        buttons.getChildren().addAll(editButton, deleteButton);
                        buttons.setAlignment(Pos.CENTER);
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(buttons);
                        }
                    }
                };
            }
        });

        tablaClientes.getColumns().add(colAcciones);

        tablaClientes.setItems(listaClientes);

        EmailValidacionHandler emailHandler = new EmailValidacionHandler(this);
        DuplicadoClienteHandler duplicadoHandler = new DuplicadoClienteHandler(correosRegistrados, this);
        AsignacionCategoriaHandler categoriaHandler = new AsignacionCategoriaHandler();
        DireccionValidacionHandler direccionHandler = new DireccionValidacionHandler(this);
        BeneficiosClienteHandler beneficiosClienteHandler = new BeneficiosClienteHandler();
        MensajeBienvenidaHandler bienvenidaHandler = new MensajeBienvenidaHandler(this);

        emailHandler.setNext(duplicadoHandler);
        duplicadoHandler.setNext(categoriaHandler);
        categoriaHandler.setNext(direccionHandler);
        direccionHandler.setNext(beneficiosClienteHandler);
        beneficiosClienteHandler.setNext(bienvenidaHandler);

        this.handlerChain = emailHandler;

        cmbCategoria.setOnAction(event -> {
            actualizarBeneficios();
        });
    }

    public void setMensajeErrorCorreo(String mensaje) {
        this.mensajeErrorCorreo = mensaje;
        lblCorreoError.setText(mensajeErrorCorreo);
    }

    public void setMensajeErrorDireccion(String mensaje) {
        this.mensajeErrorDireccion = mensaje;
        lblDireccionError.setText(mensajeErrorDireccion);
    }

    public void setClienteValidoParaRegistro(boolean valido) {
        this.clienteValidoParaRegistro = valido;
    }

    @FXML
    public void registrarCliente() {
        if (validarCamposBasicos()) {
            String categoriaSeleccionada = cmbCategoria.getValue();

            Cliente nuevoCliente = new Cliente(
                    txtNombre.getText().trim(),
                    txtCorreo.getText().trim(),
                    txtDireccion.getText().trim(),
                    categoriaSeleccionada
            );

            clienteValidoParaRegistro = true;
            mensajeErrorCorreo = "";
            mensajeErrorDireccion = "";
            lblCorreoError.setText("");
            lblDireccionError.setText("");

            handlerChain.handle(nuevoCliente);

            if (clienteValidoParaRegistro) {
                listaClientes.add(nuevoCliente);
                correosRegistrados.add(nuevoCliente.getEmail());
                actualizarCamposBeneficios(nuevoCliente.getCategoria());

                tablaClientes.refresh();
                limpiarCampos();
            } else {
                if (!mensajeErrorCorreo.isEmpty() || !mensajeErrorDireccion.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error de Registro");
                    alert.setHeaderText(null);
                    StringBuilder content = new StringBuilder("Por favor,");
                    if (!mensajeErrorCorreo.isEmpty()) {
                        content.append("- ").append(mensajeErrorCorreo).append("\n");
                    }
                    if (!mensajeErrorDireccion.isEmpty()) {
                        content.append("- ").append(mensajeErrorDireccion).append("\n");
                    }
                    alert.setContentText(content.toString());
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error de Registro");
                    alert.setHeaderText(null);
                    alert.setContentText("No se pudo registrar el cliente. Revise los datos.");
                    alert.showAndWait();
                }
            }
        } else {
            if (txtNombre.getText().trim().isEmpty()) {
                lblNombreError.setText("El nombre es obligatorio.");
            }
            if (txtCorreo.getText().trim().isEmpty()) {
                lblCorreoError.setText("El correo es obligatorio.");
            }
            if (txtDireccion.getText().trim().isEmpty()) {
                lblDireccionError.setText("La dirección es obligatoria.");
            }
        }
    }

    @FXML
    public void editarCliente(Cliente cliente) {
        clienteAEditar = cliente;
        txtNombre.setText(cliente.getNombre());
        txtCorreo.setText(cliente.getEmail());
        txtDireccion.setText(cliente.getDireccion());
        cmbCategoria.setValue(cliente.getCategoria());

        actualizarCamposBeneficios(cliente.getCategoria());

        btnGuardarEdicion.setVisible(true);
        btnGuardarEdicion.setManaged(true);
    }

    @FXML
    public void guardarEdicionCliente() {
        if (clienteAEditar != null && validarCamposBasicos()) {

            clienteAEditar.setNombre(txtNombre.getText().trim());
            clienteAEditar.setEmail(txtCorreo.getText().trim());
            clienteAEditar.setDireccion(txtDireccion.getText().trim());
            clienteAEditar.setCategoria(cmbCategoria.getValue());

            BeneficiosClienteHandler beneficiosClienteHandler = new BeneficiosClienteHandler();
            beneficiosClienteHandler.handle(clienteAEditar);
            actualizarCamposBeneficios(clienteAEditar.getCategoria());

            int index = -1;
            for (int i = 0; i < listaClientes.size(); i++) {
                if (listaClientes.get(i).equals(clienteAEditar)) {
                    index = i;
                    break;
                }
            }

            if (index >= 0) {
                listaClientes.set(index, clienteAEditar);
                tablaClientes.refresh();
            } else {
                System.out.println("¡Error! No se encontró el cliente a editar en la lista.");
            }

            limpiarCampos();
            clienteAEditar = null;
            btnGuardarEdicion.setVisible(false);
            btnGuardarEdicion.setManaged(false);

        } else {
            if (txtNombre.getText().trim().isEmpty()) {
                lblNombreError.setText("El nombre es obligatorio.");
            }
            if (txtCorreo.getText().trim().isEmpty()) {
                lblCorreoError.setText("El correo es obligatorio.");
            }
            if (txtDireccion.getText().trim().isEmpty()) {
                lblDireccionError.setText("La dirección es obligatoria.");
            }
        }
    }

    @FXML
    public void eliminarCliente(Cliente cliente) {
        listaClientes.remove(cliente);
        correosRegistrados.remove(cliente.getEmail());
    }

    private boolean validarCamposBasicos() {
        boolean esValido = true;

        lblNombreError.setText("");
        lblCorreoError.setText("");
        lblDireccionError.setText("");

        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String direccion = txtDireccion.getText().trim();

        if (nombre.isEmpty()) {
            esValido = false;
        }

        if (correo.isEmpty()) {
            esValido = false;
        }

        if (direccion.isEmpty()) {
            esValido = false;
        }

        return esValido;
    }

    public void limpiarCampos() {
        txtNombre.clear();
        txtCorreo.clear();
        txtDireccion.clear();
        cmbCategoria.setValue("Básico");
        txtMontoCompraMensual.clear();
        txtBeneficios.clear();
    }

    @FXML
    public void volverAlMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/View/VistaPrincipal.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) txtNombre.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarBeneficios() {
        String categoriaSeleccionada = cmbCategoria.getValue();
        actualizarCamposBeneficios(categoriaSeleccionada);

        if (clienteAEditar != null) {
            clienteAEditar.setCategoria(categoriaSeleccionada);
            BeneficiosClienteHandler beneficiosClienteHandler = new BeneficiosClienteHandler();
            beneficiosClienteHandler.handle(clienteAEditar);
        }
    }

    private void actualizarCamposBeneficios(String categoria) {
        String beneficiosTexto = "";
        String montoCompraTexto = "";

        switch (categoria) {
            case "Nuevo":
            case "Básico":
                beneficiosTexto = "5% de descuento en primera compra, Newsletter semanal";
                montoCompraTexto = "N/A";
                break;
            case "Plata":
                beneficiosTexto = "10% de descuento, Envío estándar gratuito";
                montoCompraTexto = "500.000 - 700.000";
                break;
            case "Oro":
                beneficiosTexto = "15% de descuento, Atención prioritaria";
                montoCompraTexto = "800.000 - 1.000.000";
                break;
            case "Premium":
                beneficiosTexto = "20% de descuento";
                montoCompraTexto = "1.100.000 - 1.300.000";
                break;
            default:
                beneficiosTexto = "";
                montoCompraTexto = "";
                break;
        }
        txtBeneficios.setText(beneficiosTexto);
        txtMontoCompraMensual.setText(montoCompraTexto);
    }
}
