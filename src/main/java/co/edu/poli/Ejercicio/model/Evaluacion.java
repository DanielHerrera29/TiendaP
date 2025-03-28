package co.edu.poli.Ejercicio.model;
import java.time.LocalDate;

public class Evaluacion {
    private String id;
    private LocalDate fecha;
    private double puntuacion;
    private String comentarios;

    public Evaluacion(String id, LocalDate fecha, double puntuacion, String comentarios) {
        this.id = id;
        this.fecha = fecha;
        this.puntuacion = puntuacion;
        this.comentarios = comentarios;
    }

    // Getters
    public String getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public double getPuntuacion() { return puntuacion; }
    public String getComentarios() { return comentarios; }
}