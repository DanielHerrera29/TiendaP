package co.edu.poli.model;

public interface IProducto {
    String obtenerDetalles(int usuarioId);
    double obtenerPrecio(int usuarioId);
    String obtenerEspecificaciones(int usuarioId);
    Proveedor obtenerProveedor(int usuarioId); // Nuevo método
}
// package co.edu.poli.model;

// public interface IProducto {
//     String obtenerDetalles(int usuarioId);
//     double obtenerPrecio(int usuarioId);
//     String obtenerEspecificaciones(int usuarioId);
//     Proveedor obtenerProveedor();
//     Proveedor actualizarProveedor(Proveedor proveedor); 
// }