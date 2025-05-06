package co.edu.poli.View;

import co.edu.poli.Controller.ProductoController;

public class Main {
    public static void main(String[] args) {
        ProductoController controller = new ProductoController();

        System.out.println("== Estado Inicial ==");
        controller.mostrarEstado();

        System.out.println("\n== Cambiando precio a $2700.00 ==");
        controller.cambiarPrecio(2700.00);
        controller.mostrarEstado();

        System.out.println("\n== Cambiando precio a $2900.00 ==");
        controller.cambiarPrecio(2900.00);
        controller.mostrarEstado();

        System.out.println("\n== Restaurando al primer precio ==");
        controller.restaurarPrimerPrecio();
        controller.mostrarEstado();
    }
}

