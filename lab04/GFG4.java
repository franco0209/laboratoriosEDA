// Programa para iterar elementos en LinkedList
import java.util.LinkedList;

public class GFG4 {
    public static void main(String args[]) {
        LinkedList<String> ll = new LinkedList<>();
        
        ll.add("Uno");
        ll.add("Dos");
        ll.add(1, "Tres");

        // Iteración con for-get
        for (int i = 0; i < ll.size(); i++) {
            System.out.print(ll.get(i) + " ");
        }
        System.out.println();

        // Iteración con for-each
        for (String str : ll) {
            System.out.print(str + " ");
        }
    }
}