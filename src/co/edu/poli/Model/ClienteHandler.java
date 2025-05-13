package co.edu.poli.Model;

public abstract class ClienteHandler {
    protected ClienteHandler next;

    public void setNext(ClienteHandler next) {
        this.next = next;
    }

    public void handle(Cliente cliente) {
        if (next != null) {
            next.handle(cliente);
        }
    }
}