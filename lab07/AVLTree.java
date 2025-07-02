import java.util.LinkedList;
import java.util.Queue;


public class AVLTree<T extends Comparable<T>> {
    private NodeAVL<T> root;

    // Constructor
    public AVLTree() {
        root = null;
    }

    // Destruir el árbol
    public void destroy() {
        root = null;
    }

    // Verificar si el árbol está vacío
    public boolean isEmpty() {
        return root == null;
    }

    // Insertar un elemento
    public void insert(T data) {
        root = insertRec(root, data);
    }

    private NodeAVL<T> insertRec(NodeAVL<T> node, T data) {
        if (node == null) {
            return new NodeAVL<>(data);
        }

        if (data.compareTo(node.data) < 0) {
            node.left = insertRec(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insertRec(node.right, data);
        } else {
            return node; // No se permiten duplicados
        }

        node.updateHeight();
        return balance(node);
    }

    // Eliminar un elemento
    public void remove(T data) {
        root = removeRec(root, data);
    }

    private NodeAVL<T> removeRec(NodeAVL<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.data) < 0) {
            node.left = removeRec(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = removeRec(node.right, data);
        } else {
            // Nodo con un solo hijo o sin hijos
            if (node.left == null || node.right == null) {
                NodeAVL<T> temp = (node.left != null) ? node.left : node.right;
                
                // Sin hijos
                if (temp == null) {
                    temp = node;
                    node = null;
                } else { // Un hijo
                    node = temp;
                }
            } else {
                // Nodo con dos hijos: obtener el sucesor inorden (mínimo en el subárbol derecho)
                NodeAVL<T> temp = minValueNode(node.right);
                node.data = temp.data;
                node.right = removeRec(node.right, temp.data);
            }
        }

        if (node == null) {
            return null;
        }

        node.updateHeight();
        return balance(node);
    }

    // Buscar un elemento
    public boolean search(T data) {
        return searchRec(root, data);
    }

    private boolean searchRec(NodeAVL<T> node, T data) {
        if (node == null) {
            return false;
        }

        if (data.compareTo(node.data) == 0) {
            return true;
        }

        return data.compareTo(node.data) < 0 
            ? searchRec(node.left, data) 
            : searchRec(node.right, data);
    }

    // Obtener el mínimo valor
    public T min() {
        if (isEmpty()) {
            return null;
        }
        return minValueNode(root).data;
    }

    private NodeAVL<T> minValueNode(NodeAVL<T> node) {
        NodeAVL<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Obtener el máximo valor
    public T max() {
        if (isEmpty()) {
            return null;
        }
        return maxValueNode(root).data;
    }

    private NodeAVL<T> maxValueNode(NodeAVL<T> node) {
        NodeAVL<T> current = node;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    // Obtener predecesor
    public T predecessor(T data) {
        NodeAVL<T> predecessor = null;
        NodeAVL<T> current = root;

        while (current != null) {
            if (data.compareTo(current.data) > 0) {
                predecessor = current;
                current = current.right;
            } else if (data.compareTo(current.data) < 0) {
                current = current.left;
            } else {
                if (current.left != null) {
                    return maxValueNode(current.left).data;
                }
                break;
            }
        }

        return (predecessor != null) ? predecessor.data : null;
    }

    // Obtener sucesor
    public T successor(T data) {
        NodeAVL<T> successor = null;
        NodeAVL<T> current = root;

        while (current != null) {
            if (data.compareTo(current.data) < 0) {
                successor = current;
                current = current.left;
            } else if (data.compareTo(current.data) > 0) {
                current = current.right;
            } else {
                if (current.right != null) {
                    return minValueNode(current.right).data;
                }
                break;
            }
        }

        return (successor != null) ? successor.data : null;
    }

    // Recorridos
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(NodeAVL<T> node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.data + " ");
            inOrderRec(node.right);
        }
    }

    public void preOrder() {
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(NodeAVL<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    public void postOrder() {
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(NodeAVL<T> node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Balancear el árbol
    private NodeAVL<T> balance(NodeAVL<T> node) {
        int balanceFactor = node.getBalanceFactor();

        // Desbalanceado por la izquierda
        if (balanceFactor > 1) {
            if (node.left.getBalanceFactor() >= 0) {
                return rotateRight(node);
            } else {
                return balanceLeft(node);
            }
        }

        // Desbalanceado por la derecha
        if (balanceFactor < -1) {
            if (node.right.getBalanceFactor() <= 0) {
                return rotateLeft(node);
            } else {
                return balanceRight(node);
            }
        }

        return node;
    }

    // Rotaciones
    private NodeAVL<T> rotateLeft(NodeAVL<T> y) {
        NodeAVL<T> x = y.right;
        NodeAVL<T> T2 = x.left;

        x.left = y;
        y.right = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    private NodeAVL<T> rotateRight(NodeAVL<T> x) {
        NodeAVL<T> y = x.left;
        NodeAVL<T> T2 = y.right;

        y.right = x;
        x.left = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }

    private NodeAVL<T> balanceLeft(NodeAVL<T> node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    private NodeAVL<T> balanceRight(NodeAVL<T> node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    // Método para imprimir el árbol (opcional, para pruebas)
    public void printTree() {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return;
        }

        Queue<NodeAVL<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                NodeAVL<T> current = queue.poll();
                System.out.print(current.data + "(" + current.height + ") ");
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            System.out.println();
        }
    }
}