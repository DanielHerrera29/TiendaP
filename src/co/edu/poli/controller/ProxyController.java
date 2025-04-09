package co.edu.poli.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import co.edu.poli.model.*;
import java.util.HashMap;
import java.util.Map;

public class ProxyController {

    @FXML
    private Label lblResultado;

    @FXML
    public void mostrarDetalles() {
        Map<String, String> specs = new HashMap<>();
        specs.put("Color", "Negro");
        specs.put("Tamaño", "15 pulgadas");

        ProductoReal real = new ProductoReal(1, "Laptop", 2500.00, "Laptop para trabajo", specs);

        Usuario usuario = new Usuario(101, "Daniel", 2); // nivel bajo
        int nivelRequerido = 2;

        IProducto proxy = new ProductoProxy(real, usuario, nivelRequerido);

        String resultado = proxy.obtenerDetalles(usuario.getId());
        lblResultado.setText(resultado);
    }
}

