package co.edu.poli.Ejercicio.model;

public class PoliticaEntrega {
    private String tiempoEntrega;
    private double costoEnvio;

    public PoliticaEntrega(String tiempoEntrega, double costoEnvio) {
        this.tiempoEntrega = tiempoEntrega;
        this.costoEnvio = costoEnvio;
    }

    public String getTiempoEntrega() {
        return tiempoEntrega;
    }

    public double getCostoEnvio() {
        return costoEnvio;
    }

    @Override
    public String toString() {
        return "PoliticaEntrega{" +
                "tiempoEntrega='" + tiempoEntrega + '\'' +
                ", costoEnvio=" + costoEnvio +
                '}';
    }
}