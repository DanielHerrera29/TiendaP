package co.edu.poli.Model;

public class AsignacionCategoriaHandler extends ClienteHandler {
    @Override
    public void handle(Cliente cliente) {
        if (cliente.getCategoria() == null || cliente.getCategoria().isEmpty()) {
            cliente.setCategoria("Nuevo");
        }
        super.handle(cliente);
    }
}

