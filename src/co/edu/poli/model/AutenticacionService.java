package co.edu.poli.model;

public class AutenticacionService {
    public boolean verificarNivelAcceso(Usuario usuario, int nivelRequerido) {
        return usuario.obtenerNivelAcceso() >= nivelRequerido;
    }
}
