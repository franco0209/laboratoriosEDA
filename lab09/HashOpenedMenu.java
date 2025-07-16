import java.util.Scanner;

public class HashOpenedMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el tamaño de la tabla hash: ");
        int size = scanner.nextInt();
        HashOpened<String> hashTable = new HashOpened<>(size);
        
        while (true) {
            System.out.println("\n--- Menú Tabla Hash Abierta ---");
            System.out.println("1. Insertar elemento");
            System.out.println("2. Buscar elemento");
            System.out.println("3. Eliminar elemento");
            System.out.println("4. Mostrar tabla");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            int option = scanner.nextInt();
            
            switch (option) {
                case 1:
                    System.out.print("Ingrese la clave (entero): ");
                    int key = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    System.out.print("Ingrese el valor (String): ");
                    String value = scanner.nextLine();
                    hashTable.insert(key, value);
                    break;
                    
                case 2:
                    System.out.print("Ingrese la clave a buscar: ");
                    key = scanner.nextInt();
                    String result = hashTable.search(key);
                    if (result != null) {
                        System.out.println("Valor encontrado: " + result);
                    }
                    break;
                    
                case 3:
                    System.out.print("Ingrese la clave a eliminar: ");
                    key = scanner.nextInt();
                    hashTable.delete(key);
                    break;
                    
                case 4:
                    hashTable.showTable();
                    break;
                    
                case 5:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}