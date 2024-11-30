package ludogassull.modelo;

public class Nodo {
    private Jugador elemento;
    private Nodo siguiente;
    
    public Nodo(Jugador x){//COMPOSICIÓN
        elemento=x;
        siguiente=null;
    }

    public Jugador getElemento() {
        return elemento;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setElemento(Jugador elemento) {
        this.elemento = elemento;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
}