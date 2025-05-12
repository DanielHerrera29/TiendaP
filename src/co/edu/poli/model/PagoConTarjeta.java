package co.edu.poli.Model;

public class PagoConTarjeta implements EstrategiaPago {
    private String numeroTarjeta;
    private String nombreTitular;

    public PagoConTarjeta(String numeroTarjeta, String nombreTitular, String cvv) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTitular = nombreTitular;
    }

    @Override
    public void pagar(double monto) {
        System.out.println("Pagando $" + monto + " con tarjeta de " + nombreTitular);
        // Aquí iría la lógica real de pago con tarjeta
    }
}
