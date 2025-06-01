import java.util.Scanner;

class Nodo {
    int dato;
    Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class ListaCircular {
    private Nodo ultimo;
    private int tamaño;

    public ListaCircular() {
        ultimo = null;
        tamaño = 0;
    }

    // Método para insertar al final (addLast)
    public void insert(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        
        if (ultimo == null) {
            ultimo = nuevoNodo;
            ultimo.siguiente = ultimo; // Se apunta a sí mismo
        } else {
            nuevoNodo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevoNodo;
            ultimo = nuevoNodo;
        }
        tamaño++;
    }

    // Método para imprimir la lista
    public void printList() {
        if (ultimo == null) {
            System.out.println("La lista está vacía");
            return;
        }

        Nodo actual = ultimo.siguiente;
        System.out.print("Lista circular: ");
        
        do {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        } while (actual != ultimo.siguiente);
        
        System.out.println();
    }

    // Método para eliminar por valor
    public void deleteByKey(int key) {
        if (ultimo == null) return;

        Nodo actual = ultimo.siguiente;
        Nodo anterior = ultimo;

        do {
            if (actual.dato == key) {
                // Caso cuando hay un solo nodo
                if (actual == actual.siguiente) {
                    ultimo = null;
                } 
                // Caso cuando es el primer nodo
                else {
                    anterior.siguiente = actual.siguiente;
                    // Si era el último nodo, actualizar referencia
                    if (actual == ultimo) {
                        ultimo = anterior;
                    }
                }
                tamaño--;
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        } while (actual != ultimo.siguiente);

        System.out.println("Valor no encontrado en la lista");
    }

    // Método para eliminar por posición
    public void deleteAtPosition(int position) {
        if (position < 0 || position >= tamaño) {
            System.out.println("Posición inválida");
            return;
        }

        if (ultimo == null) return;

        if (position == 0) {
            // Eliminar primer nodo
            if (ultimo.siguiente == ultimo) {
                ultimo = null;
            } else {
                ultimo.siguiente = ultimo.siguiente.siguiente;
            }
        } else {
            Nodo actual = ultimo.siguiente;
            for (int i = 0; i < position - 1; i++) {
                actual = actual.siguiente;
            }
            actual.siguiente = actual.siguiente.siguiente;
            // Si eliminamos el último nodo
            if (position == tamaño - 1) {
                ultimo = actual;
            }
        }
        tamaño--;
    }

    // Método para obtener el tamaño
    public int size() {
        return tamaño;
    }

    // Método para eliminar el primer nodo
    public void removeFirst() {
        if (ultimo == null) return;

        if (ultimo.siguiente == ultimo) {
            ultimo = null;
        } else {
            ultimo.siguiente = ultimo.siguiente.siguiente;
        }
        tamaño--;
    }

    // Método para eliminar el último nodo
    public void removeLast() {
        if (ultimo == null) return;

        if (ultimo.siguiente == ultimo) {
            ultimo = null;
        } else {
            Nodo actual = ultimo.siguiente;
            while (actual.siguiente != ultimo) {
                actual = actual.siguiente;
            }
            actual.siguiente = ultimo.siguiente;
            ultimo = actual;
        }
        tamaño--;
    }

    // Método para agregar al inicio
    public void addFirst(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        
        if (ultimo == null) {
            ultimo = nuevoNodo;
            ultimo.siguiente = ultimo;
        } else {
            nuevoNodo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevoNodo;
        }
        tamaño++;
    }

    // Método para agregar al final (similar a insert)
    public void addLast(int dato) {
        insert(dato);
    }
}

public class Principal3 {
    public static void main(String[] args) {
        ListaCircular lista = new ListaCircular();
        Scanner scanner = new Scanner(System.in);
        int opcion, valor, posicion;

        // Inicializar lista con valores del 1 al 12
        for (int i = 1; i <= 12; i++) {
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
                    System.out.print("Ingrese la posición a eliminar (0-" + (lista.size()-1) + "): ");
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