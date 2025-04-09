package co.edu.poli.model;

public class Usuario {
    private int id;
    private String nombre;
    private int nivelAcceso;

    public Usuario(int id, String nombre, int nivelAcceso) {
        this.id = id;
        this.nombre = nombre;
        this.nivelAcceso = nivelAcceso;
    }

    public int getId() {
        return id;
    }

    public int obtenerNivelAcceso() {
        return nivelAcceso;
    }
}
