package co.edu.poli.Model;

public class CrearProductoCommand implements Command {
    private Producto producto;

    public CrearProductoCommand(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String ejecutar() {
        return producto.crear();
    }
}
