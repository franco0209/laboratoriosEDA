import java.io.*;

// Un programa java para implementar
// Una simple lista enlazada
public class LinkedList4 {
    Node head; // cabecera de la lista

    // Nodo de lista enlazada.
    // Esta clase interna se hace estática
    // para que main() pueda acceder a ella
    static class Node {
        int data;
        Node next;

        // Constructor
        Node(int d) {
            data = d;
            next = null;
        }
    }

    // **************INSERCION**************
    // Método para insertar un nuevo nodo
    public static LinkedList4 insert(LinkedList4 list, int data) {
        // Crea un nuevo nodo con los datos dados
        Node new_node = new Node(data);
        new_node.next = null;

        // Si la lista enlazada está vacía,
        // entonces convierte el nuevo nodo en la cabeza
        if (list.head == null) {
            list.head = new_node;
        } else {
            // De lo contrario recorra hasta el último nodo
            // e inserte el nuevo nodo allí
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            // Inserta el nuevo nodo al último nodo
            last.next = new_node;
        }
        // Retorna la lista desde la cabeza
        return list;
    }

    // **************Recorrido**************
    // Metodo para imprimir la lista enlazada
    public static void printList(LinkedList4 list) {
        Node currNode = list.head;
        System.out.print("\nLinkedList4: ");

        // Recorre la lista enlazada (LinkedList4)
        while (currNode != null) {
            // Imprime el dato en el nodo actual
            System.out.print(currNode.data + " ");
            // Va al siguiente nodo
            currNode = currNode.next;
        }
        System.out.println("\n");
    }

    // **************BORRADO POR DATO**************
    // Metodo para eliminar un nodo en LinkedList4 por dato
    public static LinkedList4 deleteByKey(LinkedList4 list, int key) {
        // Aloja el nodo cabecera
        Node currNode = list.head, prev = null;

        // CASO 1:
        // Si el nodo principal tiene el dato que se va a eliminar
        if (currNode != null && currNode.data == key) {
            list.head = currNode.next; // Changed head
            // Muetra el mensaje
            System.out.println(key + " found and deleted");
            // Retorna la lista actualizada
            return list;
        }

        // CASO 2:
        // Si el dato está en otro lugar que no sea la cabecera
        // Busca el dato para ser borrado,
        // realiza un seguimiento al nodo anterior
        // ya que es necesario cambiar currNode.next
        while (currNode != null && currNode.data != key) {
            // si currNode no tiene el dato
            // continua con el siguiente nodo
            prev = currNode;
            currNode = currNode.next;
        }

        // si el dato esuviera presente, sería el currNode
        // Por lo tanto, el currNode no debe ser nulo
        if (currNode != null) {
            // Desde que el dato está en currNode
            // Desenlaza currNode de la linked list
            prev.next = currNode.next;
            // muestra el mensaje
            System.out.println(key + " found and deleted");
        }

        // CASO 3: El dato no está presente
        // Si la el dato no está presente en linked list
        // el nodo actual podría ser nulo
        if (currNode == null) {
            // Muestra el mensaje
            System.out.println(key + " not found");
        }
        // devuelve la lista
        return list;
    }

    // **************Borrado por posicion**************
    // Metodo para eliminar un nodo en la LinkedList4 por POSITION
    public static LinkedList4 deleteAtPosition(LinkedList4 list, int index) {
        // Guarda el nodo cabecera
        Node currNode = list.head, prev = null;

        // CASE 1:
        // si el índice es 0, entonces el nodo principal debe ser
        // eliminado
        if (index == 0 && currNode != null) {
            list.head = currNode.next; // Cambia la cabecera
            // Muestra el mensaje
            System.out.println(index + " position element deleted");
            // Retorna la lista actualizada
            return list;
        }

        // CASO 2:
        // Si el índice es mayor que 0 pero menor que el
        // tamaño de LinkedList4
        int counter = 0;
        // Conteo del índice a ser eliminado
        // seguimiento del nodo anterior
        // ya que es necesario cambiar currNode.next
        while (currNode != null) {
            if (counter == index) {
                // Dado el currNode es la posición requerida
                // se desenlaza currNode de la lista enlazada
                prev.next = currNode.next;
                // Muestra el mensaje
                System.out.println(index + " position element deleted");
                break;
            } else {
                // si la posición actual no es el indice
                // continua con el siguiente nodo
                prev = currNode;
                currNode = currNode.next;
                counter++;
            }
        }

        // CASE 3: El indice es mayor que el tamaño de la LinkedList4
        if (currNode == null) {
            // Muestra el mensaje
            System.out.println(index + " position element not found");
        }
        // devuelve la lista
        return list;
    }

    // **************Metodo principal**************
    public static void main(String[] args) {
        /* Inicia con una lista vacia. */
        LinkedList4 list = new LinkedList4();

        // ******INSERCIÓN******
        // Inserta los valores
        list = insert(list, 1);
        list = insert(list, 2);
        list = insert(list, 3);
        list = insert(list, 4);
        list = insert(list, 5);
        list = insert(list, 6);
        list = insert(list, 7);
        list = insert(list, 8);

        // Imprime la LinkedList4
        printList(list);

        // ******Eliminación por dato ******
        // Elimina el nodo con el valor 1 (cabecera)
        deleteByKey(list, 1);
        printList(list);

        // Borramos el nodo con el valor 4 (medio)
        deleteByKey(list, 4);
        printList(list);

        // Borrar el nodo con el valor 10 (no existe)
        deleteByKey(list, 10);
        printList(list);

        // ******BORRADO POR LA POSICIÓN ******
        // Eliminar nodo en la posición 0 (cabecera)
        deleteAtPosition(list, 0);
        printList(list);

        // Eliminar nodo en la posición 2 (medio)
        deleteAtPosition(list, 2);
        printList(list);

        // Eliminar nodo en la posición 10 (no existe)
        deleteAtPosition(list, 10);
        printList(list);
    }
}