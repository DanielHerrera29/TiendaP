package co.edu.poli.model;

public interface IProducto {
    void obtenerDetalles(int usuarioId);
    double obtenerPrecio(int usuarioId);
    String obtenerEspecificaciones(int usuarioId);
}
