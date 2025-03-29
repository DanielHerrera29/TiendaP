package co.edu.poli.Ejercicio.model;
import java.util.ArrayList;
import java.util.List;

public class Departamento extends Componente {

    private List<Componente> componentes;

    public Departamento(String nombre) {
        super(nombre);
        this.componentes = new ArrayList<>();
    }

    public void agregar(Componente componente) {
        componentes.add(componente);
    }

    @Override
    public void mostrar() {
        System.out.println("Departamento: " + nombre);
        for (Componente c : componentes) {
            c.mostrar();
        }
    }

    public List<Componente> getComponentes() {
        return componentes;
    }
}