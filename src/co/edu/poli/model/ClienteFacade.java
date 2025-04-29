package co.edu.poli.model;

public class ClienteFacade {
    private InformacionPersonal informacion;
    private FormaDePago pago;
    private Cliente cliente;
    private HistorialPedido historial;

    public ClienteFacade(Cliente cliente, InformacionPersonal informacion, FormaDePago pago, HistorialPedido historial) {
        this.cliente = cliente;
        this.informacion = informacion;
        this.pago = pago;
        this.historial = historial;
    }

    public String verResumenCliente(int clienteId) {
        if (cliente.getClienteId() != clienteId) {
            return "Cliente no encontrado.";
        }

        StringBuilder resumen = new StringBuilder();
        resumen.append("Resumen del Cliente:\n");
        resumen.append("Nombre: ").append(cliente.getNombre()).append("\n");
        resumen.append("Email: ").append(informacion.getEmail()).append("\n");
        resumen.append(pago.obtenerFormaPago()).append("\n");
        resumen.append("Historial de Pedidos:\n");

        // historial de pedidos
        java.util.List<String> listaPedidos = historial.obtenerPedidos();
        if (listaPedidos.isEmpty()) {
            resumen.append("No hay pedidos registrados para este cliente.\n");
        } else {
            for (String pedido : listaPedidos) {
                resumen.append("- ").append(pedido).append("\n");
            }
        }

        return resumen.toString();
    }
}
