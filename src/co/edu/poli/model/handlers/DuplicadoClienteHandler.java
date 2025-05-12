package co.edu.poli.Model.handlers;

import co.edu.poli.Controller.ClienteController;
import co.edu.poli.Model.Cliente;
import java.util.Set;


public class DuplicadoClienteHandler extends ClienteHandler {
    private Set<String> correosRegistrados;
    private ClienteController controller;

    public DuplicadoClienteHandler(Set<String> correosRegistrados, ClienteController controller) {
        this.correosRegistrados = correosRegistrados;
        this.controller = controller;
    }

    @Override
    public void handle(Cliente cliente) {
        if (correosRegistrados.contains(cliente.getEmail())) {
            if (controller != null) {
                controller.setClienteValidoParaRegistro(false);
                controller.setMensajeErrorCorreo("Ya se encuentra registrado el correo.");
            }
            return;
        }
        super.handle(cliente);
    }
}