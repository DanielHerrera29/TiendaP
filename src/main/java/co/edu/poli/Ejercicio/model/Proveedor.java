package co.edu.poli.Ejercicio.model;

import java.util.ArrayList;
import java.util.List;

public class Proveedor {
    private String id;
    private String nombre;
    private List<Producto> productos;
    private List<Certificacion> certificaciones;
    private List<Evaluacion> evaluaciones;
    private PoliticaEntrega politicaEntrega;
    
    // Constructor privado para usar con el patrón Builder
    private Proveedor() {
        this.productos = new ArrayList<>();
        this.certificaciones = new ArrayList<>();
        this.evaluaciones = new ArrayList<>();
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public List<Producto> getProductos() {
        return productos;
    }
    
    public List<Certificacion> getCertificaciones() {
        return certificaciones;
    }
    
    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
    
    public PoliticaEntrega getPoliticaEntrega() {
        return politicaEntrega;
    }
    
    // Métodos para agregar elementos
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
    
    public void agregarCertificacion(Certificacion certificacion) {
        certificaciones.add(certificacion);
    }
    
    public void agregarEvaluacion(Evaluacion evaluacion) {
        evaluaciones.add(evaluacion);
    }
    
    // PoliticaEntrega inner class
    public static class PoliticaEntrega {
        private int tiempoEntrega; // en días
        private double costoEnvio;
        
        public PoliticaEntrega(int tiempoEntrega, double costoEnvio) {
            this.tiempoEntrega = tiempoEntrega;
            this.costoEnvio = costoEnvio;
        }
        
        public int getTiempoEntrega() {
            return tiempoEntrega;
        }
        
        public double getCostoEnvio() {
            return costoEnvio;
        }
        
        @Override
        public String toString() {
            return "Tiempo de entrega: " + tiempoEntrega + " días, Costo de envío: $" + costoEnvio;
        }
    }
    
    // Builder class
    public static class Builder {
        private Proveedor proveedor;
        
        public Builder() {
            proveedor = new Proveedor();
        }
        
        public Builder conIdentificacion(String id, String nombre) {
            proveedor.id = id;
            proveedor.nombre = nombre;
            return this;
        }
        
        public Builder conPoliticaEntrega(int tiempoEntrega, double costoEnvio) {
            proveedor.politicaEntrega = new PoliticaEntrega(tiempoEntrega, costoEnvio);
            return this;
        }
        
        public Builder agregarProducto(Producto producto) {
            proveedor.productos.add(producto);
            return this;
        }
        
        public Builder agregarCertificacion(Certificacion certificacion) {
            proveedor.certificaciones.add(certificacion);
            return this;
        }
        
        public Builder agregarEvaluacion(Evaluacion evaluacion) {
            proveedor.evaluaciones.add(evaluacion);
            return this;
        }
        
        public Proveedor build() {
            return proveedor;
        }
    }
}

