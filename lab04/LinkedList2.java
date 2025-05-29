import java.io.*;

// Un programa java para implementar
// Una simple lista enlazada
public class LinkedList2 {
    Node head; // Cabecera de la lista

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

    // Método para insertar un nuevo nodo
    public static LinkedList2 insert(LinkedList2 list, int data) {
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

    // Metodo para imprimir la lista enlazada
    public static void printList(LinkedList2 list) {
        Node currNode = list.head;
        System.out.print("LinkedList2: ");

        // Recorre la lista enlazada (LinkedList2)
        while (currNode != null) {
            // Imprime el dato en el nodo actual
            System.out.print(currNode.data + " ");
            // Va al siguiente nodo
            currNode = currNode.next;
        }
        System.out.println();
    }

    // **************DELETION BY KEY**************
    // Metodo para eliminar un nodo en LinkedList2 por dato
    public static LinkedList2 deleteByKey(LinkedList2 list, int key) {
        // Aloja el nodo cabecera
        Node currNode = list.head, prev = null;

        // CASO 1:
        // Si el nodo principal tiene el dato que se va a eliminar
        if (currNode != null && currNode.data == key) {
            list.head = currNode.next; // Cambia la cabeza
            // Muestra el mensaje
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

        // si el dato estuviera presente, sería el currNode
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

    // **************Metodo principal**************
    // método para crear una simple lista enlazada con n nodos
    public static void main(String[] args) {
        /* Inicia con una lista vacia. */
        LinkedList2 list = new LinkedList2();

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

        // Imprime la LinkedList2
        printList(list);

        // ******Eliminación por dato ******
        // Elimina el nodo con el valor 1
        // En el caso el dato 1 está en ***la cabeza***
        deleteByKey(list, 1);
        // Imprime la LinkedList2
        printList(list);

        // Borramos el nodo con el valor 4
        // En este caso el dato esta presente ***en el medio***
        deleteByKey(list, 4);
        // Imprime la LinkedList2
        printList(list);

        // Borrar el nodo con el valor 10
        // En este caso el dato esta ***no presente***
        deleteByKey(list, 10);
        // Imprime la LinkedList2
        printList(list);
    }
}