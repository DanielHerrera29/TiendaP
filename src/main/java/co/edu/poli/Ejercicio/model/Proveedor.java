package co.edu.poli.Ejercicio.model;
import java.util.ArrayList;
import java.util.List;

public class Proveedor {

    private String id;
    private String nombre;
    private String contacto;
    private List<Certificacion> certificaciones;
    private List<Evaluacion> evaluaciones;
    private PoliticaEntrega politicaEntrega;

    // Constructor privado para usar con el Builder
    private Proveedor(String id, String nombre, String contacto, List<Certificacion> certificaciones, List<Evaluacion> evaluaciones, PoliticaEntrega politicaEntrega) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.certificaciones = certificaciones;
        this.evaluaciones = evaluaciones;
        this.politicaEntrega = politicaEntrega;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getContacto() { return contacto; }
    public List<Certificacion> getCertificaciones() { return certificaciones; }
    public List<Evaluacion> getEvaluaciones() { return evaluaciones; }
    public PoliticaEntrega getPoliticaEntrega() { return politicaEntrega; }

    // Clase interna PoliticaEntrega
    public static class PoliticaEntrega {
        private int tiempoMaximo;
        private double costoEnvio;
        private boolean requiereSeguro;
        private List<String> zonasCobertura;

        public PoliticaEntrega(int tiempoMaximo, double costoEnvio, boolean requiereSeguro, List<String> zonasCobertura) {
            this.tiempoMaximo = tiempoMaximo;
            this.costoEnvio = costoEnvio;
            this.requiereSeguro = requiereSeguro;
            this.zonasCobertura = zonasCobertura;
        }

        // Getters
        public int getTiempoMaximo() { return tiempoMaximo; }
        public double getCostoEnvio() { return costoEnvio; }
        public boolean isRequiereSeguro() { return requiereSeguro; }
        public List<String> getZonasCobertura() { return zonasCobertura; }
    }

    // Clase Builder
    public static class ProveedorBuilder {
        private String id;
        private String nombre;
        private String contacto;
        private List<Certificacion> certificaciones = new ArrayList<>();
        private List<Evaluacion> evaluaciones = new ArrayList<>();
        private PoliticaEntrega politicaEntrega;

        public ProveedorBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public ProveedorBuilder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public ProveedorBuilder setContacto(String contacto) {
            this.contacto = contacto;
            return this;
        }

        public ProveedorBuilder addCertificacion(Certificacion certificacion) {
            this.certificaciones.add(certificacion);
            return this;
        }

        public ProveedorBuilder addEvaluacion(Evaluacion evaluacion) {
            this.evaluaciones.add(evaluacion);
            return this;
        }

        public ProveedorBuilder setPoliticaEntrega(PoliticaEntrega politicaEntrega) {
            this.politicaEntrega = politicaEntrega;
            return this;
        }

        public Proveedor build() {
            return new Proveedor(id, nombre, contacto, certificaciones, evaluaciones, politicaEntrega);
        }
    }
}