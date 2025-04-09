package co.edu.poli.model;

public class ProductoProxy implements IProducto {
    private ProductoReal productoReal;
    private AutenticacionService autenticacionService;
    private Usuario usuario;
    private int nivelRequerido;

    public ProductoProxy(ProductoReal productoReal, Usuario usuario, int nivelRequerido) {
        this.productoReal = productoReal;
        this.usuario = usuario;
        this.nivelRequerido = nivelRequerido;
        this.autenticacionService = new AutenticacionService();
    }

    @Override
    public String obtenerDetalles(int usuarioId) {
        if (verificarAcceso()) {
            return productoReal.obtenerDetalles(usuarioId);
        } else {
            return "Acceso denegado a los detalles del producto.";
        }
    }

    @Override
    public double obtenerPrecio(int usuarioId) {
        if (verificarAcceso()) {
            return productoReal.obtenerPrecio(usuarioId);
        } else {
            return -1;
        }
    }

    @Override
    public String obtenerEspecificaciones(int usuarioId) {
        if (verificarAcceso()) {
            return productoReal.obtenerEspecificaciones(usuarioId);
        } else {
            return "Acceso denegado a las especificaciones del producto.";
        }
    }

    private boolean verificarAcceso() {
        return autenticacionService.verificarNivelAcceso(usuario, nivelRequerido);
    }
}
