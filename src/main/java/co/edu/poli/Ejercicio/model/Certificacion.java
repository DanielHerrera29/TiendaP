package co.edu.poli.Ejercicio.model;
import java.time.LocalDate;

public class Certificacion {
    private String id;
    private String nombre;
    private LocalDate fechaEmision;
    private LocalDate fechaExpiracion;

    public Certificacion(String id, String nombre, LocalDate fechaEmision, LocalDate fechaExpiracion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaEmision = fechaEmision;
        this.fechaExpiracion = fechaExpiracion;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public LocalDate getFechaExpiracion() { return fechaExpiracion; }
}