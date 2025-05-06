package co.edu.poli.model;
import java.util.*;

public class HistorialPrecios {
    private int productId;
    private List<ProductoMemento> historial = new ArrayList<>();

    public ProductoMemento agregarMemento(ProductoMemento memento) {
        historial.add(memento);
        return memento;
    }

    public ProductoMemento obtenerMemento(int productId, Date fecha) {
        for (ProductoMemento m : historial) {
            if (m.getProductId() == productId && m.getFecha().equals(fecha)) {
                return m;
            }
        }
        return null;
    }

    public ProductoMemento obtenerPrecio(int productId) {
        for (int i = historial.size() - 1; i >= 0; i--) {
            if (historial.get(i).getProductId() == productId) {
                return historial.get(i);
            }
        }
        return null;
    }

    public List<ProductoMemento> obtenerHistorialProducto(int productId) {
        List<ProductoMemento> resultado = new ArrayList<>();
        for (ProductoMemento m : historial) {
            if (m.getProductId() == productId) {
                resultado.add(m);
            }
        }
        return resultado;
    }
}
