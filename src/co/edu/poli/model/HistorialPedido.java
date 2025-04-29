package co.edu.poli.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistorialPedido {
    private int clienteId;
    private Date fecha;
    private double total;
    private List<String> pedidos;

    public HistorialPedido() {
        this.pedidos = new ArrayList<>();
        this.fecha = new Date();
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void mostrarHistorial() {
        System.out.println("Historial de pedidos del cliente " + clienteId + ":");
        for (String pedido : pedidos) {
            System.out.println("- " + pedido);
        }
    }

    public void listarPedidoCliente(int clienteId) {
        if (this.clienteId == clienteId) {
            if (pedidos.isEmpty()) {
                System.out.println("No hay historial para este cliente.");
            } else {
                mostrarHistorial();
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void agregarPedido(String pedido) {
        pedidos.add(pedido);
        System.out.println("Pedido agregado: " + pedido);
    }

   
    public List<String> obtenerPedidos() {
        return this.pedidos;
    }
}
