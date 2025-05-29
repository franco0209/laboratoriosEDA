// Programa en java para cambiar los elementos
// en una LinkedList
import java.util.LinkedList;

public class GFG3 {
    public static void main(String args[]) {
        // Crear una LinkedList de Strings
        LinkedList<String> ll = new LinkedList<>();
        
        // Añadir elementos iniciales
        ll.add("Uno");
        ll.add("Dos");
        ll.add(1, "Tres");
        
        // Mostrar la LinkedList inicial
        System.out.println("Initial LinkedList " + ll);
        
        // Actualizar un elemento en la posición 1
        ll.set(1, "Cuatro");
        
        // Mostrar la LinkedList actualizada
        System.out.println("Updated LinkedList " + ll);
    }
}