package co.edu.poli.Ejercicio.model;


public class Proveedor {
    private String nombre;
    private String direccion;
    private Certificacion certificacion;
    private Evaluacion evaluacion;
    private PoliticaEntrega politicaEntrega;

    private Proveedor(Builder builder) {
        this.nombre = builder.nombre;
        this.direccion = builder.direccion;
        this.certificacion = builder.certificacion;
        this.evaluacion = builder.evaluacion;
        this.politicaEntrega = builder.politicaEntrega;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Certificacion getCertificacion() {
        return certificacion;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public PoliticaEntrega getPoliticaEntrega() {
        return politicaEntrega;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", certificacion=" + certificacion +
                ", evaluacion=" + evaluacion +
                ", politicaEntrega=" + politicaEntrega +
                '}';
    }

    public static class Builder {
        private String nombre;
        private String direccion;
        private Certificacion certificacion;
        private Evaluacion evaluacion;
        private PoliticaEntrega politicaEntrega;

        public Builder(String nombre, String direccion) {
            this.nombre = nombre;
            this.direccion = direccion;
        }

        public Builder certificacion(Certificacion certificacion) {
            this.certificacion = certificacion;
            return this;
        }

        public Builder evaluacion(Evaluacion evaluacion) {
            this.evaluacion = evaluacion;
            return this;
        }

        public Builder politicaEntrega(PoliticaEntrega politicaEntrega) {
            this.politicaEntrega = politicaEntrega;
            return this;
        }

        public Proveedor build() {
            return new Proveedor(this);
        }
    }
}