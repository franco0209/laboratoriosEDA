import java.util.LinkedList;

public class HashOpened<E> {
    private LinkedList<Register<E>>[] table;
    private int capacity;

    @SuppressWarnings("unchecked")
    public HashOpened(int capacity) {
        this.capacity = capacity;
        this.table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return key % capacity;
    }

    public void insert(int key, E value) {
        int index = hash(key);
        Register<E> newReg = new Register<>(key, value);

        // Verificar si la clave ya existe
        for (Register<E> reg : table[index]) {
            if (reg.getKey() == key && !reg.isDeleted()) {
                System.out.println("Clave duplicada: " + key + ". No se insertó.");
                return;
            }
        }

        table[index].add(newReg);
        System.out.println("Insertado: " + key + ": " + value + " en posición " + index);
    }

    public E search(int key) {
        int index = hash(key);
        
        for (Register<E> reg : table[index]) {
            if (reg.getKey() == key && !reg.isDeleted()) {
                return reg.getValue();
            }
        }

        System.out.println("Clave " + key + " no encontrada.");
        return null;
    }

    public void delete(int key) {
        int index = hash(key);
        
        for (Register<E> reg : table[index]) {
            if (reg.getKey() == key && !reg.isDeleted()) {
                reg.delete();
                System.out.println("Eliminado: " + key);
                return;
            }
        }

        System.out.println("Clave " + key + " no encontrada para eliminar.");
    }

    public void showTable() {
        System.out.println("\n--- Estado de la Tabla Hash (Abierto) ---");
        for (int i = 0; i < capacity; i++) {
            System.out.print(i + ": ");
            if (table[i].isEmpty()) {
                System.out.println("[VACÍO]");
            } else {
                for (Register<E> reg : table[i]) {
                    System.out.print(reg + " -> ");
                }
                System.out.println("null");
            }
        }
        System.out.println();
    }
}