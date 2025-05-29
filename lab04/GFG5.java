import java.util.LinkedList;

public class GFG5 {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(123);
        list.add(12);
        list.add(11);
        list.add(1134);

        System.out.println("LinkedList: " + list);

        Object[] a = list.toArray();
        System.out.print("Después de convertir LinkedList a un Array: ");
        for (Object element : a)
            System.out.print(element + " ");
    }
}
