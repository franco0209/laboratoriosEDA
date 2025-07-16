import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BTree<T extends Comparable<T>> {
    private static final int DEFAULT_DEGREE = 3;
    private final int degree;
    private BTreeNode<T> root;
    
    // Clase interna para nodos del árbol B
    private static class BTreeNode<T> {
        List<T> keys;
        List<BTreeNode<T>> children;
        boolean isLeaf;
        
        BTreeNode(boolean isLeaf) {
            this.keys = new ArrayList<>();
            this.children = new ArrayList<>();
            this.isLeaf = isLeaf;
        }
    }
    
    public BTree() {
        this(DEFAULT_DEGREE);
    }
    
    public BTree(int degree) {
        this.degree = degree;
        this.root = new BTreeNode<>(true);
    }
    
    // Operaciones básicas
    public void destroy() {
        root = new BTreeNode<>(true);
    }
    
    public boolean isEmpty() {
        return root.keys.isEmpty();
    }
    
    // Operaciones de búsqueda
    public boolean search(T key) {
        return search(root, key);
    }
    
    private boolean search(BTreeNode<T> node, T key) {
        int i = 0;
        while (i < node.keys.size() && key.compareTo(node.keys.get(i)) > 0) {
            i++;
        }
        
        if (i < node.keys.size() && key.compareTo(node.keys.get(i)) == 0) {
            return true;
        }
        
        if (node.isLeaf) {
            return false;
        }
        
        return search(node.children.get(i), key);
    }
    
    public T min() {
        if (isEmpty()) {
            return null;
        }
        return min(root);
    }
    
    private T min(BTreeNode<T> node) {
        if (node.isLeaf) {
            return node.keys.get(0);
        }
        return min(node.children.get(0));
    }
    
    public T max() {
        if (isEmpty()) {
            return null;
        }
        return max(root);
    }
    
    private T max(BTreeNode<T> node) {
        if (node.isLeaf) {
            return node.keys.get(node.keys.size() - 1);
        }
        return max(node.children.get(node.children.size() - 1));
    }
    
    public T predecessor(T key) {
        if (isEmpty()) {
            return null;
        }
        return predecessor(root, key, null);
    }
    
    private T predecessor(BTreeNode<T> node, T key, T candidate) {
        int i = 0;
        while (i < node.keys.size() && key.compareTo(node.keys.get(i)) > 0) {
            candidate = node.keys.get(i);
            i++;
        }
        
        if (i < node.keys.size() && key.compareTo(node.keys.get(i)) == 0) {
            if (!node.isLeaf) {
                return max(node.children.get(i));
            } else if (i > 0) {
                return node.keys.get(i - 1);
            }
        }
        
        if (node.isLeaf) {
            return candidate;
        }
        
        return predecessor(node.children.get(i), key, candidate);
    }
    
    public T successor(T key) {
        if (isEmpty()) {
            return null;
        }
        return successor(root, key, null);
    }
    
    private T successor(BTreeNode<T> node, T key, T candidate) {
        int i = node.keys.size() - 1;
        while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
            candidate = node.keys.get(i);
            i--;
        }
        
        if (i >= 0 && key.compareTo(node.keys.get(i)) == 0) {
            if (!node.isLeaf) {
                return min(node.children.get(i + 1));
            } else if (i < node.keys.size() - 1) {
                return node.keys.get(i + 1);
            }
        }
        
        if (node.isLeaf) {
            return candidate;
        }
        
        return successor(node.children.get(i + 1), key, candidate);
    }
    
    // Operaciones de modificación
    public void insert(T key) {
        if (root.keys.size() == (2 * degree - 1)) {
            BTreeNode<T> newRoot = new BTreeNode<>(false);
            newRoot.children.add(root);
            root = newRoot;
            splitChild(newRoot, 0);
        }
        insertNonFull(root, key);
    }
    
    private void insertNonFull(BTreeNode<T> node, T key) {
        int i = node.keys.size() - 1;
        
        if (node.isLeaf) {
            while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                i--;
            }
            node.keys.add(i + 1, key);
        } else {
            while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                i--;
            }
            i++;
            
            if (node.children.get(i).keys.size() == (2 * degree - 1)) {
                splitChild(node, i);
                if (key.compareTo(node.keys.get(i)) > 0) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), key);
        }
    }
    
    private void splitChild(BTreeNode<T> parent, int childIndex) {
        BTreeNode<T> child = parent.children.get(childIndex);
        BTreeNode<T> newChild = new BTreeNode<>(child.isLeaf);
        
        parent.keys.add(childIndex, child.keys.get(degree - 1));
        
        for (int i = 0; i < degree - 1; i++) {
            newChild.keys.add(child.keys.remove(degree));
        }
        
        if (!child.isLeaf) {
            for (int i = 0; i < degree; i++) {
                newChild.children.add(child.children.remove(degree));
            }
        }
        
        parent.children.add(childIndex + 1, newChild);
    }
    
    public void remove(T key) {
        delete(root, key);
        
        if (root.keys.isEmpty() && !root.isLeaf) {
            root = root.children.get(0);
        }
    }
    
    private void delete(BTreeNode<T> node, T key) {
        int idx = findKey(node, key);
        
        if (idx < node.keys.size() && node.keys.get(idx).compareTo(key) == 0) {
            if (node.isLeaf) {
                node.keys.remove(idx);
            } else {
                deleteFromNonLeaf(node, idx);
            }
        } else {
            if (node.isLeaf) {
                return;
            }
            
            boolean flag = (idx == node.keys.size());
            
            if (node.children.get(idx).keys.size() < degree) {
                fill(node, idx);
            }
            
            if (flag && idx > node.keys.size()) {
                delete(node.children.get(idx - 1), key);
            } else {
                delete(node.children.get(idx), key);
            }
        }
    }
    
    private void deleteFromNonLeaf(BTreeNode<T> node, int idx) {
        T key = node.keys.get(idx);
        
        if (node.children.get(idx).keys.size() >= degree) {
            T pred = getPredecessor(node, idx);
            node.keys.set(idx, pred);
            delete(node.children.get(idx), pred);
        } else if (node.children.get(idx + 1).keys.size() >= degree) {
            T succ = getSuccessor(node, idx);
            node.keys.set(idx, succ);
            delete(node.children.get(idx + 1), succ);
        } else {
            merge(node, idx);
            delete(node.children.get(idx), key);
        }
    }
    
    private T getPredecessor(BTreeNode<T> node, int idx) {
        BTreeNode<T> curr = node.children.get(idx);
        while (!curr.isLeaf) {
            curr = curr.children.get(curr.keys.size());
        }
        return curr.keys.get(curr.keys.size() - 1);
    }
    
    private T getSuccessor(BTreeNode<T> node, int idx) {
        BTreeNode<T> curr = node.children.get(idx + 1);
        while (!curr.isLeaf) {
            curr = curr.children.get(0);
        }
        return curr.keys.get(0);
    }
    
    private void fill(BTreeNode<T> node, int idx) {
        if (idx != 0 && node.children.get(idx - 1).keys.size() >= degree) {
            borrowFromPrev(node, idx);
        } else if (idx != node.keys.size() && node.children.get(idx + 1).keys.size() >= degree) {
            borrowFromNext(node, idx);
        } else {
            if (idx != node.keys.size()) {
                merge(node, idx);
            } else {
                merge(node, idx - 1);
            }
        }
    }
    
    private void borrowFromPrev(BTreeNode<T> node, int idx) {
        BTreeNode<T> child = node.children.get(idx);
        BTreeNode<T> sibling = node.children.get(idx - 1);
        
        child.keys.add(0, node.keys.get(idx - 1));
        
        if (!child.isLeaf) {
            child.children.add(0, sibling.children.remove(sibling.children.size() - 1));
        }
        
        node.keys.set(idx - 1, sibling.keys.remove(sibling.keys.size() - 1));
    }
    
    private void borrowFromNext(BTreeNode<T> node, int idx) {
        BTreeNode<T> child = node.children.get(idx);
        BTreeNode<T> sibling = node.children.get(idx + 1);
        
        child.keys.add(node.keys.get(idx));
        
        if (!child.isLeaf) {
            child.children.add(sibling.children.remove(0));
        }
        
        node.keys.set(idx, sibling.keys.remove(0));
    }
    
    private void merge(BTreeNode<T> node, int idx) {
        BTreeNode<T> child = node.children.get(idx);
        BTreeNode<T> sibling = node.children.get(idx + 1);
        
        child.keys.add(node.keys.remove(idx));
        
        child.keys.addAll(sibling.keys);
        
        if (!child.isLeaf) {
            child.children.addAll(sibling.children);
        }
        
        node.children.remove(idx + 1);
    }
    
    private int findKey(BTreeNode<T> node, T key) {
        int idx = 0;
        while (idx < node.keys.size() && node.keys.get(idx).compareTo(key) < 0) {
            ++idx;
        }
        return idx;
    }
    
    // Operaciones de visualización
    @Override
    public String toString() {
        return writeTree();
    }
    
    public String writeTree() {
        StringBuilder sb = new StringBuilder();
        writeTree(root, 0, sb);
        return sb.toString();
    }
    
    private void writeTree(BTreeNode<T> node, int level, StringBuilder sb) {
        sb.append("Nivel ").append(level).append(": ").append(node.keys).append("\n");
        
        if (!node.isLeaf) {
            for (BTreeNode<T> child : node.children) {
                writeTree(child, level + 1, sb);
            }
        }
    }
    
    // Métodos adicionales solicitados
    public void fuzeNode(BTreeNode<T> parent, int index) {
        // Similar a merge pero con más control
        merge(parent, index);
    }
    
    public void dividedNode(BTreeNode<T> parent, int index) {
        // Similar a splitChild pero con más control
        splitChild(parent, index);
    }
    
    // Clase Test con menú interactivo
    public static class Test {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el grado del árbol B: ");
            int degree = scanner.nextInt();
            BTree<Integer> tree = new BTree<>(degree);
            
            while (true) {
                System.out.println("\n--- MENÚ ÁRBOL B ---");
                System.out.println("1. Insertar clave");
                System.out.println("2. Eliminar clave");
                System.out.println("3. Buscar clave");
                System.out.println("4. Obtener mínimo");
                System.out.println("5. Obtener máximo");
                System.out.println("6. Obtener predecesor");
                System.out.println("7. Obtener sucesor");
                System.out.println("8. Mostrar árbol");
                System.out.println("9. Vaciar árbol");
                System.out.println("10. Verificar si está vacío");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                
                int opcion = scanner.nextInt();
                
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese clave a insertar: ");
                        tree.insert(scanner.nextInt());
                        break;
                    case 2:
                        System.out.print("Ingrese clave a eliminar: ");
                        tree.remove(scanner.nextInt());
                        break;
                    case 3:
                        System.out.print("Ingrese clave a buscar: ");
                        System.out.println(tree.search(scanner.nextInt()) ? "Encontrado" : "No encontrado");
                        break;
                    case 4:
                        System.out.println("Mínimo: " + tree.min());
                        break;
                    case 5:
                        System.out.println("Máximo: " + tree.max());
                        break;
                    case 6:
                        System.out.print("Ingrese clave para encontrar predecesor: ");
                        System.out.println("Predecesor: " + tree.predecessor(scanner.nextInt()));
                        break;
                    case 7:
                        System.out.print("Ingrese clave para encontrar sucesor: ");
                        System.out.println("Sucesor: " + tree.successor(scanner.nextInt()));
                        break;
                    case 8:
                        System.out.println("\nEstructura del árbol:");
                        System.out.println(tree.writeTree());
                        break;
                    case 9:
                        tree.destroy();
                        System.out.println("Árbol vaciado");
                        break;
                    case 10:
                        System.out.println("El árbol " + (tree.isEmpty() ? "está" : "no está") + " vacío");
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            }
        }
    }
}