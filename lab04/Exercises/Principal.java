import java.util.Scanner;

// Clase Nodo para la lista enlazada simple
class Nodo {
    int dato;
    Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

// Clase ListaEnlazadaSimple con todos los métodos requeridos
class ListaEnlazadaSimple {
    private Nodo cabeza;
    private int tamaño;

    public ListaEnlazadaSimple() {
        cabeza = null;
        tamaño = 0;
    }

    // Método para insertar al final
    public void insert(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }

    // Método para imprimir la lista
    public void printList() {
        Nodo actual = cabeza;
        System.out.print("Lista: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    // Método para eliminar por valor
    public void deleteByKey(int key) {
        Nodo actual = cabeza;
        Nodo anterior = null;

        // Caso cuando la clave está en la cabeza
        if (actual != null && actual.dato == key) {
            cabeza = actual.siguiente;
            tamaño--;
            return;
        }

        // Buscar la clave a eliminar
        while (actual != null && actual.dato != key) {
            anterior = actual;
            actual = actual.siguiente;
        }

        // Si se encontró la clave
        if (actual != null) {
            anterior.siguiente = actual.siguiente;
            tamaño--;
        }
    }

    // Método para eliminar por posición
    public void deleteAtPosition(int position) {
        if (position < 0 || position >= tamaño) {
            System.out.println("Posición inválida");
            return;
        }

        if (position == 0) {
            cabeza = cabeza.siguiente;
        } else {
            Nodo actual = cabeza;
            for (int i = 0; i < position - 1; i++) {
                actual = actual.siguiente;
            }
            actual.siguiente = actual.siguiente.siguiente;
        }
        tamaño--;
    }

    // Método para obtener el tamaño
    public int size() {
        return tamaño;
    }

    // Método para eliminar el primer nodo
    public void removeFirst() {
        if (cabeza != null) {
            cabeza = cabeza.siguiente;
            tamaño--;
        }
    }

    // Método para eliminar el último nodo
    public void removeLast() {
        if (cabeza == null) return;

        if (cabeza.siguiente == null) {
            cabeza = null;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = null;
        }
        tamaño--;
    }

    // Método para agregar al inicio
    public void addFirst(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
        tamaño++;
    }

    // Método para agregar al final
    public void addLast(int dato) {
        insert(dato); // Reutilizamos el método insert
    }
}

// Clase Principal con menú de opciones
public class Principal {
    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        Scanner scanner = new Scanner(System.in);
        int opcion, valor, posicion;

        // Inicializar lista con valores del 1 al 10
        for (int i = 1; i <= 10; i++) {
            lista.insert(i);
        }

        do {
            System.out.println("\n--- MENÚ DE OPCIONES ---");
            System.out.println("1. Imprimir lista");
            System.out.println("2. Insertar elemento al final");
            System.out.println("3. Eliminar por valor");
            System.out.println("4. Eliminar por posición");
            System.out.println("5. Mostrar tamaño de la lista");
            System.out.println("6. Eliminar primer elemento");
            System.out.println("7. Eliminar último elemento");
            System.out.println("8. Insertar al inicio");
            System.out.println("9. Insertar al final");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    lista.printList();
                    break;
                case 2:
                    System.out.print("Ingrese el valor a insertar: ");
                    valor = scanner.nextInt();
                    lista.insert(valor);
                    break;
                case 3:
                    System.out.print("Ingrese el valor a eliminar: ");
                    valor = scanner.nextInt();
                    lista.deleteByKey(valor);
                    break;
                case 4:
                    System.out.print("Ingrese la posición a eliminar: ");
                    posicion = scanner.nextInt();
                    lista.deleteAtPosition(posicion);
                    break;
                case 5:
                    System.out.println("Tamaño de la lista: " + lista.size());
                    break;
                case 6:
                    lista.removeFirst();
                    System.out.println("Primer elemento eliminado");
                    break;
                case 7:
                    lista.removeLast();
                    System.out.println("Último elemento eliminado");
                    break;
                case 8:
                    System.out.print("Ingrese el valor a insertar al inicio: ");
                    valor = scanner.nextInt();
                    lista.addFirst(valor);
                    break;
                case 9:
                    System.out.print("Ingrese el valor a insertar al final: ");
                    valor = scanner.nextInt();
                    lista.addLast(valor);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);

        scanner.close();
    }
}