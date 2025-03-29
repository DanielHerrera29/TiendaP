package co.edu.poli.Ejercicio.model;

public abstract class Componente {
    protected String nombre;
    
    public Componente(String nombre) {
        this.nombre = nombre;
    }


    @Override
	public String toString() {
		return "Componente [nombre=" + nombre ;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public abstract void mostrar();
}