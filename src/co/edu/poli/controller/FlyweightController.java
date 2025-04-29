package co.edu.poli.controller;

import co.edu.poli.model.ProductoReal;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FlyweightController {

    @FXML
    private Label proveedorNombreLabel;

    @FXML
    private Label proveedorDireccionLabel;

    @FXML
    private Label proveedorContactoLabel;

    private ProductoReal producto;

    public void setProducto(ProductoReal producto) {
        this.producto = producto;
        mostrarInformacionProveedor();
    }

    private void mostrarInformacionProveedor() {
        if (producto != null && producto.getProveedor() != null) {
            proveedorNombreLabel.setText("Nombre: " + producto.getProveedor().getNombre());
            proveedorDireccionLabel.setText("Dirección: " + producto.getProveedor().getDireccion());
            proveedorContactoLabel.setText("Dirección: " + producto.getProveedor().getDireccion());
        } else {
            proveedorNombreLabel.setText("Nombre: No disponible");
            proveedorDireccionLabel.setText("Dirección: No disponible");
            proveedorContactoLabel.setText("Dirección: No disponible");
        }
    }

    @FXML
    public void cerrarVentana() {
        Stage stage = (Stage) proveedorNombreLabel.getScene().getWindow();
        stage.close();
    }
}