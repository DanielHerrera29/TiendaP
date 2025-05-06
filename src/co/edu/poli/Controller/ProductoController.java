package co.edu.poli.Controller;

import co.edu.poli.model.*;

public class ProductoController {

    private Producto producto;
    private HistorialPrecios historial;

    public ProductoController() {
        this.producto = new Producto(1, "Laptop", "Gamer con RTX 4080", 2500.00);
        this.historial = new HistorialPrecios();

        // Observador
        ObservadorPrecio observador = new ObservadorPrecio();
        producto.registrarObservador(observador);

        // Guardar primer estado
        historial.agregarMemento(producto.crearMemento());
    }

    public void cambiarPrecio(double nuevoPrecio) {
        producto.setPrecio(producto.getId(), nuevoPrecio);
        historial.agregarMemento(producto.crearMemento());
    }

    public void restaurarPrimerPrecio() {
        ProductoMemento memento = historial.obtenerHistorialProducto(producto.getId()).get(0);
        producto.restaurarMemento(memento);
    }

    public void mostrarEstado() {
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Precio actual: $" + producto.getPrecio());
        System.out.println("Historial de precios:");
        for (ProductoMemento m : historial.obtenerHistorialProducto(producto.getId())) {
            System.out.println(" - [" + m.getFecha() + "] $" + m.getPrecio());
        }
    }

    public Producto getProducto() {
        return producto;
    }
}
