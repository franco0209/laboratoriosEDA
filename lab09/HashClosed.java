public class HashClosed<E> {
    private Register<E>[] table;
    private int capacity;
    private int size;
    private final Register<E> DELETED = new Register<>(-1, null);

    @SuppressWarnings("unchecked")
    public HashClosed(int capacity) {
        this.capacity = capacity;
        this.table = (Register<E>[]) new Register[capacity];
        this.size = 0;
    }

    private int hash(int key) {
        return key % capacity;
    }

    public void insert(int key, E value) {
        if (size == capacity) {
            System.out.println("Tabla hash llena. No se puede insertar " + key);
            return;
        }

        int index = hash(key);
        int originalIndex = index;
        int i = 0;

        // Buscar posición vacía o marcada como eliminada
        while (table[index] != null && table[index] != DELETED && table[index].getKey() != key) {
            i++;
            index = (originalIndex + i) % capacity;
            
            if (i == capacity) {
                System.out.println("No se encontró espacio para insertar " + key);
                return;
            }
        }

        // Si encontramos la misma clave y no está eliminada
        if (table[index] != null && table[index] != DELETED && table[index].getKey() == key) {
            System.out.println("Clave duplicada: " + key + ". No se insertó.");
            return;
        }

        table[index] = new Register<>(key, value);
        size++;
        System.out.println("Insertado: " + key + ": " + value + " en posición " + index);
    }

    public E search(int key) {
        int index = hash(key);
        int originalIndex = index;
        int i = 0;

        while (table[index] != null) {
            if (table[index] != DELETED && table[index].getKey() == key) {
                return table[index].getValue();
            }
            i++;
            index = (originalIndex + i) % capacity;
            
            if (i == capacity) break;
        }

        System.out.println("Clave " + key + " no encontrada.");
        return null;
    }

    public void delete(int key) {
        int index = hash(key);
        int originalIndex = index;
        int i = 0;

        while (table[index] != null) {
            if (table[index] != DELETED && table[index].getKey() == key) {
                table[index] = DELETED;
                size--;
                System.out.println("Eliminado: " + key);
                return;
            }
            i++;
            index = (originalIndex + i) % capacity;
            
            if (i == capacity) break;
        }

        System.out.println("Clave " + key + " no encontrada para eliminar.");
    }

    public void showTable() {
        System.out.println("\n--- Estado de la Tabla Hash (Cerrado) ---");
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) {
                System.out.println(i + ": [VACÍO]");
            } else if (table[i] == DELETED) {
                System.out.println(i + ": [ELIMINADO]");
            } else {
                System.out.println(i + ": " + table[i]);
            }
        }
        System.out.println("Tamaño actual: " + size + "/" + capacity + "\n");
    }
}