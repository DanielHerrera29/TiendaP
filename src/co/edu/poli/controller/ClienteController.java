package co.edu.poli.controller;

import co.edu.poli.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClienteController {

    @FXML
    private TextField clienteIdTextField;

    @FXML
    private TextField nombreClienteTextField;  
    @FXML
    private TextArea resumenTextArea;

    private ClienteFacade clienteFacade;

    public ClienteController() {
        Cliente cliente = new Cliente(1, "Juan Pérez");
        InformacionPersonal info = new InformacionPersonal(1, "Juan Pérez", "juan@example.com");
        FormaDePago pago = new FormaDePago(1, "Tarjeta", true);
        HistorialPedido historial = new HistorialPedido();
        historial.agregarPedido("Pedido 1 - $100");
        historial.agregarPedido("Pedido 2 - $200");

        this.clienteFacade = new ClienteFacade(cliente, info, pago, historial);
    }

    @FXML
    public void handleVerResumen() {
        try {
            int clienteId = Integer.parseInt(clienteIdTextField.getText());
            String resumen = clienteFacade.verResumenCliente(clienteId);
            resumenTextArea.setText(resumen);
        } catch (NumberFormatException e) {
            resumenTextArea.setText("Por favor ingrese un ID válido.");
        }
    }

    @FXML
    public void handleCrearNuevoCliente() {
        try {
            int clienteId = Integer.parseInt(clienteIdTextField.getText());
            String nombre = nombreClienteTextField.getText();
            if (nombre.isEmpty()) {
                resumenTextArea.setText("Por favor ingrese un nombre para el cliente.");
                return;
            }

            Cliente nuevoCliente = new Cliente(clienteId, nombre);
            InformacionPersonal info = new InformacionPersonal(clienteId, nombre, "nuevo@example.com");
            FormaDePago pago = new FormaDePago(clienteId, "Tarjeta", true);
            HistorialPedido historial = new HistorialPedido();
            historial.agregarPedido("Primer pedido - $0");

            clienteFacade = new ClienteFacade(nuevoCliente, info, pago, historial);

            resumenTextArea.setText("Nuevo cliente creado con ID: " + clienteId + " y nombre: " + nombre);
        } catch (NumberFormatException e) {
            resumenTextArea.setText("Por favor ingrese un ID válido.");
        }
    }
}
