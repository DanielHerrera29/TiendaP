package co.edu.poli.Ejercicio.model;

import java.util.Date;

public class Certificacion {
    private String tipo;
    private Date fechaEmision;
    private String entidadEmisora;
    
    public Certificacion(String tipo, Date fechaEmision, String entidadEmisora) {
        this.tipo = tipo;
        this.fechaEmision = fechaEmision;
        this.entidadEmisora = entidadEmisora;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public Date getFechaEmision() {
        return fechaEmision;
    }
    
    public String getEntidadEmisora() {
        return entidadEmisora;
    }
    
    @Override
    public String toString() {
        return "Certificación: " + tipo + ", emitida por " + entidadEmisora + " en " + fechaEmision;
    }
}
