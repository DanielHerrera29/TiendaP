package co.edu.poli.Model.handlers;

import co.edu.poli.Model.Cliente;

public class BeneficiosClienteHandler extends ClienteHandler {
    @Override
    public void handle(Cliente cliente) {
        String categoria = cliente.getCategoria();
        String beneficios = "";
        double montoCompra = 0.0;

        switch (categoria) {
            case "Nuevo":
            case "Básico":
                beneficios = "5% de descuento en primera compra, Newsletter semanal";
                montoCompra = 0.0;
                break;
            case "Plata":
                beneficios = "10% de descuento, Envío estándar gratuito";
                montoCompra = 600000.0;
                break;
            case "Oro":
                beneficios = "15% de descuento, Atención prioritaria";
                montoCompra = 900000.0;
                break;
            case "Premium":
                beneficios = "20% de descuento";
                montoCompra = 1200000.0;
                break;
            default:
                beneficios = "Sin beneficios";
                montoCompra = 0.0;
                break;
        }

        cliente.setBeneficios(beneficios);
        cliente.setMontocompraMensual(montoCompra);

        super.handle(cliente);
    }
}
