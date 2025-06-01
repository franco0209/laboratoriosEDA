// Clase Nodo genérica para la lista circular
class Nodo<E> {
    E dato;
    Nodo<E> siguiente;

    public Nodo(E dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

// Clase CircularLinkedList genérica
public class CircularLinkedList<E> {
    private Nodo<E> ultimo;
    private int tamaño;

    public CircularLinkedList() {
        ultimo = null;
        tamaño = 0;
    }

    // Método para agregar elementos al final de la lista circular
    public void agregarAlFinal(E dato) {
        Nodo<E> nuevoNodo = new Nodo<>(dato);
        
        if (ultimo == null) {
            // Lista vacía
            ultimo = nuevoNodo;
            ultimo.siguiente = ultimo; // Se apunta a sí mismo
        } else {
            // Lista no vacía
            nuevoNodo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevoNodo;
            ultimo = nuevoNodo;
        }
        tamaño++;
    }

    // Método para imprimir la lista circular
    public void imprimirLista() {
        if (ultimo == null) {
            System.out.println("La lista está vacía");
            return;
        }

        Nodo<E> actual = ultimo.siguiente;
        System.out.print("Lista circular: ");
        
        do {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        } while (actual != ultimo.siguiente);
        
        System.out.println();
    }

    // Método para obtener el tamaño de la lista
    public int tamaño() {
        return tamaño;
    }
}

// Clase principal para demostrar el funcionamiento
public class Main2 {
    public static void main(String[] args) {
        CircularLinkedList<Integer> listaCircular = new CircularLinkedList<>();

        // Agregar números del 1 al 12
        for (int i = 1; i <= 12; i++) {
            listaCircular.agregarAlFinal(i);
        }

        // Mostrar resultados
        System.out.println("Tamaño de la lista circular: " + listaCircular.tamaño());
        listaCircular.imprimirLista();
    }
}