package co.edu.poli.model;

import java.util.List;

public class ProductoReal implements IProducto {

    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private List<Especificacion> especificaciones;

    public ProductoReal(int id, String nombre, double precio, String descripcion, List<Especificacion> especificaciones) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.especificaciones = especificaciones;
    }

    @Override
    public String obtenerDetalles(int usuarioId) {
        return "Producto: " + nombre + " - " + descripcion;
    }

    @Override
    public double obtenerPrecio(int usuarioId) {
        return precio;
    }

    @Override
    public String obtenerEspecificaciones(int usuarioId) {
        StringBuilder sb = new StringBuilder();
        for (Especificacion e : especificaciones) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}
