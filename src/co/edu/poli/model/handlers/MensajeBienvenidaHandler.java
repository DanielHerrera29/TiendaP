package co.edu.poli.Model.handlers;


import co.edu.poli.Controller.ClienteController;
import co.edu.poli.Model.Cliente;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MensajeBienvenidaHandler extends ClienteHandler {
    private final ClienteController controller;

    public MensajeBienvenidaHandler(ClienteController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(Cliente cliente) {
      
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Bienvenido");
        alert.setHeaderText(null);
        alert.setContentText("¡Bienvenido, " + cliente.getNombre() + " ya esta registrado!");
        alert.showAndWait();

      
        if (controller != null) {
            controller.limpiarCampos();
        }

       
        super.handle(cliente);
    }
}

