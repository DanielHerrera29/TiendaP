package co.edu.poli.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Objects; 

public class Cliente {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty telefono = new SimpleStringProperty();
    private final StringProperty direccion = new SimpleStringProperty();
    private final StringProperty categoria = new SimpleStringProperty();
    private final DoubleProperty montocompraMensual = new SimpleDoubleProperty(0.0); 
    private final StringProperty beneficios = new SimpleStringProperty();

    public Cliente(String nombre, String email, String direccion) {
        this.nombre.set(nombre);
        this.email.set(email);
        this.direccion.set(direccion);
        this.categoria.set("Nuevo");
    }

    public Cliente(String nombre, String email, String direccion, String categoria) {
        this.nombre.set(nombre);
        this.email.set(email);
        this.direccion.set(direccion);
        this.categoria.set(categoria);
    }

    
    public StringProperty idProperty() { return id; }
    public StringProperty nombreProperty() { return nombre; }
    public StringProperty emailProperty() { return email; }
    public StringProperty telefonoProperty() { return telefono; }
    public StringProperty direccionProperty() { return direccion; }
    public StringProperty categoriaProperty() { return categoria; }
    public DoubleProperty montocompraMensualProperty() { return montocompraMensual; }
    public StringProperty beneficiosProperty() { return beneficios; }

   
    public String getId() { return id.get(); }
    public void setId(String value) { id.set(value); }

    public String getNombre() { return nombre.get(); }
    public void setNombre(String value) { nombre.set(value); }

    public String getEmail() { return email.get(); }
    public void setEmail(String value) { email.set(value); }

    public String getTelefono() { return telefono.get(); }
    public void setTelefono(String value) { telefono.set(value); }

    public String getDireccion() { return direccion.get(); }
    public void setDireccion(String value) { direccion.set(value); }

    public String getCategoria() { return categoria.get(); }
    public void setCategoria(String value) { categoria.set(value); }

    public double getMontocompraMensual() { return montocompraMensual.get(); }
    public void setMontocompraMensual(double value) { montocompraMensual.set(value); }

    public String getBeneficios() { return beneficios.get(); }
    public void setBeneficios(String value) { beneficios.set(value); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(email.get(), cliente.email.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(email.get());
    }
}

// public class Cliente {
//     private String nombre;
//     private String correo;

//     public Cliente(String nombre, String correo) {
//         this.nombre = nombre;
//         this.correo = correo;
//     }

//     public String getNombre() {
//         return nombre;
//     }

//     public String getCorreo() {
//         return correo;
//     }
// }



