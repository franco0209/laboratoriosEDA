import java.util.LinkedList;
import java.util.ListIterator;

public class ListaCircularSimulada {
    public static void main(String[] args) {
        // Crear una lista enlazada
        LinkedList<Integer> lista = new LinkedList<>();
        
        // Agregar elementos del 1 al 12
        for (int i = 1; i <= 12; i++) {
            lista.add(i);
        }
        
        // Simular comportamiento circular
        System.out.println("Recorrido circular (5 vueltas):");
        ListIterator<Integer> iterador = lista.listIterator();
        int vueltas = 0;
        int maxVueltas = 5;
        
        while (vueltas < maxVueltas) {
            if (iterador.hasNext()) {
                System.out.print(iterador.next() + " ");
            } else {
                // Volver al inicio cuando llega al final
                iterador = lista.listIterator();
                vueltas++;
                System.out.println("\n--- Vuelta completada " + vueltas + " ---");
            }
        }
        
        // Mostrar información de la lista
        System.out.println("\n\nContenido completo de la lista:");
        System.out.println(lista);
        System.out.println("Tamaño de la lista: " + lista.size());
    }
}