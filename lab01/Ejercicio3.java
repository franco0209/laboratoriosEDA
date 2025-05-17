public class Ejercicio3 {

    public static int[] orderedList(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};
        System.out.println("Array original: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
        int[] sortedArray = orderedList(array);
        System.out.println("Array ordenado: ");
        for (int num : sortedArray) {
            System.out.print(num + " ");
        }
        
    }
}