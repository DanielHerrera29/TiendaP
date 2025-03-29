package co.edu.poli.Ejercicio.model;

public interface Componente {
	    void iniciarRegistroTiempo();
	    void finalizarRegistroTiempo();  
	    
	    long obtenerTiempoTranscurrido();  
}
