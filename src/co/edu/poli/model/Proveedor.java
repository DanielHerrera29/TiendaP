package co.edu.poli.model;

public class Proveedor {
    private String nombre;
    private String direccion;
    private String contacto; // Nuevo campo para el contacto

    public Proveedor(String nombre, String direccion, String contacto) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void mostrarInformacion() {
        System.out.println("Proveedor: " + nombre + ", Dirección: " + direccion + ", Contacto: " + contacto);
    }
}