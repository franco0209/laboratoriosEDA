// Programa java para añadir elementos
// a la LinkedList
import java.util.LinkedList;

public class GFG2 {
    public static void main(String args[]) {
        LinkedList<String> ll = new LinkedList<>();
        
        ll.add("Uno");
        ll.add("Tres");
        ll.add(1, "Dos");
        
        System.out.println(ll);
    }
}