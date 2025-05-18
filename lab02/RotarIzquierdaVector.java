import java.util.Scanner;

public class RotarIzquierdaVector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el tamaño del vector: ");
        int N = scanner.nextInt();
        
        int[] vector = new int[N];
        System.out.println("Ingrese los elementos del vector:");
        for (int i = 0; i < N; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            vector[i] = scanner.nextInt();
        }
        
        System.out.print("Ingrese el número de posiciones a rotar (d): ");
        int d = scanner.nextInt();
        
        System.out.print("\nVector original: [");
        for (int i = 0; i < N; i++) {
            System.out.print(vector[i]);
            if (i < N - 1) {
                System.out.print(" ");
            }
        }
        System.out.println("]");
        
        int[] vectorRotado = rotarIzquierdaArray(vector, d);
        
        System.out.print("Vector rotado: [");
        for (int i = 0; i < N; i++) {
            System.out.print(vectorRotado[i]);
            if (i < N - 1) {
                System.out.print(" ");
            }
        }
        System.out.println("]");
        
        scanner.close();
    }
    
    public static int[] rotarIzquierdaArray(int[] A, int d) {
        int n = A.length;
        int[] rotado = new int[n];
        
        d = d % n;
        
        for (int i = 0; i < n; i++) {
            int nuevaPosicion = (i + (n - d)) % n;
            rotado[nuevaPosicion] = A[i];
        }
        
        return rotado;
    }
}