package co.edu.poli.model;
public interface ProductoObservable {
    void registrarObservador(IProductoObserver observador);
    void eliminarObservador(IProductoObserver observador);
    void notificarObservadores();
}
