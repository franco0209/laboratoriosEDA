import java.util.Scanner;

public class TestAVLTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVLTree<Integer> avlTree = new AVLTree<>();
        
        int option;
        do {
            System.out.println("\n--- Menú Árbol AVL ---");
            System.out.println("1. Insertar elemento");
            System.out.println("2. Eliminar elemento");
            System.out.println("3. Buscar elemento");
            System.out.println("4. Mostrar mínimo");
            System.out.println("5. Mostrar máximo");
            System.out.println("6. Mostrar predecesor");
            System.out.println("7. Mostrar sucesor");
            System.out.println("8. Recorrido InOrder");
            System.out.println("9. Recorrido PreOrder");
            System.out.println("10. Recorrido PostOrder");
            System.out.println("11. Mostrar árbol (nivel por nivel)");
            System.out.println("12. Verificar si está vacío");
            System.out.println("13. Destruir árbol");
            System.out.println("14. Graficar árbol"); // Nueva opción
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            option = scanner.nextInt();
            
            switch (option) {
                case 1:
                    System.out.print("Ingrese el número a insertar: ");
                    int numInsert = scanner.nextInt();
                    avlTree.insert(numInsert);
                    System.out.println("Elemento insertado.");
                    break;
                    
                case 2:
                    System.out.print("Ingrese el número a eliminar: ");
                    int numRemove = scanner.nextInt();
                    avlTree.remove(numRemove);
                    System.out.println("Elemento eliminado si existía.");
                    break;
                    
                case 3:
                    System.out.print("Ingrese el número a buscar: ");
                    int numSearch = scanner.nextInt();
                    System.out.println("El elemento " + numSearch + 
                        (avlTree.search(numSearch) ? " existe" : " no existe") + " en el árbol.");
                    break;
                    
                case 4:
                    System.out.println("Mínimo: " + avlTree.min());
                    break;
                    
                case 5:
                    System.out.println("Máximo: " + avlTree.max());
                    break;
                    
                case 6:
                    System.out.print("Ingrese el número para encontrar su predecesor: ");
                    int numPredecessor = scanner.nextInt();
                    System.out.println("Predecesor: " + avlTree.predecessor(numPredecessor));
                    break;
                    
                case 7:
                    System.out.print("Ingrese el número para encontrar su sucesor: ");
                    int numSuccessor = scanner.nextInt();
                    System.out.println("Sucesor: " + avlTree.successor(numSuccessor));
                    break;
                    
                case 8:
                    System.out.print("Recorrido InOrder: ");
                    avlTree.inOrder();
                    break;
                    
                case 9:
                    System.out.print("Recorrido PreOrder: ");
                    avlTree.preOrder();
                    break;
                    
                case 10:
                    System.out.print("Recorrido PostOrder: ");
                    avlTree.postOrder();
                    break;
                    
                case 11:
                    System.out.println("Árbol AVL (nivel por nivel):");
                    avlTree.printTree();
                    break;
                    
                case 12:
                    System.out.println("El árbol está " + (avlTree.isEmpty() ? "vacío" : "no vacío"));
                    break;
                    
                case 13:
                    avlTree.destroy();
                    System.out.println("Árbol destruido.");
                    break;
                    
                case 0:
                    System.out.println("Saliendo...");
                    break;
                    
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 0);
        
        scanner.close();
    }
}