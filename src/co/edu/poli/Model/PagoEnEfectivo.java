package co.edu.poli.Model;

public class PagoEnEfectivo implements EstrategiaPago {
    private String ubicacionCaja;

    public PagoEnEfectivo(String ubicacionCaja) {
        this.ubicacionCaja = ubicacionCaja;
    }

    @Override
    public void pagar(double monto) {
        System.out.println("Pagando $" + monto + " en efectivo en la caja: " + ubicacionCaja);
    }
}
