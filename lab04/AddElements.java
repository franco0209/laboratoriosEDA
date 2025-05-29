// Un programa java para añadir elementos a una LinkedList
import java.util.LinkedList;

public class AddElements {
    // Metodo principal
    public static void main(String[] args) {
        // Creando unaLinkedList
        LinkedList<String> l = new LinkedList<String>();
        
        // Añadiendo los elementos a la LinkedList usando el método add()
        l.add("Uno");
        l.add("Dos");
        l.add("Tres");
        l.add("Cuatro");
        l.add("Cinco");
        
        // Imprimiendo la LinkedList
        System.out.println(l);
    }
}