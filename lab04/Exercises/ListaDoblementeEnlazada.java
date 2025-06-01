import java.util.LinkedList;

public class ListaDoblementeEnlazada {
    public static void main(String[] args) {
        // Crear una lista doblemente enlazada usando LinkedList
        LinkedList<Integer> lista = new LinkedList<>();
        
        // Agregar elementos del 1 al 10
        for (int i = 1; i <= 10; i++) {
            lista.add(i); // add() añade al final de la lista
        }
        
        // Mostrar la lista en orden directo
        System.out.println("Recorrido hacia adelante:");
        for (Integer numero : lista) {
            System.out.print(numero + " ");
        }
        
        // Mostrar la lista en orden inverso
        System.out.println("\n\nRecorrido hacia atrás:");
        for (int i = lista.size() - 1; i >= 0; i--) {
            System.out.print(lista.get(i) + " ");
        }
        
        // Otra forma de recorrer hacia atrás usando descendingIterator()
        System.out.println("\n\nRecorrido hacia atrás con descendingIterator():");
        for (var it = lista.descendingIterator(); it.hasNext();) {
            System.out.print(it.next() + " ");
        }
        
        // Mostrar información adicional
        System.out.println("\n\nPrimer elemento: " + lista.getFirst());
        System.out.println("Último elemento: " + lista.getLast());
        System.out.println("Tamaño de la lista: " + lista.size());
    }
}