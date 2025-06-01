import java.util.Scanner;

// Clase Nodo para lista doblemente enlazada
class Nodo {
    int dato;
    Nodo anterior;
    Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }
}

// Clase ListaDoblementeEnlazada
class ListaDoblementeEnlazada {
    private Nodo cabeza;
    private Nodo cola;
    private int tamaño;

    public ListaDoblementeEnlazada() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    // Método para insertar al final (addLast)
    public void insert(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
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

    // Método para imprimir la lista hacia adelante
    public void printList() {
        Nodo actual = cabeza;
        System.out.print("Lista (adelante): ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    // Método para imprimir la lista hacia atrás
    public void printListReverse() {
        Nodo actual = cola;
        System.out.print("Lista (atrás): ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.anterior;
        }
        System.out.println();
    }

    // Método para eliminar por valor
    public void deleteByKey(int key) {
        Nodo actual = cabeza;

        // Buscar el nodo con el valor clave
        while (actual != null && actual.dato != key) {
            actual = actual.siguiente;
        }

        // Si se encontró el nodo
        if (actual != null) {
            // Caso cuando es el único nodo
            if (cabeza == cola) {
                cabeza = null;
                cola = null;
            }
            // Caso cuando es el primer nodo
            else if (actual == cabeza) {
                cabeza = cabeza.siguiente;
                cabeza.anterior = null;
            }
            // Caso cuando es el último nodo
            else if (actual == cola) {
                cola = cola.anterior;
                cola.siguiente = null;
            }
            // Caso cuando está en medio
            else {
                actual.anterior.siguiente = actual.siguiente;
                actual.siguiente.anterior = actual.anterior;
            }
            tamaño--;
        } else {
            System.out.println("Valor no encontrado en la lista");
        }
    }

    // Método para eliminar por posición
    public void deleteAtPosition(int position) {
        if (position < 0 || position >= tamaño) {
            System.out.println("Posición inválida");
            return;
        }

        Nodo actual = cabeza;
        int contador = 0;

        // Encontrar el nodo en la posición dada
        while (contador < position) {
            actual = actual.siguiente;
            contador++;
        }

        // Lógica de eliminación similar a deleteByKey
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else if (actual == cabeza) {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        } else if (actual == cola) {
            cola = cola.anterior;
            cola.siguiente = null;
        } else {
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
        }
        tamaño--;
    }

    // Método para obtener el tamaño
    public int size() {
        return tamaño;
    }

    // Método para eliminar el primer nodo
    public void removeFirst() {
        if (cabeza == null) return;

        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        }
        tamaño--;
    }

    // Método para eliminar el último nodo
    public void removeLast() {
        if (cola == null) return;

        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cola = cola.anterior;
            cola.siguiente = null;
        }
        tamaño--;
    }

    // Método para agregar al inicio
    public void addFirst(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            nuevoNodo.siguiente = cabeza;
            cabeza.anterior = nuevoNodo;
            cabeza = nuevoNodo;
        }
        tamaño++;
    }

    // Método para agregar al final (similar a insert)
    public void addLast(int dato) {
        insert(dato);
    }
}

// Clase Principal con menú de opciones
public class Principal2 {
    public static void main(String[] args) {
        ListaDoblementeEnlazada lista = new ListaDoblementeEnlazada();
        Scanner scanner = new Scanner(System.in);
        int opcion, valor, posicion;

        // Inicializar lista con valores del 1 al 10
        for (int i = 1; i <= 10; i++) {
            lista.insert(i);
        }

        do {
            System.out.println("\n--- MENÚ DE OPCIONES ---");
            System.out.println("1. Imprimir lista (adelante)");
            System.out.println("2. Imprimir lista (atrás)");
            System.out.println("3. Insertar elemento al final");
            System.out.println("4. Eliminar por valor");
            System.out.println("5. Eliminar por posición");
            System.out.println("6. Mostrar tamaño de la lista");
            System.out.println("7. Eliminar primer elemento");
            System.out.println("8. Eliminar último elemento");
            System.out.println("9. Insertar al inicio");
            System.out.println("10. Insertar al final");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    lista.printList();
                    break;
                case 2:
                    lista.printListReverse();
                    break;
                case 3:
                    System.out.print("Ingrese el valor a insertar: ");
                    valor = scanner.nextInt();
                    lista.insert(valor);
                    break;
                case 4:
                    System.out.print("Ingrese el valor a eliminar: ");
                    valor = scanner.nextInt();
                    lista.deleteByKey(valor);
                    break;
                case 5:
                    System.out.print("Ingrese la posición a eliminar (0-" + (lista.size()-1) + "): ");
                    posicion = scanner.nextInt();
                    lista.deleteAtPosition(posicion);
                    break;
                case 6:
                    System.out.println("Tamaño de la lista: " + lista.size());
                    break;
                case 7:
                    lista.removeFirst();
                    System.out.println("Primer elemento eliminado");
                    break;
                case 8:
                    lista.removeLast();
                    System.out.println("Último elemento eliminado");
                    break;
                case 9:
                    System.out.print("Ingrese el valor a insertar al inicio: ");
                    valor = scanner.nextInt();
                    lista.addFirst(valor);
                    break;
                case 10:
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