package co.edu.poli.Ejercicio.model;

import java.util.List;

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


	public void setnombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Componente> getComponente() {
	    return this.getComponente();
	}


	public abstract void mostrar();
}