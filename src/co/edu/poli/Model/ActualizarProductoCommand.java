package co.edu.poli.Model;

public class ActualizarProductoCommand implements Command {
    private Producto producto;
    private String nuevoNombre;

    public ActualizarProductoCommand(Producto producto, String nuevoNombre) {
        this.producto = producto;
        this.nuevoNombre = nuevoNombre;
    }

    @Override
    public String ejecutar() {
        return producto.actualizar(nuevoNombre);
    }
}
