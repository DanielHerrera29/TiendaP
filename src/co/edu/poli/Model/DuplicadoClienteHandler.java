package co.edu.poli.Model;

import co.edu.poli.Controller.ClienteController;

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
            System.out.println("Cliente ya registrado.");
          
            if (controller != null) {
                controller.setClienteValidoParaRegistro(false);
                controller.setMensajeErrorCorreo("Ya se encuentra registrado un cliente con el correo.");
            }
            return; 
        }
        correosRegistrados.add(cliente.getEmail());
        super.handle(cliente);
    }
}