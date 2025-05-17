import java.util.ArrayList;

public class Ejercicio2 {
    public static ArrayList<Integer> primosEratostenes(int n) {
        ArrayList<Integer> primos = new ArrayList<>();
        boolean[] esPrimo = new boolean[n + 1];
        
        for (int i = 2; i <= n; i++) {
            esPrimo[i] = true;
        }
        
        for (int i = 2; i * i <= n; i++) {
            if (esPrimo[i]) {
                for (int j = i * i; j <= n; j += i) {
                    esPrimo[j] = false;
                }
            }
        }
        
        for (int i = 2; i <= n; i++) {
            if (esPrimo[i]) {
                primos.add(i);
            }
        }
        
        return primos;
    }
    public static void main(String[] args) {
        int n=20;
        ArrayList<Integer> primos = primosEratostenes(n);
        System.out.println("Los números primos hasta " + n + " son: " + primos);
        
    }
}