import java.util.Scanner;

public class InvertirVector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el tamaño del vector (N): ");
        int N = scanner.nextInt();
        
        int[] vector = new int[N];
        System.out.println("Ingrese los elementos del vector:");
        for (int i = 0; i < N; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            vector[i] = scanner.nextInt();
        }
        
        System.out.print("\nVector original: [");
        for (int i = 0; i < N; i++) {
            System.out.print(vector[i]);
            if (i < N - 1) {
                System.out.print(" ");
            }
        }
        System.out.println("]");
        
        int[] vectorInvertido = invertirArray(vector);
        
        System.out.print("Vector invertido: [");
        for (int i = 0; i < N; i++) {
            System.out.print(vectorInvertido[i]);
            if (i < N - 1) {
                System.out.print(" ");
            }
        }
        System.out.println("]");
        
        scanner.close();
    }
    
    public static int[] invertirArray(int[] array) {
        int longitud = array.length;
        int[] invertido = new int[longitud];
        
        for (int i = 0; i < longitud; i++) {
            invertido[i] = array[longitud - 1 - i];
        }
        
        return invertido;
    }
}