package co.edu.poli.model;

public class Cliente {
    private int clienteId;
    private String nombre;

    public Cliente(int clienteId, String nombre) {
        this.clienteId = clienteId;
        this.nombre = nombre;
    }

    public int getClienteId() {
        return clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
