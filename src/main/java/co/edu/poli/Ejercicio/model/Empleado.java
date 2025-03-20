package co.edu.poli.Ejercicio.model;

public class Empleado {
	
	    private String id;
	    private String nombre;

	    public Empleado() {
	    }

	    public void Empelado(String id, String nombre) {
	        this.id = id;
	        this.nombre = nombre;
	    }

	    public String getId() {
	        return this.id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getNombre() {
	        return this.nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    @Override
	    public String toString() {
	        return "Cliente [id=" + this.id + ", nombre=" + this.nombre + "]";
	    }
	}

