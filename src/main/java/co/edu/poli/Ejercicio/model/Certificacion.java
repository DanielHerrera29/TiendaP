package co.edu.poli.Ejercicio.model;


public class Certificacion {
    private String nombre;
    private String entidadEmisora;
    // Constructor, getters, setters
    public Certificacion(String nombre, String entidadEmisora) {
        this.nombre = nombre;
        this.entidadEmisora = entidadEmisora;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEntidadEmisora() {
        return entidadEmisora;
    }
    public void setEntidadEmisora(String entidadEmisora) {
        this.entidadEmisora = entidadEmisora;
    }
    @Override
    public String toString() {
        return "Certificacion{" +
                "nombre='" + nombre + '\'' +
                ", entidadEmisora='" + entidadEmisora + '\'' +
                '}';
    }
}