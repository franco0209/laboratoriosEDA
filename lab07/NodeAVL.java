public class NodeAVL<T extends Comparable<T>> {
    T data;
    NodeAVL<T> left;
    NodeAVL<T> right;
    int height;

    public NodeAVL(T data) {
        this.data = data;
        this.height = 1;
    }

    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    public int getBalanceFactor() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }
}