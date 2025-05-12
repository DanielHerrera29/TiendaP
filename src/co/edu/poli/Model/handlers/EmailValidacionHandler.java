package co.edu.poli.Model.handlers;

import co.edu.poli.Controller.ClienteController;
import co.edu.poli.Model.Cliente;

import java.util.regex.*;

public class EmailValidacionHandler extends ClienteHandler {
    private ClienteController controller;

    public EmailValidacionHandler(ClienteController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(Cliente cliente) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.\\w{2,}$";
        if (!Pattern.matches(regex, cliente.getEmail())) {
            System.out.println("Email inválido.");
            if (controller != null) {
                controller.setClienteValidoParaRegistro(false);
                controller.setMensajeErrorCorreo("Ingrese un correo electrónico válido.");
            }
            return; 
        }
        super.handle(cliente);
    }
}