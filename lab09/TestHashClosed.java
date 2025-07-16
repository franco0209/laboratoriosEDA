public class TestHashClosed {
    public static void main(String[] args) {
        HashClosed<String> hashTable = new HashClosed<>(11);
        
        System.out.println("\n--- Insertando elementos ---");
        hashTable.insert(100, "Valor 100");
        hashTable.insert(5, "Valor 5");
        hashTable.insert(14, "Valor 14");
        hashTable.insert(15, "Valor 15");
        hashTable.insert(22, "Valor 22");
        hashTable.insert(16, "Valor 16");
        hashTable.insert(17, "Valor 17");
        hashTable.insert(32, "Valor 32");
        hashTable.insert(13, "Valor 13");
        hashTable.insert(32, "Valor 32 DUPLICADO");
        hashTable.insert(100, "Valor 100 DUPLICADO");
        
        hashTable.showTable();
        
        System.out.println("\n--- Buscando elementos ---");
        System.out.println("Buscando 32: " + hashTable.search(32));
        System.out.println("Buscando 200: " + hashTable.search(200));
        
        System.out.println("\n--- Eliminando elementos ---");
        hashTable.delete(17);
        hashTable.delete(100);
        hashTable.delete(200);
        
        hashTable.showTable();
    }
}