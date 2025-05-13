package co.edu.poli.Model;

import co.edu.poli.Controller.ClienteController;

public class DireccionValidacionHandler extends ClienteHandler {
    private ClienteController controller;

    public DireccionValidacionHandler(ClienteController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(Cliente cliente) {
        if (cliente.getDireccion() == null || cliente.getDireccion().isEmpty()) {
            if (controller != null) {
                controller.setClienteValidoParaRegistro(false);
                controller.setMensajeErrorDireccion("La dirección es obligatoria.");
            }
            return;
        }
        super.handle(cliente);
    }
}
