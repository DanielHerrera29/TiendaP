package co.edu.poli.Ejercicio.model;

public class Evaluacion {
    private double puntuacion;
    private String comentarios;
    // Constructor, getters, setters
    public Evaluacion(double puntuacion, String comentarios) {
        this.puntuacion = puntuacion;
        this.comentarios = comentarios;
    }
    public double getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }
    public String getComentarios() {
        return comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    @Override
    public String toString() {
        return "Evaluacion{" +
                "puntuacion=" + puntuacion +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }
}