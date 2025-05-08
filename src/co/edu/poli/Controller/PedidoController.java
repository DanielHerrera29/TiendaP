package co.edu.poli.Controller;

import co.edu.poli.Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class PedidoController {

    @FXML private TextField txtCliente;
    @FXML private TextField txtProducto;
    @FXML private TextField txtPrecio;
    @FXML private TextArea areaCarrito;
    @FXML private ComboBox<String> comboPago;
    @FXML private Button btnAgregarProducto;
    @FXML private Button btnProcesarPago;

    private Pedido pedido;

    @FXML
    public void initialize() {
        comboPago.getItems().addAll("Tarjeta", "Efectivo");

        btnAgregarProducto.setOnAction(e -> agregarProducto());
        btnProcesarPago.setOnAction(e -> procesarPago());
    }

    private void agregarProducto() {
        String nombreCliente = txtCliente.getText();
        if (pedido == null) {
            if (nombreCliente.isEmpty()) {
                mostrarAlerta("Debe ingresar un nombre de cliente.");
                return;
            }
            pedido = new Pedido(new Cliente(nombreCliente, ""));
        }

        String nombreProd = txtProducto.getText();
        String precioStr = txtPrecio.getText();
        if (nombreProd.isEmpty() || precioStr.isEmpty()) {
            mostrarAlerta("Debe completar producto y precio.");
            return;
        }

        double precio = Double.parseDouble(precioStr);
        Producto producto = new Producto(nombreProd, precio);
        pedido.agregarProducto(producto);
        areaCarrito.appendText(producto.getNombre() + " - $" + producto.getPrecio() + "\n");

        txtProducto.clear();
        txtPrecio.clear();
    }

    private void procesarPago() {
        if (pedido == null || pedido.getProductos().isEmpty()) {
            mostrarAlerta("No hay productos en el pedido.");
            return;
        }

        String metodoPago = comboPago.getValue();
        if (metodoPago == null) {
            mostrarAlerta("Debe seleccionar un método de pago.");
            return;
        }

        EstrategiaPago estrategia = null;

        if (metodoPago.equals("Tarjeta")) {
            estrategia = new PagoConTarjeta("1234-5678-9012-3456", pedido.getCliente().getNombre(), "123");
        } else if (metodoPago.equals("Efectivo")) {
            estrategia = new PagoEnEfectivo("Caja 1");
        }

        pedido.setEstrategiaPago(estrategia);
        pedido.procesarPago();

        mostrarAlerta("Pago procesado por $" + pedido.calcularTotal());

        areaCarrito.clear();
        txtCliente.clear();
        comboPago.setValue(null);
        pedido = null;
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
