public class TrianguloRecursivo {
    public static void main(String[] args) {
        int b = 5;
        System.out.println("Triángulo recursivo (b = " + b + "):");
        imprimirTriangulo(b);
    }
    
    public static void imprimirTriangulo(int b) {
        if (b <= 0) return;
        
        imprimirTriangulo(b - 1); 
        
        for (int i = 0; i < b; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}