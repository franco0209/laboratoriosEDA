import java.util.ArrayList;
import java.util.List;

public class BTree1<T extends Comparable<T>> {
    private static final int DEGREE = 5;
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
        
        @Override
        public String toString() {
            return keys.toString();
        }
    }
    
    public BTree1() {
        root = new BTreeNode<>(true);
    }
    
    // Método para insertar una clave
    public void insert(T key) {
        System.out.println("Insertando: " + key);
        
        if (root.keys.size() == (2 * DEGREE - 1)) {
            BTreeNode<T> newRoot = new BTreeNode<>(false);
            newRoot.children.add(root);
            root = newRoot;
            splitChild(newRoot, 0);
        }
        insertNonFull(root, key);
        printTree();
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
            
            if (node.children.get(i).keys.size() == (2 * DEGREE - 1)) {
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
        
        // Mover las claves e hijos
        parent.keys.add(childIndex, child.keys.get(DEGREE - 1));
        
        for (int i = 0; i < DEGREE - 1; i++) {
            newChild.keys.add(child.keys.remove(DEGREE));
        }
        
        if (!child.isLeaf) {
            for (int i = 0; i < DEGREE; i++) {
                newChild.children.add(child.children.remove(DEGREE));
            }
        }
        
        parent.children.add(childIndex + 1, newChild);
    }
    
    // Método para eliminar una clave
    public void delete(T key) {
        System.out.println("Eliminando: " + key);
        delete(root, key);
        
        if (root.keys.isEmpty() && !root.isLeaf) {
            root = root.children.get(0);
        }
        printTree();
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
                System.out.println("La clave " + key + " no existe en el árbol");
                return;
            }
            
            boolean flag = (idx == node.keys.size());
            
            if (node.children.get(idx).keys.size() < DEGREE) {
                fill(node, idx);
            }
            
            if (flag && idx > node.keys.size()) {
                delete(node.children.get(idx - 1), key);
            } else {
                delete(node.children.get(idx), key);
            }
        }
    }
    
    private int findKey(BTreeNode<T> node, T key) {
        int idx = 0;
        while (idx < node.keys.size() && node.keys.get(idx).compareTo(key) < 0) {
            ++idx;
        }
        return idx;
    }
    
    private void deleteFromNonLeaf(BTreeNode<T> node, int idx) {
        T key = node.keys.get(idx);
        
        if (node.children.get(idx).keys.size() >= DEGREE) {
            T pred = getPredecessor(node, idx);
            node.keys.set(idx, pred);
            delete(node.children.get(idx), pred);
        } else if (node.children.get(idx + 1).keys.size() >= DEGREE) {
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
        if (idx != 0 && node.children.get(idx - 1).keys.size() >= DEGREE) {
            borrowFromPrev(node, idx);
        } else if (idx != node.keys.size() && node.children.get(idx + 1).keys.size() >= DEGREE) {
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
    
    // Método para imprimir el árbol
    public void printTree() {
        System.out.println("Árbol actual:");
        printTree(root, 0);
        System.out.println();
    }
    
    private void printTree(BTreeNode<T> node, int level) {
        System.out.print("Nivel " + level + ": ");
        System.out.println(node.keys);
        
        if (!node.isLeaf) {
            for (BTreeNode<T> child : node.children) {
                printTree(child, level + 1);
            }
        }
    }
    
    public static void main(String[] args) {
        BTree1<Integer> tree = new BTree1<>();
        
        // Inserción de nodos
        System.out.println("=== INSERCIONES ===");
        tree.insert(100);
        tree.insert(200);
        tree.insert(300);
        tree.insert(400);
        tree.insert(500);
        tree.insert(50);
        tree.insert(25);
        tree.insert(350);
        tree.insert(375);
        tree.insert(360);
        tree.insert(355);
        tree.insert(150);
        tree.insert(175);
        tree.insert(120);
        tree.insert(190);
        
        // Eliminación de nodos
        System.out.println("\n=== ELIMINACIONES ===");
        tree.delete(100);
        tree.delete(200);
        tree.delete(300);
        tree.delete(400);
        tree.delete(500);
        tree.delete(50);
        tree.delete(25);
        tree.delete(350);
        tree.delete(375);
        tree.delete(360);
        tree.delete(355);
        tree.delete(150);
        tree.delete(175);
        tree.delete(120);
        tree.delete(190);
    }
}