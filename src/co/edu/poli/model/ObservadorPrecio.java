package co.edu.poli.model;
import java.util.*;

public class ObservadorPrecio implements IProductoObserver {
    private double precio;
    private List<Producto> observadorProductos = new ArrayList<>();

    @Override
    public void actualizar(Producto producto) {
        this.precio = producto.getPrecio(producto.getId());
        observadorProductos.add(producto);
        System.out.println("Observador actualizado: precio del producto " + producto.getId() + " = " + precio);
    }
}
