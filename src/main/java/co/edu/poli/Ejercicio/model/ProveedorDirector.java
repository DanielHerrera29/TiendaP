package co.edu.poli.Ejercicio.model;

import java.util.Date;

public class ProveedorDirector {
    private Proveedor.Builder builder;
    
    public ProveedorDirector(Proveedor.Builder builder) {
        this.builder = builder;
    }
    
    public void construirProveedorEstandar(String id, String nombre) {
        builder.conIdentificacion(id, nombre)
               .conPoliticaEntrega(5, 10000.0);
    }
    
    public void construirProveedorPremium(String id, String nombre) {
        Certificacion isoCalidad = new Certificacion("ISO 9001", new Date(), "Bureau Veritas");
        Evaluacion evaluacionInicial = new Evaluacion(4.5, "Proveedor excelente", new Date());
        
        builder.conIdentificacion(id, nombre)
               .conPoliticaEntrega(2, 5000.0)
               .agregarCertificacion(isoCalidad)
               .agregarEvaluacion(evaluacionInicial);
    }
    
    public Proveedor getProveedor() {
        return builder.build();
    }
}