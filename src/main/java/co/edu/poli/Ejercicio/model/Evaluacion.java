package co.edu.poli.Ejercicio.model;

import java.util.Date;

public class Evaluacion {
    private double puntaje;
    private String comentario;
    private Date fecha;
    
    public Evaluacion(double puntaje, String comentario, Date fecha) {
        this.puntaje = puntaje;
        this.comentario = comentario;
        this.fecha = fecha;
    }
    
    public double getPuntaje() {
        return puntaje;
    }
    
    public String getComentario() {
        return comentario;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    @Override
    public String toString() {
        return "Evaluación: " + puntaje + "/5, " + comentario + " (" + fecha + ")";
    }
}
