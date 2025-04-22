package co.edu.poli.model;

public class InformacionPersonal {
    private int id;
    private String nombre;
    private String email;

    public InformacionPersonal(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre + ", Email: " + email);
    }

    public void actualizarInformacion(String nombre) {
        this.nombre = nombre;
        System.out.println("Nombre actualizado a: " + nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}
