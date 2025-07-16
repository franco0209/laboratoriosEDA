public class TestHashOpened {
    public static void main(String[] args) {
        HashOpened<String> hashTable = new HashOpened<>(8);
        
        System.out.println("\n--- Insertando elementos ---");
        hashTable.insert(5, "Pepe");
        hashTable.insert(21, "Jesús");
        hashTable.insert(19, "Juan");
        hashTable.insert(16, "María");
        hashTable.insert(21, "DUPLICADO");
        
        hashTable.showTable();
        
        System.out.println("\n--- Buscando elementos ---");
        System.out.println("Buscando 5: " + hashTable.search(5));
        System.out.println("Buscando 21: " + hashTable.search(21));
        
        System.out.println("\n--- Eliminando elementos ---");
        hashTable.delete(21);
        hashTable.delete(100);
        
        hashTable.showTable();
    }
}