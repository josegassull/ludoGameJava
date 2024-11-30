package ludogassull.modelo;

public class Cola{

    protected Nodo frente;
    protected Nodo fin;

    public Cola() {
        frente = fin = null;
    }

    public void insertar(Jugador elemento) {
        Nodo a;
        a = new Nodo(elemento);
        if (colaVacia()) {
            this.frente = a;
        } else {
            this.fin.setSiguiente(a);
        }
        this.fin = a;
    }

    public Jugador quitar() {
        Jugador aux;
        if (!colaVacia()) {
            aux = this.frente.getElemento();
            this.frente = this.frente.getSiguiente();
        } else {
            aux = null;
        }
        return aux;
    }

    public void borrarCola() {
        while (this.frente != null) {
            this.frente = this.frente.getSiguiente();
        }
    }

    public boolean colaVacia() {
        if(this.frente == null) {
            return true;
        } else {
            return false;
        }
    }

    public Nodo getFrente() {
        return frente;
    }

    public Nodo getFin() {
        return fin;
    }
}
