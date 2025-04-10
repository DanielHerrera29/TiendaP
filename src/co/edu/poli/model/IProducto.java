package co.edu.poli.model;

public interface IProducto {
    String obtenerDetalles(int usuarioId);
    double obtenerPrecio(int usuarioId);
    String obtenerEspecificaciones(int usuarioId);
}
