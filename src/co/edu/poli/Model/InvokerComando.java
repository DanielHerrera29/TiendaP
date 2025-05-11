package co.edu.poli.Model;

public class InvokerComando {
	private Command comando;

    public void setComando(Command comando) {
        this.comando = comando;
    }

    public String ejecutarComando() {
        return comando.ejecutar();
    }
}