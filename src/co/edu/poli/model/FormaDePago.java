package co.edu.poli.model;

public class FormaDePago {
    private int clienteId;
    private String tipo;
    private boolean activa;

    public FormaDePago(int clienteId, String tipo, boolean activa) {
        this.clienteId = clienteId;
        this.tipo = tipo;
        this.activa = activa;
    }

    public String obtenerFormaPago() {
        return "Tipo de pago: " + tipo + ", Activa: " + (activa ? "Sí" : "No");
    }

    public void activarFormaPago() {
        this.activa = true;
        System.out.println("Forma de pago activada.");
    }
    public void bloquearFormaPago() {
        this.activa = true;
        System.out.println("Forma de pago bloqueado.");
    }
}
