package co.edu.poli.model;

import java.util.List;

public class ProductoReal implements IProducto {
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private List<Especificacion> especificaciones;
    private Proveedor proveedor; // Referencia al proveedor

    public ProductoReal(int id, String nombre, double precio, String descripcion, List<Especificacion> especificaciones, Proveedor proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.especificaciones = especificaciones;
        this.proveedor = proveedor;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Especificacion> getEspecificaciones() {
        return especificaciones;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public String obtenerInformacionProveedor() {
        if (proveedor != null) {
            return "Proveedor: " + proveedor.getNombre();
        } else {
            return "Proveedor: No asignado";
        }
    }

    @Override
    public String obtenerDetalles(int usuarioId) {
        return "Detalles del producto: " + nombre + ", " + descripcion;
    }

    @Override
    public double obtenerPrecio(int usuarioId) {
        return precio;
    }

    @Override
    public String obtenerEspecificaciones(int usuarioId) {
        StringBuilder sb = new StringBuilder("Especificaciones:\n");
        for (Especificacion spec : especificaciones) {
            sb.append("- ").append(spec).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Proveedor obtenerProveedor(int usuarioId) {
        return this.proveedor;
    }
}
