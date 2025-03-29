package co.edu.poli.Ejercicio.model;


	public class EmpleadoHoja extends Empleado {

	    public EmpleadoHoja(String nombre, String Id) {
	        super(nombre, Id);
	    }

	    
	    
	    @Override
	    public void mostrar() {
	        System.out.printf("Empleado: " + this.getNombre(),this.getId());
	    }
	}

