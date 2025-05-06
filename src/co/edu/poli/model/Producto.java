package co.edu.poli.model;

import java.util.*;

public class Producto implements ProductoObservable {
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private Date fechaActualizacion;

    private List<IProductoObserver> observadores = new ArrayList<>();

    public Producto(int id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaActualizacion = new Date();
    }

    public double getPrecio(int productId) {
        return this.precio;
    }

    public void setPrecio(int productId, double nuevoPrecio) {
        this.precio = nuevoPrecio;
        this.fechaActualizacion = new Date();
        notificarObservadores();
    }

    public ProductoMemento crearMemento() {
        return new ProductoMemento(id, precio, fechaActualizacion);
    }

    public void restaurarMemento(ProductoMemento memento) {
        this.precio = memento.getPrecio();
        this.fechaActualizacion = memento.getFecha();
    }

    @Override
    public void registrarObservador(IProductoObserver observador) {
        observadores.add(observador);
    }

    @Override
    public void eliminarObservador(IProductoObserver observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        for (IProductoObserver observador : observadores) {
            observador.actualizar(this);
        }
    }

    // Getters y setters adicionales

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
        this.fechaActualizacion = new Date();
        notificarObservadores();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public List<IProductoObserver> getObservadores() {
        return observadores;
    }

    public void setObservadores(List<IProductoObserver> observadores) {
        this.observadores = observadores;
    }
}
