package co.edu.poli.model;

public class ProductoProxy implements IProducto {

    private AutenticacionService autenticacionService;
    private ProductoReal productoReal;
    private Usuario usuario;

    public ProductoProxy(ProductoReal productoReal, AutenticacionService autenticacionService) {
        this.productoReal = productoReal;
        this.autenticacionService = autenticacionService;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private boolean verificarAcceso(int nivelRequerido) {
        return autenticacionService.verificarNivelAcceso(usuario, nivelRequerido);
    }

    @Override
    public String obtenerDetalles(int usuarioId) {
        if (verificarAcceso(1)) {
            return productoReal.obtenerDetalles(usuarioId);
        }
        return "Acceso denegado a los detalles del producto.";
    }

    @Override
    public double obtenerPrecio(int usuarioId) {
        if (verificarAcceso(1)) {
            return productoReal.obtenerPrecio(usuarioId);
        }
        return 0.0;
    }

    @Override
    public String obtenerEspecificaciones(int usuarioId) {
        if (verificarAcceso(2)) {
            return productoReal.obtenerEspecificaciones(usuarioId);
        }
        return "Acceso denegado a las especificaciones.";
    }
}
