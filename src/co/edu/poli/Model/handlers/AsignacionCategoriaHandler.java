package co.edu.poli.Model.handlers;

import co.edu.poli.Model.Cliente;

public class AsignacionCategoriaHandler extends ClienteHandler {
    @Override
    public void handle(Cliente cliente) {
        if (cliente.getCategoria() == null || cliente.getCategoria().isEmpty()) {
            cliente.setCategoria("Nuevo");
        }
        super.handle(cliente);
    }
}

