// Clase Nodo genérica para la lista doblemente enlazada
class Nodo<E> {
    E dato;
    Nodo<E> anterior;
    Nodo<E> siguiente;

    public Nodo(E dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }
}

// Clase LinkedList genérica
public class LinkedList<E> {
    private Nodo<E> cabeza;
    private Nodo<E> cola;
    private int tamaño;

    public LinkedList() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    // Método para agregar elementos al final de la lista
    public void agregarAlFinal(E dato) {
        Nodo<E> nuevoNodo = new Nodo<>(dato);
        if (cola == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            nuevoNodo.anterior = cola;
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }
        tamaño++;
    }

    // Método para imprimir la lista de inicio a fin
    public void imprimirAdelante() {
        Nodo<E> actual = cabeza;
        System.out.print("Lista (adelante): ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    // Método para imprimir la lista de fin a inicio
    public void imprimirAtras() {
        Nodo<E> actual = cola;
        System.out.print("Lista (atrás): ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.anterior;
        }
        System.out.println();
    }

    // Método para obtener el tamaño de la lista
    public int tamaño() {
        return tamaño;
    }
}

// Clase principal para demostrar el funcionamiento
public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> lista = new LinkedList<>();

        // Agregar números del 1 al 10
        for (int i = 1; i <= 10; i++) {
            lista.agregarAlFinal(i);
        }

        // Mostrar resultados
        System.out.println("Tamaño de la lista: " + lista.tamaño());
        lista.imprimirAdelante();
        lista.imprimirAtras();
    }
}